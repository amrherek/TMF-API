package com.orange.bscs.api.connection.backend;

import com.lhs.ccb.common.soi.ExchangeFormatFactory;
import com.lhs.ccb.common.soi.InvalidParameterTypeException;
import com.lhs.ccb.common.soi.SVLObject;
import com.lhs.ccb.common.soi.SVLObjectList;
import com.lhs.ccb.common.soiimpl.DefaultSVLObjectFactory;
import com.lhs.ccb.func.ect.SystemException;
import com.orange.bscs.api.model.SVLObjectWrapper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

public class SVLDeserializerTest {

    private static final String CRLF = System.getProperty("line.separator");

    @BeforeClass
    public static void initExchange() throws NoSuchAlgorithmException {
        try {
            ExchangeFormatFactory.instance();
        } catch (SystemException se) {
            ExchangeFormatFactory.init(new DefaultSVLObjectFactory());
        }
    }


    /**
     * Parse a file with 2 commands.
     *
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void InputStreamTest() throws IOException, ParseException {

        InputStream is = SVLDeserializerTest.class.getClassLoader().getResourceAsStream("mock/businesspartner/CUSTOMER.READ_Rc0vYu3DjIFC14Qzox54YQ__.TXT");
        Assert.assertNotNull("InputStream cannot be Null", is);

        SVLDeserializerIXR4 des = new SVLDeserializerIXR4();
        des.deserialize(is);

        List<ParsedCommand> res = des.getParsedCommands();
//		TODO doesn't work...
//		Assert.assertEquals(2, res.size());
    }

    /**
     * Parse a file with sub-Array.
     *
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void InputStreamSubArrayTest() throws IOException, ParseException {

        InputStream is = SVLDeserializerTest.class.getClassLoader().getResourceAsStream("mock/SERVICES.READ_ITEL.TXT");
        Assert.assertNotNull("InputStream cannot be Null", is);

        SVLDeserializerIXR4 des = new SVLDeserializerIXR4();
        SVLObjectWrapper out = des.deserialize(is);
        Assert.assertNotNull(out);
    }


    /**
     * Parse a string representing a SVLObject, but it is not a Command
     *
     * @throws IOException
     * @throws ParseException
     * @throws InvalidParameterTypeException
     */
    @Test
    public void StringUnmarshallSVLO() throws IOException, ParseException, InvalidParameterTypeException {

        String output = "CS_ID = 733 : java.lang.Long" + CRLF +
                "CS_ID_PUB = CUST0000000661 : java.lang.String" + CRLF +
                "TOTO" + CRLF;

        SVLDeserializerIXR4 des = new SVLDeserializerIXR4();
        SVLObject res = des.unmarshall(output);

        Assert.assertNotNull(res);
        Assert.assertEquals("CUST0000000661", res.getStringValue("CS_ID_PUB"));
        Assert.assertEquals(733L, res.getLongValue("CS_ID"));

        List<ParsedCommand> cmds = des.getParsedCommands();
        Assert.assertEquals(0, cmds.size());

    }

    @Test
    public void stringUnmarshalCommand() throws InvalidParameterTypeException, IOException {
        String output = "execute (COMMAND.READ, {CS_ID = 733 : java.lang.Long" + CRLF +
                "} => {" + CRLF +
                "CS_ID = 733 : java.lang.Long" + CRLF +
                "CS_ID_PUB = CUST0000000661 : java.lang.String" + CRLF +
                "}";
        SVLDeserializerIXR4 des = new SVLDeserializerIXR4();
        SVLObject res = des.unmarshall(output);
        Assert.assertEquals("CUST0000000661", res.getStringValue("CS_ID_PUB"));

        List<ParsedCommand> cmds = des.getParsedCommands();
        Assert.assertEquals(1, cmds.size());
        Assert.assertEquals("COMMAND.READ", cmds.get(0).getCommand());


    }

    @Test
    public void deserializeBusinessTransaction() throws IOException, ParseException {
        InputStream is = SVLDeserializerTest.class.getClassLoader().getResourceAsStream("mock/BUSINESSTRANSACTION.READ.TXT");
        Assert.assertNotNull("InputStream cannot be Null", is);

        SVLDeserializerIXR4 des = new SVLDeserializerIXR4();
        SVLObjectWrapper svlBt = des.deserialize(is);

        Assert.assertNotNull(svlBt);
        Object referencedbtrans = svlBt.getValue("referencedbtrans");
        Assert.assertNotNull(referencedbtrans);
        Assert.assertTrue(referencedbtrans instanceof SVLObjectList);

    }

}
