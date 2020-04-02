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
import com.orange.bscs.model.BscsAddress;
import com.orange.bscs.model.BscsCustomer;
import com.orange.bscs.model.BscsCustomerGroup;
import com.orange.bscs.model.BscsCustomerSearch;
import com.orange.bscs.model.BscsIdentificationDocumentType;
import com.orange.bscs.model.BscsPaymentArrangement;
import com.orange.bscs.model.BscsTitle;
import com.orange.bscs.model.businesspartner.EnumAddressRole;
import com.orange.bscs.model.criteria.BscsCustomerSearchCriteria;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BscsTestConfig.class })
@ActiveProfiles("corba")
@Category(CorbaBscsTest.class)
public class BscsBusinessPartnerServiceAdapterV8Test {

    @Autowired
    private BscsBusinessPartnerServiceAdapter adapter;

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
    public void findCustomersByCriteria_okByName() {
        final BscsCustomerSearchCriteria customerSearchParams = bscsObjectFactory.createBscsCustomerSearchCriteria();
        customerSearchParams.setLastName("Kanjoh");
        customerSearchParams.setFlagCase(false);
        List<BscsCustomerSearch> findCustomersByCriteria = adapter.findCustomersByCriteria(customerSearchParams);
        assertThat(findCustomersByCriteria).isNotEmpty();
    }

    @Test
    public void findCustomersByCriteria_okByEMail() {
        final BscsCustomerSearchCriteria customerSearchParams = bscsObjectFactory.createBscsCustomerSearchCriteria();
        customerSearchParams.setEmail("lloicsole@yahoo.fr");
        List<BscsCustomerSearch> findCustomersByCriteria = adapter.findCustomersByCriteria(customerSearchParams);
        assertThat(findCustomersByCriteria).isNotEmpty();
    }

    @Test
    public void getCustomer_unknownId() {
        try {
            adapter.getCustomer("10");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("10");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CUSTOMER_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getCustomer_ok() throws BscsInvalidIdException {
        BscsCustomer customer = adapter.getCustomer("1001");
        assertThat(customer).isNotNull();
    }

    @Test
    public void getAddress_unknownId() {
        try {
            adapter.getAddress("10", EnumAddressRole.BILL);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("10");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CUSTOMER_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getAddress_ok() throws BscsInvalidIdException {
        BscsAddress customer = adapter.getAddress("1001", EnumAddressRole.BILL);
        assertThat(customer).isNotNull();
    }

    @Test
    public void writeAddress_ok() throws BscsInvalidIdException, BscsInvalidFieldException {
        BscsAddress address = bscsObjectFactory.createBscsAddress();
        address.setCustomerId("8181196");
        address.setSequenceNumber(1L);
        adapter.writeAddress(address, true);
    }

    @Test
    public void writeAddress_unknownId() throws BscsInvalidFieldException {
        try {
            BscsAddress address = bscsObjectFactory.createBscsAddress();
            address.setCustomerId("10");
            address.setSequenceNumber(1L);
            adapter.writeAddress(address, true);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("10");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CUSTOMER_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeAddress_unknownTitle() throws BscsInvalidIdException {
        try {
            BscsAddress address = bscsObjectFactory.createBscsAddress();
            address.setCustomerId("8181196");
            address.setSequenceNumber(1L);
            address.setTitleId(0L);
            adapter.writeAddress(address, true);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("0");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.TITLE);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeAddress_unknwonIdentificationType() throws BscsInvalidIdException {
        try {
            BscsAddress address = bscsObjectFactory.createBscsAddress();
            address.setCustomerId("8181196");
            address.setSequenceNumber(1L);
            address.setNationalIdTypeCode(0L);
            adapter.writeAddress(address, true);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("0");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.IDENTIFICATION_TYPE);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeAddress_badEmail() throws BscsInvalidIdException {
        try {
            BscsAddress address = bscsObjectFactory.createBscsAddress();
            address.setCustomerId("8181196");
            address.setSequenceNumber(1L);
            address.setEmail("toto"); // no @
            adapter.writeAddress(address, true);
        } catch (BscsInvalidFieldException e) {
            // value is not given
            assertThat(e.getValue()).isNull();
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.EMAIL);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeAddress_incompatibleGenderAndTitle() throws BscsInvalidIdException {
        try {
            BscsAddress address = bscsObjectFactory.createBscsAddress();
            address.setCustomerId("8181196");
            address.setSequenceNumber(1L);
            address.setSex('F');
            address.setTitleId(1L); // M.
            adapter.writeAddress(address, true);
        } catch (BscsInvalidFieldException e) {
            // value is not given
            assertThat(e.getValue()).isNull();
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.TITLE);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeAddress_badPostCode() throws BscsInvalidIdException, BscsInvalidFieldException {
        // no check
        BscsAddress address = bscsObjectFactory.createBscsAddress();
        address.setCustomerId("8181196");
        address.setSequenceNumber(1L);
        address.setCountryId(73L); // France
        address.setPostalCode("12");
        adapter.writeAddress(address, true);
    }

    @Test
    public void getCustomerGroups_ok() {
        List<BscsCustomerGroup> customerGroups = adapter.getCustomerGroups();
        assertThat(customerGroups).isNotEmpty();
    }

    @Test
    public void getCustomerGroup_ok() throws BscsInvalidIdException {
        BscsCustomerGroup customerGroup = adapter.getCustomerGroup("20");
        assertThat(customerGroup).isNotNull();
    }

    @Test
    public void getCustomerGroup_unknownId() {
        try {
            adapter.getCustomerGroup("999999999");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("999999999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CUSTOMER_GROUP_CODE);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getCurrentPaymentArrangement_ok() throws BscsInvalidIdException {
        BscsPaymentArrangement payment = adapter.getCurrentPaymentArrangement("1001");
        assertThat(payment).isNotNull();
    }

    @Test
    public void getCurrentPaymentArrangement_unknownCustomerId() {
        try {
            adapter.getCurrentPaymentArrangement("1000");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("1000");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CUSTOMER_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getTitles_ok() throws BscsInvalidIdException {
        List<BscsTitle> titles = adapter.getTitles();
        assertThat(titles).isNotNull();
    }

    @Test
    public void getIdentificationDocumentTypes_ok() throws BscsInvalidIdException {
        List<BscsIdentificationDocumentType> identificationDocumentTypes = adapter.getIdentificationDocumentTypes();
        assertThat(identificationDocumentTypes).isNotNull();
    }

}
