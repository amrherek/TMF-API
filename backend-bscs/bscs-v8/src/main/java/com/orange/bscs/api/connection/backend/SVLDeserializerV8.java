package com.orange.bscs.api.connection.backend;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.io.Resource;

import com.orange.bscs.api.connection.SVLInputHash;
import com.orange.bscs.api.model.SVLDeserializer;
import com.orange.bscs.api.model.SVLObjectWrapper;
import com.orange.bscs.model.wrapper.SVLObjectWrapperV8;
import com.semagroup.targys.framework.common.SVLDate;
import com.semagroup.targys.framework.common.SVLDateTime;
import com.semagroup.targys.framework.common.SVLMoney;
import com.semagroup.targys.framework.common.SVLObject;
import com.semagroup.targys.framework.common.SVLObjectList;
import com.semagroup.targys.servicelayer.common.NVExchangeFormatFactory;

/**
 * Created by deyb6792 on 18/08/2016.
 */
public class SVLDeserializerV8 implements SVLDeserializer {
    private static final String CRLF = System.getProperty("line.separator");
    private static final int MAX_LIST_LEVEL = 10;

    private static final String DETECT_COMMENT_START = "<!--";
    private static final String DETECT_COMMENT_END = "-->";
    private static final String DETECT_OUTPUT = "|   => ";
    public static final String DETECT_START_INPUT = " {";
    public static final Pattern PATTERN_EXECUTE = Pattern.compile("(?:execute\\s*\\(?\\s*)([a-zA-Z]+\\.[a-zA-Z]+)(?:,?\\s*\\{)(.*)");

    private ParsedCommand sOICommand = null;
    private boolean parseInput = false;

    private DateFormat dfDateTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
    // The locale is fixed in order to always have the same deserialization of
    // SVLMoney (which is locale dependent)
    private NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

    private List<ParsedCommand> commandes = new ArrayList<>();
    private SVLObjectList[] queueLists = new SVLObjectList[MAX_LIST_LEVEL];


    private SVLObjectList currentList = null;
    private SVLObject currentListValue = null;

    private SVLObject lastValue = null;
    private SVLObject actualValue = null;

	private int timezoneOffset;

	public SVLDeserializerV8(int timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}

    public SVLObjectWrapper deserialize(Resource resource) throws IOException, ParseException {
        return deserialize(resource.getInputStream());
    }


    public SVLObjectWrapper deserialize(File file) throws IOException, ParseException {

        commandes = new ArrayList<ParsedCommand>();
		SVLObjectWrapper lastOutput = new SVLObjectWrapperV8(parseLogFilename(file), timezoneOffset);
        return lastOutput;
    }

    @Override
	public SVLObjectWrapper deserialize(InputStream inputStream) throws IOException, ParseException {

        commandes = new ArrayList<ParsedCommand>();
        SVLObjectWrapper lastOutput;
        try (Reader reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"))) {
            SVLObject svlObject = parseReader(reader);
            if (svlObject == null) {
                svlObject = NVExchangeFormatFactory.instance().createSVLObject();
            }
            lastOutput = new SVLObjectWrapperV8(svlObject, timezoneOffset);
        }
        return lastOutput;
    }


    private void serialiseCommand(File parent, String filename, ParsedCommand cmd) throws IOException {
        File fileCmd = new File(parent, filename);

        try (FileOutputStream fout = new FileOutputStream(fileCmd);
                PrintStream ps = new PrintStream(fout, false, "UTF-8")) {
            StringBuilder buffer = new StringBuilder();

            buffer.append("<!--").append(CRLF)
                    .append(" execute (").append(cmd.getCommand()).append(DETECT_START_INPUT)
                    .append(null == cmd.getInput() ? "})" + CRLF : CRLF + cmd.getInput().toString() + "})" + CRLF)
                    .append("-->").append(CRLF);

            ps.print(buffer);
            if (null == cmd.getOutput()) {
                ps.println("<!-- Output=Null -->");
            } else {
                ps.println(" => {");
                ps.print(cmd.getOutput().toString());
                ps.println('}');
            }
            ps.println();
            ps.close();
        }
    }

