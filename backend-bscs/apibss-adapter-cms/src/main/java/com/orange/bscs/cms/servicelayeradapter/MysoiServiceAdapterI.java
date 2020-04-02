package com.orange.bscs.cms.servicelayeradapter;

import java.util.Date;

import com.orange.bscs.model.resource.EnumDirectoryNumberStatus;

public interface MysoiServiceAdapterI {

    /**
     * Custom command.
     *
     * <code>
     * PHONE_NUMBER_STATUS.WRITE {
     * * DIRNUM               =  : java.lang.String
     * * DN_STATUS            =  : java.lang.String
     * }
     * => {
     * }
     * </code>
     *
     * @param dirnum a {@link java.lang.String} object.
     * @param newStatus a {@link com.orange.bscs.model.resource.EnumDirectoryNumberStatus} object.
     */
    void phoneNumberStatusWrite(String dirnum, EnumDirectoryNumberStatus newStatus);

    /**
     * ChangeContractExpirationDate.
     * <p>
     * CUSTOM CMS Command
     * </p>
     * 
     * <pre>
     * {@code
     * CONTRACT_EXPIRATION_DATE.WRITE {
     *   CO_ID                =  : java.lang.Long
     *   CO_ID_PUB            =  : java.lang.String
     *   CO_ADD_MONTHS        =  : java.lang.Integer
     *   CO_EXPIR_DATE        =  : com.lhs.ccb.common.soiimpl.SVLDateImpl
     * }
     * => {
     * }
     * }
     * </pre>
     * 
     * @param coId
     *            a contract internal id.
     * @param coIdPub
     *            a contract public key.
     * @param endDate
     *            a {@link java.util.Date}.
     */
    void contractExpirationDateWrite(Long coId, String coIdPub, Date endDate);


    /**
     * Custom CMS COMMAND.
     * 
     * @param coIdPub
     *            a contract internal Id.
     * @param sncodePub
     *            a service public key.
     * @param profileId
     *            a profile Id.
     * @return a {@link java.util.Date}.
     */
    Date serviceCreationDateRead(String coIdPub, String sncodePub, Long profileId);

}
