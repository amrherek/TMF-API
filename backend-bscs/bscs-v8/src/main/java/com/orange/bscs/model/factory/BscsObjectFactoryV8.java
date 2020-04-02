package com.orange.bscs.model.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.model.BscsAddress;
import com.orange.bscs.model.BscsAddressV8;
import com.orange.bscs.model.BscsBalance;
import com.orange.bscs.model.BscsBalanceV8;
import com.orange.bscs.model.BscsBillCycle;
import com.orange.bscs.model.BscsBillCycleV8;
import com.orange.bscs.model.BscsBillDocument;
import com.orange.bscs.model.BscsBillingAccount;
import com.orange.bscs.model.BscsBillingAccountSearch;
import com.orange.bscs.model.BscsBillingAccountVersion;
import com.orange.bscs.model.BscsBillingAssignment;
import com.orange.bscs.model.BscsBookingRequest;
import com.orange.bscs.model.BscsBookingRequestV8;
import com.orange.bscs.model.BscsBusinessTransaction;
import com.orange.bscs.model.BscsBusinessTransactionSearch;
import com.orange.bscs.model.BscsBusinessTransactionSearchV8;
import com.orange.bscs.model.BscsBusinessTransactionV8;
import com.orange.bscs.model.BscsCharges;
import com.orange.bscs.model.BscsContract;
import com.orange.bscs.model.BscsContractFreeUnitAccount;
import com.orange.bscs.model.BscsContractFreeUnitAccountV8;
import com.orange.bscs.model.BscsContractInfo;
import com.orange.bscs.model.BscsContractInfoV8;
import com.orange.bscs.model.BscsContractResource;
import com.orange.bscs.model.BscsContractResourceV8;
import com.orange.bscs.model.BscsContractSearch;
import com.orange.bscs.model.BscsContractSearchV8;
import com.orange.bscs.model.BscsContractService;
import com.orange.bscs.model.BscsContractServiceFromContract;
import com.orange.bscs.model.BscsContractServiceFromContractV8;
import com.orange.bscs.model.BscsContractServiceParameter;
import com.orange.bscs.model.BscsContractServiceParameterV8;
import com.orange.bscs.model.BscsContractServiceV8;
import com.orange.bscs.model.BscsContractV8;
import com.orange.bscs.model.BscsCountry;
import com.orange.bscs.model.BscsCountryV8;
import com.orange.bscs.model.BscsCustomer;
import com.orange.bscs.model.BscsCustomerGroup;
import com.orange.bscs.model.BscsCustomerGroupV8;
import com.orange.bscs.model.BscsCustomerInfo;
import com.orange.bscs.model.BscsCustomerInfoV8;
import com.orange.bscs.model.BscsCustomerSearch;
import com.orange.bscs.model.BscsCustomerSearchV8;
import com.orange.bscs.model.BscsCustomerV8;
import com.orange.bscs.model.BscsDebitsByCredit;
import com.orange.bscs.model.BscsDebitsByCreditV8;
import com.orange.bscs.model.BscsFinancialDocument;
import com.orange.bscs.model.BscsFinancialDocumentDetail;
import com.orange.bscs.model.BscsFinancialDocumentSearch;
import com.orange.bscs.model.BscsFinancialTransaction;
import com.orange.bscs.model.BscsFinancialTransactionDetail;
import com.orange.bscs.model.BscsFinancialTransactionSearch;
import com.orange.bscs.model.BscsFreeUnitPackage;
import com.orange.bscs.model.BscsFreeUnitPackageV8;
import com.orange.bscs.model.BscsIdentificationDocumentType;
import com.orange.bscs.model.BscsIdentificationDocumentTypeV8;
import com.orange.bscs.model.BscsParameter;
import com.orange.bscs.model.BscsParameterV8;
import com.orange.bscs.model.BscsParameterValue;
import com.orange.bscs.model.BscsParameterValueV8;
import com.orange.bscs.model.BscsPayment;
import com.orange.bscs.model.BscsPaymentArrangement;
import com.orange.bscs.model.BscsPaymentArrangementV8;
import com.orange.bscs.model.BscsPaymentMethod;
import com.orange.bscs.model.BscsPaymentMethodV8;
import com.orange.bscs.model.BscsPaymentV8;
import com.orange.bscs.model.BscsRatePlan;
import com.orange.bscs.model.BscsRatePlanV8;
import com.orange.bscs.model.BscsReferencedTransactionSearch;
import com.orange.bscs.model.BscsReferencedTransactionSearchV8;
import com.orange.bscs.model.BscsService;
import com.orange.bscs.model.BscsServicePackage;
import com.orange.bscs.model.BscsServicePackageV8;
import com.orange.bscs.model.BscsServiceV8;
import com.orange.bscs.model.BscsStorageMedium;
import com.orange.bscs.model.BscsStorageMediumSearchCriteriaV8;
import com.orange.bscs.model.BscsStorageMediumV8;
import com.orange.bscs.model.BscsTitle;
import com.orange.bscs.model.BscsTitleV8;
import com.orange.bscs.model.BscsUsageDataRecord;
import com.orange.bscs.model.BscsUsageDataRecordV8;
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
import com.orange.bscs.model.criteria.BscsBusinessTransactionSearchCriteriaV8;
import com.orange.bscs.model.criteria.BscsContractSearchCriteria;
import com.orange.bscs.model.criteria.BscsContractSearchCriteriaV8;
import com.orange.bscs.model.criteria.BscsContractServiceParameterSearchCriteria;
import com.orange.bscs.model.criteria.BscsContractServiceParameterSearchCriteriaV8;
import com.orange.bscs.model.criteria.BscsContractServiceSearchCriteria;
import com.orange.bscs.model.criteria.BscsContractServiceSearchCriteriaV8;
import com.orange.bscs.model.criteria.BscsCustomerSearchCriteria;
import com.orange.bscs.model.criteria.BscsCustomerSearchCriteriaV8;
import com.orange.bscs.model.criteria.BscsFinancialDocumentSearchCriteria;
import com.orange.bscs.model.criteria.BscsFinancialTransactionSearchCriteria;
import com.orange.bscs.model.criteria.BscsReferencedTransactionSearchCriteria;
import com.orange.bscs.model.criteria.BscsReferencedTransactionSearchCriteriaV8;
import com.orange.bscs.model.criteria.BscsServicePackageSearchCriteria;
import com.orange.bscs.model.criteria.BscsServicePackageSearchCriteriaV8;
import com.orange.bscs.model.criteria.BscsServiceSearchCriteria;
import com.orange.bscs.model.criteria.BscsServiceSearchCriteriaV8;
import com.orange.bscs.model.criteria.BscsStorageMediumSearchCriteria;
import com.orange.bscs.model.criteria.BscsUsageDataRecordSearchCriteria;
import com.orange.bscs.model.criteria.BscsUsageDataRecordSearchCriteriaV8;
import com.orange.bscs.model.product.BSCSRatePlan;
import com.orange.bscs.model.product.BSCSServicePackage;
import com.orange.bscs.model.resource.BSCSStorageMedium;
import com.orange.bscs.model.usagedata.BSCSUsageDataRecord;
import com.orange.bscs.model.usagedata.Balances;

