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
public class BscsParameterServiceAdapterV8Test {

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
        List<BscsContractServiceParameter> parameters = adapter.getContractServiceParameters("2951090", 0L, "222");
        assertThat(parameters).isNotEmpty();
    }

    @Test
    public void getContractServiceParameters_noParams() throws BscsInvalidIdException {
        List<BscsContractServiceParameter> parameters = adapter.getContractServiceParameters("2951090", 0L, "34");
        assertThat(parameters).isEmpty();
    }

    @Test
    public void getContractServiceParameters_serviceNotInContract() throws BscsInvalidIdException {
        List<BscsContractServiceParameter> parameters = adapter.getContractServiceParameters("1001", 0L, "4");
        assertThat(parameters).isEmpty();
    }

    @Test
    public void getContractServiceParameters_unknownContract()
            throws BscsInvalidIdException {
        List<BscsContractServiceParameter> parameters = adapter.getContractServiceParameters("10", 0L, "222");
        assertThat(parameters).isEmpty();
    }

    @Test
    public void getContractServiceParameters_unknownService() throws BscsInvalidIdException {
        List<BscsContractServiceParameter> parameters = adapter.getContractServiceParameters("2951090", 0L, "99999999");
        assertThat(parameters).isEmpty();
    }

    @Test
    public void getServiceParameter_ok() throws BscsInvalidIdException {
        BscsParameter serviceParameter = adapter.getServiceParameter("222", "58");
        assertThat(serviceParameter).isNotNull();
    }

    @Test
    public void getServiceParameter_unknownServiceId() throws BscsInvalidIdException {
        try {
            adapter.getServiceParameter("99999999", "58");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("99999999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getServiceParameter_unknownParameterId() throws BscsInvalidIdException {
        try {
            adapter.getServiceParameter("222", "99999999");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("99999999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.PARAMETER_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getServiceParameter_parameterIdNotInService() throws BscsInvalidIdException {
        try {
            adapter.getServiceParameter("222", "57");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("57");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.PARAMETER_ID);
            assertThat(e.getMessage()).contains("{222}");
            assertThat(e.getMessage()).contains("{57}");
            return;
        }
        fail("No exception");
    }

    @Test
    public void getParameters_ok() throws BscsInvalidIdException {
        List<BscsParameter> parameters = adapter.getParameters("222");
        assertThat(parameters).isNotEmpty();
    }

    @Test
    public void getParameters_noParams() throws BscsInvalidIdException {
        List<BscsParameter> parameters = adapter.getParameters("1");
        assertThat(parameters).isEmpty();
    }

    @Test
    public void getParameters_unknownService() throws BscsInvalidIdException {
        try {
            List<BscsParameter> parameters = adapter.getParameters("99999999");
            assertThat(parameters).isEmpty();
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("99999999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
    }

    @Test
    public void writeContractServiceParameters_ok()
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        BscsParameter prm = adapter.getServiceParameter("222", "58");
        BscsContractServiceParameter param = adapter
                .buildContractServiceParameter(prm, "4.1101", "4.1101", 'm');
        List<BscsContractServiceParameter> params = Arrays.asList(param);
        adapter.writeContractServiceParameters("2951090", "222", params, 0L, false);
    }

    @Test
    public void writeContractServiceParameters_unknownContract()
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        try {
            BscsParameter prm = adapter.getServiceParameter("222", "58");
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "4.1101", "4.1101", 'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("10", "222", params, 0L, false);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("10");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractServiceParameters_unknownService()
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        try {
            BscsParameter prm = adapter.getServiceParameter("222", "58");
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "4.1101", "4.1101", 'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("2951090", "9999999", params, 0L, false);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("9999999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractServiceParameters_parameterNotInContract()
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        try {
            BscsParameter prm = adapter.getServiceParameter("222", "58");
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "4.1101", "4.1101", 'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("14625", "14", params, 0L, false);
        } catch (BscsInvalidFieldException e) {
            // same as invalid parameter value
            assertThat(e.getValue()).contains("Parameter not initialized");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.PARAMETER_VALUE);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractServiceParameters_serviceNotInContract()
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        try {
            BscsParameter prm = adapter.getServiceParameter("222", "58");
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "4.1101", "4.1101", 'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("14625", "222", params, 0L, false);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("222");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            assertThat(e.getMessage()).contains("{222}");
            assertThat(e.getMessage()).contains("{14625}");
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractServiceParameters_ParameterValue()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            BscsParameter prm = adapter.getServiceParameter("234", "61");
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "toto", null, 'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("2951090", "234", params, 0L, false);
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
            BscsParameter prm = adapter.getServiceParameter("184", "48");
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "2000000001", "2000000001",
                    'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("8081197", "184", params, 0L, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("2.000000001E9");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.PARAMETER_VALUE);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractServiceParameters_suspended()
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException {
        try {
            BscsParameter prm = adapter.getServiceParameter("222", "58");
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "4.1101", "4.1101", 'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("25488997", "222", params, 0L, false);
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
            BscsParameter prm = adapter.getServiceParameter("222", "58");
            BscsContractServiceParameter param = adapter.buildContractServiceParameter(prm, "4.1101", "4.1101", 'm');
            List<BscsContractServiceParameter> params = Arrays.asList(param);
            adapter.writeContractServiceParameters("25491977", "222", params, 0L, false);
        } catch (BscsInvalidStateException e) {
            assertThat(e.getField()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

}
