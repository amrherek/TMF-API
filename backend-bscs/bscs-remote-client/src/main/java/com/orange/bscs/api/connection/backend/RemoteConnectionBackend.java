package com.orange.bscs.api.connection.backend;

import com.orange.bscs.api.config.ApiGeneric;
import com.orange.bscs.api.connection.IConnectionBackend;
import com.orange.bscs.api.connection.SOIConnection;
import com.orange.bscs.api.connection.SOITransactionsManager;
import com.orange.bscs.api.exceptions.APIException;
import com.orange.bscs.api.inspectorbackend.APIParameterList;
import com.orange.bscs.api.model.*;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.interfaces.common.v1.*;
import com.orange.bscs.interfaces.common.v1.model.ebs.BscsAuthenticationALLREQ;
import com.orange.bscs.interfaces.common.v1.model.ebs.HeaderViewForLocalApiClientRequest;
import com.orange.bscs.interfaces.common.v1.model.ebs.HeaderViewForLocalApiClientResponse;
import com.orange.bscs.svlbackend.SOAParameterInfo;
import com.orange.bscs.svlbackend.SOAParameterList;
import com.orange.bscs.svlbackend.SVLBackendPortType;

import javax.xml.ws.Holder;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Consumer of a WebService SVLBackend.
 *
 * @author IT&L@bs
 */
public class RemoteConnectionBackend implements IConnectionBackend {


    private boolean isAlive = true;

    // prevent COMMIT/ROLLBACK or invert
    private boolean lastOpeIsRollbackCommit = false;

    private SVLBackendPortType jaxwsClient;

    private SVLDeserializer unmarshaller;

    private HeaderViewForLocalApiClientRequest firstHeaders = new HeaderViewForLocalApiClientRequest();
    private HeaderViewForLocalApiClientRequest currentHeaders = new HeaderViewForLocalApiClientRequest();
    private HeaderViewForLocalApiClientResponse localApiClientResponse = new HeaderViewForLocalApiClientResponse();


    public RemoteConnectionBackend() {
        unmarshaller = AbstractSVLObjectFactory.getDeserializer();

        firstHeaders = new HeaderViewForLocalApiClientRequest();
        BscsAuthenticationALLREQ authentication = new BscsAuthenticationALLREQ();
        authentication.setBscsLogin(ApiGeneric.getBscsLogin());
        authentication.setBusinessUnit(ApiGeneric.getBscsBusinessUnit());
        firstHeaders.setBscsAuthentication(authentication);
        firstHeaders.setApplicationID(ApiGeneric.getBscsApplicationID());
        firstHeaders.setAutoCommit(Boolean.FALSE);

        currentHeaders = firstHeaders;
    }


    @Override
    public void close() {
        execute("CLOSE", null);
    }

