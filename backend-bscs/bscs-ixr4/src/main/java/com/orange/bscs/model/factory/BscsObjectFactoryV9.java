package com.orange.bscs.model.factory;

import org.springframework.stereotype.Component;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.model.BscsAddress;
import com.orange.bscs.model.BscsAddressV9;
import com.orange.bscs.model.BscsBalance;
import com.orange.bscs.model.BscsBalanceV9;
import com.orange.bscs.model.BscsBillCycle;
import com.orange.bscs.model.BscsBillCycleV9;
import com.orange.bscs.model.BscsBillDocument;
import com.orange.bscs.model.BscsBillDocumentV9;
import com.orange.bscs.model.BscsBillingAccount;
import com.orange.bscs.model.BscsBillingAccountSearch;
import com.orange.bscs.model.BscsBillingAccountSearchV9;
import com.orange.bscs.model.BscsBillingAccountV9;
import com.orange.bscs.model.BscsBillingAccountVersion;
import com.orange.bscs.model.BscsBillingAccountVersionV9;
import com.orange.bscs.model.BscsBillingAssignment;
import com.orange.bscs.model.BscsBillingAssignmentV9;
import com.orange.bscs.model.BscsBookingRequest;
import com.orange.bscs.model.BscsBookingRequestV9;
import com.orange.bscs.model.BscsBusinessTransaction;
import com.orange.bscs.model.BscsBusinessTransactionSearch;
import com.orange.bscs.model.BscsBusinessTransactionSearchV9;
import com.orange.bscs.model.BscsBusinessTransactionV9;
import com.orange.bscs.model.BscsCharges;
import com.orange.bscs.model.BscsChargesV9;
import com.orange.bscs.model.BscsContract;
import com.orange.bscs.model.BscsContractFreeUnitAccount;
import com.orange.bscs.model.BscsContractFreeUnitAccountV9;
import com.orange.bscs.model.BscsContractInfo;
import com.orange.bscs.model.BscsContractInfoV9;
import com.orange.bscs.model.BscsContractResource;
import com.orange.bscs.model.BscsContractResourceV9;
import com.orange.bscs.model.BscsContractSearch;
import com.orange.bscs.model.BscsContractSearchV9;
import com.orange.bscs.model.BscsContractService;
import com.orange.bscs.model.BscsContractServiceFromContract;
import com.orange.bscs.model.BscsContractServiceFromContractV9;
import com.orange.bscs.model.BscsContractServiceParameter;
import com.orange.bscs.model.BscsContractServiceParameterV9;
import com.orange.bscs.model.BscsContractServiceV9;
import com.orange.bscs.model.BscsContractV9;
import com.orange.bscs.model.BscsCountry;
import com.orange.bscs.model.BscsCountryV9;
import com.orange.bscs.model.BscsCustomer;
import com.orange.bscs.model.BscsCustomerGroup;
import com.orange.bscs.model.BscsCustomerGroupV9;
import com.orange.bscs.model.BscsCustomerInfo;
import com.orange.bscs.model.BscsCustomerInfoV9;
import com.orange.bscs.model.BscsCustomerSearch;
import com.orange.bscs.model.BscsCustomerSearchV9;
import com.orange.bscs.model.BscsCustomerV9;
import com.orange.bscs.model.BscsDebitsByCredit;
import com.orange.bscs.model.BscsDebitsByCreditV9;
import com.orange.bscs.model.BscsFinancialDocument;
import com.orange.bscs.model.BscsFinancialDocumentDetail;
import com.orange.bscs.model.BscsFinancialDocumentDetailV9;
import com.orange.bscs.model.BscsFinancialDocumentSearch;
import com.orange.bscs.model.BscsFinancialDocumentSearchV9;
import com.orange.bscs.model.BscsFinancialDocumentV9;
import com.orange.bscs.model.BscsFinancialTransaction;
import com.orange.bscs.model.BscsFinancialTransactionDetail;
import com.orange.bscs.model.BscsFinancialTransactionDetailV9;
import com.orange.bscs.model.BscsFinancialTransactionSearch;
import com.orange.bscs.model.BscsFinancialTransactionSearchV9;
import com.orange.bscs.model.BscsFinancialTransactionV9;
import com.orange.bscs.model.BscsFreeUnitPackage;
import com.orange.bscs.model.BscsFreeUnitPackageV9;
import com.orange.bscs.model.BscsIdentificationDocumentType;
import com.orange.bscs.model.BscsIdentificationDocumentTypeV9;
import com.orange.bscs.model.BscsParameter;
import com.orange.bscs.model.BscsParameterV9;
import com.orange.bscs.model.BscsParameterValue;
import com.orange.bscs.model.BscsParameterValueV9;
import com.orange.bscs.model.BscsPayment;
import com.orange.bscs.model.BscsPaymentArrangement;
import com.orange.bscs.model.BscsPaymentArrangementV9;
import com.orange.bscs.model.BscsPaymentMethod;
import com.orange.bscs.model.BscsPaymentMethodV9;
import com.orange.bscs.model.BscsPaymentV9;
import com.orange.bscs.model.BscsRatePlan;
import com.orange.bscs.model.BscsRatePlanV9;
import com.orange.bscs.model.BscsReferencedTransactionSearch;
import com.orange.bscs.model.BscsReferencedTransactionSearchV9;
import com.orange.bscs.model.BscsService;
import com.orange.bscs.model.BscsServicePackage;
import com.orange.bscs.model.BscsServicePackageV9;
import com.orange.bscs.model.BscsServiceV9;
import com.orange.bscs.model.BscsStorageMedium;
import com.orange.bscs.model.BscsStorageMediumV9;
import com.orange.bscs.model.BscsTitle;
import com.orange.bscs.model.BscsTitleV9;
import com.orange.bscs.model.BscsUsageDataRecord;
import com.orange.bscs.model.BscsUsageDataRecordV9;
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
import com.orange.bscs.model.criteria.BscsBillingAssignmentSearchCriteriaV9;
import com.orange.bscs.model.criteria.BscsBusinessTransactionSearchCriteria;
import com.orange.bscs.model.criteria.BscsBusinessTransactionSearchCriteriaV9;
import com.orange.bscs.model.criteria.BscsContractSearchCriteria;
import com.orange.bscs.model.criteria.BscsContractSearchCriteriaV9;
import com.orange.bscs.model.criteria.BscsContractServiceParameterSearchCriteria;
import com.orange.bscs.model.criteria.BscsContractServiceParameterSearchCriteriaV9;
import com.orange.bscs.model.criteria.BscsContractServiceSearchCriteria;
import com.orange.bscs.model.criteria.BscsContractServiceSearchCriteriaV9;
import com.orange.bscs.model.criteria.BscsCustomerSearchCriteria;
import com.orange.bscs.model.criteria.BscsCustomerSearchCriteriaV9;
import com.orange.bscs.model.criteria.BscsFinancialDocumentSearchCriteria;
import com.orange.bscs.model.criteria.BscsFinancialDocumentSearchCriteriaV9;
import com.orange.bscs.model.criteria.BscsFinancialTransactionSearchCriteria;
import com.orange.bscs.model.criteria.BscsFinancialTransactionSearchCriteriaV9;
import com.orange.bscs.model.criteria.BscsReferencedTransactionSearchCriteria;
import com.orange.bscs.model.criteria.BscsReferencedTransactionSearchCriteriaV9;
import com.orange.bscs.model.criteria.BscsServicePackageSearchCriteria;
import com.orange.bscs.model.criteria.BscsServicePackageSearchCriteriaV9;
import com.orange.bscs.model.criteria.BscsServiceSearchCriteria;
import com.orange.bscs.model.criteria.BscsServiceSearchCriteriaV9;
import com.orange.bscs.model.criteria.BscsStorageMediumSearchCriteria;
import com.orange.bscs.model.criteria.BscsStorageMediumSearchCriteriaV9;
import com.orange.bscs.model.criteria.BscsUsageDataRecordSearchCriteria;
import com.orange.bscs.model.criteria.BscsUsageDataRecordSearchCriteriaV9;
import com.orange.bscs.model.product.BSCSRatePlan;
import com.orange.bscs.model.product.BSCSServicePackage;
import com.orange.bscs.model.resource.BSCSStorageMedium;
import com.orange.bscs.model.usagedata.BSCSUsageDataRecord;
import com.orange.bscs.model.usagedata.Balances;

