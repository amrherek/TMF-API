package com.orange.apibss.cucumber.config.productOrdering;

import com.orange.apibss.productOrdering.model.RelatedIndividual;
import lombok.Data;

/**
 * Activate offer properties
 *
 * @author Thibault Duperron
 */
@Data
public class ActivateOffer {
    private String validProductId;
    private String invalidProductId;
    private String updatableProductId;
    private String terminatedProductId;

    private RelatedIndividual validRelatedIndividual;
}
