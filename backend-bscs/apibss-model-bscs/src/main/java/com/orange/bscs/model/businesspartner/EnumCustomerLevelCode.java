package com.orange.bscs.model.businesspartner;

public enum EnumCustomerLevelCode {

    ROOT("10"),
    DIVISION("20"),
    COST_CENTER("30"),
    SUBSCRIBER("40");
    
    
    private String csLevelCode;
    
    private EnumCustomerLevelCode(String level){
        csLevelCode=level;
    }
    
    public String getLevelCode() { return csLevelCode;}
    
    public static EnumCustomerLevelCode parse(String level){
        EnumCustomerLevelCode result=null;
        if(null==level){
             return null;
        } else if("10".equals(level)){
            result = ROOT;
        } else if ("20".equals(level)){
            result=DIVISION;
        } else if("30".equals(level)){
            result = COST_CENTER;
        } else if ("40".equals(level)){
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
