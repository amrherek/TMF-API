package com.orange.bscs.model.factory;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.model.BscsAddress;
import com.orange.bscs.model.BscsBalance;
import com.orange.bscs.model.BscsBillCycle;
import com.orange.bscs.model.BscsBillDocument;
import com.orange.bscs.model.BscsBillingAccount;
import com.orange.bscs.model.BscsBillingAccountSearch;
import com.orange.bscs.model.BscsBillingAccountVersion;
import com.orange.bscs.model.BscsBillingAssignment;
import com.orange.bscs.model.BscsBookingRequest;
import com.orange.bscs.model.BscsBusinessTransaction;
import com.orange.bscs.model.BscsBusinessTransactionSearch;
import com.orange.bscs.model.BscsCharges;
import com.orange.bscs.model.BscsContract;
import com.orange.bscs.model.BscsContractFreeUnitAccount;
import com.orange.bscs.model.BscsContractInfo;
import com.orange.bscs.model.BscsContractResource;
import com.orange.bscs.model.BscsContractSearch;
import com.orange.bscs.model.BscsContractService;
import com.orange.bscs.model.BscsContractServiceFromContract;
import com.orange.bscs.model.BscsContractServiceParameter;
import com.orange.bscs.model.BscsCountry;
import com.orange.bscs.model.BscsCustomer;
import com.orange.bscs.model.BscsCustomerGroup;
import com.orange.bscs.model.BscsCustomerInfo;
import com.orange.bscs.model.BscsCustomerSearch;
import com.orange.bscs.model.BscsDebitsByCredit;
import com.orange.bscs.model.BscsFinancialDocument;
import com.orange.bscs.model.BscsFinancialDocumentDetail;
import com.orange.bscs.model.BscsFinancialDocumentSearch;
import com.orange.bscs.model.BscsFinancialTransaction;
import com.orange.bscs.model.BscsFinancialTransactionDetail;
import com.orange.bscs.model.BscsFinancialTransactionSearch;
import com.orange.bscs.model.BscsFreeUnitPackage;
import com.orange.bscs.model.BscsIdentificationDocumentType;
import com.orange.bscs.model.BscsParameter;
import com.orange.bscs.model.BscsParameterValue;
import com.orange.bscs.model.BscsPayment;
import com.orange.bscs.model.BscsPaymentArrangement;
import com.orange.bscs.model.BscsPaymentMethod;
import com.orange.bscs.model.BscsRatePlan;
import com.orange.bscs.model.BscsReferencedTransactionSearch;
import com.orange.bscs.model.BscsService;
import com.orange.bscs.model.BscsServicePackage;
import com.orange.bscs.model.BscsStorageMedium;
import com.orange.bscs.model.BscsTitle;
import com.orange.bscs.model.BscsUsageDataRecord;
import com.orange.bscs.model.accounting.BSCSPaymentMethod;
import com.orange.bscs.model.billing.BSCSBillCycle;
import com.orange.bscs.model.billing.BSCSBillingAccount;
import com.orange.bscs.model.billing.BSCSBillingAccount.BAVersion;
import com.orange.bscs.model.billing.BSCSDocuments;
import com.orange.bscs.model.billing.BusinessTransaction;
import com.orange.bscs.model.businesspartner.BSCSAddress;
import com.orange.bscs.model.businesspartner.BSCSBillingAssignment;
import com.orange.bscs.model.businesspartner.BSCSCustomer;
import com.orange.bscs.model.businesspartner.BSCSPaymentArrangement;
import com.orange.bscs.model.contract.BSCSCharges;
import com.orange.bscs.model.contract.BSCSContract;
import com.orange.bscs.model.contract.BSCSContractFreeUnit;
import com.orange.bscs.model.contract.BSCSContractResource;
import com.orange.bscs.model.contract.BSCSContractService;
import com.orange.bscs.model.contract.BSCSContractServiceParameter;
import com.orange.bscs.model.contract.BSCSContractServiceParameter.ParamValue;
import com.orange.bscs.model.contract.BSCSFreeUnitPackage;
import com.orange.bscs.model.contract.BSCSParameter;
import com.orange.bscs.model.contract.BSCSService;
import com.orange.bscs.model.criteria.BscsBillingAssignmentSearchCriteria;
import com.orange.bscs.model.criteria.BscsBusinessTransactionSearchCriteria;
import com.orange.bscs.model.criteria.BscsContractSearchCriteria;
import com.orange.bscs.model.criteria.BscsContractServiceParameterSearchCriteria;
import com.orange.bscs.model.criteria.BscsContractServiceSearchCriteria;
import com.orange.bscs.model.criteria.BscsCustomerSearchCriteria;
import com.orange.bscs.model.criteria.BscsFinancialDocumentSearchCriteria;
import com.orange.bscs.model.criteria.BscsFinancialTransactionSearchCriteria;
import com.orange.bscs.model.criteria.BscsReferencedTransactionSearchCriteria;
import com.orange.bscs.model.criteria.BscsServicePackageSearchCriteria;
import com.orange.bscs.model.criteria.BscsServiceSearchCriteria;
import com.orange.bscs.model.criteria.BscsStorageMediumSearchCriteria;
import com.orange.bscs.model.criteria.BscsUsageDataRecordSearchCriteria;
import com.orange.bscs.model.product.BSCSRatePlan;
import com.orange.bscs.model.product.BSCSServicePackage;
import com.orange.bscs.model.resource.BSCSStorageMedium;
import com.orange.bscs.model.usagedata.BSCSUsageDataRecord;
import com.orange.bscs.model.usagedata.Balances;

public interface BscsObjectFactory {

    // model objects

