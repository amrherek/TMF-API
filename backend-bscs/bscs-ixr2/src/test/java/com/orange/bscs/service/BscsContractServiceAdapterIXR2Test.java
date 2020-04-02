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
public class BscsContractServiceAdapterIXR2Test {

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
        BscsContractSearch contract = adapter.findContractByMsisdn("555706910208");
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
        BscsContractSearch contract = adapter.findContractByIccId("895550591210002288");
        assertThat(contract).isNotNull();
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
            adapter.findContractByIccId("895550591210009051");
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("895550591210009051");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.STORAGE_MEDIUM);
            return;
        }
        fail("No exception");
    }

    @Test
    public void findContractsByPartyId_ok() {
        List<BscsContractSearch> contracts = adapter.findContractsByPartyId(404L);
        assertThat(contracts).hasSize(2);
    }

    @Test
    public void findContractsByPartyId_badParty() {
        List<BscsContractSearch> contracts = adapter.findContractsByPartyId(12L);
        assertThat(contracts).isEmpty();
    }

    @Test
    public void findContractsByPartyId_partyWithoutContract() {
        List<BscsContractSearch> contracts = adapter.findContractsByPartyId(1510L);
        assertThat(contracts).isEmpty();
    }

    @Test
    public void findContractById_ok() throws BscsInvalidFieldException, BscsInvalidIdException {
        BscsContractSearch contract = adapter.findContractById("CONTR0000000481");
        assertThat(contract).isNotNull();
    }

    @Test
    public void findContractById_unknownId() {
        try {
            adapter.findContractById("UNKNOWN");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("UNKNOWN");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getContract_ok() throws BscsInvalidFieldException, BscsInvalidIdException {
        BscsContract contract = adapter.getContract("CONTR0000000481");
        assertThat(contract).isNotNull();
    }

    @Test
    public void getContract_unknownId() {
        try {
            adapter.getContract("UNKNOWN");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("UNKNOWN");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getContractLong_ok() throws BscsInvalidFieldException, BscsInvalidIdException {
        BscsContract contract = adapter.getContract(449L);
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
        contract.setId("CONTR0000001396");
        adapter.writeContract(contract, true);
    }

    @Test
    public void writeContract_unknownId() throws BscsInvalidFieldException, BscsPendingException {
        try {
            BscsContract contract = bscsObjectFactory.createBscsContract();
            contract.setId("UNKNOWN");
            adapter.writeContract(contract, true);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("UNKNOWN");
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
            contract.setId("CONTR0000001396");
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
            contract.setId("CONTR0000001396");
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
            contract.setId("CONTR0000001396");
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
            contract.setId("CONTR0000001396");
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
        BscsContractService contractService = adapter.getContractService("CONTR0000001640", "HYBTP");
        assertThat(contractService).isNotNull();
    }

    @Test
    public void getContractService_unknownContractId() throws BscsInvalidFieldException {
        try {
            adapter.getContractService("CONTR000000", "HYBTP");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("CONTR000000");
            // we do not known which one is the bad id
            assertThat(e.getName()).isNull();
            return;
        }
        fail("No exception");
    }

    @Test
    public void getContractService_unknownServiceId() throws BscsInvalidFieldException, BscsInvalidIdException {
        try {
            adapter.getContractService("CONTR0000001640", "toto");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("toto");
            // we do not known which one is the bad id
            assertThat(e.getName()).isNull();
            return;
        }
        fail("No exception");
    }

    @Test
    public void getContractService_serviceNotInContract() throws BscsInvalidFieldException, BscsInvalidIdException {
        try {
            adapter.getContractService("CONTR0000001623", "MOBIT");
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("MOBIT");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getContractServiceLong_ok() throws BscsInvalidFieldException, BscsInvalidIdException {
        BscsContractService contractService = adapter.getContractService(1608L, 275L);
        assertThat(contractService).isNotNull();
    }

    @Test
    public void getContractServiceLong_unknownContractId() throws BscsInvalidFieldException {
        try {
            adapter.getContractService(1L, 275L);
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
            adapter.getContractService(1608L, 99999999L);
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
            adapter.getContractService(1608L, 4L);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("4");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getServicesByContractIdAndServicePackageId_ok() {
        List<BscsContractService> services = adapter.getServicesByContractIdAndServicePackageId("CONTR0000001640",
                "HYBSE");
        assertThat(services).isNotEmpty();
    }

    @Test
    public void getServicesByContractIdAndServicePackageId_badContract() {
        List<BscsContractService> services = adapter.getServicesByContractIdAndServicePackageId("UNKNOWN", "HYBSE");
        assertThat(services).isEmpty();
    }

    @Test
    public void getServicesByContractIdAndServicePackageId_badSP() {
        List<BscsContractService> services = adapter.getServicesByContractIdAndServicePackageId("CONTR0000001640",
                "toto");
        assertThat(services).isEmpty();
    }

    @Test
    public void getServicesByContractId_ok() {
        List<BscsContractService> services = adapter.getServicesByContractId(1608L);
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
        contractService.setCode("MVS");
        contractService.setServicePackageCode("OP001");
        contractService.setProfileId(0L);
        adapter.addContractService("CONTR0000001640", contractService, false);
    }

    @Test
    public void addContractService_badContractId()
            throws BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractService contractService = bscsObjectFactory.createBscsContractService();
            contractService.setCode("MVS");
            contractService.setServicePackageCode("OP001");
            contractService.setProfileId(0L);
            adapter.addContractService("9999999", contractService, false);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("9999999");
            // we do not known which one is the bad id
            assertThat(e.getName()).isNull();
            return;
        }
        fail("No exception");
    }

    @Test
    public void addContractService_badServiceId()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidFieldException, BscsInvalidStateException {
        try {
            final BscsContractService contractService = bscsObjectFactory.createBscsContractService();
            contractService.setCode("9999999");
            contractService.setServicePackageCode("OP001");
            contractService.setProfileId(0L);
            adapter.addContractService("CONTR0000001640", contractService, false);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("9999999");
            // we do not known which one is the bad id
            assertThat(e.getName()).isNull();
            return;
        }
        fail("No exception");
    }

    @Test
    public void addContractService_badSpId()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidFieldException, BscsInvalidStateException {
        try {
            final BscsContractService contractService = bscsObjectFactory.createBscsContractService();
            contractService.setCode("MVS");
            contractService.setServicePackageCode("9999999");
            contractService.setProfileId(0L);
            adapter.addContractService("CONTR0000001640", contractService, false);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("9999999");
            // we do not known which one is the bad id
            assertThat(e.getName()).isNull();
            return;
        }
        fail("No exception");
    }

    @Test
    public void addContractService_serviceNotInSp()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractService contractService = bscsObjectFactory.createBscsContractService();
            contractService.setCode("GTEL");
            contractService.setServicePackageCode("OP001");
            contractService.setProfileId(0L);
            adapter.addContractService("CONTR0000001640", contractService, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("GTEL");
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
            contractService.setCode("GTEL");
            contractService.setServicePackageCode("PPBSE");
            contractService.setProfileId(0L);
            adapter.addContractService("CONTR0000001640", contractService, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("PPBSE");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_PACKAGE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void addContractService_suspended()
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException {
        try {
            final BscsContractService contractService = bscsObjectFactory.createBscsContractService();
            contractService.setCode("MVS");
            contractService.setServicePackageCode("OP001");
            contractService.setProfileId(0L);
            adapter.addContractService("CONTR0000001634", contractService, false);
        } catch (BscsInvalidStateException e) {
            assertThat(e.getField()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void addContractService_terminated()
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException {
        try {
            final BscsContractService contractService = bscsObjectFactory.createBscsContractService();
            contractService.setCode("GBAOC");
            contractService.setServicePackageCode("OP001");
            contractService.setProfileId(0L);
            adapter.addContractService("CONTR0000001637", contractService, false);
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
        service.setCode("HYBTP");
        service.setProfileId(0L);
        service.setStatus(EnumContractStatus.DEACTIVATED);
        adapter.writeContractService("CONTR0000001640", service, false);
    }

    @Test
    public void writeContractService_badContractId()
            throws BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractService service = bscsObjectFactory.createBscsContractService();
            service.setCode("HYBTP");
            service.setProfileId(0L);
            service.setStatus(EnumContractStatus.DEACTIVATED);
            adapter.writeContractService("1", service, false);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("1");
            // we do not known which one is the bad id
            assertThat(e.getName()).isNull();
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractService_badServiceId()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidFieldException, BscsInvalidStateException {
        try {
            final BscsContractService service = bscsObjectFactory.createBscsContractService();
            service.setCode("9999999");
            service.setProfileId(0L);
            service.setStatus(EnumContractStatus.DEACTIVATED);
            adapter.writeContractService("CONTR0000001640", service, false);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("9999999");
            // we do not known which one is the bad id
            assertThat(e.getName()).isNull();
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractService_statusAlreadySet()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractService service = bscsObjectFactory.createBscsContractService();
            service.setCode("HYBTP");
            service.setProfileId(0L);
            service.setStatus(EnumContractStatus.ACTIVATED);
            adapter.writeContractService("CONTR0000001640", service, false);
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
            service.setCode("MVS");
            service.setProfileId(0L);
            service.setStatus(EnumContractStatus.ACTIVATED);
            adapter.writeContractService("CONTR0000001640", service, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("MVS");
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
            service.setCode("HYBTP");
            service.setProfileId(0L);
            service.setStatus(EnumContractStatus.ACTIVATED);
            adapter.writeContractService("CONTR0000001634", service, false);
        } catch (BscsInvalidStateException e) {
            assertThat(e.getField()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractService_suspended2()
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractService service = bscsObjectFactory.createBscsContractService();
            service.setCode("HYBTP");
            service.setProfileId(0L);
            service.setStatus(EnumContractStatus.DEACTIVATED);
            adapter.writeContractService("CONTR0000001634", service, false);
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
            service.setCode("HYBTP");
            service.setProfileId(0L);
            service.setStatus(EnumContractStatus.ACTIVATED);
            adapter.writeContractService("CONTR0000001637", service, false);
        } catch (BscsInvalidStateException e) {
            assertThat(e.getField()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getContractInfo_ok() throws BscsInvalidIdException {
        BscsContractInfo contractInfo = adapter.getContractInfo(449L);
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
        info.setContractNumericId(449L);
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
    public void getBalances_ok() {
        List<BscsBalance> balances = adapter.getBalances("CONTR0000000484");
        assertThat(balances).isNotEmpty();
    }

    @Test
    public void getBalances_noBalance() {
        List<BscsBalance> balances = adapter.getBalances("CONTR0000001640");
        assertThat(balances).isEmpty();
    }

    @Test
    public void getBalances_badContract() {
        List<BscsBalance> balances = adapter.getBalances("1");
        assertThat(balances).isEmpty();
    }

    @Test
    public void getFreeUnits_ok() {
        List<BscsContractFreeUnitAccount> freeUnits = adapter.getFreeUnits("CONTR0000000151");
        assertThat(freeUnits).isNotEmpty();
    }

    @Test
    public void getFreeUnits_noBalance() {
        List<BscsContractFreeUnitAccount> freeUnits = adapter.getFreeUnits("CONTR0000001640");
        assertThat(freeUnits).isEmpty();
    }

    @Test
    public void getFreeUnits_badContract() {
        List<BscsContractFreeUnitAccount> freeUnits = adapter.getFreeUnits("UNKNOWN");
        assertThat(freeUnits).isEmpty();
    }

    @Test
    public void replaceContractResource_ok()
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        final BscsContractResource requestResource = bscsObjectFactory.createBscsContractResource();
        requestResource.setType(2);
        requestResource.setOldNumber("895550591210006669");
        requestResource.setNewId(1223L);
        requestResource.setContractId("CONTR0000001640");
        adapter.replaceContractResource(requestResource, false);
    }

    @Test
    public void replaceContractResource_badContractId()
            throws BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractResource requestResource = bscsObjectFactory.createBscsContractResource();
            requestResource.setType(2);
            requestResource.setOldNumber("895550591210006669");
            requestResource.setNewId(1223L);
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
            requestResource.setNewId(1223L);
            requestResource.setContractId("CONTR0000001640");
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
            requestResource.setOldNumber("895550591210002197");
            requestResource.setNewId(1223L);
            requestResource.setContractId("CONTR0000001640");
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
            requestResource.setOldNumber("895550591210006669");
            requestResource.setNewId(1L);
            requestResource.setContractId("CONTR0000001640");
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
            requestResource.setOldNumber("895550591210006669");
            requestResource.setNewId(419L); // 895550591210002197
            requestResource.setContractId("CONTR0000001640");
            adapter.replaceContractResource(requestResource, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("895550591210002197");
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
            requestResource.setOldNumber("895550591210006669");
            requestResource.setNewId(1223L);
            requestResource.setContractId("CONTR0000001634");
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
            requestResource.setOldNumber("895550591210006669");
            requestResource.setNewId(1223L);
            requestResource.setContractId("CONTR0000001637");
            adapter.replaceContractResource(requestResource, false);
        } catch (BscsInvalidStateException e) {
            assertThat(e.getField()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void findContractsByMsisdn_ok() {
        List<BscsContractSearch> contract = adapter.findContractsByMsisdn("555706910208");
        assertThat(contract).hasSize(1);
    }

    @Test
    public void findContractsByMsisdn_okWithStar() {
        List<BscsContractSearch> contract = adapter.findContractsByMsisdn("55570691020*");
        assertThat(contract).hasSize(10);
    }

    @Test
    public void findContractsByMsisdn_badMsisdn() {
        List<BscsContractSearch> contract = adapter.findContractsByMsisdn("12345");
        assertThat(contract).isEmpty();
        ;
    }

    @Test
    public void findContractsByIccId() {
        List<BscsContractSearch> contract = adapter.findContractsByIccId("895550591210002288");
        assertThat(contract).hasSize(1);
    }

    @Test
    public void findContractsByIccId_okWithStar() {
        List<BscsContractSearch> contract = adapter.findContractsByIccId("8955505912100022*");
        assertThat(contract).hasSize(10);
    }

    @Test
    public void findContractsByIccId_badMsisdn() {
        List<BscsContractSearch> contract = adapter.findContractsByIccId("12345");
        assertThat(contract).isEmpty();
    }

    @Test
    public void changeRatePlan_ok()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException, BscsInvalidFieldException {
        adapter.changeRatePlan("CONTR0000000372", "GSBOY", false);
    }

    @Test
    public void changeRatePlan_badContractId()
            throws BscsPendingException, BscsInvalidStateException, BscsInvalidFieldException {
        try {
            adapter.changeRatePlan("UNKNOWN", "GSBOY", false);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("UNKNOWN");
            // we do not known which one is the bad id
            assertThat(e.getName()).isNull();
            return;
        }
        fail("No exception");
    }

    @Test
    public void changeRatePlan_badRpId()
            throws BscsPendingException, BscsInvalidStateException, BscsInvalidFieldException {
        try {
            adapter.changeRatePlan("CONTR0000000372", "BAD", false);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("BAD");
            // we do not known which one is the bad id
            assertThat(e.getName()).isNull();
            return;
        }
        fail("No exception");
    }

    @Test
    public void changeRatePlan_spNotInRp()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException, BscsInvalidFieldException {
        try {
            adapter.changeRatePlan("CONTR0000000372", "GSMSR", false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("GSMSR");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.RATE_PLAN_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void changeRatePlan_suspended()
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException {
        try {
            adapter.changeRatePlan("CONTR0000000450", "GSM2", false);
        } catch (BscsInvalidStateException e) {
            assertThat(e.getField()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void changeRatePlan_terminated()
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException {
        try {
            adapter.changeRatePlan("CONTR0000000341", "GSBOY", false);
        } catch (BscsInvalidStateException e) {
            assertThat(e.getField()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void changeRatePlan_noChange()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException, BscsInvalidFieldException {
        try {
            adapter.changeRatePlan("CONTR0000000372", "GSBOP", false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("GSBOP");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.RATE_PLAN_ID);
            return;
        }
        fail("No exception");
    }

}
