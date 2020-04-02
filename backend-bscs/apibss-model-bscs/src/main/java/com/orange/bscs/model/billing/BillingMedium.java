package com.orange.bscs.model.billing;

/**
 * <p>BillingMedium class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BillingMedium {

    private Long id;
    private String publicId;
    private String description;

    /**
     * <p>withId.</p>
     *
     * @param mediumId a {@link java.lang.Long} object.
     * @return a {@link com.orange.bscs.model.billing.BillingMedium} object.
     */
    public void setId(Long mediumId) {
        this.id = mediumId;
    }

    /**
     * <p>withPublicId.</p>
     *
     * @param id a {@link java.lang.String} object.
     * @return a {@link com.orange.bscs.model.billing.BillingMedium} object.
     */
    public void setPublicId(String id) {
        this.publicId = id;
    }

    /**
     * <p>withDescription.</p>
     *
     * @param description a {@link java.lang.String} object.
     * @return a {@link com.orange.bscs.model.billing.BillingMedium} object.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * <p>Getter for the field <code>publicId</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPublicId() {
        return publicId;
    }

    /**
     * <p>Getter for the field <code>id</code>.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>Getter for the field <code>description</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDescription() {
        return description;
    }
}
