package com.orange.mea.apisi.customerbill.rest.model;

import com.orange.apibss.customerbill.model.Attachment;

public class BillDocument {

    private Attachment document;

    private byte[] data;

    public Attachment getDocument() {
        return document;
    }

    public void setDocument(Attachment document) {
        this.document = document;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
