package com.orange.bscs.model.businesspartner;

/**
 * The list of the 9 address roles defined in BSCS. 
 * <ul>
 * <li>BillAddressRole = 'B';
 * <li>PreviousBillAddressRole = 'R'; 
 * <li>TemporaryBillAddressRole = 'E';
 * <li>DetailedBillAddressRole = 'I'; 
 * <li>ContractAddressRole = 'C'; 
 * <li>MagazineAddressRole = 'P'; 
 * <li>DirectoryAddressRole = 'D'; 
 * <li>ShipmentAddressRole = 'S'; 
 * <li>UserAddressRole = 'U';
 * </ul>
 * 
 * @author IT&Labs
 * @version $Id: $
 */
public enum EnumAddressRole {

    BILL("BillAddressRole", 'B'), 
    PREVIOUSBILL("PreviousBillAddressRole", 'R'), 
    TEMPORARYBILL("TemporaryBillAddressRole", 'E'), 
    DETAILEDBILL("DetailedBillAddressRole", 'I'), 
    CONTRACT("ContractAddressRole", 'C'), 
    MAGAZINE("MagazineAddressRole", 'P'), 
    DIRECTORY("DirectoryAddressRole", 'D'), 
    SHIPMENT("ShipmentAddressRole", 'S'), 
    USER("UserAddressRole", 'U');

    private String label;
    private char car;

    private EnumAddressRole(String label, char bscsCode) {
        this.label = label;
        car = bscsCode;
    }

    /**
     * <p>toCharacter.</p>
     *
     * @return a char.
     */
    public char toCharacter() {
        return car;
    }

    /**
     * <p>toCharacter.</p>
     *
     * @param role a {@link com.orange.bscs.model.businesspartner.EnumAddressRole} object.
     * @return a {@link java.lang.Character} object.
     */
    public static Character toCharacter(EnumAddressRole role) {
        if (null == role) {
            return null;
        }
        return role.toCharacter();
    }

    /**
     * <p>parseCharacter.</p>
     *
     * @param adrRole a {@link java.lang.Character} object.
     * @return a {@link com.orange.bscs.model.businesspartner.EnumAddressRole} object.
     */
    public static EnumAddressRole parseCharacter(Character adrRole) {
        if (null == adrRole) {
            return null;
        }
        EnumAddressRole res=null;
        for(EnumAddressRole r : values()){
            if(adrRole.equals(r.car)){
                res=r;
                break;
            }
        }
        return res;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return label;
    }

}
