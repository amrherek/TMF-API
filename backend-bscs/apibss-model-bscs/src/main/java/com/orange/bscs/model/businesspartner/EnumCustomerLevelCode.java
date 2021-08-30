package com.orange.bscs.model.businesspartner;

public enum EnumCustomerLevelCode {

    ROOT("1"),
    DIVISION("2"),
    COST_CENTER("3"),
    SUBSCRIBER("4");
    
    
    private String csLevelCode;
    
    private EnumCustomerLevelCode(String level){
        csLevelCode=level;
    }
    
    public String getLevelCode() { return csLevelCode;}
    
    public static EnumCustomerLevelCode parse(String level){
        EnumCustomerLevelCode result=null;
        if(null==level){
             return null;
        } else if("1".equals(level)){
            result = ROOT;
        } else if ("2".equals(level)){
            result=DIVISION;
        } else if("3".equals(level)){
            result = COST_CENTER;
        } else if ("4".equals(level)){
            result=SUBSCRIBER;
        }
        return result;
    }

    public static String toString(EnumCustomerLevelCode value) {
        if(null==value){
            return null;
        }
        return value.getLevelCode();
    }
}
