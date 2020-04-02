package com.orange.bscs.cms.servicelayeradapter;

import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.model.businesspartner.BSCSTickler;
import com.orange.bscs.model.businesspartner.BSCSTicklerMode;
import com.orange.bscs.model.businesspartner.BSCSTicklerNew;
import com.orange.bscs.model.businesspartner.BSCSTicklerSearch;
import com.orange.bscs.model.businesspartner.BSCSTicklerSearchResult;
import com.orange.bscs.model.businesspartner.BSCSTicklerWrite;

/**
 * <pre>
 * {@code
 * TICKLER.READ
 * TICKLERS.SEARCH
 * TICKLER.NEW
 * TICKLER.WRITE
 * TICKLER.DELETE
 *
 * TICKLER_DEF.READ
 * TICKLER_STATES.READ
 * TICKLER_TRACKING_REFERENCE.READ
 * FOLLOW_UP_CODE.READ
 * }
 * @author IT&Labs
 *
 */
public interface BusinessPartnerEventManagementServiceAdapterI {

    BSCSTickler ticklerRead(Long number);
    BSCSTicklerSearchResult ticklersSearch(BSCSTicklerSearch criteria);

    BSCSTickler ticklerNew(BSCSTicklerNew newTickler);
    void ticklerWrite(BSCSTicklerWrite tickler);
    void ticklerDelete(Long number);


    List<BSCSModel> ticklerDefRead();
    BSCSModel ticklerStatesRead();
    List<BSCSTicklerMode> ticklerTrackingReferenceRead(String mode);

    List<String> followUpCodeRead();

}
