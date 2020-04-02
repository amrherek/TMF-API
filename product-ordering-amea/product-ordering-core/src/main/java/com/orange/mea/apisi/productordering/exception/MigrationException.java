package com.orange.mea.apisi.productordering.exception;

import java.util.ArrayList;
import java.util.Arrays;

import com.orange.apibss.common.exceptions.badrequest.BadRequestException;

public class MigrationException extends BadRequestException {

    /**
     * 
     */
    private static final long serialVersionUID = -1109213332099764224L;

    private static final Integer CONSTANT_CODE = ProductOrderingApiExceptionCode.FC_MIGRATION;
    private static final String MESSAGE = "Migration impossible";

    public MigrationException(String coId, String ratePlanId, String message) {
        super("Impossible to migrate contract with id [" + coId + "] to rate plan with id [" + ratePlanId + "]: "
                + message);
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
        arguments = new ArrayList<Object>(Arrays.asList(coId, ratePlanId, message));
	}

}
