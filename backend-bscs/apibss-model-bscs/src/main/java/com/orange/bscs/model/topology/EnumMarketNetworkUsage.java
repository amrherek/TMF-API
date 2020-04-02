package com.orange.bscs.model.topology;

public enum EnumMarketNetworkUsage {
    MANDATORY('M'),
    OPTIONAL('O'),
    NONE('N');
    
  private char code;
    
    private EnumMarketNetworkUsage(char c){
        this.code=c;
    }
    
    public static EnumMarketNetworkUsage fromCharacter(Character c){
        if(null==c){
            return null;
        }
        EnumMarketNetworkUsage result;
        switch(c){
        case 'M':result=MANDATORY; break;
        case 'O': result=OPTIONAL; break;
        case 'N': result=NONE; break;
        default:
            throw new IllegalArgumentException(String.format("Invalid character '%s' for EnumMarketNetworkUsage",c));
        }
        return result;
    }
    
    public char getCode(){ return code;}
    
    public static Character getCode(EnumMarketNetworkUsage usage){
        if(null==usage){ return null;}
        
        return usage.code;
    }    
}
