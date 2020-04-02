package com.orange.bscs.model.billing;

/**
 * 
 * Enum used in BillingAccout.Write and billingAccountSearch.
 * 
 * BSCS IX R2 accept a Character but IX R3 accept a String (multiples
 * values) so billingAccountSearch accept a
 * <pre>
 * {@code Flags<EnumInvoicingIndicator> }
 * </pre>
 * 
 * 
 * @author IT&Labs
 * 
 */
public enum EnumInvoicingIndicator {
    
    INVOCING('I'),
    RECONCILIATION('R'),
    CREDIT_MEMO('C');
    
    private char c;
    
    private EnumInvoicingIndicator(char bscs){ c=bscs;}
    
    public char toCharacter(){ return c;}
    
    
    public static EnumInvoicingIndicator parse(Character bscs){
        if(null==bscs){ return null;}
        
        EnumInvoicingIndicator res=null;
        switch(bscs){
        case 'I': res=INVOCING; break;
        case 'R': res=RECONCILIATION; break;
        case 'C': res=CREDIT_MEMO; break;
        default:
            throw new IllegalArgumentException(String.format("%s is not a valid InvoicingIndicator ", bscs));
        }
        return res;
    }

    public static Character toCharacter(EnumInvoicingIndicator indicator) {
        if(null==indicator){ return null;}
        
        return indicator.toCharacter();
    }
}
