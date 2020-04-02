package com.orange.bscs.cms.servicelayeradapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.commands.BaseDAO;
import com.orange.bscs.model.Caches;
import com.orange.bscs.model.businesspartner.BSCSTickler;
import com.orange.bscs.model.businesspartner.BSCSTicklerMode;
import com.orange.bscs.model.businesspartner.BSCSTicklerNew;
import com.orange.bscs.model.businesspartner.BSCSTicklerSearch;
import com.orange.bscs.model.businesspartner.BSCSTicklerSearchResult;
import com.orange.bscs.model.businesspartner.BSCSTicklerWrite;

/**
 * <pre>
 * {@code
 * FOLLOW_UP_CODE.READ
 * TICKLER.NEW
 * TICKLER.WRITE
 * TICKLER_DEF.READ
 * TICKLERS.SEARCH
 * TICKLER_STATES.READ
 * TICKLER.READ
 * TICKLER.DELETE
 * TICKLER_TRACKING_REFERENCE.READ
 * }
 * @author IT&Labs
 * 
 */
@Repository
public class BusinessPartnerEventManagementServiceAdapter extends BaseDAO implements BusinessPartnerEventManagementServiceAdapterI {

    private static final String CMD_FOLLOW_UP_CODE_READ = "FOLLOW_UP_CODE.READ";
    private static final String P_FOLLOWUPCODES = "followupcodes";
    private static final String P_FOLLOW_UP_CODE = "FOLLOW_UP_CODE";

    public static final String CMD_TICKLER_READ = "TICKLER.READ";
    public static final String CMD_TICKLERS_SEARCH = "TICKLERS.SEARCH";
    public static final String CMD_TICKLER_NEW = "TICKLER.NEW";
    public static final String CMD_TICKLER_WRITE = "TICKLER.WRITE";
    public static final String CMD_TICKLER_DELETE = "TICKLER.DELETE";
    public static final String CMD_TICKLER_DEF_READ = "TICKLER_DEF.READ";
    public static final String CMD_TICKLER_STATES_READ = "TICKLER_STATES.READ";
    public static final String CMD_TICKLER_TRACKING_REFERENCE_READ = "TICKLER_TRACKING_REFERENCE.READ";
    
    @Override
    @Cacheable(value = Caches.REFERENTIAL, key = QUOTE + CMD_FOLLOW_UP_CODE_READ + QUOTE)
    public List<String> followUpCodeRead() {
        List<String> result = new ArrayList<String>();
        BSCSModel res = execute(CMD_FOLLOW_UP_CODE_READ);
        List<BSCSModel> lst = res.getListOfBSCSModelValue(P_FOLLOWUPCODES);
        if (null != lst && !lst.isEmpty()) {
            for (BSCSModel m : lst) {
                result.add(m.getStringValue(P_FOLLOW_UP_CODE));
            }
        }
        return result;
    }

    @Override
    public BSCSTickler ticklerRead(Long number) {
        BSCSTickler input = new BSCSTickler();
        input.setTickNumber(number);
        BSCSTickler res = execute(CMD_TICKLER_READ, input, BSCSTickler.class);

        // Read does not return number, add it
        res.setTickNumber(number);
        return res;
    }

    @Override
    public BSCSTicklerSearchResult ticklersSearch(BSCSTicklerSearch criteria) {
        BSCSModel res = execute(CMD_TICKLERS_SEARCH, criteria);
        return new BSCSTicklerSearchResult(res.getListOfBSCSModelValue("RESULT", BSCSTickler.class),
                res.getBooleanValue("SEARCH_IS_COMPLETE"));

    }


    /**
     * Store a new Tickler.
     * 
     * @param newTickler
     * @return clone of input + tickNumber.
     */
    @Override
    public BSCSTickler ticklerNew(BSCSTicklerNew newTickler) {
        BSCSTickler output = execute(CMD_TICKLER_NEW, newTickler, BSCSTickler.class);
        Long number = output.getTickNumber();

        BSCSTickler result = newTickler.cloneModel();
        result.setTickNumber(number);

        return result;
    }

    /**
     * Update/Alter a Tickler
     * 
     * 
     * <pre>{@code
     * TICKLER.WRITE {
     *   CLOSE_TICKLER        =  : java.lang.Boolean
     *   CREATION_USER        =  : java.lang.String
     *   FIRST_DISTRIB_USER   =  : java.lang.String
     *   FOLLOW_UP_CODE       =  : java.lang.String
     *   FOLLOW_UP_DATE       =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
     *   FOLLOW_UP_USER       =  : java.lang.String
     *   LAST_SEND_DATE       =  : java.lang.Boolean
     *   LAST_SEND_USER       =  : java.lang.String
     *   SECOND_DISTRIB_USER  =  : java.lang.String
     *   THIRD_DISTRIB_USER   =  : java.lang.String
     *   TICK_CODE            =  : java.lang.String
     *   TICK_LDES            =  : java.lang.String
     * * TICK_NUMBER          =  : java.lang.Long
     *   TICK_PRIORITY        =  : java.lang.Integer
     *   TICK_SHDES           =  : java.lang.String
     *   TICK_STATUS          =  : java.lang.String
     *   TICK_XCOORD          =  : java.lang.String
     *   TICK_YCOORD          =  : java.lang.String
     *   problem types        = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
     * *-[0]TR_ID                =  : java.lang.Long
     * }
     * => {
     * }
     * }</pre>
     * @author IT&Labs
     *
     */
    @Override
    public void ticklerWrite(BSCSTicklerWrite tickler) {
        execute(CMD_TICKLER_WRITE, tickler);
    }
    
    
    /**
     * Delete a tickler.
     * 
     * <pre>
     * {@code
     * TICKLER.DELETE {
     *   TICK_NUMBER          =  : java.lang.Long
     * }
     * => {
     * }
     * </pre>
     */
    @Override
    public void ticklerDelete(Long number){
        BSCSTickler input = new  BSCSTickler();
        input.setTickNumber(number);
        execute(CMD_TICKLER_DELETE,input);
    }
    

