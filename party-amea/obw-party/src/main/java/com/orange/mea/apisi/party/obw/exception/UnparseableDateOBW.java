package com.orange.mea.apisi.party.obw.exception;

import java.util.ArrayList;
import java.util.Arrays;

import com.orange.apibss.common.exceptions.technical.TechnicalException;

public class UnparseableDateOBW extends TechnicalException {

    /**
     *
     */
    private static final long serialVersionUID = 808233082251316797L;

    private static final Integer CONSTANT_CODE = 5101;

    private String fieldName;
    private String fieldValue;

    public UnparseableDateOBW(final String fieldName, final String fieldValue) {
        super("Unparseable date for field " + fieldName + ": [" + fieldValue + "]");
        this.setCode(CONSTANT_CODE);
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        arguments = new ArrayList<Object>(Arrays.asList(fieldName, fieldValue));
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(final String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(final String fieldValue) {
        this.fieldValue = fieldValue;
    }

}