@Component
public class BscsObjectFactoryV9 implements BscsObjectFactory {

    @Override
    public BscsContractSearch createBscsContractSearch(BSCSContract contract) {
        if (contract == null) {
            return null;
        }
        return new BscsContractSearchV9(contract);
    }

    @Override
    public BscsContractSearchCriteria createBscsContractSearchCriteria() {
        return new BscsContractSearchCriteriaV9();
    }

    @Override
    public BscsContract createBscsContract(BSCSContract contract) {
        if (contract == null) {
            return null;
        }
        return new BscsContractV9(contract);
    }

    @Override
    public BscsRatePlan createBscsRatePlan(BSCSRatePlan ratePlan) {
        if (ratePlan == null) {
            return null;
        }
        return new BscsRatePlanV9(ratePlan);
    }

    @Override
    public BscsContractService createBscsContractService(BSCSContractService contractService) {
        if (contractService == null) {
            return null;
        }
        return new BscsContractServiceV9(contractService);
    }

    @Override
    public BscsService createBscsService(BSCSService service) {
        if (service == null) {
            return null;
        }
        return new BscsServiceV9(service);
    }

    @Override
    public BscsCharges createBscsCharges(BSCSCharges charges) {
        if (charges == null) {
            return null;
        }
        return new BscsChargesV9(charges);
    }

