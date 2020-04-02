package com.orange.bscs.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.orange.bscs.api.connection.service.BscsConnectionService;
import com.orange.bscs.config.BscsTestConfig;
import com.orange.bscs.model.BscsContractServiceParameter;
import com.orange.bscs.model.BscsParameter;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.bscs.service.exception.BscsInvalidStateException;
import com.orange.bscs.service.exception.BscsPendingException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BscsTestConfig.class })
@ActiveProfiles("corba")
@Category(CorbaBscsTest.class)
public class BscsParameterServiceAdapterV9Test {

    @Autowired
    private BscsParameterServiceAdapter adapter;

    @Autowired
    private BscsConnectionService bscsConnectionService;

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

    @Before
    public void openConnection() throws Exception {
        bscsConnectionService.openConnection();
    }

    @After
    public void closeConnection() {
        bscsConnectionService.closeConnection();
    }

    @Test
    public void getContractServiceParameters_ok() throws BscsInvalidIdException {
        List<BscsContractServiceParameter> parameters = adapter.getContractServiceParameters("CONTR0000000694", 0L,
                "SUB0");
        assertThat(parameters).isNotEmpty();
    }

    @Test
    public void getContractServiceParameters_noParams() throws BscsInvalidIdException {
        List<BscsContractServiceParameter> parameters = adapter.getContractServiceParameters("CONTR0000000694", 0L,
                "CHOLD");
        assertThat(parameters).isEmpty();
    }

    @Test
    public void getContractServiceParameters_serviceNotInContract() throws BscsInvalidIdException {
        List<BscsContractServiceParameter> parameters = adapter.getContractServiceParameters("CONTR0000000694", 0L,
                "CUG");
        assertThat(parameters).isEmpty();
    }

    @Test
    public void getContractServiceParameters_unknownContract() throws BscsInvalidIdException {
        List<BscsContractServiceParameter> parameters = adapter.getContractServiceParameters("CONTR999", 0L, "SUB0");
        assertThat(parameters).isEmpty();
    }

    @Test
    public void getContractServiceParameters_unknownService() throws BscsInvalidIdException {
        List<BscsContractServiceParameter> parameters = adapter.getContractServiceParameters("CONTR0000000694", 0L,
                "UNKNOWN");
        assertThat(parameters).isEmpty();
    }

    @Test
    public void getServiceParameter_ok() throws BscsInvalidIdException {
        BscsParameter serviceParameter = adapter.getServiceParameter("SUB0", "DE23");
        assertThat(serviceParameter).isNotNull();
    }

    @Test
    public void getServiceParameter_unknownServiceId() throws BscsInvalidIdException {
        try {
            adapter.getServiceParameter("UNKNOWN", "DE23");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("UNKNOWN");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getServiceParameter_unknownParameterId() throws BscsInvalidIdException {
        try {
            adapter.getServiceParameter("SUB0", "UNKNOWN");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("UNKNOWN");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.PARAMETER_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getServiceParameter_parameterIdNotInService() throws BscsInvalidIdException {
        try {
            adapter.getServiceParameter("SUB0", "DEMO6");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("DEMO6");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.PARAMETER_ID);
            assertThat(e.getMessage()).contains("{SUB0}");
            assertThat(e.getMessage()).contains("{DEMO6}");
            return;
        }
        fail("No exception");
    }

    @Test
    public void getParameters_ok() throws BscsInvalidIdException {
        List<BscsParameter> parameters = adapter.getParameters("SUB0");
        assertThat(parameters).isNotEmpty();
    }

    @Test
    public void getParameters_noParams() throws BscsInvalidIdException {
        List<BscsParameter> parameters = adapter.getParameters("CHOLD");
        assertThat(parameters).isEmpty();
    }

    @Test
    public void getParameters_unknownService() throws BscsInvalidIdException {
        try {
            List<BscsParameter> parameters = adapter.getParameters("UNKNOWN");
            assertThat(parameters).isEmpty();
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("UNKNOWN");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
    }

    @Test
    public void writeContractServiceParameters_ok()
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        BscsParameter prm = adapter.getServiceParameter("SUB0", "DE23");
        BscsContractServiceParameter param = adapter
                .buildContractServiceParameter(prm, "1", "No email", 'm');
        List<BscsContractServiceParameter> params = Arrays.asList(param);
        adapter.writeContractServiceParameters("CONTR0000000694", "SUB0", params, 0L, true);
    }

    @Test
    public void writeContractServiceParameters_unknownContract()
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        try {
            BscsParameter prm = adapter.getServiceParameter("SUB0", "DE23");
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "1", "No email", 'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("UNKNOWN", "SUB0", params, 0L, true);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("UNKNOWN");
            // we do not known which field contains invalid public key
            assertThat(e.getName()).isNull();
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractServiceParameters_unknownService()
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        try {
            BscsParameter prm = adapter.getServiceParameter("SUB0", "DE23");
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "1", "No email", 'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("CONTR0000000694", "UNKNOWN", params, 0L, true);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("UNKNOWN");
            // we do not known which field contains invalid public key
            assertThat(e.getName()).isNull();
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractServiceParameters_serviceNotInContract()
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        try {
            BscsParameter prm = adapter.getServiceParameter("BAIC", "GSM11");
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "1", "No email", 'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("CONTR0000000694", "BAIC", params, 0L, true);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("BAIC");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            assertThat(e.getMessage()).contains("BAIC");
            assertThat(e.getMessage()).contains("CONTR0000000694");
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractServiceParameters_parameterNbNotInContract()
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        try {
            BscsParameter prm = adapter.getServiceParameter("BAIC", "GSM12");
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "1", "No email", 'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("CONTR0000000694", "SUB0", params, 0L, true);
        } catch (BscsInvalidFieldException e) {
            // we do not known this id
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.PARAMETER_VALUE);
            assertThat(e.getMessage()).contains("Parameter not initialized");
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractServiceParameters_ParameterValue()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            BscsParameter prm = adapter.getServiceParameter("SUB0", "DE23");
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "toto", "No email", 'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("CONTR0000000694", "SUB0", params, 0L, true);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("toto");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.PARAMETER_VALUE);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractServiceParameters_ParameterValueOutOfBound()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            BscsContractServiceParameter prm = adapter.getContractServiceParameter("CONT0000037558", "DCF", "DE22", 0L);
            // format: [0-9]{1,15}
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "123456789123456789",
                    "123456789123456789", 'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("CONT0000037558", "DCF", params, 0L, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("?");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.PARAMETER_VALUE);
            return;
        }
        // no exception (format must not be checked)
        // fail("No exception");
    }

    @Test
    public void writeContractServiceParameters_suspended()
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException {
        try {
            BscsParameter prm = adapter.getServiceParameter("SUB0", "DE23");
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "1", "No email", 'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("CONT0000196011", "SUB0", params, 0L, true);
        } catch (BscsInvalidStateException e) {
            assertThat(e.getField()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractServiceParameters_terminated()
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        try {
            BscsParameter prm = adapter.getServiceParameter("SUB0", "DE23");
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "1", "No email", 'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("CONT0000147490", "SUB0", params, 0L, true);
        } catch (BscsInvalidIdException e) {
            // no more service in terminated contract
            assertThat(e.getId()).isEqualTo("SUB0");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

}
