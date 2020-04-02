package com.orange.bscs.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

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
import com.orange.bscs.model.BscsContract;
import com.orange.bscs.model.BscsFreeUnitPackage;
import com.orange.bscs.model.BscsRatePlan;
import com.orange.bscs.model.BscsService;
import com.orange.bscs.model.BscsServicePackage;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.model.product.BSCSRatePlan;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BscsTestConfig.class })
@ActiveProfiles("corba")
@Category(CorbaBscsTest.class)
public class BscsProductServiceAdapterV8Test {
    
    @Autowired
    private BscsProductServiceAdapter adapter;

    @Autowired
    private BscsContractServiceAdapter contractServiceAdapter;

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
    public void getRatePlan_ok() throws BscsInvalidIdException {
        BscsRatePlan ratePlan = adapter.getRatePlan("62");
        assertThat(ratePlan).isNotNull();
    }

    @Test
    public void getRatePlan_unknownId() {
        try {
            adapter.getRatePlan("9999999");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("9999999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.RATE_PLAN_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getRatePlanNumeric_ok() throws BscsInvalidIdException {
        BscsRatePlan ratePlan = adapter.getRatePlan(62L);
        assertThat(ratePlan).isNotNull();
    }

    @Test
    public void getRatePlanNumeric_unknownId() {
        try {
            adapter.getRatePlan(9999999L);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("9999999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.RATE_PLAN_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getAvailableServicesFromContract_ok() throws BscsInvalidFieldException, BscsInvalidIdException {
        BscsContract contract = contractServiceAdapter.getContract("4836067");
        List<BscsService> services = adapter.getAvailableServicesFromContract(contract);
        assertThat(services).isNotEmpty();
    }

    @Test
    public void allowedServicesRead_ok() throws BscsInvalidIdException {
        BSCSRatePlan ratePlan = adapter.allowedServicesRead("62");
        assertThat(ratePlan).isNotNull();
    }

    @Test
    public void allowedServicesRead_badRp() {
        try {
            adapter.allowedServicesRead("99999");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("99999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.RATE_PLAN_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getServiceByRatePlanServicePackageAndServiceId_ok()
            throws BscsInvalidFieldException, BscsInvalidIdException {
        BscsService service = adapter.getServiceByRatePlanServicePackageAndServiceId("62", "70", "1");
        assertThat(service).isNotNull();
    }

    @Test
    public void getServiceByRatePlanServicePackageAndServiceId_unknownRp() throws BscsInvalidIdException {
        try {
            adapter.getServiceByRatePlanServicePackageAndServiceId("99999", "70", "1");
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("70");
            assertThat(e.getMessage()).contains("70");
            assertThat(e.getMessage()).contains("99999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_PACKAGE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getServiceByRatePlanServicePackageAndServiceId_unknownSp() throws BscsInvalidIdException {
        try {
            adapter.getServiceByRatePlanServicePackageAndServiceId("62", "99999", "1");
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("99999");
            assertThat(e.getMessage()).contains("99999");
            assertThat(e.getMessage()).contains("62");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_PACKAGE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getServiceByRatePlanServicePackageAndServiceId_unknownService()
            throws BscsInvalidFieldException, BscsInvalidIdException {
        try {
            adapter.getServiceByRatePlanServicePackageAndServiceId("62", "70", "99999");
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("99999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getServiceByRatePlanServicePackageAndServiceId_serviceNotInSp() throws BscsInvalidIdException {
        try {
            adapter.getServiceByRatePlanServicePackageAndServiceId("62", "70", "29");
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("29");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getServiceByRatePlanServicePackageAndServiceId_SpNotInRp() throws BscsInvalidIdException {
        try {
            adapter.getServiceByRatePlanServicePackageAndServiceId("103", "70", "29");
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("70");
            assertThat(e.getMessage()).contains("103");
            assertThat(e.getMessage()).contains("70");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_PACKAGE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getServiceWithRp_ok() throws BscsInvalidFieldException, BscsInvalidIdException {
        BscsService service = adapter.getService("1", "70", "62", 4L);
        assertThat(service).isNotNull();
    }

    @Test
    public void getServiceWithRp_unknownRpVersion() throws BscsInvalidIdException {
        try {
            adapter.getService("1", "70", "62", 99L);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("70");
            assertThat(e.getMessage()).contains("70");
            assertThat(e.getMessage()).contains("62");
            assertThat(e.getMessage()).contains("99");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_PACKAGE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getService_ok() throws BscsInvalidIdException {
        BscsService service = adapter.getService("1");
        assertThat(service).isNotNull();
    }

    @Test
    public void getService_unknownService() {
        try {
            adapter.getService("99999");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("99999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getServicePackage_ok() throws BscsInvalidIdException {
        BscsServicePackage sp = adapter.getServicePackage("70");
        assertThat(sp).isNotNull();
    }

    @Test
    public void getServicePackage_unknownServicePackage() {
        try {
            adapter.getServicePackage("99999");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("99999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_PACKAGE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getFreeUnitPackages_ok() {
        List<BscsFreeUnitPackage> freeUnitPackages = adapter.getFreeUnitPackages();
        assertThat(freeUnitPackages).isNotEmpty();
    }

    @Test
    public void getFreeUnitPackage_ok() throws BscsInvalidIdException {
        BscsFreeUnitPackage freeUnitPackage = adapter.getFreeUnitPackage(95L);
        assertThat(freeUnitPackage).isNotNull();
    }

    @Test
    public void getFreeUnitPackage_unkwnownId() {
        try {
            adapter.getFreeUnitPackage(99999L);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("99999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.FREE_UNIT_PACKAGE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getAllowedRatePlans_notAvailable() {
        try {
            adapter.getAllowedRatePlansFromContract("4836067", 'P');
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).containsIgnoringCase("Not available");
            return;
        }
        fail("No exception");
    }

    @Test
    public void getAllowedRatePlansByRp_ok() {
        List<BscsRatePlan> ratePlans = adapter.getAllowedRatePlansByRp(12L);
        assertThat(ratePlans).isNotEmpty();
    }

    @Test
    public void getAllowedRatePlansByRp_badId() {
        List<BscsRatePlan> ratePlans = adapter.getAllowedRatePlansByRp(9999L);
        assertThat(ratePlans).isEmpty();
    }

    @Test
    public void getFreeUnitPackages_withAssignment_ok() throws BscsInvalidFieldException {
        List<BscsFreeUnitPackage> freeUnitPackages = adapter.getFreeUnitPackages('C');
        assertThat(freeUnitPackages).isNotEmpty();
    }

    @Test
    public void getFreeUnitPackages_withAssignement_badAssignment() {
        try {
            adapter.getFreeUnitPackages('T');
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("T");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.ASSIGNEMENT_LEVEL);
            return;
        }
        fail("No exception");
    }

}
