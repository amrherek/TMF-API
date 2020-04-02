package com.orange.apibss.cucumber.config;

import com.orange.apibss.cucumber.config.party.IndividualProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Thibault Duperron
 */
@ToString
public class PartyProperties {
    /**
     * Msisdn properties
     */
    @Getter
    @Setter
    private IndividualProperties individual;
}
