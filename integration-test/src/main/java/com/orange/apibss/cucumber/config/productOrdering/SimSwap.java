package com.orange.apibss.cucumber.config.productOrdering;


import com.orange.apibss.productOrdering.model.Product;
import lombok.Data;

/**
 * Simswap offer properties
 *
 * @author Thibault Duperron
 */
@Data
public class SimSwap {
    /**
     * Product before simswap linked with {@link #afterSimSwap}
     */
    private Product beforeSimSwap;

    /**
     * Product after simswap linked with {@link #beforeSimSwap}
     */
    private Product afterSimSwap;
    
    private String validIccId;
    private String invalidIccId;
    private String terminatedIccId;
    private String validProductId;
    private String invalidProductId;
    private String badFormatProductId;
}