    @Override
    public BscsContractServiceSearchCriteria createBscsContractServiceSearchCriteria() {
        return new BscsContractServiceSearchCriteriaV9();
    }

    @Override
    public BscsContractServiceFromContract createBscsContractServiceFromContract(BSCSContractService contractService) {
        if (contractService == null) {
            return null;
        }
        return new BscsContractServiceFromContractV9(contractService);
    }

    @Override
    public BscsServiceSearchCriteria createBscsServiceSearchCriteria() {
        return new BscsServiceSearchCriteriaV9();
    }

    @Override
    public BscsContractServiceParameterSearchCriteria createBscsContractServiceParameterSearchCriteria() {
        return new BscsContractServiceParameterSearchCriteriaV9();
    }

    @Override
    public BscsContractServiceParameter createBscsContractServiceParameter(BSCSContractServiceParameter parameter) {
        if (parameter == null) {
            return null;
        }
        return new BscsContractServiceParameterV9(parameter);
    }

    @Override
    public BscsParameterValue createBscsParameterValue(ParamValue value) {
        if (value == null) {
            return null;
        }
        return new BscsParameterValueV9(value);
    }

    @Override
    public BscsContractInfo createBscsContractInfo(BSCSModel info) {
        if (info == null) {
            return null;
        }
        return new BscsContractInfoV9(info);
    }

    @Override
    public BscsServicePackage createBscsServicePackage(BSCSServicePackage servicePackage) {
        if (servicePackage == null) {
            return null;
        }
        return new BscsServicePackageV9(servicePackage);
    }

    @Override
    public BscsServicePackageSearchCriteria createBscsServicePackageSearchCriteria() {
        return new BscsServicePackageSearchCriteriaV9();
    }

    @Override
    public BscsBillingAssignment createBscsBillingAssignment(BSCSBillingAssignment assignment) {
        if (assignment == null) {
            return null;
        }
        return new BscsBillingAssignmentV9(assignment);
    }

    @Override
    public BscsBillingAssignmentSearchCriteria createBscsBillingAssignmentSearchCriteria() {
        return new BscsBillingAssignmentSearchCriteriaV9();
    }

    @Override
    public BscsCustomerSearch createBscsCustomerSearch(BSCSCustomer customer) {
        if (customer == null) {
            return null;
        }
        return new BscsCustomerSearchV9(customer);
    }

    @Override
    public BscsCustomerSearchCriteria createBscsCustomerSearchCriteria() {
        return new BscsCustomerSearchCriteriaV9();
    }

    @Override
    public BscsAddress createBscsAddress(BSCSAddress address) {
        if (address == null) {
            return null;
        }
        return new BscsAddressV9(address);
    }

    @Override
    public BscsCustomerInfo createBscsCustomerInfo(BSCSModel info) {
        if (info == null) {
            return null;
        }
        return new BscsCustomerInfoV9(info);
    }

    @Override
    public BscsCustomerGroup createBscsCustomerGroup(BSCSModel value) {
        if (value == null) {
            return null;
        }
        return new BscsCustomerGroupV9(value);
    }

