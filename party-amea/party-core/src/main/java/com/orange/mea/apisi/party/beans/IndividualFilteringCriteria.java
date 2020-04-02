package com.orange.mea.apisi.party.beans;


/**
 * Criteria holder for finding individuals by criteria
 *
 * @author xbbs3851
 *
 */
public class IndividualFilteringCriteria {

    /* Criteria for finding by name */

    /**
     * Firstname
     */
    private String givenName;

    /**
     * Lastname
     */
    private String familyName;

    /**
     * Email address
     */
    private String email;

    private String tradingName;

    /**
     * Default constructor
     */
    public IndividualFilteringCriteria() {
    }

    /**
     *
     * All parameters constructor
     *
     * @param givenName
     * @param familyName
     * @param email
     */
    public IndividualFilteringCriteria(final String givenName, final String familyName, final String email) {
        this.givenName = givenName;
        this.familyName = familyName;
        this.email = email;
    }


    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(final String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(final String familyName) {
        this.familyName = familyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "IndividualFilteringCriteria [givenName=" + givenName + ", familyName=" + familyName + ", email=" + email
                + "]";
    }


}
