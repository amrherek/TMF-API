package com.orange.mea.apisi.productordering.backend.bscs;

import java.util.Date;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.common.exceptions.technical.PendingRequestException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.bscs.model.BscsContractResource;
import com.orange.bscs.model.BscsStorageMedium;
import com.orange.bscs.model.criteria.BscsStorageMediumSearchCriteria;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.service.BscsContractServiceAdapter;
import com.orange.bscs.service.BscsResourceServiceAdapter;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.bscs.service.exception.BscsInvalidStateException;
import com.orange.bscs.service.exception.BscsPendingException;
import com.orange.mea.apisi.productordering.exception.SimSwapException;

/**
 * Service for managing Sim cards in BSCS
 * 
 * @author xbbs3851
 *
 */
@Service
public class BscsSimServiceFacade  {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${simswap.plcode}")
	private Long plCode;

	@Value("${simswap.submId}")
	private Long submId;

	@Value("${simswap.smcId}")
	private Long smcId;

	@Autowired
    private BscsResourceServiceAdapter resourceServiceAdapter;

	@Autowired
    private BscsContractServiceAdapter contractServiceAdapter;

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

    /**
     * Searches a sim in bscs using its number
     * 
     * @param number
     * @return
     * @throws ApiException
     */
    public BscsStorageMedium searchSim(final String number) throws ApiException {

		if (!StringUtils.isEmpty(number)) {

			// Initializing BSCS search criteria
            final BscsStorageMediumSearchCriteria criteria = bscsObjectFactory.createBscsStorageMediumSearchCriteria();

			criteria.setSubmarketId(submId);
			criteria.setNetworkId(plCode);
            criteria.setSerialNumber(number);
            criteria.setType(smcId);

			// Calling BSCS

            final List<BscsStorageMedium> foundMedia = resourceServiceAdapter.findStorageMedium(criteria);

			checkBscsMediumSearchResult(number, foundMedia);

			return foundMedia.get(0);
		}
        throw new MissingParameterException("NewSimNum");

	}

    /**
     * Replaces a ressource by another since a given date
     * 
     * @param newSimId
     * @param oldSimNumber
     * @param coId
     * @param startDate
     * @throws ApiException
     */
    public void replaceContractResource(final Long newSimId, final String oldSimNumber, final String coId,
            final Date startDate) throws ApiException {

        final BscsContractResource requestResource = bscsObjectFactory.createBscsContractResource();
        requestResource.setType(2);
        requestResource.setOldNumber(oldSimNumber);
        requestResource.setNewId(newSimId);

        requestResource.setContractId(coId);

        requestResource.setActivationDate(startDate);

        try {
            contractServiceAdapter.replaceContractResource(requestResource, true);
        } catch (BscsInvalidFieldException | BscsInvalidIdException | BscsInvalidStateException e) {
            logger.debug("Sim swap error", e);
            logger.error("Sim swap error: " + e.getMessage());
            throw new SimSwapException(e.getMessage());
        } catch (BscsPendingException e) {
            logger.debug("Sim swap error", e);
            logger.error("Sim swap impossible because of a pending request");
            throw new PendingRequestException(coId);
        }
	}

	/**
	 * Verifies if found media list is consistent
	 * 
	 * @param number
	 * @param foundMedia
	 * @throws TechnicalException
	 * @throws BadRequestException
	 */
    private void checkBscsMediumSearchResult(final String number, final List<BscsStorageMedium> foundMedia)
			throws TechnicalException, BadRequestException {
		// No result found
		if (foundMedia == null || foundMedia.isEmpty()) {
			throw new SimSwapException("Sim card not found for number  : " + number);
		}

		// More than one result found
		if (foundMedia.size() > 1) {
			throw new TechnicalException(
					"More than one result found calling STORAGE_MEDIUM.SEARCH command for number : " + number);
		}

	}

}