    @Override
    public BscsBalance createBscsBalance(Balances balance) {
        if (balance == null) {
            return null;
        }
        return new BscsBalanceV9(balance);
    }

    @Override
    public BscsContractFreeUnitAccount createBscsContractFreeUnitAccount(BSCSContractFreeUnit fua) {
        if (fua == null) {
            return null;
        }
        return new BscsContractFreeUnitAccountV9(fua);
    }

    @Override
    public BscsUsageDataRecord createBscsUsageDataRecord(BSCSUsageDataRecord record) {
        if (record == null) {
            return null;
        }
        return new BscsUsageDataRecordV9(record);
    }

    @Override
    public BscsUsageDataRecordSearchCriteria createBscsUsageDataRecordSearchCriteria() {
        return new BscsUsageDataRecordSearchCriteriaV9();
    }

    @Override
    public BscsPaymentMethod createBscsPaymentMethod(BSCSPaymentMethod method) {
        if (method == null) {
            return null;
        }
        return new BscsPaymentMethodV9(method);
    }

    @Override
    public BscsBillCycle createBscsBillCycle(BSCSBillCycle billCycle) {
        if (billCycle == null) {
            return null;
        }
        return new BscsBillCycleV9(billCycle);
    }

    @Override
    public BscsCustomer createBscsCustomer(BSCSCustomer customer) {
        if (customer == null) {
            return null;
        }
        return new BscsCustomerV9(customer);
    }

    @Override
    public BscsPaymentArrangement createBscsPaymentArrangement(BSCSPaymentArrangement paymentArrangement) {
        if (paymentArrangement == null) {
            return null;
        }
        return new BscsPaymentArrangementV9(paymentArrangement);
    }

    @Override
    public BscsBillingAccountSearch createBscsBillingAccountSearch(BSCSBillingAccount billingAccount) {
        if (billingAccount == null) {
            return null;
        }
        return new BscsBillingAccountSearchV9(billingAccount);
    }

    @Override
    public BscsBillingAccount createBscsBillingAccount(BSCSBillingAccount billingAccount) {
        if (billingAccount == null) {
            return null;
        }
        return new BscsBillingAccountV9(billingAccount);
    }

    @Override
    public BscsBillingAccountVersion createBscsBillingAccountVersion(BAVersion billingAccountVersion) {
        if (billingAccountVersion == null) {
            return null;
        }
        return new BscsBillingAccountVersionV9(billingAccountVersion);
    }

    @Override
    public BscsFinancialDocument createBscsFinancialDocument(BSCSDocuments document) {
        if (document == null) {
            return null;
        }
        return new BscsFinancialDocumentV9(document);
    }

    @Override
    public BscsFinancialDocumentSearch createBscsFinancialDocumentSearch(BSCSDocuments document) {
        if (document == null) {
            return null;
        }
        return new BscsFinancialDocumentSearchV9(document);
    }

    @Override
    public BscsFinancialDocumentSearchCriteria createBscsFinancialDocumentSearchCriteria() {
        return new BscsFinancialDocumentSearchCriteriaV9();
    }

    @Override
    public BscsBusinessTransaction createBscsBusinessTransaction(BusinessTransaction transaction) {
        if (transaction == null) {
            return null;
        }
        return new BscsBusinessTransactionV9(transaction);
    }

    @Override
    public BscsBusinessTransactionSearch createBscsBusinessTransactionSearch(BusinessTransaction transaction) {
        if (transaction == null) {
            return null;
        }
        return new BscsBusinessTransactionSearchV9(transaction);
    }

    @Override
    public BscsBusinessTransactionSearchCriteria createBscsBusinessTransactionSearchCriteria() {
        return new BscsBusinessTransactionSearchCriteriaV9();
    }

    @Override
    public BscsCountry createBscsCountry(BSCSModel value) {
        if (value == null) {
            return null;
        }
        return new BscsCountryV9(value);
    }

    @Override
    public BscsTitle createBscsTitle(BSCSModel value) {
        if (value == null) {
            return null;
        }
        return new BscsTitleV9(value);
    }

    @Override
    public BscsIdentificationDocumentType createBscsIdentificationDocumentType(BSCSModel value) {
        if (value == null) {
            return null;
        }
        return new BscsIdentificationDocumentTypeV9(value);
    }

    @Override
    public BscsContract createBscsContract() {
        return new BscsContractV9();
    }

