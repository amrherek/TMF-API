package com.orange.bscs.model.criteria;

import java.util.Date;
import java.util.List;

import com.orange.bscs.model.billing.BSCSDocuments;

/**
 * Model used to set criteria for FINANCIAL_DOCUMENT.SEARCH command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsFinancialDocumentSearchCriteria {

    protected BSCSDocuments documentSearchCriteria;

    public BscsFinancialDocumentSearchCriteria() {
        this.documentSearchCriteria = new BSCSDocuments();
    }

    public BSCSDocuments getBscsModel() {
        return documentSearchCriteria;
    }

    public abstract void setIgnoreBusinessUnitIndicator(boolean ignoreBu);

    public abstract void setDocumentTypes(List<String> documentTypes);

    /**
     * Set result order: 'a' - ascending search, oldest first. 'd' - descnding
     * search, youngest entries first.
     * 
     * @param order
     */
    public abstract void setOrder(Character order);

    public abstract void setCustomerId(String customerId);

    public abstract void setCustomerCode(String customerCode);

    public abstract void setStartDate(Date startDate);

    public abstract void setEndDate(Date endDate);

    public abstract void setWishedRecords(Integer wishedResults);

}