@Component
public class BscsObjectFactoryV8 implements BscsObjectFactory {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public BscsContractSearch createBscsContractSearch(BSCSContract contract) {
        if (contract == null) {
            return null;
        }
        return new BscsContractSearchV8(contract);
    }

    @Override
    public BscsContractSearchCriteria createBscsContractSearchCriteria() {
        return new BscsContractSearchCriteriaV8();
    }

    @Override
    public BscsContract createBscsContract(BSCSContract contract) {
        if (contract == null) {
            return null;
        }
        return new BscsContractV8(contract);
    }

    @Override
    public BscsRatePlan createBscsRatePlan(BSCSRatePlan ratePlan) {
        if (ratePlan == null) {
            return null;
        }
        return new BscsRatePlanV8(ratePlan);
    }

    @Override
    public BscsContractService createBscsContractService(BSCSContractService contractService) {
        if (contractService == null) {
            return null;
        }
        return new BscsContractServiceV8(contractService);
    }

    @Override
    public BscsService createBscsService(BSCSService service) {
        if (service == null) {
            return null;
        }
        return new BscsServiceV8(service);
    }

    @Override
    public BscsCharges createBscsCharges(BSCSCharges charges) {
        throw new RuntimeException("BSCSCharges object not available in V8");
    }

    @Override
    public BscsContractServiceSearchCriteria createBscsContractServiceSearchCriteria() {
        return new BscsContractServiceSearchCriteriaV8();
    }