    public List<ParsedCommand> traiteLog(File fInput) throws IOException, ParseException, NoSuchAlgorithmException {
        if (!fInput.exists()) {
            return null;
        }
        deserialize(fInput);

        return commandes;
    }

    /**
     * Parse un fichier de log
     * et genere un fichier différent pour chaque commande trouvée dans le répertoire destination
     *
     * @param fInput  fichier à traiter
     * @param dOutput répertoire destination
     * @throws IOException
     * @throws ParseException
     * @throws NoSuchAlgorithmException
     */
    public void traiteLog(File fInput, File dOutput) throws IOException, ParseException, NoSuchAlgorithmException {

        if (null == fInput || null == dOutput) {
            return;
        }

        if (!fInput.exists()) {
            return;
        }

        if (!dOutput.mkdirs()) {
            throw new IOException(String.format("directories %s not created", dOutput.getPath()));
        }

        deserialize(fInput);

        for (ParsedCommand cmd : commandes) {
			String filename = SVLInputHash.encodeCommand(cmd.getCommand(),
					new SVLObjectWrapperV8(cmd.getInput(), timezoneOffset)) + ".TXT";

            serialiseCommand(dOutput, filename, cmd);
        }

    }

    private SVLObject parseLogFilename(File file) throws IOException, ParseException {
        SVLObject result = null;
        try (FileReader fr = new FileReader(file)) {
            result = parseReader(fr);
        }
        return result;
    }

    private SVLObject parseReader(Reader in) throws IOException, ParseException {

        try (BufferedReader sr = new BufferedReader(in)) {

            String line = null;

            boolean bComment = false;

            do {
                line = sr.readLine();
                if (null == line) {
                    break;
                }
                if (line.isEmpty()) {
                    continue;
                }
                if (DETECT_COMMENT_START.equals(line)) {
                    bComment = true;
                    continue;
                } else if (DETECT_COMMENT_END.equals(line)) {
                    bComment = false;
                    continue;
                } else if (bComment) {
                    continue;
                }

                String endOfStartExecuteLine = resteOfExecuteLine(line);
                if (null != endOfStartExecuteLine) {
                    line = endOfStartExecuteLine;
                    parseInput = true;
                } else if (isOutputLine(line)) {
                    line = tronqueOutputLine(line);
                    parseInput = false;
                }

                processLine(line);
            } while (null != line);
            closeCurrentCommande();
        }

        return lastValue;
    }

    private void processLine(String line) throws ParseException {
        ParsedLine pl = new ParsedLine();
        if (pl.hasParseLine(line)) {
            traiteLine(pl);
        } else if (null != actualValue) {
            if (null != sOICommand) {
                if (parseInput) {
                    sOICommand.setInput(actualValue);
                    parseInput = false;
                } else {
                    sOICommand.setOutput(actualValue);
                    closeCurrentCommande();
                }
            }
            lastValue = actualValue;
            actualValue = null;
        }
    }

    private void traiteLine(ParsedLine pl) throws ParseException {
        pl.parseValue();

        if (null == actualValue) {
            actualValue = NVExchangeFormatFactory.instance().createSVLObject();
        }


        if (pl.levelList != -1) {
            // Encore dans un tableau
            currentList = queueLists[pl.levelList];
            if (currentList.size() <= pl.idx) {
                currentListValue = NVExchangeFormatFactory.instance().createSVLObject();
                currentList.add(pl.idx, currentListValue);
            } else {
                currentListValue = currentList.get(pl.idx);
            }
            if (pl.isList()) {
                currentListValue.setValue(pl.getAttributeName(), pl.getList());
                queueLists[pl.levelList + 1] = pl.getList();
            } else {
                currentListValue.setValue(pl.getAttributeName(), pl.getValue());
            }
        } else {
            currentList = null;
            currentListValue = null;
            if (pl.isList()) {
                actualValue.setValue(pl.getAttributeName(), pl.getList());
                queueLists[pl.levelList + 1] = pl.getList();
            } else {
                actualValue.setValue(pl.getAttributeName(), pl.getValue());
            }
        }
    }


