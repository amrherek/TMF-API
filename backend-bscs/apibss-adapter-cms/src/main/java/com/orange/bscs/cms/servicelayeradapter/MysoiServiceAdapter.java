package com.orange.bscs.cms.servicelayeradapter;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.api.utils.tools.Tools;
import com.orange.bscs.commands.BaseDAO;
import com.orange.bscs.model.resource.BSCSDirectoryNumber;
import com.orange.bscs.model.resource.EnumDirectoryNumberStatus;

@Repository
public class MysoiServiceAdapter extends BaseDAO implements MysoiServiceAdapterI {

    /** {@inheritDoc} */
    @Override
    public void phoneNumberStatusWrite(String dirnum, EnumDirectoryNumberStatus newStatus) {
        BSCSDirectoryNumber input = new BSCSDirectoryNumber();
        input.setPhoneNumber(dirnum);
        input.setStatusCode(newStatus);
        execute(Constants.CMD_PHONE_NUMBER_STATUS_WRITE, input, BSCSModel.class);
    }

    /** {@inheritDoc} */
    @Override
    public void contractExpirationDateWrite(Long coId, String coIdPub, Date endDate) {
        final String cmd = Constants.CMD_CONTRACT_EXPIRATION_DATE_WRITE;
    
        if (null == coId && Tools.isNullOrEmpty(coIdPub)) {
            throw new SOIException(String.format(ERR_RC2000, cmd, Constants.P_CO_ID));
        }
    
        BSCSModel contractExpirationDateWriteInputModel = new BSCSModel();
        if (!Tools.isNullOrEmpty(coIdPub)) {
            contractExpirationDateWriteInputModel.setStringValue(Constants.P_CO_ID_PUB, coIdPub);
        } else {
            contractExpirationDateWriteInputModel.setLongValue(Constants.P_CO_ID, coId);
        }
    
        contractExpirationDateWriteInputModel.setDateValue(Constants.P_CO_EXPIR_DATE, endDate);
    
        // --- execute the CMS command CMD_CONTRACT_EXPIRATION_DATE_WRITE
        execute(cmd, contractExpirationDateWriteInputModel);
    }



    /**
     * {@inheritDoc}
     *
     * Custom CMS COMMAND : SERVICE_CREATION_DATE.READ
     * SERVICE_CREATION_DATE.READ { CO_ID = : java.lang.Long CO_ID_PUB = :
     * java.lang.String SNCODE = : java.lang.Long SNCODE_PUB = :
     * java.lang.String } => { ENTRY_DATE = :
     * com.lhs.ccb.common.soiimpl.SVLDateImpl }
     */
    @Override
    public Date serviceCreationDateRead(String contractId, String sncodePub, Long profileId) {
        BSCSModel input = initContractModel(null, contractId, Constants.CMD_SERVICE_CREATION_DATE_READ);
        if(StringUtils.isBlank(sncodePub)){
            throw new SOIException(String.format(ERR_RC2000, Constants.CMD_SERVICE_CREATION_DATE_READ, Constants.P_SNCODE_PUB));
        }
        input.setStringValue(Constants.P_SNCODE_PUB, sncodePub);
        BSCSModel output = execute(Constants.CMD_SERVICE_CREATION_DATE_READ, input);
        Date res = null;
        if (null != output) {
            res = output.getDateValue(Constants.P_ENTRY_DATE);
        }
        return res;
    }

}
