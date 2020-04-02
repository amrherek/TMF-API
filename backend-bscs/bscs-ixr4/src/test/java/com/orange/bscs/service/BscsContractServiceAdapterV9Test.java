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
import com.orange.bscs.config.BscsTestConfig;
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
public class BscsContractServiceAdapterV9Test {

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
        BscsContractSearch contract = adapter.findContractByMsisdn("26772261176");
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
        BscsContractSearch contract = adapter.findContractByIccId("8926702081500095657F");
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
            adapter.findContractByIccId("8926702039800033273");
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("8926702039800033273");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.STORAGE_MEDIUM);
            return;
        }
        fail("No exception");
    }

    @Test
    public void findContractsByPartyId_ok() {
        List<BscsContractSearch> contracts = adapter.findContractsByPartyId(663891L);
        assertThat(contracts).hasSize(2);
    }

    @Test
    public void findContractsByPartyId_badParty() {
        List<BscsContractSearch> contracts = adapter.findContractsByPartyId(12L);
        assertThat(contracts).isEmpty();
    }

    @Test
    public void findContractsByPartyId_partyWithoutContract() {
        List<BscsContractSearch> contracts = adapter.findContractsByPartyId(2200653L);
        assertThat(contracts).isEmpty();
    }

    @Test
    public void findContractById_ok() throws BscsInvalidFieldException, BscsInvalidIdException {
        BscsContractSearch contract = adapter.findContractById("CONTR0000001055");
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
        BscsContract contract = adapter.getContract("CONTR0000001055");
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
        BscsContract contract = adapter.getContract(2061333L);
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
        contract.setId("CONTR0000001057");
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
            contract.setId("CONTR0000001057");
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
            contract.setId("CONTR0000001057");
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
            contract.setId("CONTR0000001057");
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
            contract.setId("CONTR0000001057");
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
        BscsContractService contractService = adapter.getContractService("CONTR0000000694", "SUB0");
        assertThat(contractService).isNotNull();
    }

    @Test
    public void getContractService_unknownContractId() throws BscsInvalidFieldException {
        try {
            adapter.getContractService("CONTR000000", "SUB0");
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
            adapter.getContractService("CONTR0000000694", "toto");
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
            adapter.getContractService("CONTR0000000694", "MINCO");
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("MINCO");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getContractServiceLong_ok() throws BscsInvalidFieldException, BscsInvalidIdException {
        BscsContractService contractService = adapter.getContractService(2060972L, 61L);
        assertThat(contractService).isNotNull();
    }

    @Test
    public void getContractServiceLong_unknownContractId() throws BscsInvalidFieldException {
        try {
            adapter.getContractService(1L, 61L);
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
            adapter.getContractService(2060972L, 99999999L);
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
            adapter.getContractService(2060972L, 4L);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("4");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getServicesByContractIdAndServicePackageId_ok() {
        List<BscsContractService> services = adapter.getServicesByContractIdAndServicePackageId("CONTR0000000694",
                "OMSP1");
        assertThat(services).isNotEmpty();
    }

    @Test
    public void getServicesByContractIdAndServicePackageId_badContract() {
        List<BscsContractService> services = adapter.getServicesByContractIdAndServicePackageId("UNKNOWN", "OMSP1");
        assertThat(services).isEmpty();
    }

    @Test
    public void getServicesByContractIdAndServicePackageId_badSP() {
        List<BscsContractService> services = adapter.getServicesByContractIdAndServicePackageId("CONTR0000000694",
                "toto");
        assertThat(services).isEmpty();
    }

    @Test
    public void getServicesByContractId_ok() {
        List<BscsContractService> services = adapter.getServicesByContractId(2060972L);
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
        contractService.setCode("CrLEC");
        contractService.setServicePackageCode("OPTSP");
        contractService.setProfileId(0L);
        adapter.addContractService("CONTR0000000694", contractService, false);
    }

    @Test
    public void addContractService_badContractId()
            throws BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractService contractService = bscsObjectFactory.createBscsContractService();
            contractService.setCode("CrLEC");
            contractService.setServicePackageCode("OPTSP");
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
            contractService.setServicePackageCode("OPTSP");
            contractService.setProfileId(0L);
            adapter.addContractService("CONTR0000000694", contractService, false);
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
            contractService.setCode("CrLEC");
            contractService.setServicePackageCode("9999999");
            contractService.setProfileId(0L);
            adapter.addContractService("CONTR0000000694", contractService, false);
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
            contractService.setCode("OpWKE");
            contractService.setServicePackageCode("OPTSP");
            contractService.setProfileId(0L);
            adapter.addContractService("CONTR0000000694", contractService, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("OpWKE");
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
            contractService.setCode("R4GLT");
            contractService.setServicePackageCode("OSASP");
            contractService.setProfileId(0L);
            adapter.addContractService("CONTR0000000694", contractService, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("OSASP");
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
            contractService.setCode("CrdLs");
            contractService.setServicePackageCode("OPTSP");
            contractService.setProfileId(0L);
            adapter.addContractService("CONTR0000000694", contractService, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("CrdLs");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void addContractService_suspended()
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException {
        try {
            final BscsContractService contractService = bscsObjectFactory.createBscsContractService();
            contractService.setCode("CrLEC");
            contractService.setServicePackageCode("OPTSP");
            contractService.setProfileId(0L);
            adapter.addContractService("CONT0000196011", contractService, false);
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
            contractService.setCode("CrLEC");
            contractService.setServicePackageCode("OPTSP");
            contractService.setProfileId(0L);
            adapter.addContractService("CONT0000147490", contractService, false);
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
        service.setCode("CrdLs");
        service.setProfileId(0L);
        service.setStatus(EnumContractStatus.DEACTIVATED);
        adapter.writeContractService("CONTR0000000694", service, false);
    }

    @Test
    public void writeContractService_badContractId()
            throws BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractService service = bscsObjectFactory.createBscsContractService();
            service.setCode("CrdLs");
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
            adapter.writeContractService("CONTR0000000694", service, false);
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
            service.setCode("CrdLs");
            service.setProfileId(0L);
            service.setStatus(EnumContractStatus.ACTIVATED);
            adapter.writeContractService("CONTR0000000694", service, false);
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
            service.setCode("CrLEC");
            service.setProfileId(0L);
            service.setStatus(EnumContractStatus.ACTIVATED);
            adapter.writeContractService("CONTR0000000694", service, true);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("CrLEC");
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
            service.setCode("CrdLs");
            service.setProfileId(0L);
            service.setStatus(EnumContractStatus.DEACTIVATED);
            adapter.writeContractService("CONT0000196011", service, false);
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
            service.setCode("CrdLs");
            service.setProfileId(0L);
            service.setStatus(EnumContractStatus.ACTIVATED);
            adapter.writeContractService("CONT0000196011", service, false);
        } catch (BscsInvalidStateException e) {
            assertThat(e.getField()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeContractService_terminated()
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        // no exception
        final BscsContractService service = bscsObjectFactory.createBscsContractService();
        service.setCode("CrdLs");
        service.setProfileId(0L);
        service.setStatus(EnumContractStatus.ACTIVATED);
        adapter.writeContractService("CONT0000147490", service, true);
    }

    @Test
    public void getContractInfo_ok() throws BscsInvalidIdException {
        BscsContractInfo contractInfo = adapter.getContractInfo(2061333L);
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
        info.setContractNumericId(2061333L);
        adapter.writeContractInfo(info, true);
    }

    @Test
    public void writeContractInfo_terminated() throws BscsInvalidIdException {
        final BscsContractInfo info = bscsObjectFactory.createBscsContractInfo();
        info.setContractNumericId(147490L);
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
        List<BscsBalance> balances = adapter.getBalances("CONTR0000001055");
        // no contract with balances found
        assertThat(balances).isEmpty();
    }

    @Test
    public void getBalances_noBalance() {
        List<BscsBalance> balances = adapter.getBalances("CONTR0000001055");
        assertThat(balances).isEmpty();
    }

    @Test
    public void getBalances_badContract() {
        List<BscsBalance> balances = adapter.getBalances("1");
        assertThat(balances).isEmpty();
    }

    @Test
    public void getFreeUnits_ok() {
        List<BscsContractFreeUnitAccount> freeUnits = adapter.getFreeUnits("CONT0000000156");
        assertThat(freeUnits).isNotEmpty();
    }

    @Test
    public void getFreeUnits_noFua() {
        List<BscsContractFreeUnitAccount> freeUnits = adapter.getFreeUnits("CONTR0000001055");
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
        requestResource.setOldNumber("8926702031401126713F");
        requestResource.setNewId(14160L);
        requestResource.setContractId("CONTR0000001055");
        adapter.replaceContractResource(requestResource, false);
    }

    @Test
    public void replaceContractResource_badContractId()
            throws BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractResource requestResource = bscsObjectFactory.createBscsContractResource();
            requestResource.setType(2);
            requestResource.setOldNumber("8926702031401126713F");
            requestResource.setNewId(14160L);
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
            requestResource.setNewId(14160L);
            requestResource.setContractId("CONTR0000001055");
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
            requestResource.setOldNumber("8926702039800029172");
            requestResource.setNewId(14160L);
            requestResource.setContractId("CONTR0000001055");
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
            requestResource.setOldNumber("8926702031401126713F");
            requestResource.setNewId(99999999L);
            requestResource.setContractId("CONTR0000001055");
            adapter.replaceContractResource(requestResource, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("99999999");
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
            requestResource.setOldNumber("8926702031401126713F");
            requestResource.setNewId(7320409L); // 8926702081500407852F
            requestResource.setContractId("CONTR0000001055");
            adapter.replaceContractResource(requestResource, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("8926702081500407852F");
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
            requestResource.setOldNumber("8926702011501654046F");
            requestResource.setNewId(14160L);
            requestResource.setContractId("CONT0000196011");
            adapter.replaceContractResource(requestResource, false);
        } catch (BscsInvalidStateException e) {
            assertThat(e.getField()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void replaceContractResource_terminated()
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            final BscsContractResource requestResource = bscsObjectFactory.createBscsContractResource();
            requestResource.setType(2);
            requestResource.setOldNumber("8926702061200423920F");
            requestResource.setNewId(14160L);
            requestResource.setContractId("CONT0000147490");
            adapter.replaceContractResource(requestResource, false);
        } catch (BscsInvalidStateException e) {
            assertThat(e.getField()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void findContractsByMsisdn_ok() {
        List<BscsContractSearch> contract = adapter.findContractsByMsisdn("26772261176");
        assertThat(contract).hasSize(1);
    }

    @Test
    public void findContractsByMsisdn_okWithStar() {
        List<BscsContractSearch> contract = adapter.findContractsByMsisdn("2677226117*");
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
        List<BscsContractSearch> contract = adapter.findContractsByIccId("8926702081500095657F");
        assertThat(contract).hasSize(1);
    }

    @Test
    public void findContractsByIccId_okWithStar() {
        List<BscsContractSearch> contract = adapter.findContractsByIccId("89267020815000956*");
        assertThat(contract).hasSize(7);
    }

    @Test
    public void findContractsByIccId_badMsisdn() {
        List<BscsContractSearch> contract = adapter.findContractsByIccId("12345");
        assertThat(contract).isEmpty();
    }

    @Test
    public void changeRatePlan_ok()
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException, BscsInvalidFieldException {
        adapter.changeRatePlan("CONT0000381545", "PROZE", false);
    }

    @Test
    public void changeRatePlan_badContractId()
            throws BscsPendingException, BscsInvalidStateException, BscsInvalidFieldException {
        try {
            adapter.changeRatePlan("UNKNOWN", "PROZE", false);
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
            adapter.changeRatePlan("CONT0000381545", "BAD", false);
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
        // no exceptions (services dropped, new added)
        adapter.changeRatePlan("CONTR0000001847", "PROZE", false);
    }

    @Test
    public void changeRatePlan_suspended()
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException {
        try {
            adapter.changeRatePlan("CONT0001319193", "PROZE", false);
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
            adapter.changeRatePlan("CONT0000588092", "PROZE", false);
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
            adapter.changeRatePlan("CONTR0000001847", "BB10T", false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("BB10T");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.RATE_PLAN_ID);
            return;
        }
        fail("No exception");
    }

}
