package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.model.billing.BSCSDocuments;

/**
 * Model used for result of FINANCIAL_DOCUMENT.SEARCH command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsFinancialDocumentSearch {

    protected BSCSDocuments document;

    public BscsFinancialDocumentSearch(BSCSDocuments document) {
        this.document = document;
    }

    public abstract Long getId();

    public abstract Date getRefDate();

    public abstract Date getDueDate();

    public abstract Double getAmount();

    public abstract Double getOpenAmount();

    public abstract String getCustomerId();

    public abstract String getAmountCurrency();

    public abstract String getStatus();

    public abstract boolean getImageGenerated();

}
