package com.orange.bscs.model.businesspartner;

/**
 * <p>EnumMaritalStatus class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public enum EnumMaritalStatus {
    
    SINGLE("SING", "single", 1), 
    MARRIED("MAR", "married", 2), 
    DIVORCED("DIV", "divorced", 3), 
    WIDOWED("WIDOW", "widowed", 4);

    private long code;
    private String codePub;
    private String description;

    private EnumMaritalStatus(String coPub, String des, long co) {
        codePub = coPub;
        description = des;
        code = co;
    }

    /**
     * <p>parse.</p>
     *
     * @param any a {@link java.lang.String} object.
     * @return a {@link com.orange.bscs.model.businesspartner.EnumMaritalStatus} object.
     */
    public static EnumMaritalStatus parse(String any) {
        if (null == any) {
            return null;
        }

        EnumMaritalStatus res = null;

        if (any.matches("\\d+")) {
            long l = Long.parseLong(any);
            res = parseLong(l);

        } else {
            // Search for codePub
            for (EnumMaritalStatus ms : EnumMaritalStatus.values()) {
                if (ms.codePub.equalsIgnoreCase(any)) {
                    res = ms;
                    break;
                }
            }

            // Search by description
            if (null == res) {
                for (EnumMaritalStatus ms : EnumMaritalStatus.values()) {
                    if (ms.description.equalsIgnoreCase(any)) {
                        res = ms;
                        break;
                    }
                }
            }
        }
        return res;
    }

    /**
     * <p>parseLong.</p>
     *
     * @param l a long.
     * @return a {@link com.orange.bscs.model.businesspartner.EnumMaritalStatus} object.
     */
    public static EnumMaritalStatus parseLong(long l) {
        EnumMaritalStatus res = null;
        for (EnumMaritalStatus ms : EnumMaritalStatus.values()) {
            if (ms.code == l) {
                res = ms;
                break;
            }
        }
        return res;
    }

    /**
     * <p>Getter for the field <code>description</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDescription() {
        return description;
    }

    /**
     * <p>Getter for the field <code>code</code>.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getCode() {
        return code;
    }

    /**
     * <p>Getter for the field <code>codePub</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCodePub() {
        return codePub;
    }

}
