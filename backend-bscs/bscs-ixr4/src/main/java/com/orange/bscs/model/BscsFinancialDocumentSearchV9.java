package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.model.billing.BSCSDocuments;

public class BscsFinancialDocumentSearchV9 extends BscsFinancialDocumentSearch {

    public BscsFinancialDocumentSearchV9(BSCSDocuments document) {
        super(document);
    }

    @Override
    public Long getId() {
        // DOCUMENT_ID
        return document.getInvoiceId();
    }

    @Override
    public Date getRefDate() {
        // REF_DATE
        return document.getOhRefDate();
    }

    @Override
    public Date getDueDate() {
        // DUE_DATE
        return document.getOhDueDate();
    }

    @Override
    public Double getAmount() {
        // DOCUMENT_AMOUNT_DOC
        return document.getOhInvAmtDoc();
    }

    @Override
    public Double getOpenAmount() {
        // OPEN_AMOUNT_DOC
        return document.getOhOpenAmtDoc();
    }

    @Override
    public String getCustomerId() {
        // CS_ID_PUB
        return document.getCsIdPub();
    }

    @Override
    public String getAmountCurrency() {
        // DOCUMENT_AMOUNT_DOC#currency
        return document.getOhInvAmtDoc_currency();
    }

    @Override
    public String getStatus() {
        // DOCUMENT_PROCESSING_STATUS
        return document.getOhStatut();
    }

    @Override
    public boolean getImageGenerated() {
        // IMAGE_GENERATED
        return document.getBooleanValue("IMAGE_GENERATED");
    }

}
