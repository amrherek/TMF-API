package com.orange.apibss.cucumber.config.party;

import com.orange.apibss.party.model.Individual;
import lombok.Data;

/**
 * Properties for IndividualProperties
 *
 * @author Thibault Duperron
 *
 */
@Data
public class IndividualProperties {

    /**
     * Valid individual
     */
    public Individual valid;
    /**
     * Id not well formated
     */
    public String invalidId;
    /**
     * Id well formated but unknown on BSCS
     */
    public String unknownId;
    /**
     * Id of a minor
     */
    public Individual minor;
    /**
     * Minor's tutor
     */
    public Individual tutor;
    /**
     * individual with id
     */
    public Individual withId;
    /**
     * Valid tutor's id
     *
     *  Twice with {#tutor} because it's contract ID for OCM
     */
    public String validTutorId;
    /**
     * Id not well formated
     */
    public String invalidTutorId;
    /**
     * Id well formated but unknown on BSCS
     */
    public String unknownTutorId;
    /**
     * Id of tutor updatable on BSCS
     */
    public String updatableTutorId;
    /**
     * Id of user updatable on BSCS
     */
    public String updatableId;
}