    private void closeCurrentCommande() {
        if (null != sOICommand) {
            commandes.add(sOICommand);
            sOICommand = null;
            parseInput = false;
        }
    }

    private String tronqueOutputLine(String line) {
        int idx = line.indexOf(DETECT_OUTPUT);
        return line.substring(idx + DETECT_OUTPUT.length());
    }

    private boolean isOutputLine(String line) {
        int idx = line.indexOf(DETECT_OUTPUT);
        return idx > 0;
    }

    private String resteOfExecuteLine(String line) {
        Matcher matcher = PATTERN_EXECUTE.matcher(line);
        if (matcher.matches()) {
            String command = matcher.group(1);
            String reste = matcher.group(2);

            closeCurrentCommande();
            sOICommand = new ParsedCommand();
            sOICommand.setCommand(command);
            parseInput = true;

            return reste;
        }
        return null;
    }


    // Représente un attribut (pouvant être ajouté à un SVLObject)
    private class ParsedLine {

        private int levelList = -1;
        private int idx = -1;
        private String attribute;
        private String strValue;

        private Object value;
        private SVLObjectList list;
        private String typeName;

        public boolean hasParseLine(String line) {
            clear();
            int eq1 = line.indexOf('=');
            int eq2 = line.lastIndexOf('=');

            // For SVLDate
            int point1 = line.indexOf(':', eq1);
            int point2 = line.lastIndexOf(':');

            if (eq1 == eq2 && point1 <= point2 && eq1 > 0 && point1 > eq1) {
                attribute = line.substring(0, eq1 - 1);
                strValue = line.substring(eq1 + 2, point2 - 1);
                typeName = line.substring(point2 + 2);


                // Recherche le dernier index (-[2]-[0]RPCODE
                int parO = attribute.indexOf('[');
                int parF = attribute.indexOf(']');
                while (parO >= 0 && parF >= 0) {
                    levelList++;
                    idx = Integer.parseInt(attribute.substring(parO + 1, parF));
                    attribute = attribute.substring(parF + 1);
                    parO = attribute.indexOf('[');
                    parF = attribute.indexOf(']');
                }
                return true;
            }
            return false;
        }

        public String getAttributeName() {
            return attribute;
        }

        public Object getValue() {
            return value;
        }

        public SVLObjectList getList() {
            return list;
        }

        public boolean isList() {
            return null != list;
        }

        private void clear() {
            levelList = -1;
            idx = -1;
            attribute = null;
            strValue = null;
            typeName = null;
            value = null;
            list = null;
        }

        @Override
        public String toString() {
            if (idx == -1) {
                return attribute + "=" + value + ":" + typeName;
            } else {
                return "[" + idx + "]" + attribute + "=" + strValue + ":" + typeName;
            }
        }


        public void parseValue() throws ParseException {
            if (strValue.length() > 0 && '@' == strValue.charAt(0)) {
                value = strValue;
                return;
            }

            TypesEnum type = TypesEnum.parse(typeName);

            switch (type) {
                case UNKNOWN:
                    if ("null".equals(strValue)) {
                        value = null;
                    } else {
                        throw new ParseException(typeName, 0);
                    }
                    break;
                case TSTRING:
                    value = strValue;
                    break;
                case TDOUBLE:
                    value = Double.parseDouble(strValue);
                    break;
                case TINT:
                    value = Integer.parseInt(strValue);
                    break;
                case TBOOLEAN:
                    value = Boolean.parseBoolean(strValue);
                    break;
                case TLONG:
                    value = Long.parseLong(strValue);
                    break;
                case TCHAR:
                    value = (0 == strValue.length() ? null : strValue.charAt(0));
                    break;

                case TSVLDATETIME:
                    value = parseDateTime(strValue);
                    break;
                case TSVLDATE:
                    value = parseDate(strValue);
                    break;
                case TMONEY:
                    value = parseMoney(strValue);
                    break;
                case TLIST:
                    list = prepareList();
                    break;
                default:
                    throw new ParseException(typeName, 0);
            }

        }

