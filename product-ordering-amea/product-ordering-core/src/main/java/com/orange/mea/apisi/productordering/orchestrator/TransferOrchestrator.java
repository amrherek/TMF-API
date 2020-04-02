package com.orange.mea.apisi.productordering.orchestrator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.common.exceptions.badrequest.TooManyParameterException;
import com.orange.apibss.common.exceptions.technical.NotImplementedException;
import com.orange.apibss.productOrdering.model.Characteristic;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.ProductOffering;
import com.orange.apibss.productOrdering.model.ProductOrder;
import com.orange.mea.apisi.productordering.backend.TransferBackend;
import com.orange.mea.apisi.productordering.backend.TransferCreditBackend;
import com.orange.mea.apisi.productordering.backend.TransferCreditPostpaidBackend;
import com.orange.mea.apisi.productordering.backend.TransferDataBackend;
import com.orange.mea.apisi.productordering.tools.ProductOrderingTools;

@Component
public class TransferOrchestrator {

    public static final String VOLUME = "volume";
    public static final String TARGET_MSISDN = "targetPublicKey";

    public static final String TRANSFER_DATA_OPERATION_ID = "transferDataFee";
    public static final String TRANSFER_CREDIT_OPERATION_ID = "transferCreditFee";
    public static final String TRANSFER_CREDIT_POSTPAID_OPERATION_ID = "transferCreditFeePostpaid";

    @Autowired(required = false)
    private TransferDataBackend transferDataBackend;

    @Autowired(required = false)
    private TransferCreditBackend transferCreditBackend;

    @Autowired(required = false)
    private TransferCreditPostpaidBackend transferCreditPostpaidBackend;

    private void transfer(OrderItem orderItem, ProductOrder productOrder, TransferBackend transferBackend,
            String transferOperationId) throws ApiException {
        // validate data
        String msisdn = ProductOrderingTools.extractMsisdn(productOrder.getRelatedPublicKey());

        final ProductOffering productOffering = orderItem.getProductOffering();
        if (null == productOffering || StringUtils.isBlank(productOffering.getId())) {
            throw new MissingParameterException("orderItem.productOffering.id");
        }
        if (!transferOperationId.equalsIgnoreCase(productOffering.getId())) {
            throw new BadParameterValueException("orderItem.productOffering.id", productOffering.getId(),
                    transferOperationId);
        }

        if (orderItem.getProduct() == null || orderItem.getProduct().getProductCharacteristic() == null) {
            throw new MissingParameterException("orderItem.product.productCharacteristic");
        }
        String volume = null;
        String targetMsisdn = null;
        for (Characteristic car : orderItem.getProduct().getProductCharacteristic()) {
            if (VOLUME.equalsIgnoreCase(car.getName())) {
                if (volume != null) {
                    throw new TooManyParameterException("orderItem.product.productCharacteristic[" + VOLUME + "]", 1);
                }
                volume = car.getValue();
            }
            if (TARGET_MSISDN.equalsIgnoreCase(car.getName())) {
                if (targetMsisdn != null) {
                    throw new TooManyParameterException(
                            "orderItem.product.productCharacteristic[" + TARGET_MSISDN + "]", 1);
                }
                targetMsisdn = car.getValue();
            }
        }
        if (StringUtils.isBlank(volume)) {
            throw new MissingParameterException("orderItem.product.productCharacteristic[" + VOLUME + "]");
        }
        if (StringUtils.isBlank(targetMsisdn)) {
            throw new MissingParameterException("orderItem.product.productCharacteristic[" + TARGET_MSISDN + "]");
        }

        // perform transfer
        transferBackend.transfer(msisdn, targetMsisdn, volume);
    }

    /**
     * Perform a data transfer
     * 
     * @param orderItem
     * @param productOrder
     * @throws ApiException
     */
    public void transferData(OrderItem orderItem, ProductOrder productOrder) throws ApiException {
        if (transferDataBackend == null) {
            throw new NotImplementedException("Transfer data");
        }
        transfer(orderItem, productOrder, transferDataBackend, TRANSFER_DATA_OPERATION_ID);
    }

    /**
     * Perform a credit transfer
     * 
     * @param orderItem
     * @param productOrder
     * @throws ApiException
     */
    public void transferCredit(OrderItem orderItem, ProductOrder productOrder) throws ApiException {
        if (transferCreditBackend == null) {
            throw new NotImplementedException("Transfer credit");
        }
        transfer(orderItem, productOrder, transferCreditBackend, TRANSFER_CREDIT_OPERATION_ID);
    }

    /**
     * Perform a credit transfer for postpaid
     * 
     * @param orderItem
     * @param productOrder
     * @throws ApiException
     */
    public void transferCreditPostpaid(OrderItem orderItem, ProductOrder productOrder) throws ApiException {
        if (transferCreditPostpaidBackend == null) {
            throw new NotImplementedException("Transfer credit for postpaid");
        }
        transfer(orderItem, productOrder, transferCreditPostpaidBackend, TRANSFER_CREDIT_POSTPAID_OPERATION_ID);
    }

}
