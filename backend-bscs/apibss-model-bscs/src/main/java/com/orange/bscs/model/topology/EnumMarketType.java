package com.orange.bscs.model.topology;

/**
* Market type:
* <ul><li>P - Port driven 
* <li>S - Storage Medium driven
* <li>E - Equipment driven 
* <li>D - Directory Number driven
* <li>X - Non network market
* </ul>
*/
public enum EnumMarketType {

    PORT_DRIVEN('P'),
    STORAGE_MEDIUM('S'),
    EQUIPMENT_DRIVEN('E'),
    DIRECTORY_NUMBER('D'),
    NON_NETWORK('X');
    
    private char code;
    
    private EnumMarketType(char c){
        this.code=c;
    }
    
    public static EnumMarketType fromCharacter(Character c){
        if(null==c){
            return null;
        }
        EnumMarketType result;
        switch(c){
        case 'P':result=PORT_DRIVEN; break;
        case 'S': result=STORAGE_MEDIUM; break;
        case 'E': result=EQUIPMENT_DRIVEN; break;
        case 'D': result=DIRECTORY_NUMBER; break;
        case 'X': result=NON_NETWORK; break;
        default:
            throw new IllegalArgumentException(String.format("Invalid character '%s' for EnumMarketType",c));
        }
        return result;
    }
    
    public char getCode(){ return code;}
    
    public static Character getCode(EnumMarketType type){
        if(null==type){ return null;}
        
        return type.code;
    }
    
}
