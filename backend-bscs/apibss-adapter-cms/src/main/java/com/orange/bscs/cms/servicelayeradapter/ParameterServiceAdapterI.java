package com.orange.bscs.cms.servicelayeradapter;

import java.util.Date;
import java.util.List;

import com.orange.bscs.model.contract.BSCSContractService;
import com.orange.bscs.model.contract.BSCSContractServiceParameter;
import com.orange.bscs.model.contract.BSCSParameter;
import com.orange.bscs.model.contract.BSCSService;

public interface ParameterServiceAdapterI {

    /**
     * Reads the value of the parameter of a contracted service.
     * <ul>
     * <li>INT_SERVICES: List of internal services
     * <li>NUM_PARAMS : List of parameter value nodes of the contracted service.
     * </ul>
     * 
     * <pre>
     * {@code
     * CONTRACT_SERVICE_PARAMETERS.READ {
     *   CO_ID                =  : java.lang.Long
     *   CO_ID_PUB            =  : java.lang.String
     *   EXT_SERVICE_ID       =  : java.lang.Long
     *   EXT_SERVICE_ID_PUB   =  : java.lang.String
     * * PROFILE_ID           =  : java.lang.Long
     *   SNCODE               =  : java.lang.Long
     *   SNCODE_PUB           =  : java.lang.String
     *   SPCODE               =  : java.lang.Long
     *   SPCODE_PUB           =  : java.lang.String
     * }
     * => {
     *   CO_ID                =  : java.lang.Long
     *   CO_ID_PUB            =  : java.lang.String
     *   INT_SERVICES         = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
     *  -[0]SNCODE               =  : java.lang.Long
     *  -[0]SNCODE_PUB           =  : java.lang.String
     *  -[0]NUM_PARAMS           = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
     *  -[0][0]BSCSContractServiceParameter
     * * PROFILE_ID           =  : java.lang.Long
     *   SPCODE               =  : java.lang.Long
     *   SPCODE_PUB           =  : java.lang.String
     *   SNCODE               =  : java.lang.Long
     *   SNCODE_PUB           =  : java.lang.String
     *   NUM_PARAMS           = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
     *  -[0]BSCSContractServiceParameter
     * }
     * }
     * </pre>
     * 
     * @param coid
     *            a contract internal id.
     * @param coidpub
     *            a contract public key.
     * @param profileId
     *            a profile id.
     * @param spcodepub
     *            a service package public key.
     * @param sncodepub
     *            a service public key.
     * @return a {@link com.orange.bscs.model.contract.BSCSContractService}.
     */
    BSCSContractService contractServiceParametersRead(Long coid, String coidpub, Long profileId, String spcodepub, String sncodepub);

    /**
     * Call CONTRACT_SERVICE_PARAMETERS.READ with criteria
     * 
     * @param criteria
     * @return
     */
    BSCSContractService contractServiceParametersRead(BSCSContractService criteria);

    /**
     * 
     * @param coidpub
     *            contract internal id
     * @param spcodepub
     *            service package public key
     * @param sncodepub
     *            service public key
     * @param profileId
     *            profile id: mandatory
     * @param validAt
     *            date of validity (may be null)
     * @return {@link com.orange.bscs.model.contract.BSCSContractService}
     */
    List<BSCSContractServiceParameter> contractServiceParametersHistoryRead(String coidpub, String spcodepub,
            String sncodepub, Long profileId, Date validAt);

