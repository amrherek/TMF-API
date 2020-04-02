package com.orange.mea.apisi.productordering.backend.bscs;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.technical.PendingRequestException;
import com.orange.bscs.model.BscsContract;
import com.orange.bscs.model.BscsContractService;
import com.orange.bscs.model.businesspartner.EnumContractStatus;
import com.orange.bscs.model.contract.ContractReasonStatus;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.service.BscsContractServiceAdapter;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.bscs.service.exception.BscsInvalidStateException;
import com.orange.bscs.service.exception.BscsPendingException;
import com.orange.mea.apisi.productordering.exception.ContractStateChangeException;
import com.orange.mea.apisi.productordering.exception.MigrationException;
import com.orange.mea.apisi.productordering.exception.ServiceAddException;
import com.orange.mea.apisi.productordering.exception.ServiceStateChangeException;

/**
 * Service managing contract in bscs
 *
 * @author xbbs3851
 *
 */
@Service
public class BscsContractServiceFacade {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${profileId}")
    protected Long profileId;

    @Autowired
    protected BscsContractServiceAdapter contractServiceAdapter;

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

    /**
     * Activate a contract in BSCS
     *
     * @param coId
     * @param reason
     *            reason of suspension
     * @param requestedStartDate
     * @throws ApiException
     */
    public void activateContract(final String coId, Long reason, final Date requestedStartDate) throws ApiException {
        if (reason == null) {
            reason = ContractReasonStatus.ACTIVATING.getValue();
        }
        changeContractState(coId, requestedStartDate, EnumContractStatus.ACTIVATED, reason);
    }

    /**
     * Reactivate a contract in BSCS
     *
     * @param coId
     * @param reason
     *            reason of suspension
     * @param requestedStartDate
     * @throws ApiException
     */
    public void reactivateContract(final String coId, Long reason, final Date requestedStartDate) throws ApiException {
        if (reason == null) {
            reason = ContractReasonStatus.REACTIVATING.getValue();
        }
        changeContractState(coId, requestedStartDate, EnumContractStatus.ACTIVATED, reason);
    }


    /**
     * Suspend a contract
     *
     * @param contractId
     *            contract Id
     * @param reason
     *            reason of suspension
     * @param requestedStartDate
     *            Suspension date
     * @throws ApiException
     */
    public void suspend(final String contractId, Long reason, final Date requestedStartDate) throws ApiException {
        if (reason == null) {
            reason = ContractReasonStatus.SUSPENDING.getValue();
        }
        changeContractState(contractId, requestedStartDate, EnumContractStatus.SUSPENDED, reason);
    }

    /**
     * Add a new service. Do not commit (parameters may be added before commit)
     *
     * @param contractId
     *            contract id
     * @param serviceCode
     *            service code
     * @param servicePackageCode
     *            service package
     * @param activationDate
     *            activation date
     * @throws ApiException
     */
    public void addService(final String contractId, final String serviceCode, final String servicePackageCode,
            final Date activationDate) throws ApiException {
        final BscsContractService service = bscsObjectFactory.createBscsContractService();
        service.setCode(serviceCode);
        service.setServicePackageCode(servicePackageCode);
        service.setProfileId(profileId);
        if (activationDate != null) {
            service.setActivationDate(activationDate);
        }
        try {
            contractServiceAdapter.addContractService(contractId, service, false);
        } catch (BscsInvalidFieldException | BscsInvalidIdException | BscsInvalidStateException e) {
            logger.debug("Add service error", e);
            logger.error("Add service error: " + e.getMessage());
            throw new ServiceAddException(serviceCode, servicePackageCode, contractId, e.getMessage());
        } catch (BscsPendingException e) {
            logger.debug("Add service error", e);
            logger.error("Add service impossible because of a pending request");
            throw new PendingRequestException(contractId);
        }
    }

    /**
     * Modify a service status (activate or deactivate). Do not commit
     * (parameters may be updated before commit)
     *
     * @param contractId
     * @param serviceId
     * @param status
     * @param requestedStartDate
     * @throws ApiException
     */
    public void modifyServiceStatus(final String contractId, final String serviceId, final EnumContractStatus status,
            final Date requestedStartDate) throws ApiException {
        final BscsContractService service = bscsObjectFactory.createBscsContractService();
        service.setCode(serviceId);
        service.setProfileId(profileId);
        service.setStatus(status);
        service.setStatusDate(requestedStartDate);
        try {
            contractServiceAdapter.writeContractService(contractId, service, false);
        } catch (BscsInvalidFieldException | BscsInvalidIdException | BscsInvalidStateException e) {
            logger.debug("Modify service error", e);
            logger.error("Modify service error: " + e.getMessage());
            throw new ServiceStateChangeException(serviceId, contractId, e.getMessage());
        } catch (BscsPendingException e) {
            logger.debug("Modify service error", e);
            logger.error("Modify service impossible because of a pending request");
            throw new PendingRequestException(contractId);
        }
    }

    /**
     * Update contract state
     * 
     * @param coId
     *            contract ID
     * @param requestedStartDate
     * @param state
     * @param reason
     * @throws ApiException
     */
    private void changeContractState(final String coId, final Date requestedStartDate, final EnumContractStatus state,
            final Long reason) throws ApiException {

        final BscsContract contract = bscsObjectFactory.createBscsContract();

        try {
            contract.setId(coId);
        } catch (final NumberFormatException nfe) {
            throw new BadRequestException(coId + " must be a long");
        }

        contract.setStatus(state);

        contract.setReasonChangeStatus(reason);

        contract.setValidFrom(requestedStartDate);

        try {
            // CMS command CONTRACT.WRITE
            contractServiceAdapter.writeContract(contract, true);
        } catch (BscsInvalidFieldException | BscsInvalidIdException e) {
            logger.debug("Change contract state error", e);
            logger.error("Change contract state error: " + e.getMessage());
            throw new ContractStateChangeException(coId, e.getMessage());
        } catch (BscsPendingException e) {
            logger.debug("Change contract state error", e);
            logger.error("Change contract state impossible because of a pending request");
            throw new PendingRequestException(coId);
        }

    }

    public BscsContract getContract(String contractId) throws BscsInvalidIdException {
        return contractServiceAdapter.getContract(contractId);
    }

    /**
     * Modify rate plan for a contract
     * 
     * @param contractId
     * @param newRatePlanId
     * @throws ApiException
     */
    public void changeRatePlan(String contractId, String newRatePlanId) throws ApiException {
        // CMS command PRODUCT.CHANGE
        try {
            contractServiceAdapter.changeRatePlan(contractId, newRatePlanId, true);
        } catch (BscsInvalidIdException | BscsInvalidStateException | BscsInvalidFieldException e) {
            logger.debug("Change rate plan error", e);
            logger.error("Change rate plan error: " + e.getMessage());
            throw new MigrationException(contractId, newRatePlanId, e.getMessage());
        } catch (BscsPendingException e) {
            logger.debug("Change rate plan error", e);
            logger.error("Change rate plan impossible because of a pending request");
            throw new PendingRequestException(contractId);
        }
    }
}
