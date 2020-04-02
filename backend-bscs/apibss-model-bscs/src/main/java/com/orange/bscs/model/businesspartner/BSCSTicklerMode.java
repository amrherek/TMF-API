package com.orange.bscs.model.businesspartner;

import java.util.ArrayList;
import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;


/**
 * <pre>{@code
 * TICKLER_TRACKING_REFERENCE.READ {
 *   TR_MODE              =  : java.lang.Long
 * }
 * => {
 *   tracking modes       = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
 *  -[0]DESCRIPTION          =  : java.lang.String
 *  -[0]TR_MODE              =  : java.lang.Long
 *  -[0]problem types        = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
 *  -[0][0]DESCRIPTION          =  : java.lang.String
 *  -[0][0]SH_DESCRIPTION       =  : java.lang.String
 *  -[0][0]TR_ID                =  : java.lang.Long
 *  -[0][0]markets              = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
 *  -[0][0][0]SCCODE               =  : java.lang.Long
 * }
 * }</pre>
 *  
 * @author IT&Labs
 *
 */
public class BSCSTicklerMode extends BSCSModel{

    /** TR_MODE */
    public String getCode(){ return getStringValue("TR_MODE");}
    public void setCode(String code){ setStringValue("TR_CODE",code);}
    
    public String getDescription(){ return getStringValue(Constants.P_DESCRIPTION);}
    public void setDescription(String description){ setStringValue(Constants.P_DESCRIPTION, description);}

    public List <BSCSTickerProblemType> getProblemTypes(){
        return getListOfBSCSModelValue("problem types",BSCSTickerProblemType.class);
    }
    public void setProblemTypes(List <BSCSTickerProblemType> problemTypes){
        setListOfBSCSModelValue("problem types", problemTypes);
    }
    
    public static class BSCSTickerProblemType extends BSCSModel{
        
        public Long getId() {
            return getLongValue("TH_ID");
        }
        public void setId(Long id) { setLongValue("TR_ID",id);}

        public String getShortDescription() { return getStringValue("SH_DESCRIPTION");}
        public void setShortDescription(String shortDescription) { setStringValue("SH_DESCRIPTION",shortDescription);}

        public String getDescription() { return getStringValue(Constants.P_DESCRIPTION); }
        public void setDescription(String description) { setStringValue(Constants.P_DESCRIPTION,description); }

        public List<Long> getMarkets() {
            List<Long> result = new ArrayList<Long>();
            List<BSCSModel> l = getListOfBSCSModelValue("markets");
            for(BSCSModel elt : l){
                result.add(elt.getLongValue(Constants.P_SCCODE));
            }
            return result;
        }
        public void setMarkets(List<Long> markets) {
            if(null==markets){
                removeAttribute("markets");
                return;
            }
            List<BSCSModel> input = new ArrayList<BSCSModel>();
            for(Long lg : markets){
                BSCSModel value = new BSCSModel();
                value.setLongValue(Constants.P_SCCODE,lg);
                input.add(value);
            }
            setListOfBSCSModelValue("markets", input);
        }
    }
}
