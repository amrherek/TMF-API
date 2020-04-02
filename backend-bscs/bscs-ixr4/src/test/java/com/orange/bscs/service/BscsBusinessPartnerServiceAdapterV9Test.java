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
public class BscsBusinessPartnerServiceAdapterV9Test {

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
        customerSearchParams.setLastName("Mholo");
        customerSearchParams.setFlagCase(false);
        List<BscsCustomerSearch> findCustomersByCriteria = adapter.findCustomersByCriteria(customerSearchParams);
        assertThat(findCustomersByCriteria).isNotEmpty();
    }

    @Test
    public void findCustomersByCriteria_okByEMail() {
        final BscsCustomerSearchCriteria customerSearchParams = bscsObjectFactory.createBscsCustomerSearchCriteria();
        customerSearchParams.setEmail("rantswanengk@africanalliance.bw");
        List<BscsCustomerSearch> findCustomersByCriteria = adapter.findCustomersByCriteria(customerSearchParams);
        assertThat(findCustomersByCriteria).isNotEmpty();
    }

    @Test
    public void getCustomer_unknownId() {
        try {
            adapter.getCustomer("UNKNOWN");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("UNKNOWN");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CUSTOMER_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getCustomer_ok() throws BscsInvalidIdException {
        BscsCustomer customer = adapter.getCustomer("CUST0000084286");
        assertThat(customer).isNotNull();
    }

    @Test
    public void getAddress_unknownId() {
        try {
            adapter.getAddress("UNKNOWN", EnumAddressRole.BILL);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("UNKNOWN");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CUSTOMER_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getAddress_ok() throws BscsInvalidIdException {
        BscsAddress customer = adapter.getAddress("CUST0000084286", EnumAddressRole.BILL);
        assertThat(customer).isNotNull();
    }

    @Test
    public void writeAddress_ok() throws BscsInvalidIdException, BscsInvalidFieldException {
        BscsAddress address = bscsObjectFactory.createBscsAddress();
        address.setCustomerId("CUST0000000658");
        address.setSequenceNumber(1L);
        adapter.writeAddress(address, true);
    }

    @Test
    public void writeAddress_unknownId() throws BscsInvalidFieldException {
        try {
            BscsAddress address = bscsObjectFactory.createBscsAddress();
            address.setCustomerId("UNKNOWN");
            address.setSequenceNumber(1L);
            adapter.writeAddress(address, true);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("UNKNOWN");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CUSTOMER_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeAddress_unknownTitle() throws BscsInvalidIdException {
        try {
            BscsAddress address = bscsObjectFactory.createBscsAddress();
            address.setCustomerId("CUST0000084286");
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
            address.setCustomerId("CUST0000084286");
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
            address.setCustomerId("CUST0000084286");
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
            address.setCustomerId("CUST0000084286");
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
        try {
            BscsAddress address = bscsObjectFactory.createBscsAddress();
            address.setCustomerId("CUST0000000658");
            address.setSequenceNumber(1L);
            address.setCountryId(73L); // France
            address.setPostalCode("12");
            adapter.writeAddress(address, true);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("12");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.POSTAL_CODE);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getCustomerGroups_ok() {
        List<BscsCustomerGroup> customerGroups = adapter.getCustomerGroups();
        assertThat(customerGroups).isNotEmpty();
    }

    @Test
    public void getCustomerGroup_ok() throws BscsInvalidIdException {
        BscsCustomerGroup customerGroup = adapter.getCustomerGroup("TEL BUREAU");
        assertThat(customerGroup).isNotNull();
    }

    @Test
    public void getCustomerGroup_unknownId() {
        try {
            adapter.getCustomerGroup("UNKNOWN");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("UNKNOWN");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CUSTOMER_GROUP_CODE);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getCurrentPaymentArrangement_ok() throws BscsInvalidIdException {
        BscsPaymentArrangement payment = adapter.getCurrentPaymentArrangement("CUST0000000658");
        assertThat(payment).isNotNull();
    }

    @Test
    public void getCurrentPaymentArrangement_unknownCustomerId() {
        try {
            adapter.getCurrentPaymentArrangement("UNKNOWN");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("UNKNOWN");
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