    /**
     * <pre>
     * {@code
     * TICKLER_DEF.READ {
     *   TICK_CODE            =  : java.lang.String
     * }
     * => {
     *   tickler definitions  = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
     *  -[0]DIST_USERS_OVER      =  : java.lang.Boolean
     *  -[0]FIRST_DISTRIB_USER   =  : java.lang.String
     *  -[0]FOLLOW_UP_DATE_MND   =  : java.lang.Boolean
     *  -[0]FOLLOW_UP_DATE_OVR   =  : java.lang.Boolean
     *  -[0]FOLLOW_UP_OFF        =  : java.lang.Integer
     *  -[0]FOLLOW_UP_USER       =  : java.lang.String
     *  -[0]FOLLOW_UP_USER_MND   =  : java.lang.Boolean
     *  -[0]FOLLOW_UP_USER_OVR   =  : java.lang.Boolean
     *  -[0]LONG_DESC            =  : java.lang.String
     *  -[0]LONG_DESC_MND        =  : java.lang.Boolean
     *  -[0]LONG_DESC_OVR        =  : java.lang.Boolean
     *  -[0]SECOND_DISTRIB_USER  =  : java.lang.String
     *  -[0]SHORT_DESC           =  : java.lang.String
     *  -[0]SHORT_DESC_MND       =  : java.lang.Boolean
     *  -[0]SHORT_DESC_OVR       =  : java.lang.Boolean
     *  -[0]THIRD_DISTRIB_USER   =  : java.lang.String
     *  -[0]TICK_CODE            =  : java.lang.String
     *  -[0]TICK_PRIORITY        =  : java.lang.Integer
     *  -[0]TICK_PRIORITY_OVR    =  : java.lang.Boolean
     *  -[0]TICK_STATUS          =  : java.lang.String
     *  -[0]TICK_STATUS_OVR      =  : java.lang.Boolean
     *  -[0]TRACKING_ENABLED     =  : java.lang.Boolean
     *  -[0]X_COORD_MAND         =  : java.lang.Boolean
     *  -[0]Y_COORD_MAND         =  : java.lang.Boolean
     * }
     * }
     * </pre>
     */
    @Override
    @Cacheable(value = Caches.REFERENTIAL, key = QUOTE + CMD_TICKLER_DEF_READ + QUOTE)
    public List<BSCSModel> ticklerDefRead() {
        BSCSModel output= execute(CMD_TICKLER_DEF_READ);
        return output.getListOfBSCSModelValue("tickler definitions");

    }

    /**
     * <pre>
     * {@code
     * execute TICKLER_STATES.READ, {
     * 
     *  => {
     * OPEN_STATUS = OPEN : java.lang.String
     * tickler states = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
     * -[0]TICK_STATUS = CLOSED : java.lang.String
     * -[0]TICKLER_STATUS = CLOSED : java.lang.String
     * -[1]TICK_STATUS = NOTE : java.lang.String
     * -[1]TICKLER_STATUS = NOTE : java.lang.String
     * -[2]TICK_STATUS = OPEN : java.lang.String
     * -[2]TICKLER_STATUS = OPEN : java.lang.String
     * CLOSED_STATUS = CLOSED : java.lang.String
     * }
     * }
     * </pre>
     */
    @Override
    @Cacheable(value = Caches.REFERENTIAL, key = QUOTE + CMD_TICKLER_STATES_READ + QUOTE)
    public BSCSModel ticklerStatesRead() {
        return execute(CMD_TICKLER_STATES_READ);
    }

    /**
     * 
     * <pre>
     * {@code
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
     * @param mode
     * @return
     */
    @Override
    @Cacheable(value = Caches.REFERENTIAL, key = QUOTE + CMD_TICKLER_TRACKING_REFERENCE_READ + QUOTE)
    public List<BSCSTicklerMode> ticklerTrackingReferenceRead(String mode) {
        if (null == mode) {
            return execute(CMD_TICKLER_TRACKING_REFERENCE_READ).getListOfBSCSModelValue("tracking modes", BSCSTicklerMode.class);
        } else {
            BSCSTicklerMode criteria = new BSCSTicklerMode();
            criteria.setCode(mode);
            return execute(CMD_TICKLER_TRACKING_REFERENCE_READ, criteria).getListOfBSCSModelValue("tracking modes",
                    BSCSTicklerMode.class);
        }
    }
}
