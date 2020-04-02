package com.orange.mea.apisi.productinventory.exception;

import com.orange.apibss.common.exceptions.technical.TechnicalException;

public class RatePlanNotFoundException extends TechnicalException {

    /**
     * Serial Id
     */
    private static final long serialVersionUID = 1409237176424563410L;

    /**
     * 
     * @param id
     */
    public RatePlanNotFoundException(String id) {
        super("No Rate Plan found with id: " + id);
    }
}