    @Override
    public BscsContractService createBscsContractService() {
        return new BscsContractServiceV9();
    }

    @Override
    public BscsContractResource createBscsContractResource(BSCSContractResource resource) {
        if (resource == null) {
            return null;
        }
        return new BscsContractResourceV9(resource);
    }

    @Override
    public BscsContractResource createBscsContractResource() {
        return new BscsContractResourceV9();
    }

    @Override
    public BscsStorageMedium createBscsStorageMedium(BSCSStorageMedium storageMedium) {
        if (storageMedium == null) {
            return null;
        }
        return new BscsStorageMediumV9(storageMedium);
    }

    @Override
    public BscsStorageMediumSearchCriteria createBscsStorageMediumSearchCriteria() {
        return new BscsStorageMediumSearchCriteriaV9();
    }

    @Override
    public BscsFreeUnitPackage createBscsFreeUnitPackage(BSCSFreeUnitPackage freeUnit) {
        if (freeUnit == null) {
            return null;
        }
        return new BscsFreeUnitPackageV9(freeUnit);
    }

    @Override
    public BscsParameter createBscsParameter(BSCSParameter parameter) {
        if (parameter == null) {
            return null;
        }
        return new BscsParameterV9(parameter);
    }

    @Override
    public BscsBillDocument createBscsBillDocument(BSCSModel billDocumentRead) {
        if (billDocumentRead == null) {
            return null;
        }
        return new BscsBillDocumentV9(billDocumentRead);
    }

    @Override
    public BscsContractServiceParameter createBscsContractServiceParameter() {
        return new BscsContractServiceParameterV9();
    }

    @Override
    public BscsParameterValue createBscsParameterValue() {
        return new BscsParameterValueV9();
    }

    @Override
    public BscsBookingRequest createBscsBookingRequest() {
        return new BscsBookingRequestV9();
    }

    @Override
    public BscsAddress createBscsAddress() {
        return new BscsAddressV9();
    }

    @Override
    public BscsContractInfo createBscsContractInfo() {
        return new BscsContractInfoV9();
    }

    @Override
    public BscsCustomerInfo createBscsCustomerInfo() {
        return new BscsCustomerInfoV9();
    }

    @Override
    public BscsFinancialTransaction createBscsFinancialTransaction(BSCSModel transaction) {
        if (transaction == null) {
            return null;
        }
        return new BscsFinancialTransactionV9(transaction);
    }

    @Override
    public BscsFinancialTransactionSearch createBscsFinancialTransactionSearch(BSCSModel transaction) {
        if (transaction == null) {
            return null;
        }
        return new BscsFinancialTransactionSearchV9(transaction);
    }

    @Override
    public BscsFinancialTransactionSearchCriteria createBscsFinancialTransactionSearchCriteria() {
        return new BscsFinancialTransactionSearchCriteriaV9();
    }

    @Override
    public BscsFinancialTransaction createBscsFinancialTransaction() {
        return new BscsFinancialTransactionV9();
    }

    @Override
    public BscsFinancialTransactionDetail createBscsFinancialTransactionDetail() {
        return new BscsFinancialTransactionDetailV9();
    }

    @Override
    public BscsFinancialTransactionDetail createBscsFinancialTransactionDetail(BSCSModel detail) {
        if (detail == null) {
            return null;
        }
        return new BscsFinancialTransactionDetailV9(detail);
    }

    @Override
    public BscsFinancialDocumentDetail createBscsFinancialDocumentDetail(BSCSModel detail) {
        if (detail == null) {
            return null;
        }
        return new BscsFinancialDocumentDetailV9(detail);
    }

    @Override
    public BscsReferencedTransactionSearch createBscsReferencedTransactionSearch(BSCSModel transaction) {
        if (transaction == null) {
            return null;
        }
        return new BscsReferencedTransactionSearchV9(transaction);
    }

	@Override
	public BscsPayment createBscsPayment() {
		return new BscsPaymentV9();
	}

	@Override
	public BscsReferencedTransactionSearchCriteria createBscsReferencedTransactionSearchCriteria() {
		return new BscsReferencedTransactionSearchCriteriaV9();
	}

	@Override
	public BscsDebitsByCredit createBscsDebitByCredit(BSCSModel model) {		
        if (model == null) {
            return null;
        }
        return new BscsDebitsByCreditV9(model);
	}

	
}
