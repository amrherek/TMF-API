package com.orange.apibss.cucumber.config.productOrdering;

import lombok.Data;

/**
 * Public key offer properties
 *
 * @author Thibault Duperron
 */
@Data
public class PublicKey {
    private String validMsisdn;
    private String invalidMsisdn;
}
