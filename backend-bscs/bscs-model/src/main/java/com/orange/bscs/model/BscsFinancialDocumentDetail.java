package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;

/**
 * Model used for result of FINANCIAL_DOCUMENT_DETAIL.READ
 * 
 * @author jwck2987
 *
 */
public abstract class BscsFinancialDocumentDetail {

    protected BSCSModel model;

    public BscsFinancialDocumentDetail(BSCSModel detail) {
        model = detail;
    }

    public abstract Double getAmount();

    public abstract String getAmountCurrency();

    public abstract Long getId();

    public abstract Date getRefDate();

    public abstract String getUseCaseCode();

}