        //Thu Jun 14 11:46:33 CEST 2012
        private SVLDateTime parseDateTime(String value) throws ParseException {
            Date dt = dfDateTime.parse(value);
            return NVExchangeFormatFactory.instance().createSVLDateTime(dt);
        }

        //YYYY-MM-DD: 2012-6-14
        private SVLDate parseDate(String value) throws ParseException {
            Date dt = dfDateTime.parse(value);
//            int point = value.indexOf(':');
//            if (point > 0) {
//                String sDate = value.substring(0, point - 3);
//
//                Date dt = dfDate.parse(sDate);
//                return NVExchangeFormatFactory.instance().createSVLDate(dt);
//            }
//            Date dt = DateFormat.getInstance().parse(value);
            return NVExchangeFormatFactory.instance().createSVLDate(dt);
        }

        // 23,999.08 XAF (locale fixed to EN_US)
        private SVLMoney parseMoney(String value) {
            int currencyPos = value.lastIndexOf(' ');
            String currency = value.substring(currencyPos + 1);
            double amount = 0;
            try {
                amount = numberFormat.parse(value.substring(0, currencyPos)).doubleValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return NVExchangeFormatFactory.instance().createSVLMoney(amount, currency);
        }

        private SVLObjectList prepareList() {
            return NVExchangeFormatFactory.instance().createSVLObjectList();
        }

    }


    private enum TypesEnum {
        UNKNOWN("#UNKNOWN#"),

        TSTRING("java.lang.String"),
        TDOUBLE("java.lang.Double"),
        TINT("java.lang.Integer"),
        TBOOLEAN("java.lang.Boolean"),
        TLONG("java.lang.Long"),
        TCHAR("java.lang.Character"),
        TSVLDATE("com.semagroup.targys.framework.common.SVLDate"),
        TSVLDATETIME("com.semagroup.targys.framework.common.SVLDateTime"),
        TLIST("com.semagroup.targys.framework.common.NamedValueContainerList"),
        TMONEY("com.semagroup.targys.framework.common.SVLMoney");

        private String value;

        private TypesEnum(String value) {
            this.value = value;
        }

        @Override
		public String toString() {
            return value;
        }

        public static TypesEnum parse(String value) {
            for (TypesEnum n : TypesEnum.values()) {
                if (n.value.equals(value)) {
                    return n;
                }
            }
            return TypesEnum.UNKNOWN;
        }
    }


    /**
     * Convert back a String to SVLObject.
     * <p>
     * Value may be enclose by "{" and "}"
     * <p>
     * stream may contains any number of SVLObject commands, only the last deserialized object is return (and not the first one)
     *
     * @param svlInput form of a SVLObject.toString()
     * @return the last SVLObject parsed and deserialized.
     * @throws IOException
     */
    public SVLObject unmarshall(String svlInput) throws IOException {
        commandes = new ArrayList<ParsedCommand>();

        SVLObject lastSVLObject = null;
        try (Reader reader = new InputStreamReader(
                new ByteArrayInputStream(svlInput.getBytes(Charset.forName("utf-8"))))) {
            parseReader(reader);
            // parseReader force to return result (output part of a command).
            // but unmarshall must be able to return input the one/only SVLObject of the string.
            lastSVLObject = lastValue;
            if (null == lastSVLObject) {
                lastSVLObject = actualValue;
            }

        } catch (IOException e) {
            e.getMessage();
        } catch (ParseException e) {
            e.getMessage();
        }
        if (null == lastSVLObject) {
            lastSVLObject = NVExchangeFormatFactory.instance().createSVLObject();
        }
        return lastSVLObject;
    }


    /**
     * @return commands from last deserialisation
     */
    public List<ParsedCommand> getParsedCommands() {
        return commandes;
    }


}
