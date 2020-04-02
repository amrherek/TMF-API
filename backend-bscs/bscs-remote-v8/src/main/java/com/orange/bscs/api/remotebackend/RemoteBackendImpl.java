package com.orange.bscs.api.remotebackend;

import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.connection.IConnectionBackend;
import com.orange.bscs.api.connection.SOIConnection;
import com.orange.bscs.api.connection.SOITransactionsManager;
import com.orange.bscs.api.connection.backend.SVLDeserializerV8;
import com.orange.bscs.api.connection.service.BscsConnectionService;
import com.orange.bscs.api.exceptions.BscsConnectionException;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.ParameterListWrapper;
import com.orange.bscs.api.model.SVLObjectWrapper;
import com.orange.bscs.api.model.exception.CMSErrorInfo;
import com.orange.bscs.api.model.exception.CMSException;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.interfaces.common.v1.SOIExceptionFault;
import com.orange.bscs.interfaces.common.v1.model.ebs.HeaderViewForLocalApiClientRequest;
import com.orange.bscs.interfaces.common.v1.model.ebs.HeaderViewForLocalApiClientResponse;
import com.orange.bscs.model.wrapper.SVLObjectWrapperV8;
import com.orange.bscs.svlbackend.SOAParameterInfo;
import com.orange.bscs.svlbackend.SOAParameterList;
import com.orange.bscs.svlbackend.SVLBackendPortType;
import com.semagroup.targys.framework.common.InvalidParameterTypeException;
import com.semagroup.targys.framework.common.SVLObject;
import com.semagroup.targys.framework.common.SVLType;
import com.semagroup.targys.servicelayer.common.ParameterList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.xml.ws.Holder;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RemoteBackendImpl implements SVLBackendPortType {
    private final static Logger log = LoggerFactory.getLogger(RemoteBackendImpl.class);

    @Value("${bscs.v8.timezone.offset:0}")
    private int timezoneOffset;

    @Autowired
    private SOITransactionsManager soiTransactionManager;

    @Autowired
    private BscsConnectionService bscsConnectionService;

    private List<String> blackListCommands = new ArrayList<>();

    @RequestWrapper(localName = "execute", targetNamespace = "http://www.orange.com/bscs/SVLBackend/", className = "com.orange.bscs.svlbackend.ExecuteRequest")
    @WebMethod(action = "http://www.orange.com/bscs/SVLBackend/execute")
    @ResponseWrapper(localName = "executeResponse", targetNamespace = "http://www.orange.com/bscs/SVLBackend/", className = "com.orange.bscs.svlbackend.ExecuteResponseMessage")
    public void execute(
            @WebParam(name = "Local_ApiClientRequest") HeaderViewForLocalApiClientRequest localApiClientRequest,
            @WebParam(name = "operation") String commandCMS,
            @WebParam(name = "svlInput") String svlInput,
            @WebParam(mode = Mode.OUT, name = "Local_ApiClientResponse") Holder<HeaderViewForLocalApiClientResponse> localApiClientResponse,
            @WebParam(mode = Mode.OUT, name = "svlOutput") Holder<String> svlOutput) throws SOIExceptionFault {

        String operation = commandCMS;

        if (null == operation || 0 == operation.length()) {
            return;
        }

        operation = operation.toUpperCase();

        if ("THROW_SEC".equals(operation)) {

            try {
                throw new SecurityException("Throw by User " + localApiClientRequest.getBscsAuthentication().getBscsLogin());
            } catch (SecurityException e) {
                throw new SOIException(e);
            }
        } else if ("THROW_COMP".equals(operation)) {
            throw new SOIException("Throw by Component");

        } else if ("SOIEXCEPTION".equals(operation)) {
            SVLObject in = null;
            if (null != svlInput && 0 < svlInput.length()) {
                try {
                    in = new SVLDeserializerV8(timezoneOffset).unmarshall("execute ANONYMOUS.COMMAND {" + svlInput + "}");
                } catch (IOException e) {
                    throw new SOIExceptionFault("UNMARSHALLING", e);
                }
            }
            String code = "RC2000";
            String label = "Champ obligatoire";
            if (null != in) {
                try {
                    code = in.getStringValue("CODE");
                    label = in.getStringValue("LABEL");
                } catch (InvalidParameterTypeException e) {
                    code = "Invalid.parameter";
                    label = e.getParameterName();
                    CMSErrorInfo errorInfo = new CMSErrorInfo(code, null, null);
                    throw new SOIException(new CMSException(label, e, new CMSErrorInfo[]{errorInfo}));
                }
            }
            CMSErrorInfo errorInfo = new CMSErrorInfo(code, null, null);
            throw new SOIException(new CMSException(label, new CMSErrorInfo[]{errorInfo}));
        }
        if (blackListCommands.contains(operation)) {
            return;
        }

        SVLObjectWrapper out = null;

        try {
            this.bscsConnectionService.openConnection();
        } catch (final BscsConnectionException e) {
            log.error("BSCS uncatched error", e);
            throw new SOIExceptionFault("BSCS Connection failed", e);
        }

        SOIConnection soiConnection = ConnectionHolder.getCurrentConnection();

        try {
            if ("OPEN".equals(operation) || "CLOSE".equals(operation)) {
                // OPEN, next time, new Connexion will be retreive
                // CLOSE: Ok, Close this soiConnection
                soiTransactionManager.deleteSOIConnection(soiConnection);
                ConnectionHolder.unset();
            } else if ("COMMIT".equals(operation)) {
                soiConnection.commit();
            } else if ("ROLLBACK".equals(operation)) {
                soiConnection.rollback();
            } else {
                SVLObject in = null;
                if (null != svlInput && 0 < svlInput.length()) {
                    try {
                        in = new SVLDeserializerV8(timezoneOffset).unmarshall("execute ANONYMOUS.COMMAND {" + svlInput + "}");
                    } catch (IOException e) {
                        throw new SOIExceptionFault("UNMARSHALLING", e);
                    }
                }
                SVLObjectWrapper wrapperIn = new SVLObjectWrapperV8(in, timezoneOffset);
                BSCSModel response = soiConnection.execute(operation, new BSCSModel(wrapperIn));
                out = response.getSVLObject();
            }

            if (null != out) {
                svlOutput.value = stripNonValidXMLCharacters(serialise(out));
            }
        } finally {
            log.debug("CLOSE BSCS transaction <==================");
            this.bscsConnectionService.closeConnection();
        }

    }

    private String serialise(SVLObjectWrapper svlo) {
        return null == svlo ? null : serialise(svlo.toString());
    }

    private String serialise(String svloString) {
        if (null == svloString) {
            return null;
        }

        BufferedReader br = new BufferedReader(new StringReader(svloString));
        StringBuilder sb = new StringBuilder();
        String line;
        do {
            try {
                line = br.readLine();
            } catch (IOException e) {
                line = null;
            }
            if (null != line) {
                line = splitNamedValueContainerListLine(line);
                sb.append(line).append("\r\n");
            }
        } while (null != line);
        return sb.toString();
    }


    /**
     * -[0]TARGET_PARAM_VALUES = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList-[0]MULT_VALUES = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList-[0]VALUE_DES = mail : java.lang.String
     *
     * @param input input
     * @return split string
     */
    private String splitNamedValueContainerListLine(final String input) {
        String line = input;

        String rootPrefixe = "";
        StringBuilder sb = new StringBuilder();
        int ipos;
        do {
            ipos = line.indexOf("NamedValueContainerList");
            if (ipos < 0) {
                sb.append(rootPrefixe);
                sb.append(line);
            } else {
                String prefixe = extractPrefixe(line);
                line = line.substring(prefixe.length());
                ipos = line.indexOf("NamedValueContainerList");
                rootPrefixe = rootPrefixe + prefixe;

                String right = line.substring(ipos + 23);
                String left = line.substring(0, ipos + 23);
                sb.append(rootPrefixe);
                sb.append(left).append("\r\n");
//    			sb.append(rootPrefixe);
//    			sb.append(right);
                line = right;
            }
        } while (ipos > 0);

        return sb.toString();


    }

    private static String extractPrefixe(String line) {
        String rootPrefixe = "";
        if (line.startsWith("-")) {
            StringBuilder pref = new StringBuilder();
            byte[] datas = line.getBytes();
            boolean ok = true;
            int pos = 0;
            while (ok) {
                byte b = datas[pos];
                switch (b) {
                    case '-':
                    case '[':
                    case ']':
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        pref.append((char) b);
                        pos++;
                        break;
                    default:
                        ok = false;
                }
            }
            rootPrefixe = pref.toString();
        }
        return rootPrefixe;
    }

    public List<String> getBlackListCommands() {
        return this.blackListCommands;
    }


    public void setBlackListCommands(List<String> blackListCommands) {
        this.blackListCommands = blackListCommands;
    }


    /**
     * This method ensures that the output String has only
     * valid XML unicode characters as specified by the
     * XML 1.0 standard. For reference, please see
     * <a href="http://www.w3.org/TR/2000/REC-xml-20001006#NT-Char">the
     * standard</a>. This method will return an empty
     * String if the input is null or empty.
     *
     * @param in The String whose non-valid characters we want to remove.
     * @return The in String, stripped of non-valid characters.
     */
    private static String stripNonValidXMLCharacters(String in) {
        StringBuilder out = new StringBuilder(); // Used to hold the output.
        char current; // Used to reference the current character.

        if (in == null || ("".equals(in))) {
            return "";
        } // vacancy test.

        int len = in.length();
        for (int i = 0; i < len; i++) {
            current = in.charAt(i); // NOTE: No IndexOutOfBoundsException caught here; it should not happen.
            if ((current == 0x9) ||
                    (current == 0xA) ||
                    (current == 0xD) ||
                    ((current >= 0x20) && (current <= 0xD7FF)) ||
                    ((current >= 0xE000) && (current <= 0xFFFD)) ||
                    ((current >= 0x10000) && (current <= 0x10FFFF))) {
                out.append(current);
            }
        }
        return out.toString();
    }


    @Override
    @RequestWrapper(localName = "getInputList", targetNamespace = "http://www.orange.com/bscs/SVLBackend/", className = "com.orange.bscs.svlbackend.GetListRequest")
    @WebMethod(action = "http://www.orange.com/bscs/SVLBackend/getInputList")
    @ResponseWrapper(localName = "getInputListResponse", targetNamespace = "http://www.orange.com/bscs/SVLBackend/", className = "com.orange.bscs.svlbackend.GetListResponse")
    public void getInputList(
            @WebParam(name = "Local_ApiClientRequest") HeaderViewForLocalApiClientRequest localApiClientRequest,
            @WebParam(name = "commandName") String commandName,
            @WebParam(name = "componentName") String componentName,
            @WebParam(name = "componentVersion") String componentVersion,
            @WebParam(mode = Mode.OUT, name = "Local_ApiClientResponse") Holder<HeaderViewForLocalApiClientResponse> localApiClientResponse,
            @WebParam(mode = Mode.OUT, name = "parameterList") Holder<SOAParameterList> parameterList)
            throws SOIExceptionFault {

        IConnectionBackend backend = ConnectionHolder.getCurrentConnection().getBackend();
        ParameterListWrapper pl = backend.getInputList(commandName, componentName, componentVersion);

        if (null != pl && null != parameterList) {
            SOAParameterList soaPl = new SOAParameterList();
            fill(soaPl, (ParameterList)pl.getParameterList());
            parameterList.value = soaPl;
        }


    }


    @Override
    public void getCommands(
            HeaderViewForLocalApiClientRequest localApiClientRequest,
            String componentName,
            String componentVersion,
            Holder<HeaderViewForLocalApiClientResponse> localApiClientResponse,
            Holder<List<String>> commandNames)
            throws SOIExceptionFault {

        try {
            this.bscsConnectionService.openConnection();
        } catch (final BscsConnectionException e) {
            log.error("BSCS uncatched error", e);
            throw new SOIExceptionFault("BSCS Connection failed", e);
        }

        try {
            IConnectionBackend backend = ConnectionHolder.getCurrentConnection().getBackend();

            String[] commands = backend.getCommands(componentName, componentVersion);

            if (null != commands) {
                commandNames.value = Arrays.asList(commands);
            }
        } finally {
            this.bscsConnectionService.closeConnection();
        }
    }


    @Override
    @RequestWrapper(localName = "getOutputList", targetNamespace = "http://www.orange.com/bscs/SVLBackend/", className = "com.orange.bscs.svlbackend.GetListRequest")
    @WebMethod(action = "http://www.orange.com/bscs/SVLBackend/getOutputList")
    @ResponseWrapper(localName = "getOutputListResponse", targetNamespace = "http://www.orange.com/bscs/SVLBackend/", className = "com.orange.bscs.svlbackend.GetListResponse")
    public void getOutputList(
            @WebParam(name = "Local_ApiClientRequest") HeaderViewForLocalApiClientRequest localApiClientRequest,
            @WebParam(name = "commandName") String commandName,
            @WebParam(name = "componentName") String componentName,
            @WebParam(name = "componentVersion") String componentVersion,
            @WebParam(mode = Mode.OUT, name = "Local_ApiClientResponse") Holder<HeaderViewForLocalApiClientResponse> localApiClientResponse,
            @WebParam(mode = Mode.OUT, name = "parameterList") Holder<SOAParameterList> parameterList)
            throws SOIExceptionFault {


        IConnectionBackend backend = ConnectionHolder.getCurrentConnection().getBackend();
        ParameterListWrapper pl = backend.getOutputList(commandName, componentName, componentVersion);

        if (null != pl && null != parameterList) {
            SOAParameterList soaPl = new SOAParameterList();
            fill(soaPl, (ParameterList)pl.getParameterList());
            parameterList.value = soaPl;
        }


    }


    private void fill(SOAParameterList soaPl, ParameterList pl) {
        for (String attrName : pl.getParameterNames()) {
            ParameterList.ParameterInfo pi = pl.getParameterInfo(attrName);
            SOAParameterInfo soiPI = new SOAParameterInfo();
            soiPI.setName(pi.getName());
            soiPI.setType(pi.getType().value());
            soiPI.setFlags(pi.getFlags());
            soiPI.setLength(pi.getLength());
            soiPI.setMandatory(false);
            soiPI.setPublicKey(false);
            soiPI.setPrivateKey(false);
            if (pi.getType() == SVLType.SVL_LISTLIST) {
                SOAParameterList subList = new SOAParameterList();

                fill(subList, pi.getSubList());
                soiPI.setSubList(subList);
            }

            soaPl.getParameter().add(soiPI);
        }
    }
}
