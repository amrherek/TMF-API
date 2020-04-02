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
public class BscsParameterServiceAdapterIXR2Test {

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
        List<BscsContractServiceParameter> parameters = adapter.getContractServiceParameters("CONTR0000001640", 0L,
                "MOBIT");
        assertThat(parameters).isNotEmpty();
    }

    @Test
    public void getContractServiceParameters_noParams() throws BscsInvalidIdException {
        List<BscsContractServiceParameter> parameters = adapter.getContractServiceParameters("CONTR0000001640", 0L,
                "HYBTP");
        assertThat(parameters).isEmpty();
    }

    @Test
    public void getContractServiceParameters_serviceNotInContract() throws BscsInvalidIdException {
        List<BscsContractServiceParameter> parameters = adapter.getContractServiceParameters("CONTR0000001623", 0L,
                "MOBIT");
        assertThat(parameters).isEmpty();
    }

    @Test
    public void getContractServiceParameters_unknownContract()
            throws BscsInvalidIdException {
        List<BscsContractServiceParameter> parameters = adapter.getContractServiceParameters("CONTR999", 0L, "MOBIT");
        assertThat(parameters).isEmpty();
    }

    @Test
    public void getContractServiceParameters_unknownService() throws BscsInvalidIdException {
        List<BscsContractServiceParameter> parameters = adapter.getContractServiceParameters("CONTR0000001640", 0L,
                "UNKNOWN");
        assertThat(parameters).isEmpty();
    }

    @Test
    public void getServiceParameter_ok() throws BscsInvalidIdException {
        BscsParameter serviceParameter = adapter.getServiceParameter("MOBIT", "VOL");
        assertThat(serviceParameter).isNotNull();
    }

    @Test
    public void getServiceParameter_unknownServiceId() throws BscsInvalidIdException {
        try {
            adapter.getServiceParameter("UNKNOWN", "VOL");
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
            adapter.getServiceParameter("MOBIT", "UNKNOWN");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("UNKNOWN");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.PARAMETER_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getServiceParameter_parameterIdNotInService()
            throws BscsInvalidIdException {
        try {
            adapter.getServiceParameter("1GB", "VOL");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("VOL");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.PARAMETER_ID);
            assertThat(e.getMessage()).contains("{VOL}");
            assertThat(e.getMessage()).contains("{1GB}");
            return;
        }
        fail("No exception");
    }

    @Test
    public void getParameters_ok() throws BscsInvalidIdException {
        List<BscsParameter> parameters = adapter.getParameters("MOBIT");
        assertThat(parameters).isNotEmpty();
    }

    @Test
    public void getParameters_noParams() throws BscsInvalidIdException {
        List<BscsParameter> parameters = adapter.getParameters("HYBTP");
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
        BscsParameter prm = adapter.getServiceParameter("MOBIT", "VOL");
        BscsContractServiceParameter param = adapter
                .buildContractServiceParameter(prm, "1", "100MB", 'm');
        List<BscsContractServiceParameter> params = Arrays.asList(param);
        adapter.writeContractServiceParameters("CONTR0000001640", "MOBIT", params, 0L, true);
    }

    @Test
    public void writeContractServiceParameters_unknownContract()
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        try {
            BscsParameter prm = adapter.getServiceParameter("MOBIT", "VOL");
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "1", "100MB", 'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("UNKNOWN", "MOBIT", params, 0L, false);
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
            BscsParameter prm = adapter.getServiceParameter("MOBIT", "VOL");
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "1", "100MB", 'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("CONTR0000001640", "UNKNOWN", params, 0L, false);
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
            BscsParameter prm = adapter.getServiceParameter("1GB", "GSM51");
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "1", "100MB", 'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("CONTR0000001640", "1GB", params, 0L, true);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("1GB");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            assertThat(e.getMessage()).contains("1GB");
            assertThat(e.getMessage()).contains("CONTR0000001640");
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractServiceParameters_ParameterValue()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            BscsParameter prm = adapter.getServiceParameter("MOBIT", "VOL");
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "toto", "100MB", 'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("CONTR0000001640", "MOBIT", params, 0L, false);
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
            BscsParameter prm = adapter.getServiceParameter("FAF1", "IP1");
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "100", "100", 'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("CONTR0000002778", "FAF1", params, 0L, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("?");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.PARAMETER_VALUE);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractServiceParameters_suspended()
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException {
        try {
            BscsParameter prm = adapter.getServiceParameter("BIFO", "LANG");
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "AN", "AN", 'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("CONTR0000000760", "BIFO", params, 0L, true);
        } catch (BscsInvalidStateException e) {
            assertThat(e.getField()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractServiceParameters_terminated()
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException {
        try {
            BscsParameter prm = adapter.getServiceParameter("BIFO", "LANG");
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "AN", "AN", 'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("CONTR0000000761", "BIFO", params, 0L, true);
        } catch (BscsInvalidStateException e) {
            assertThat(e.getField()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }


}
