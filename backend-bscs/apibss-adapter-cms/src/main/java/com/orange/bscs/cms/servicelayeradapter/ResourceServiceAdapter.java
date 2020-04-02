package com.orange.bscs.cms.servicelayeradapter;

import static com.orange.bscs.api.utils.config.Constants.CMD_PORT_HISTORY_READ;
import static com.orange.bscs.api.utils.config.Constants.P_CO_ID;
import static com.orange.bscs.api.utils.config.Constants.P_CO_ID_PUB;
import static com.orange.bscs.api.utils.config.Constants.P_RETURNED_PORTS;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.api.utils.tools.Tools;
import com.orange.bscs.commands.BaseDAO;
import com.orange.bscs.model.resource.BSCSDirectoryNumber;
import com.orange.bscs.model.resource.BSCSDirectoryNumberSearch;
import com.orange.bscs.model.resource.BSCSPort;
import com.orange.bscs.model.resource.BSCSPortSearch;
import com.orange.bscs.model.resource.BSCSStorageMedium;
import com.orange.bscs.model.resource.BSCSStorageMediumSearch;

/**
 * <pre>
 * {@code
 * CUG_INTERLOCKS.SEARCH
 * CUG_AGREEMENT.READ
 * CUG_AGREEMENT.WRITE
 * CUG_AGREEMENTS.SEARCH
 * CUG_INTERNAL_MEMBERS.SEARCH
 * CUG_EXTERNAL_MEMBERS.READ
 * CUG_EXTERNAL_MEMBER.WRITE
 * DIRECTORY_NUMBER_TYPES.READ
 * DN_CONTRACT_HISTORY.READ
 * DN_HISTORY.READ
 * NUMBERING_PLANS.SEARCH
 * PHONE_NUMBER.EXPORT
 * PHONE_NUMBER.IMPORT
 * PHONE_NUMBER.REIMPORT
 * POOL_ISSUER.READ
 * PORT_CONTRACT_HISTORY.READ
 * PORT.CREATE
 * PORT_HISTORY.READ
 * PORTS.SEARCH
 * SM_CONTRACT_HISTORY.READ
 * SM_HISTORY.READ
 * STORAGE_MEDIUM_CLASS.READ
 * STORAGE_MEDIUM.CREATE
 * STORAGE_MEDIUM.SEARCH
 * VIRTUAL_PRIVATE_NETWORKS.READ
 * VANITY_NUMBER.CONVERT
 * }</pre>
 * 
 * @author IT&Labs
 *
 */
@Repository
public class ResourceServiceAdapter extends BaseDAO implements ResourceServiceAdapterI {

    private static final String DIR_READ = "GENERIC_DIRECTORY_NUMBER.READ";
    private static final String DIR_SEARCH = "GENERIC_DIRECTORY_NUMBER.SEARCH";

    private static final String SM_SEARCH = "STORAGE_MEDIUM.SEARCH";
    
    private static final String PORTS_SEARCH = "PORTS.SEARCH";

    
    /** {@inheritDoc} */
    @Override
    public List<BSCSModel> portHistoryRead(Long coId, String coIdPub) {
        final String cmd = CMD_PORT_HISTORY_READ;
    
        if (null == coId && Tools.isNullOrEmpty(coIdPub)) {
            throw new SOIException(String.format(ERR_RC2000, cmd, P_CO_ID));
        }
    
        BSCSModel portModel = new BSCSModel();
        if (!Tools.isNullOrEmpty(coIdPub)) {
            portModel.setStringValue(P_CO_ID_PUB, coIdPub);
        } else {
            portModel.setLongValue(P_CO_ID, coId);
        }
    
        portModel.setStringValue(P_CO_ID_PUB, coIdPub);
        BSCSModel portOutputModel = execute(cmd, portModel);
        return portOutputModel.getListOfBSCSModelValue(P_RETURNED_PORTS);
    }

    /** {@inheritDoc} */
    @Override
    public BSCSDirectoryNumber genericDirectoryNumberRead(Long id) {
        BSCSDirectoryNumber input = new BSCSDirectoryNumber();
        input.setId(id);
    
        return execute(DIR_READ, input, BSCSDirectoryNumber.class);
    }

    /** {@inheritDoc} */
    @Override
    public List<BSCSDirectoryNumber> genericDirectoryNumberSearch(BSCSDirectoryNumberSearch criteria) {
        BSCSModel result = execute(DIR_SEARCH, criteria, BSCSModel.class);
        return result.getListOfBSCSModelValue(Constants.P_DIRECTORYNUMBERS, BSCSDirectoryNumber.class);
    }

    /** {@inheritDoc} */
    @Override
    public BSCSDirectoryNumber genericDirectoryNumberSearch(String dirnum, Long npCode, Long plCode, Long submid) {
        BSCSDirectoryNumberSearch input = new BSCSDirectoryNumberSearch();
        input.setPhoneNumber(dirnum);
        input.setNumberingPlanId(npCode);
        input.setNetworkId(plCode);
        input.setSubmarketId(submid);
        List<BSCSDirectoryNumber> l = genericDirectoryNumberSearch(input);
    
        BSCSDirectoryNumber result = null;
        if (null != l && 0 < l.size()) {
            result = l.get(0);
        }
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public void phoneNumberExport(String phoneNumber, Long npCode, Long destPlCode, Date portingDate) {
        BSCSDirectoryNumber input = new BSCSDirectoryNumber();
        input.setPhoneNumber(phoneNumber);
        input.setNumberingPlanId(npCode);
        input.setLongValue(Constants.P_DEST_PLCODE, destPlCode);
        input.setPortingDate(portingDate);
    }

    /** {@inheritDoc} */
    @Override
    public BSCSDirectoryNumber phoneNumberExportSearch(String phoneNumber) {
        BSCSDirectoryNumber result = null;
    
        BSCSDirectoryNumber input = new BSCSDirectoryNumber();
        input.setPhoneNumber(phoneNumber);
    
        BSCSModel output = execute(Constants.CMD_PHONE_NUMBER_EXPORT_SEARCH, input, BSCSModel.class);
        List<BSCSDirectoryNumber> l = output.getListOfBSCSModelValue(Constants.P_DIRECTORYNUMBERS, BSCSDirectoryNumber.class);
        if (null!=l && !l.isEmpty()) {
            result = l.iterator().next();
        }
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public void phoneNumberReImport(String number, Long npcode, Date portingDate) {
        BSCSDirectoryNumber input = new BSCSDirectoryNumber();
        input.setPhoneNumber(number);
        input.setNumberingPlanId(npcode);
        input.setPortingDate(portingDate);
    
        execute(Constants.CMD_PHONE_NUMBER_IMPORT, input);
    }

    /** {@inheritDoc} */
    @Override
    public List<BSCSStorageMedium> storageMediumSearch(BSCSStorageMediumSearch criteria) {

        BSCSModel result = ConnectionHolder.getCurrentConnection().execute(SM_SEARCH, criteria, BSCSModel.class);
        return result.getListOfBSCSModelValue(Constants.P_ALL_STORAGE_MEDIUMS, BSCSStorageMedium.class);

    }
    
    /** {@inheritDoc} */
    @Override
    public List<BSCSPort> portSearch(BSCSPortSearch criteria) {

        BSCSModel result = ConnectionHolder.getCurrentConnection().execute(PORTS_SEARCH, criteria, BSCSModel.class);
        return result.getListOfBSCSModelValue(P_RETURNED_PORTS, BSCSPort.class);
    }

    @Override
    public void resourceStateWrite(BSCSModel input) {
        execute(Constants.CMD_RESOURCE_STATE_WRITE,input);
    }
    
}