    BscsContractSearch createBscsContractSearch(BSCSContract contract);

    BscsContract createBscsContract(BSCSContract contract);

    BscsContract createBscsContract();

    BscsRatePlan createBscsRatePlan(BSCSRatePlan ratePlan);

    BscsContractService createBscsContractService(BSCSContractService contractService);

    BscsContractService createBscsContractService();

    BscsContractServiceFromContract createBscsContractServiceFromContract(BSCSContractService contractService);

    BscsService createBscsService(BSCSService service);

    BscsCharges createBscsCharges(BSCSCharges charges);

    BscsContractServiceParameter createBscsContractServiceParameter(BSCSContractServiceParameter parameter);

    BscsParameterValue createBscsParameterValue(ParamValue value);

    BscsContractInfo createBscsContractInfo(BSCSModel info);

    BscsContractInfo createBscsContractInfo();

    BscsServicePackage createBscsServicePackage(BSCSServicePackage servicePackage);

    BscsBillingAssignment createBscsBillingAssignment(BSCSBillingAssignment assignment);

    BscsCustomerSearch createBscsCustomerSearch(BSCSCustomer customer);

    BscsAddress createBscsAddress(BSCSAddress address);

    BscsCustomerInfo createBscsCustomerInfo(BSCSModel info);

    BscsCustomerGroup createBscsCustomerGroup(BSCSModel value);

    BscsBalance createBscsBalance(Balances balance);

    BscsContractFreeUnitAccount createBscsContractFreeUnitAccount(BSCSContractFreeUnit fua);

    BscsUsageDataRecord createBscsUsageDataRecord(BSCSUsageDataRecord record);

    BscsPaymentMethod createBscsPaymentMethod(BSCSPaymentMethod method);

    BscsBillCycle createBscsBillCycle(BSCSBillCycle billCycle);

    BscsCustomer createBscsCustomer(BSCSCustomer customer);

    BscsPaymentArrangement createBscsPaymentArrangement(BSCSPaymentArrangement paymentArrangement);

    BscsBillingAccountSearch createBscsBillingAccountSearch(BSCSBillingAccount billingAccount);

    BscsBillingAccount createBscsBillingAccount(BSCSBillingAccount billingAccount);

    BscsBillingAccountVersion createBscsBillingAccountVersion(BAVersion billingAccountVersion);

    BscsFinancialDocument createBscsFinancialDocument(BSCSDocuments document);

    BscsFinancialDocumentSearch createBscsFinancialDocumentSearch(BSCSDocuments document);

    BscsBusinessTransaction createBscsBusinessTransaction(BusinessTransaction transaction);

    BscsBusinessTransactionSearch createBscsBusinessTransactionSearch(BusinessTransaction transaction);

    BscsCountry createBscsCountry(BSCSModel value);

    BscsTitle createBscsTitle(BSCSModel value);

    BscsIdentificationDocumentType createBscsIdentificationDocumentType(BSCSModel value);

    BscsContractResource createBscsContractResource(BSCSContractResource resource);

    BscsContractResource createBscsContractResource();

    BscsStorageMedium createBscsStorageMedium(BSCSStorageMedium storageMedium);

    BscsFreeUnitPackage createBscsFreeUnitPackage(BSCSFreeUnitPackage freeUnit);

    BscsParameter createBscsParameter(BSCSParameter parameter);

    BscsContractServiceParameter createBscsContractServiceParameter();

    BscsParameterValue createBscsParameterValue();

    BscsBookingRequest createBscsBookingRequest();

    BscsAddress createBscsAddress();

    BscsCustomerInfo createBscsCustomerInfo();
    
    BscsPayment createBscsPayment();
    
    BscsReferencedTransactionSearch createBscsReferencedTransactionSearch(BSCSModel model);

    BscsBillDocument createBscsBillDocument(BSCSModel billDocumentRead);

    BscsFinancialTransaction createBscsFinancialTransaction(BSCSModel transaction);

    BscsFinancialTransactionSearch createBscsFinancialTransactionSearch(BSCSModel transaction);

    BscsFinancialTransaction createBscsFinancialTransaction();

    BscsFinancialTransactionDetail createBscsFinancialTransactionDetail();

    BscsFinancialTransactionDetail createBscsFinancialTransactionDetail(BSCSModel detail);

    BscsFinancialDocumentDetail createBscsFinancialDocumentDetail(BSCSModel detail);

    BscsDebitsByCredit createBscsDebitByCredit(BSCSModel model);

    // search criteria

    BscsContractSearchCriteria createBscsContractSearchCriteria();

    BscsContractServiceSearchCriteria createBscsContractServiceSearchCriteria();

    BscsServiceSearchCriteria createBscsServiceSearchCriteria();

    BscsContractServiceParameterSearchCriteria createBscsContractServiceParameterSearchCriteria();

    BscsServicePackageSearchCriteria createBscsServicePackageSearchCriteria();

    BscsBillingAssignmentSearchCriteria createBscsBillingAssignmentSearchCriteria();

    BscsCustomerSearchCriteria createBscsCustomerSearchCriteria();

    BscsUsageDataRecordSearchCriteria createBscsUsageDataRecordSearchCriteria();

    BscsFinancialDocumentSearchCriteria createBscsFinancialDocumentSearchCriteria();

    BscsBusinessTransactionSearchCriteria createBscsBusinessTransactionSearchCriteria();

    BscsStorageMediumSearchCriteria createBscsStorageMediumSearchCriteria();

    BscsFinancialTransactionSearchCriteria createBscsFinancialTransactionSearchCriteria();
    
    BscsReferencedTransactionSearchCriteria createBscsReferencedTransactionSearchCriteria();
    

}
