package com.orange.bscs.model.businesspartner;

/**
 * <p>
 * EnumTitle class.
 * </p>
 * 
 * @author IT&Labs
 * @version $Id: $
 */
public enum EnumTitle {

    MR(1, "Mr.", EnumSex.MALE), 
    MRS(2, "Mrs.", EnumSex.FEMALE), 
    MS(3, "Ms.", EnumSex.FEMALE), 
    COMP(4, "Company", EnumSex.UNKNOWN), 
    SIR(5, "Sir", EnumSex.MALE), 
    LADY(6, "Lady", EnumSex.FEMALE), 
    TR(7, "Trading", EnumSex.UNKNOWN);

    private long id;
    private String label;
    private EnumSex sex;

    private EnumTitle(long id, String label, EnumSex s) {
        this.id = id;
        this.label = label;
        this.sex = s;
    }

    /**
     * <p>
     * fromId.
     * </p>
     * 
     * @param id
     *            id of title (and not ordinal)
     * @return null or value if found.
     */
    public static EnumTitle fromId(Long id) {
        if (null == id) {
            return null;
        }

        EnumTitle res = null;
        for (EnumTitle t : EnumTitle.values()) {
            if (t.id == id) {
                res = t;
                break;
            }
        }
        return res;
    }

    /**
     * <p>
     * parse.
     * </p>
     * 
     * @param idOrNameOrIdPub
     *            Accept "1", "Mr." or "MR"
     * @return a {@link com.orange.bscs.model.businesspartner.EnumTitle} object.
     */
    public static EnumTitle parse(String idOrNameOrIdPub) {
        if (null == idOrNameOrIdPub) {
            return null;
        }

        EnumTitle res = null;

        if (idOrNameOrIdPub.matches("\\d+")) {
            Long id = Long.parseLong(idOrNameOrIdPub);
            res = fromId(id);
        }

        for (EnumTitle t : EnumTitle.values()) {
            if (t.label.equalsIgnoreCase(idOrNameOrIdPub)) {
                res = t;
                break;
            } else if (t.name().equals(idOrNameOrIdPub)) {
                res = t;
                break;
            }
        }
        return res;
    }

    /**
     * <p>
     * Getter for the field <code>id</code>.
     * </p>
     * 
     * @return a {@link java.lang.Long} object.
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>
     * getIdPub.
     * </p>
     * 
     * @return a {@link java.lang.String} object.
     */
    public String getIdPub() {
        return name();
    }

    /**
     * <p>
     * Getter for the field <code>sex</code>.
     * </p>
     * 
     * @return a {@link com.orange.bscs.model.businesspartner.EnumSex} object.
     */
    public EnumSex getSex() {
        return sex;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
