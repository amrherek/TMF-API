package com.orange.bscs.model.contract;

import java.util.Date;
import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * 
 * <pre>{@code
 * FREE_UNIT_PACKAGES.READ {
 *   FUP_APP_METHOD       =  : java.lang.Character
 *   FUP_ASS_LEVEL        =  : java.lang.Character
 *   FU_PACK_ID           =  : java.lang.Long
 *   FU_PACK_ID_PUB       =  : java.lang.String
 *   PRG_CODE             =  : java.lang.String
 * }
 * => {
 *   FUP_LIST             = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
 * ainerList
 * *-[0]FUP_APPL_METHOD      =  : java.lang.String
 *  -[0]FUP_APPL_PERIOD_LENGTH =  : java.lang.Integer
 *  -[0]FUP_APPL_PERIOD_TYPE =  : java.lang.Character
 *  -[0]FUP_APP_METHOD       =  : java.lang.Character
 *  -[0]FUP_ASS_LEVEL        =  : java.lang.Character
 * *-[0]FUP_DES              =  : java.lang.String
 * *-[0]FUP_VERSION          =  : java.lang.Long
 *  -[0]FU_PACK_ID           =  : java.lang.Long
 *  -[0]FU_PACK_ID_PUB       =  : java.lang.String
 * }
 * }</pre>
 * 
 *
 *<pre>{@code
 * FREE_UNIT_PACKAGE_ELEMENTS.READ {
 *   FU_PACK_ID           =  : java.lang.Long
 *   FU_PACK_ID_PUB       =  : java.lang.String
 * }
 * => {
 *   FUP_LIST             = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
 * ainerList
 *  -[0]ELEM_LIST            = sub element : com.lhs.ccb.common.soiimpl.NamedValueC
 * ontainerList
 *  -[0][0]ELEMDEF_LIST         = sub element : com.lhs.ccb.common.soiimpl.NamedVal
 * ueContainerList
 * *-[0][0][0]ELEM_FREEUNITSVOLUME =  : java.lang.Float
 * *-[0][0][0]ELEM_VERSION         =  : java.lang.Long
 *  -[0][0][0]FC_CODE              =  : java.lang.String
 *  -[0][0][0]FU_FREE_UNITS_TYPE   =  : java.lang.String
 * *-[0][0][0]MAX_FREEUNITS        =  : java.lang.Float
 *  -[0][0][0]UOM_CODE             =  : java.lang.Long
 *  -[0][0][0]UOM_CODE_PUB         =  : java.lang.String
 *  -[0][0][0]UOM_SHDES            =  : java.lang.String
 * *-[0][0][0]VALID_FROM           =  : com.lhs.ccb.common.soiimpl.SVLDateImpl
 * *-[0][0]ELEM_SEQNUM          =  : java.lang.Long
 * *-[0]FUP_VERSION          =  : java.lang.Long
 *  -[0]FU_PACK_ID           =  : java.lang.Long
 *  -[0]FU_PACK_ID_PUB       =  : java.lang.String
 * }
 * 
 *}</pre>
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSFreeUnitPackage extends BSCSModel {

    /**
     * <p>getFUPackageId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getFUPackageId() {
        return getLongValue(Constants.P_FU_PACK_ID);
    }

    /**
     * <p>setFUPackageId.</p>
     *
     * @param packId a {@link java.lang.Long} object.
     */
    public void setFUPackageId(Long packId) {
        setLongValue(Constants.P_FU_PACK_ID, packId);
    }

    /**
     * <p>getFUPackageIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFUPackageIdPub() {
        return getStringValue(Constants.P_FU_PACK_ID_PUB);
    }

    /**
     * <p>setFUPackageIdPub.</p>
     *
     * @param packIdPub a {@link java.lang.String} object.
     */
    public void setFUPackageIdPub(String packIdPub) {
        setStringValue(Constants.P_FU_PACK_ID_PUB, packIdPub);
    }

    /**
     * <p>getFUPackageVersion.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getFUPackageVersion() {
        return getLongValue(Constants.P_FU_PKVER);
    }

    /**
     * <p>setFUPackageVersion.</p>
     *
     * @param version a {@link java.lang.Long} object.
     */
    public void setFUPackageVersion(Long version) {
        setLongValue(Constants.P_FU_PKVER, version);
    }

    public void setFUAssLevel(Character assignementLevel) {
        setCharacterValue(Constants.P_FUP_ASS_LEVEL, assignementLevel);
    }

    /**
     * <p>getElements.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<BSCSFUElement> getElements() {
        return getListOfBSCSModelValue(Constants.P_ELEM_LIST, BSCSFUElement.class);
    }

    public String getFUPackageDescription() {
        return getStringValue(Constants.P_FUP_DES);
    }

    public static class BSCSFUElement extends BSCSModel {

        public Long getFUEltSequenceNumber() {
            return getLongValue(Constants.P_ELEM_SEQNUM);
        }

        public void setFUPackageVersion(Long seqnum) {
            setLongValue(Constants.P_ELEM_SEQNUM, seqnum);
        }

        public List<BSCSFUElementDefinition> getDefinitions() {
            return getListOfBSCSModelValue(Constants.P_ELEMDEF_LIST, BSCSFUElementDefinition.class);
        }

    }

    public static class BSCSFUElementDefinition extends BSCSModel {

        public Date getValidFrom() {
            return getDateValue(Constants.P_VALID_FROM);
        }

        public void setValidFrom(Date validfrom) {
            setDateValue(Constants.P_VALID_FROM, validfrom);
        }

        public Double getMaxFreeUnits() {
            return getDoubleValue(Constants.P_MAX_FREEUNITS);
        }

        public void setMaxFreeUnits(Double max) {
            setDoubleValue(Constants.P_MAX_FREEUNITS, max);
        }

    }

}