    /**
     * Writes the value of the parameter of a contracted service.
     * 
     * <pre>
     * {@code
     * CONTRACT_SERVICE_PARAMETERS.WRITE {
     *   CO_ID                =  : java.lang.Long
     *   CO_ID_PUB            =  : java.lang.String
     *   EXT_SERVICE_ID       =  : java.lang.Long
     *   EXT_SERVICE_ID_PUB   =  : java.lang.String
     *   PARAM_VALUES         = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     *  -[0]COMPLEX_LEVEL        =  : java.lang.Long
     *  -[0]COMPLEX_NO           =  : java.lang.Long
     * *-[0]COS_ACTION           =  : java.lang.Character
     *  -[0]PARENT_NO            =  : java.lang.Long
     *  -[0]PRM_NO               =  : java.lang.Long
     *  -[0]SIBLING_NO           =  : java.lang.Long
     *  -[0]TARGET_PARAM_VALUES  = sub element : com.lhs.ccb.common.soiimpl.NamedValueC
     * ontainerList
     *  -[0][0]MULT_VALUES          = sub element : com.lhs.ccb.common.soiimpl.NamedVal
     * ueContainerList
     *  -[0][0][0]VALID_FROM           =  : com.lhs.ccb.common.soiimpl.SVLDateImpl
     *  -[0][0][0]VALUE                =  : java.lang.String
     *  -[0][0][0]VALUE_DES            =  : java.lang.String
     *  -[0][0][0]VALUE_PUB            =  : java.lang.String
     *  -[0][0][0]VALUE_SEQNO          =  : java.lang.Long
     *  -[0][0]TARGET_SIBLING_NO    =  : java.lang.Long
     * * PROFILE_ID           =  : java.lang.Long
     *   SNCODE               =  : java.lang.Long
     *   SNCODE_PUB           =  : java.lang.String
     *   SPCODE               =  : java.lang.Long
     *   SPCODE_PUB           =  : java.lang.String
     *   TEMPL_ID             =  : java.lang.Long
     * }
     * => {
     * }
     * }
     * </pre>
     * 
     * @param coid
     *            a contract internal id.
     * @param coidpub
     *            a contract public key.
     * @param profileId
     *            a profile Id.
     * @param spcodepub
     *            a service package internal id.
     * @param sncodepub
     *            a service public code.
     * @param params
     *            a {@link java.util.List} of parameters.
     */
    void contractServiceParameterswrite(Long coid, String coidpub, Long profileId, String spcodepub, String sncodepub,
            List<BSCSContractServiceParameter> params);

    /**
     * execute CONTRACT_SERVICE_PARAMETERS.WRITE
     * 
     * @param input
     */
    void contractServiceParameterswrite(BSCSContractService input);

    /**
     * return a list of Parameters definitions
     *
     * <pre>{@code
     * SERVICE_PARAMETERS.READ {
     *   ACTION_ID            =  : java.lang.Long
     *   EXT_SERVICE_ID       =  : java.lang.Long
     *   EXT_SERVICE_ID_PUB   =  : java.lang.String
     *   SC_CODE              =  : java.lang.Long
     *   SC_CODE_PUB          =  : java.lang.String
     *   SNCODE               =  : java.lang.Long
     *   SNCODE_PUB           =  : java.lang.String
     * }
     * => {
     *   NUM_SERV             = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     *  -[0]SCCODE               =  : java.lang.Long
     *  -[0]SCCODE_PUB           =  : java.lang.String
     *  -[0]SNCODE               =  : java.lang.Long
     *  -[0]SNCODE_PUB           =  : java.lang.String
     * *-[0]TEMPL_IND            =  : java.lang.Boolean
     *  -[0]NUM_PARAM            = sub element : com.lhs.ccb.common.soiimpl.NamedValueC
     * ontainerList
     *  -[0][0]BSCSParameter
     * }
     *  }</pre>
     *
     * @param sccodepub a {@link java.lang.String} object.
     * @param sncodepub a {@link java.lang.String} object.
     * @return NUM_SERV
     */
    List<BSCSParameter> serviceParametersRead(String sccodepub, String sncodepub);
    
    /**
     * execute SERVICE_PARAMETERS.READ
     * 
     * @param input
     * @return
     */
    List<BSCSParameter> serviceParametersRead(BSCSService input);

    /**
     * <pre>
     * {@code
     * PARAMETER.READ {
     *   CS_ID                =  : java.lang.Long
     *   CS_ID_PUB            =  : java.lang.String
     *   SC_CODE_PUB          =  : java.lang.String
     *   PRM_ID               =  : java.lang.Long
     *   GET_VALUES           =  : java.lang.Boolean
     * }
     * => {
     *  BSCSParameter
     * }
     *  }
     * </pre>
     * 
     * return Parameter definition
     * 
     * @param csid
     * @param csidpub
     * @param sccodepub
     * @param prmId
     * @param getValues
     * @return
     */
    BSCSParameter parameterRead(Long csid, String csidpub, String sccodepub, Long prmId);

}