    @Override
    public void reopen() {
        // Re-init transaction Id.
        currentHeaders = firstHeaders;
        isAlive = true;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void commit() {
        if (!lastOpeIsRollbackCommit) {
            execute("COMMIT", null);
        }
        lastOpeIsRollbackCommit = true;
    }

    @Override
    public void rollback() {
        if (!lastOpeIsRollbackCommit) {
            execute("ROLLBACK", null);
        }
        lastOpeIsRollbackCommit = true;
    }

    @Override
    public SVLObjectWrapper execute(String command, SVLObjectWrapper input) {
        if ("CLOSE".equals(command) && !isAlive) {
            // Already closed
            currentHeaders = firstHeaders;
            currentHeaders.setTransactionID(null);
            return AbstractSVLObjectFactory.createSVLObject();
        }

        if (!isAlive) {
            throw new SOIException("Connection is not Alive");
        }
        lastOpeIsRollbackCommit = false;

        Holder<String> holderOutput = new Holder<>();
        Holder<HeaderViewForLocalApiClientResponse> holderResponse = new Holder<>();
        holderResponse.value = localApiClientResponse;

        String inputRequest = null;
        if (null != input) {
            inputRequest = serialise(input);
        }
        try {

            if ("CLOSE".equals(command)) {
                if (currentHeaders == firstHeaders) {
                    // Not allready open a remote connexion, don't do that.
                    isAlive = false;
                    return null;
                } else {
                    currentHeaders.setAutoCommit(true);
                }
            }

            jaxwsClient.execute(currentHeaders, command, inputRequest, holderResponse, holderOutput);

            if (null != holderResponse.value && currentHeaders == firstHeaders) {
                currentHeaders = new HeaderViewForLocalApiClientRequest();
                currentHeaders.setTransactionID(holderResponse.value.getTransactionID());
            }

            String svlOutput = holderOutput.value;

            SVLObjectWrapper out = null;
            if (null != svlOutput && !svlOutput.isEmpty()) {
                try {
                    if (!svlOutput.endsWith("\n")) {
                        svlOutput += "\n";
                    }
                    out = unmarshaller.deserialize(new ByteArrayInputStream((" => {\n" + svlOutput + "}").getBytes(Charset.forName("utf-8"))));
                } catch (Exception e) {
                    throw new SOIException("unmarshalling response", e);
                }
            }
            if (null == out) {
                out = AbstractSVLObjectFactory.createSVLObject();
            }
            return out;
        } catch (TechnicalExceptionFault e) {
            com.orange.bscs.interfaces.common.v1.root.TechnicalExceptionFault detail = e.getFaultInfo();
            throw new APIException(e, detail.getLocalFaultCode(), detail.getFaultLabel());
        } catch (AuthenticationExceptionFault e) {
            com.orange.bscs.interfaces.common.v1.root.AuthenticationExceptionFault detail = e.getFaultInfo();
            throw new APIException(e, detail.getLocalFaultCode(), detail.getFaultLabel());
        } catch (PoolExceptionFault e) {
            com.orange.bscs.interfaces.common.v1.root.PoolExceptionFault detail = e.getFaultInfo();
            throw new APIException(e, detail.getLocalFaultCode(), detail.getFaultLabel());
        } catch (InvalidTransactionIDExceptionFault e) {
            com.orange.bscs.interfaces.common.v1.root.InvalidTransactionIDExceptionFault detail = e.getFaultInfo();
            throw new APIException(e, detail.getLocalFaultCode(), detail.getFaultLabel());
        } catch (SOIExceptionFault e) {
            com.orange.bscs.interfaces.common.v1.root.SOIExceptionFault detail = e.getFaultInfo();
            throw new APIException(e, detail.getLocalFaultCode(), detail.getFaultLabel());
        } catch (org.apache.cxf.interceptor.Fault f) {
            isAlive = false;
            throw new SOIException(f);
        } finally {
            if ("CLOSE".equals(command)) {
                currentHeaders = firstHeaders;
                firstHeaders.setTransactionID(null);
                isAlive = false;
            }
        }
    }

    @Override
    public SVLObjectWrapper createSVLObject() {
        return AbstractSVLObjectFactory.createSVLObject();
    }

    @Override
    public SVLObjectListWrapper createSVLObjectList() {
        return AbstractSVLObjectFactory.createSVLObjectList();
    }

    public void setClient(SVLBackendPortType client) {
        jaxwsClient = client;
    }


    /**
     * @return NULL
     */
    @Override
    public IConnectionBackend getSourceBackend() {
        return null;
    }


    @Override
    public String[] getCommands(String componentName, String componentVersion) {
        Holder<HeaderViewForLocalApiClientResponse> holderResponse = new Holder<>();
        holderResponse.value = localApiClientResponse;

        Holder<List<String>> holderCommands = new Holder<>();
        try {
            jaxwsClient.getCommands(currentHeaders, componentName, componentVersion, holderResponse, holderCommands);

            if (null != holderResponse.value && currentHeaders == firstHeaders) {
                currentHeaders = new HeaderViewForLocalApiClientRequest();
                currentHeaders.setTransactionID(holderResponse.value.getTransactionID());
            }

            return (null == holderCommands.value) ? null : holderCommands.value.toArray(new String[]{});
        } catch (InvalidTransactionIDExceptionFault e) {
            com.orange.bscs.interfaces.common.v1.root.InvalidTransactionIDExceptionFault detail = e.getFaultInfo();
            throw new APIException(e, detail.getLocalFaultCode(), detail.getFaultLabel());
        } catch (PoolExceptionFault e) {
            com.orange.bscs.interfaces.common.v1.root.PoolExceptionFault detail = e.getFaultInfo();
            throw new APIException(e, detail.getLocalFaultCode(), detail.getFaultLabel());
        } catch (AuthenticationExceptionFault e) {
            com.orange.bscs.interfaces.common.v1.root.AuthenticationExceptionFault detail = e.getFaultInfo();
            throw new APIException(e, detail.getLocalFaultCode(), detail.getFaultLabel());
        } catch (SOIExceptionFault e) {
            throw new SOIException(e);
        }

    }


    @Override
    public ParameterListWrapper getInputList(String command, String componentName, String componentVersion)
            throws APIException {

        Holder<HeaderViewForLocalApiClientResponse> holderResponse = new Holder<>();
        holderResponse.value = localApiClientResponse;

        Holder<com.orange.bscs.svlbackend.SOAParameterList> holderParams = new Holder<>();

        ParameterListWrapper result;

        try {
            jaxwsClient.getInputList(currentHeaders, command, componentName, componentVersion, holderResponse, holderParams);

            if (null != holderResponse.value && currentHeaders == firstHeaders) {
                currentHeaders = new HeaderViewForLocalApiClientRequest();
                currentHeaders.setTransactionID(holderResponse.value.getTransactionID());
            }

            APIParameterList pl = new APIParameterList();

            recurseParse(pl, holderParams.value);

            result = pl;
        } catch (AuthenticationExceptionFault e) {
            com.orange.bscs.interfaces.common.v1.root.AuthenticationExceptionFault detail = e.getFaultInfo();
            throw new APIException(e, detail.getLocalFaultCode(), detail.getFaultLabel());
        } catch (InvalidTransactionIDExceptionFault e) {
            com.orange.bscs.interfaces.common.v1.root.InvalidTransactionIDExceptionFault detail = e.getFaultInfo();
            throw new APIException(e, detail.getLocalFaultCode(), detail.getFaultLabel());
        } catch (PoolExceptionFault e) {
            com.orange.bscs.interfaces.common.v1.root.PoolExceptionFault detail = e.getFaultInfo();
            throw new APIException(e, detail.getLocalFaultCode(), detail.getFaultLabel());
        } catch (SOIExceptionFault e) {
            throw new APIException(e.getMessage(), e);
        }

        return result;
    }

    @Override
    public ParameterListWrapper getOutputList(String command, String componentName, String componentVersion)
            throws APIException {

        Holder<HeaderViewForLocalApiClientResponse> holderResponse = new Holder<>();
        holderResponse.value = localApiClientResponse;

        Holder<com.orange.bscs.svlbackend.SOAParameterList> holderParams = new Holder<>();

        ParameterListWrapper result ;

        try {
            jaxwsClient.getOutputList(currentHeaders, command, componentName, componentVersion, holderResponse, holderParams);

            if (null != holderResponse.value && currentHeaders == firstHeaders) {
                currentHeaders = new HeaderViewForLocalApiClientRequest();
                currentHeaders.setTransactionID(holderResponse.value.getTransactionID());
            }

            APIParameterList pl = new APIParameterList();

            recurseParse(pl, holderParams.value);

            result = pl;
        } catch (AuthenticationExceptionFault e) {
            com.orange.bscs.interfaces.common.v1.root.AuthenticationExceptionFault detail = e.getFaultInfo();
            throw new APIException(e, detail.getLocalFaultCode(), detail.getFaultLabel());
        } catch (InvalidTransactionIDExceptionFault e) {
            com.orange.bscs.interfaces.common.v1.root.InvalidTransactionIDExceptionFault detail = e.getFaultInfo();
            throw new APIException(e, detail.getLocalFaultCode(), detail.getFaultLabel());
        } catch (PoolExceptionFault e) {
            com.orange.bscs.interfaces.common.v1.root.PoolExceptionFault detail = e.getFaultInfo();
            throw new APIException(e, detail.getLocalFaultCode(), detail.getFaultLabel());
        } catch (SOIExceptionFault e) {
            throw new APIException(e.getMessage(), e);
        }

        return result;
    }

    @Override
    public void doChangeSession(SOITransactionsManager soiTransactionsManager, SOIConnection soiConnection, String s, String s1) {
        // nothing to do
    }

    private void recurseParse(APIParameterList pl, SOAParameterList value) {
        if (null == value) {
            return;
        }

        for (SOAParameterInfo pi : value.getParameter()) {
            APIParameterList sublist = toParameterList(pi.getSubList());
            if (null != sublist) {
                sublist.setListType(AbstractSVLObjectFactory.svlTypeByValue(pi.getType()));
            }
            ParameterListWrapper.ParameterInfoWrapper info = AbstractSVLObjectFactory.getParameterInfo(pi.getName(), AbstractSVLObjectFactory.svlTypeByValue(pi.getType()), pi.getFlags(), pi.getLength(), sublist);
            pl.add(info);
        }
    }


    private APIParameterList toParameterList(SOAParameterList subList) {
        if (null == subList) {
            return null;
        }
        APIParameterList pl = new APIParameterList();
        recurseParse(pl, subList);

        return pl;
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

        String rootPrefix = "";
        StringBuilder sb = new StringBuilder();
        int pos;
        do {
            pos = line.indexOf("NamedValueContainerList");
            if (pos < 0) {
                sb.append(rootPrefix);
                sb.append(line);
            } else {
                String prefix = extractPrefix(line);
                line = line.substring(prefix.length());
                pos = line.indexOf("NamedValueContainerList");
                rootPrefix = rootPrefix + prefix;

                String right = line.substring(pos + 23);
                String left = line.substring(0, pos + 23);
                sb.append(rootPrefix);
                sb.append(left).append("\r\n");
                line = right;
            }
        } while (pos > 0);

        return sb.toString();


    }

    private String extractPrefix(String line) {
        String rootPrefix = "";
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
            rootPrefix = pref.toString();
        }
        return rootPrefix;
    }
}