    @Override
    public BscsContractServiceFromContract createBscsContractServiceFromContract(BSCSContractService contractService) {
        if (contractService == null) {
            return null;
        }
        return new BscsContractServiceFromContractV8(contractService);
    }

    @Override
    public BscsServiceSearchCriteria createBscsServiceSearchCriteria() {
        return new BscsServiceSearchCriteriaV8();
    }

    @Override
    public BscsContractServiceParameterSearchCriteria createBscsContractServiceParameterSearchCriteria() {
        return new BscsContractServiceParameterSearchCriteriaV8();
    }

    @Override
    public BscsContractServiceParameter createBscsContractServiceParameter(BSCSContractServiceParameter parameter) {
        if (parameter == null) {
            return null;
        }
        return new BscsContractServiceParameterV8(parameter);
    }

    @Override
    public BscsParameterValue createBscsParameterValue(ParamValue value) {
        if (value == null) {
            return null;
        }
        return new BscsParameterValueV8(value);
    }

    @Override
    public BscsContractInfo createBscsContractInfo(BSCSModel info) {
        if (info == null) {
            return null;
        }
        return new BscsContractInfoV8(info);
    }

    @Override
    public BscsServicePackage createBscsServicePackage(BSCSServicePackage servicePackage) {
        if (servicePackage == null) {
            return null;
        }
        return new BscsServicePackageV8(servicePackage);
    }

    @Override
    public BscsServicePackageSearchCriteria createBscsServicePackageSearchCriteria() {
        return new BscsServicePackageSearchCriteriaV8();
    }

