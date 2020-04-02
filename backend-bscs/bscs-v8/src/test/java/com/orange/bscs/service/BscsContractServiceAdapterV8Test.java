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
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.model.BscsBalance;
import com.orange.bscs.model.BscsContract;
import com.orange.bscs.model.BscsContractFreeUnitAccount;
import com.orange.bscs.model.BscsContractInfo;
import com.orange.bscs.model.BscsContractResource;
import com.orange.bscs.model.BscsContractSearch;
import com.orange.bscs.model.BscsContractService;
import com.orange.bscs.model.businesspartner.EnumContractStatus;
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
public class BscsContractServiceAdapterV8Test {

    @Autowired
    private BscsContractServiceAdapter adapter;

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
    public void findLatestContractByMsisdn_ok() throws BscsInvalidFieldException {
        BscsContractSearch contract = adapter.findContractByMsisdn("699920391");
        assertThat(contract).isNotNull();
    }

    @Test
    public void findLatestContractByMsisdn_badMsisdnFormat() throws BscsInvalidFieldException {
        try {
            adapter.findContractByMsisdn("toto");
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("toto");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.MSISDN);
            return;
        }
        fail("No exception");
    }

    @Test
    public void findLatestContractByMsisdn_badMsisdn() throws BscsInvalidFieldException {
        try {
            adapter.findContractByMsisdn("123456");
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("123456");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.MSISDN);
            return;
        }
        fail("No exception");
    }

    @Test
    public void findContractByIccId_ok() throws BscsInvalidFieldException {
        BscsContractSearch contract = adapter.findContractByIccId("89237020906101205231");
        assertThat(contract).isNotNull();
    }

    @Test
    public void findContractByIccId_okWithOnHold() throws BscsInvalidFieldException {
        BscsContractSearch contract = adapter.findContractByIccId("89237021108107199885");
        assertThat(contract).isNotNull();
        assertThat(contract.getId()).isEqualTo("28210733");
    }

    @Test
    public void findContractByIccId_badIccId() {
        try {
            adapter.findContractByIccId("toto");
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("toto");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.STORAGE_MEDIUM);
            return;
        }
        fail("No exception");
    }

    @Test
    public void findContractByIccId_iccIdNotAttributed() {
        try {
            adapter.findContractByIccId("89237021109100457148");
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("89237021109100457148");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.STORAGE_MEDIUM);
            return;
        }
        fail("No exception");
    }

    @Test
    public void findContractsByPartyId_ok() {
        List<BscsContractSearch> contracts = adapter.findContractsByPartyId(16320440L);
        assertThat(contracts).hasSize(7);
    }

    @Test
    public void findContractsByPartyId_badParty() {
        List<BscsContractSearch> contracts = adapter.findContractsByPartyId(12L);
        assertThat(contracts).isEmpty();
    }

    @Test
    public void findContractsByPartyId_partyWithoutContract() {
        List<BscsContractSearch> contracts = adapter.findContractsByPartyId(28389528L);
        assertThat(contracts).isEmpty();
    }

    @Test
    public void findContractById_ok() throws BscsInvalidFieldException, BscsInvalidIdException {
        BscsContractSearch contract = adapter.findContractById("4836067");
        assertThat(contract).isNotNull();
    }

    @Test
    public void findContractById_unknownId() {
        try {
            adapter.findContractById("1");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("1");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getContract_ok() throws BscsInvalidFieldException, BscsInvalidIdException {
        BscsContract contract = adapter.getContract("4836067");
        assertThat(contract).isNotNull();
    }

    @Test
    public void getContract_unknownId() {
        try {
            adapter.getContract("1");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("1");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getContractLong_ok() throws BscsInvalidFieldException, BscsInvalidIdException {
        BscsContract contract = adapter.getContract(4836067L);
        assertThat(contract).isNotNull();
    }

    @Test
    public void getContractLong_unknownId() {
        try {
            adapter.getContract(1L);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("1");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContract_ok() throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException {
        BscsContract contract = bscsObjectFactory.createBscsContract();
        contract.setId("1007");
        adapter.writeContract(contract, true);
    }

    @Test
    public void writeContract_unknownId() throws BscsInvalidFieldException, BscsPendingException {
        try {
            BscsContract contract = bscsObjectFactory.createBscsContract();
            contract.setId("1");
            adapter.writeContract(contract, true);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("1");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContract_statusAlreadySet()
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException {
        try {
            BscsContract contract = bscsObjectFactory.createBscsContract();
            contract.setId("1007");
            contract.setStatus(EnumContractStatus.ACTIVATED);
            adapter.writeContract(contract, true);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.STATUS);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContract_statusAlreadySetWithReason()
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException {
        try {
            BscsContract contract = bscsObjectFactory.createBscsContract();
            contract.setId("1007");
            contract.setStatus(EnumContractStatus.ACTIVATED);
            contract.setReasonChangeStatus(2L);
            adapter.writeContract(contract, true);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.STATUS);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContract_unknownReason() throws BscsInvalidIdException, BscsPendingException {
        try {
            BscsContract contract = bscsObjectFactory.createBscsContract();
            contract.setId("1007");
            contract.setStatus(EnumContractStatus.SUSPENDED);
            contract.setReasonChangeStatus(999L);
            adapter.writeContract(contract, true);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.REASON);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContract_badReason() throws BscsInvalidIdException, BscsPendingException {
        try {
            BscsContract contract = bscsObjectFactory.createBscsContract();
            contract.setId("1007");
            contract.setStatus(EnumContractStatus.SUSPENDED);
            contract.setReasonChangeStatus(1L);
            adapter.writeContract(contract, true);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.REASON);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getContractService_ok() throws BscsInvalidFieldException, BscsInvalidIdException {
        BscsContractService contractService = adapter.getContractService("2951090", "34");
        assertThat(contractService).isNotNull();
    }

    @Test
    public void getContractService_unknownContractId() throws BscsInvalidFieldException {
        try {
            adapter.getContractService("1", "34");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("1");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getContractService_unknownServiceId() throws BscsInvalidFieldException, BscsInvalidIdException {
        try {
            adapter.getContractService("2951090", "99999999");
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("99999999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getContractService_serviceNotInContract() throws BscsInvalidFieldException, BscsInvalidIdException {
        try {
            adapter.getContractService("1001", "4");
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("4");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getContractServiceLong_ok() throws BscsInvalidFieldException, BscsInvalidIdException {
        BscsContractService contractService = adapter.getContractService(2951090L, 34L);
        assertThat(contractService).isNotNull();
    }

    @Test
    public void getContractServiceLong_unknownContractId() throws BscsInvalidFieldException {
        try {
            adapter.getContractService(1L, 34L);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("1");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getContractServiceLong_unknownServiceId() throws BscsInvalidFieldException, BscsInvalidIdException {
        try {
            adapter.getContractService(2951090L, 99999999L);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("99999999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getContractServiceLong_serviceNotInContract() throws BscsInvalidFieldException, BscsInvalidIdException {
        try {
            adapter.getContractService(1001L, 4L);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("4");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getServicesByContractIdAndServicePackageId_ok() {
        List<BscsContractService> services = adapter.getServicesByContractIdAndServicePackageId("2951090", "44");
        assertThat(services).isNotEmpty();
    }

    @Test
    public void getServicesByContractIdAndServicePackageId_badContract() {
        List<BscsContractService> services = adapter.getServicesByContractIdAndServicePackageId("1", "44");
        assertThat(services).isEmpty();
    }

    @Test
    public void getServicesByContractIdAndServicePackageId_badSP() {
        List<BscsContractService> services = adapter.getServicesByContractIdAndServicePackageId("2951090", "9999999");
        assertThat(services).isEmpty();
    }

    @Test
    public void getServicesByContractId_ok() {
        List<BscsContractService> services = adapter.getServicesByContractId(2951090L);
        assertThat(services).isNotEmpty();
    }

    @Test
    public void getServicesByContractId_badContract() {
        List<BscsContractService> services = adapter.getServicesByContractId(1L);
        assertThat(services).isEmpty();
    }

    @Test
    public void addContractService_ok()
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        final BscsContractService contractService = bscsObjectFactory.createBscsContractService();
        contractService.setCode("337");
        contractService.setServicePackageCode("28");
        contractService.setProfileId(0L);
        adapter.addContractService("2951090", contractService, false);
    }

    @Test
    public void addContractService_badContractId()
            throws BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractService contractService = bscsObjectFactory.createBscsContractService();
            contractService.setCode("337");
            contractService.setServicePackageCode("28");
            contractService.setProfileId(0L);
            adapter.addContractService("9999999", contractService, false);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("9999999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void addContractService_badServiceId()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractService contractService = bscsObjectFactory.createBscsContractService();
            contractService.setCode("9999999");
            contractService.setServicePackageCode("28");
            contractService.setProfileId(0L);
            adapter.addContractService("2951090", contractService, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("9999999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void addContractService_badSpId()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractService contractService = bscsObjectFactory.createBscsContractService();
            contractService.setCode("337");
            contractService.setServicePackageCode("9999999");
            contractService.setProfileId(0L);
            adapter.addContractService("2951090", contractService, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("9999999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_PACKAGE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void addContractService_serviceNotInSp()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractService contractService = bscsObjectFactory.createBscsContractService();
            contractService.setCode("4");
            contractService.setServicePackageCode("28");
            contractService.setProfileId(0L);
            adapter.addContractService("2951090", contractService, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("4");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void addContractService_spNotIncontract()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractService contractService = bscsObjectFactory.createBscsContractService();
            contractService.setCode("2");
            contractService.setServicePackageCode("17");
            contractService.setProfileId(0L);
            adapter.addContractService("2951090", contractService, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("17");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_PACKAGE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void addContractService_serviceAlreadyAssigned()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractService contractService = bscsObjectFactory.createBscsContractService();
            contractService.setCode("222");
            contractService.setServicePackageCode("42");
            contractService.setProfileId(0L);
            adapter.addContractService("2951090", contractService, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("222");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }
    
    @Test
    public void addContractService_suspended() throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException {
        try {
        final BscsContractService contractService = bscsObjectFactory.createBscsContractService();
            contractService.setCode("337");
            contractService.setServicePackageCode("28");
            contractService.setProfileId(0L);
            adapter.addContractService("4836045", contractService, false);
        } catch (BscsInvalidStateException e) {
            assertThat(e.getField()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }
    
    @Test
    public void addContractService_terminated() throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException {
        try {
            final BscsContractService contractService = bscsObjectFactory.createBscsContractService();
            contractService.setCode("337");
            contractService.setServicePackageCode("28");
            contractService.setProfileId(0L);
            adapter.addContractService("4836057", contractService, false);
        } catch (BscsInvalidStateException e) {
            assertThat(e.getField()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractService_ok()
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        final BscsContractService service = bscsObjectFactory.createBscsContractService();
        service.setCode("19");
        service.setProfileId(0L);
        service.setStatus(EnumContractStatus.DEACTIVATED);
        adapter.writeContractService("2951090", service, false);
    }

    @Test
    public void writeContractService_coreService()
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractService service = bscsObjectFactory.createBscsContractService();
            service.setCode("34");
            service.setProfileId(0L);
            service.setStatus(EnumContractStatus.DEACTIVATED);
            adapter.writeContractService("2951090", service, true);
        } catch (SOIException e) {
            // technical exception
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractService_badContractId()
            throws BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractService service = bscsObjectFactory.createBscsContractService();
            service.setCode("19");
            service.setProfileId(0L);
            service.setStatus(EnumContractStatus.DEACTIVATED);
            adapter.writeContractService("1", service, false);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("1");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractService_badServiceId()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractService service = bscsObjectFactory.createBscsContractService();
            service.setCode("9999999");
            service.setProfileId(0L);
            service.setStatus(EnumContractStatus.DEACTIVATED);
            adapter.writeContractService("2951090", service, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("9999999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractService_statusAlreadySet()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractService service = bscsObjectFactory.createBscsContractService();
            service.setCode("19");
            service.setProfileId(0L);
            service.setStatus(EnumContractStatus.ACTIVATED);
            adapter.writeContractService("2951090", service, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.STATUS);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractService_serviceNotInContract()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractService service = bscsObjectFactory.createBscsContractService();
            service.setCode("337");
            service.setProfileId(0L);
            service.setStatus(EnumContractStatus.ACTIVATED);
            adapter.writeContractService("2951090", service, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("337");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractService_suspended()
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractService service = bscsObjectFactory.createBscsContractService();
            service.setCode("19");
            service.setProfileId(0L);
            service.setStatus(EnumContractStatus.DEACTIVATED);
            adapter.writeContractService("4836045", service, false);
        } catch (BscsInvalidStateException e) {
            assertThat(e.getField()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractService_terminated()
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractService service = bscsObjectFactory.createBscsContractService();
            service.setCode("19");
            service.setProfileId(0L);
            service.setStatus(EnumContractStatus.DEACTIVATED);
            adapter.writeContractService("4836057", service, false);
        } catch (BscsInvalidStateException e) {
            assertThat(e.getField()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getContractInfo_ok() throws BscsInvalidIdException {
        BscsContractInfo contractInfo = adapter.getContractInfo(2951090L);
        assertThat(contractInfo).isNotNull();
    }

    @Test
    public void getContractInfo_badContractId() {
        try {
            BscsContractInfo contractInfo = adapter.getContractInfo(1L);
            assertThat(contractInfo).isNotNull();
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("1");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractInfo_ok() throws BscsInvalidIdException {
        final BscsContractInfo info = bscsObjectFactory.createBscsContractInfo();
        info.setContractNumericId(2951090L);
        adapter.writeContractInfo(info, true);
    }

    @Test
    public void writeContractInfo_badContractId() {
        try {
            final BscsContractInfo info = bscsObjectFactory.createBscsContractInfo();
            info.setContractNumericId(1L);
            adapter.writeContractInfo(info, true);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("1");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractInfo_terminated() throws BscsInvalidIdException {
        final BscsContractInfo info = bscsObjectFactory.createBscsContractInfo();
        info.setContractNumericId(4836057L);
        adapter.writeContractInfo(info, true);
    }

    @Test
    public void getBalances_ok() {
        List<BscsBalance> balances = adapter.getBalances("2951090");
        // no balances found
        assertThat(balances).isEmpty();
    }

    @Test
    public void getBalances_noBalance() {
        List<BscsBalance> balances = adapter.getBalances("2951090");
        assertThat(balances).isEmpty();
    }

    @Test
    public void getBalances_badContract() {
        List<BscsBalance> balances = adapter.getBalances("1");
        assertThat(balances).isEmpty();
    }

    @Test
    public void getFreeUnits_ok() {
        List<BscsContractFreeUnitAccount> freeUnits = adapter.getFreeUnits("2949531");
        assertThat(freeUnits).isNotEmpty();
    }

    @Test
    public void getFreeUnits_noBalance() {
        List<BscsContractFreeUnitAccount> freeUnits = adapter.getFreeUnits("2951090");
        assertThat(freeUnits).isEmpty();
    }

    @Test
    public void getFreeUnits_badContract() {
        List<BscsContractFreeUnitAccount> freeUnits = adapter.getFreeUnits("1");
        assertThat(freeUnits).isEmpty();
    }

    @Test
    public void replaceContractResource_ok()
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        final BscsContractResource requestResource = bscsObjectFactory.createBscsContractResource();
        requestResource.setType(2);
        requestResource.setOldNumber("89624020305101009421");
        requestResource.setNewId(11676343L);
        requestResource.setContractId("2951090");
        adapter.replaceContractResource(requestResource, false);
    }

    @Test
    public void replaceContractResource_badContractId()
            throws BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractResource requestResource = bscsObjectFactory.createBscsContractResource();
            requestResource.setType(2);
            requestResource.setOldNumber("89624020305101009421");
            requestResource.setNewId(11676343L);
            requestResource.setContractId("1");
            adapter.replaceContractResource(requestResource, false);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("1");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void replaceContractResource_badOldIcc()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractResource requestResource = bscsObjectFactory.createBscsContractResource();
            requestResource.setType(2);
            requestResource.setOldNumber("bad");
            requestResource.setNewId(11676343L);
            requestResource.setContractId("2951090");
            adapter.replaceContractResource(requestResource, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.RESOURCE_NUM);
            return;
        }
        fail("No exception");
    }

    @Test
    public void replaceContractResource_oldIccNotAssignedToContract()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractResource requestResource = bscsObjectFactory.createBscsContractResource();
            requestResource.setType(2);
            requestResource.setOldNumber("89237021402390036627");
            requestResource.setNewId(11676343L);
            requestResource.setContractId("2951090");
            adapter.replaceContractResource(requestResource, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.RESOURCE_NUM);
            return;
        }
        fail("No exception");
    }

    @Test
    public void replaceContractResource_badNewId()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractResource requestResource = bscsObjectFactory.createBscsContractResource();
            requestResource.setType(2);
            requestResource.setOldNumber("89624020305101009421");
            requestResource.setNewId(1L);
            requestResource.setContractId("2951090");
            adapter.replaceContractResource(requestResource, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("1");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.RESOURCE_NUM);
            return;
        }
        fail("No exception");
    }

    @Test
    public void replaceContractResource_newIdAlreadyAssigned()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractResource requestResource = bscsObjectFactory.createBscsContractResource();
            requestResource.setType(2);
            requestResource.setOldNumber("89624020305101009421");
            requestResource.setNewId(23837977L);
            requestResource.setContractId("2951090");
            adapter.replaceContractResource(requestResource, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.RESOURCE_NUM);
            return;
        }
        fail("No exception");
    }

    @Test
    public void replaceContractResource_suspended()
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException {
        try {
            final BscsContractResource requestResource = bscsObjectFactory.createBscsContractResource();
            requestResource.setType(2);
            requestResource.setOldNumber("89624020305101009421");
            requestResource.setNewId(11676343L);
            requestResource.setContractId("4836045");
            adapter.replaceContractResource(requestResource, false);
        } catch (BscsInvalidStateException e) {
            assertThat(e.getField()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void replaceContractResource_terminated()
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException {
        try {
            final BscsContractResource requestResource = bscsObjectFactory.createBscsContractResource();
            requestResource.setType(2);
            requestResource.setOldNumber("89624020305101009421");
            requestResource.setNewId(11676343L);
            requestResource.setContractId("4836057");
            adapter.replaceContractResource(requestResource, false);
        } catch (BscsInvalidStateException e) {
            assertThat(e.getField()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void findContractsByMsisdn_ok() {
        List<BscsContractSearch> contract = adapter.findContractsByMsisdn("699920391");
        assertThat(contract).hasSize(1);
    }

    @Test
    public void findContractsByMsisdn_okWithStar() {
        List<BscsContractSearch> contract = adapter.findContractsByMsisdn("6999203*");
        assertThat(contract).hasSize(3);
    }

    @Test
    public void findContractsByMsisdn_badMsisdn() {
        List<BscsContractSearch> contract = adapter.findContractsByMsisdn("12345");
        assertThat(contract).isEmpty();
        ;
    }

    @Test
    public void findContractsByIccId() {
        List<BscsContractSearch> contract = adapter.findContractsByIccId("89237020906101205231");
        assertThat(contract).hasSize(1);
    }

    @Test
    public void findContractsByIccId_okWithStar() {
        List<BscsContractSearch> contract = adapter.findContractsByIccId("892370209061012052*");
        assertThat(contract).hasSize(4);
    }

    @Test
    public void findContractsByIccId_badMsisdn() {
        List<BscsContractSearch> contract = adapter.findContractsByIccId("12345");
        assertThat(contract).isEmpty();
    }

    @Test
    public void changeRatePlan_badContractId()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException, BscsInvalidFieldException {
        try {
            adapter.changeRatePlan("1", "31", false);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("1");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void changeRatePlan_badRpId()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException, BscsInvalidFieldException {
        try {
            adapter.changeRatePlan("2950004", "999", false);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.RATE_PLAN_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void changeRatePlan_spNotInRp()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException, BscsInvalidFieldException {
        try {
            adapter.changeRatePlan("2950004", "31", false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("31");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.RATE_PLAN_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void changeRatePlan_noChange()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException, BscsInvalidFieldException {
        try {
            adapter.changeRatePlan("2950004", "29", false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("29");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.RATE_PLAN_ID);
            return;
        }
        fail("No exception");
    }


}