    @Override
    public BscsBillingAssignment createBscsBillingAssignment(BSCSBillingAssignment assignment) {
        logger.error("BILLING_ACCOUNT_ASSIGNMENT does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public BscsBillingAssignmentSearchCriteria createBscsBillingAssignmentSearchCriteria() {
        logger.error("BILLING_ACCOUNT_ASSIGNMENT.READ does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public BscsCustomerSearch createBscsCustomerSearch(BSCSCustomer customer) {
        if (customer == null) {
            return null;
        }
        return new BscsCustomerSearchV8(customer);
    }

    @Override
    public BscsCustomerSearchCriteria createBscsCustomerSearchCriteria() {
        return new BscsCustomerSearchCriteriaV8();
    }

    @Override
    public BscsAddress createBscsAddress(BSCSAddress address) {
        if (address == null) {
            return null;
        }
        return new BscsAddressV8(address);
    }

    @Override
    public BscsCustomerInfo createBscsCustomerInfo(BSCSModel info) {
        if (info == null) {
            return null;
        }
        return new BscsCustomerInfoV8(info);
    }

    @Override
    public BscsCustomerGroup createBscsCustomerGroup(BSCSModel value) {
        if (value == null) {
            return null;
        }
        return new BscsCustomerGroupV8(value);
    }

    @Override
    public BscsBalance createBscsBalance(Balances balance) {
        if (balance == null) {
            return null;
        }
        return new BscsBalanceV8(balance);
    }

    @Override
    public BscsContractFreeUnitAccount createBscsContractFreeUnitAccount(BSCSContractFreeUnit fua) {
        if (fua == null) {
            return null;
        }
        return new BscsContractFreeUnitAccountV8(fua);
    }

    @Override
    public BscsUsageDataRecord createBscsUsageDataRecord(BSCSUsageDataRecord record) {
        if (record == null) {
            return null;
        }
        return new BscsUsageDataRecordV8(record);
    }

    @Override
    public BscsUsageDataRecordSearchCriteria createBscsUsageDataRecordSearchCriteria() {
        return new BscsUsageDataRecordSearchCriteriaV8();
    }

    @Override
    public BscsPaymentMethod createBscsPaymentMethod(BSCSPaymentMethod method) {
        if (method == null) {
            return null;
        }
        return new BscsPaymentMethodV8(method);
    }

    @Override
    public BscsBillCycle createBscsBillCycle(BSCSBillCycle billCycle) {
        if (billCycle == null) {
            return null;
        }
        return new BscsBillCycleV8(billCycle);
    }

    @Override
    public BscsCustomer createBscsCustomer(BSCSCustomer customer) {
        if (customer == null) {
            return null;
        }
        return new BscsCustomerV8(customer);
    }

    @Override
    public BscsPaymentArrangement createBscsPaymentArrangement(BSCSPaymentArrangement paymentArrangement) {
        if (paymentArrangement == null) {
            return null;
        }
        return new BscsPaymentArrangementV8(paymentArrangement);
    }

    @Override
    public BscsBillingAccountSearch createBscsBillingAccountSearch(BSCSBillingAccount billingAccount) {
        logger.error("BILLING_ACCOUNT does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public BscsBillingAccount createBscsBillingAccount(BSCSBillingAccount billingAccount) {
        logger.error("BILLING_ACCOUNT does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public BscsBillingAccountVersion createBscsBillingAccountVersion(BAVersion billingAccountVersion) {
        logger.error("BILLING_ACCOUNT does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public BscsFinancialDocument createBscsFinancialDocument(BSCSDocuments document) {
        logger.error("FINANCIAL_DOCUMENT does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public BscsFinancialDocumentSearch createBscsFinancialDocumentSearch(BSCSDocuments document) {
        logger.error("FINANCIAL_DOCUMENT does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public BscsFinancialDocumentSearchCriteria createBscsFinancialDocumentSearchCriteria() {
        logger.error("FINANCIAL_DOCUMENT does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public BscsBillDocument createBscsBillDocument(BSCSModel billDocumentRead) {
        logger.error("BILLINGDOCUMENT does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public BscsBusinessTransaction createBscsBusinessTransaction(BusinessTransaction transaction) {
        if (transaction == null) {
            return null;
        }
        return new BscsBusinessTransactionV8(transaction);
    }

    @Override
    public BscsBusinessTransactionSearch createBscsBusinessTransactionSearch(BusinessTransaction transaction) {
        if (transaction == null) {
            return null;
        }
        return new BscsBusinessTransactionSearchV8(transaction);
    }

    @Override
    public BscsBusinessTransactionSearchCriteria createBscsBusinessTransactionSearchCriteria() {
        return new BscsBusinessTransactionSearchCriteriaV8();
    }

    @Override
    public BscsCountry createBscsCountry(BSCSModel value) {
        if (value == null) {
            return null;
        }
        return new BscsCountryV8(value);
    }

    @Override
    public BscsTitle createBscsTitle(BSCSModel value) {
        if (value == null) {
            return null;
        }
        return new BscsTitleV8(value);
    }

    @Override
    public BscsIdentificationDocumentType createBscsIdentificationDocumentType(BSCSModel value) {
        if (value == null) {
            return null;
        }
        return new BscsIdentificationDocumentTypeV8(value);
    }

    @Override
    public BscsContract createBscsContract() {
        return new BscsContractV8();
    }

    @Override
    public BscsContractService createBscsContractService() {
        return new BscsContractServiceV8();
    }

    @Override
    public BscsContractResource createBscsContractResource(BSCSContractResource resource) {
        if (resource == null) {
            return null;
        }
        return new BscsContractResourceV8(resource);
    }

    @Override
    public BscsContractResource createBscsContractResource() {
        return new BscsContractResourceV8();
    }

    @Override
    public BscsStorageMedium createBscsStorageMedium(BSCSStorageMedium storageMedium) {
        if (storageMedium == null) {
            return null;
        }
        return new BscsStorageMediumV8(storageMedium);
    }

    @Override
    public BscsStorageMediumSearchCriteria createBscsStorageMediumSearchCriteria() {
        return new BscsStorageMediumSearchCriteriaV8();
    }

    @Override
    public BscsFreeUnitPackage createBscsFreeUnitPackage(BSCSFreeUnitPackage freeUnit) {
        if (freeUnit == null) {
            return null;
        }
        return new BscsFreeUnitPackageV8(freeUnit);
    }

    @Override
    public BscsParameter createBscsParameter(BSCSParameter parameter) {
        if (parameter == null) {
            return null;
        }
        return new BscsParameterV8(parameter);
    }

    @Override
    public BscsContractServiceParameter createBscsContractServiceParameter() {
        return new BscsContractServiceParameterV8();
    }

    @Override
    public BscsParameterValue createBscsParameterValue() {
        return new BscsParameterValueV8();
    }

    @Override
    public BscsBookingRequest createBscsBookingRequest() {
        return new BscsBookingRequestV8();
    }

    @Override
    public BscsAddress createBscsAddress() {
        return new BscsAddressV8();
    }

    @Override
    public BscsContractInfo createBscsContractInfo() {
        return new BscsContractInfoV8();
    }

    @Override
    public BscsCustomerInfo createBscsCustomerInfo() {
        return new BscsCustomerInfoV8();
    }

    @Override
    public BscsFinancialTransaction createBscsFinancialTransaction(BSCSModel transaction) {
        logger.error("FINANCIAL_TRANSACTION does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public BscsFinancialTransactionSearch createBscsFinancialTransactionSearch(BSCSModel transaction) {
        logger.error("FINANCIAL_TRANSACTION does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public BscsFinancialTransactionSearchCriteria createBscsFinancialTransactionSearchCriteria() {
        logger.error("FINANCIAL_TRANSACTION does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public BscsFinancialTransaction createBscsFinancialTransaction() {
        logger.error("FINANCIAL_TRANSACTION does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public BscsFinancialTransactionDetail createBscsFinancialTransactionDetail() {
        logger.error("FINANCIAL_TRANSACTION does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public BscsFinancialTransactionDetail createBscsFinancialTransactionDetail(BSCSModel detail) {
        logger.error("FINANCIAL_TRANSACTION does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public BscsFinancialDocumentDetail createBscsFinancialDocumentDetail(BSCSModel detail) {
        logger.error("FINANCIAL_DOCUMENT does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public BscsReferencedTransactionSearch createBscsReferencedTransactionSearch(BSCSModel transaction) {
        if (transaction == null) {
            return null;
        }
        return new BscsReferencedTransactionSearchV8(transaction);
    }

    @Override
	public BscsPayment createBscsPayment() {
		return new BscsPaymentV8();
	}

	@Override
    public BscsReferencedTransactionSearchCriteria createBscsReferencedTransactionSearchCriteria() {
		return new BscsReferencedTransactionSearchCriteriaV8();
	}

	@Override
	public BscsDebitsByCredit createBscsDebitByCredit(BSCSModel model) {
        if (model == null) {
            return null;
        }
        return new BscsDebitsByCreditV8(model);
	}
	

	
	

}
