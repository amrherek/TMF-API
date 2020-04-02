package com.orange.mea.apisi.billingaccount.backend.bscs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.technical.EnumTechnicalException;
import com.orange.bscs.api.connection.service.BscsConnectionService;
import com.orange.bscs.api.exceptions.BscsConnectionException;
import com.orange.bscs.model.BscsBillCycle;
import com.orange.bscs.model.BscsPaymentMethod;
import com.orange.bscs.service.BscsAccountingServiceAdapter;
import com.orange.bscs.service.BscsBillingServiceAdapter;

/**
 * Service loading all payments methods and bill cycles from BSCS at startup to
 * provide them to any other service needing to use them during runtime
 * 
 * @author jwck2987
 *
 */
@Service
public class BscsCacheService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BscsConnectionService bscsConnectionService;

	@Autowired
    private BscsAccountingServiceAdapter accountingServiceAdapter;

    @Autowired
    protected BscsBillingServiceAdapter billingServiceAdapter;

	/**
     * Payment methods <br />
     * Key : public key of the payment method
     */
    private Map<String, BscsPaymentMethod> paymentMethods;

    /**
     * Bill cycles <br />
     * Key : number of the bill cycle
     */
    private Map<String, BscsBillCycle> billCycles;


	/**
	 * During server start-up, constants are loaded into memory to be used
	 * during runtime
	 */
	@PostConstruct
	public void loadConstantsFromBSCS() {

        logger.info("Loading payment methods from BSCS...");

		try {
			bscsConnectionService.openConnection();
		} catch (BscsConnectionException e) {
            logger.error("Cannot initialize constants from BSCS", e);
		}

        loadPaymentMethods();
        loadBillCycles();

        bscsConnectionService.closeConnection();

        logger.debug("payment methods from BSCS loaded");

    }

    private void loadBillCycles() {
        billCycles = new HashMap<>();

        // Getting values in BSCS
        List<BscsBillCycle> billCycleList = billingServiceAdapter.getBillCycles();

        // Parsing results and populating map
        for (BscsBillCycle billCycle : billCycleList) {
            String number = billCycle.getNumber();
            if (number != null) {
                billCycles.put(number, billCycle);
            }
        }
    }

    private void loadPaymentMethods() {
        paymentMethods = new HashMap<>();

        // Getting values in BSCS
        List<BscsPaymentMethod> bscsPaymentMethods = accountingServiceAdapter.getPaymentMethods();

        // Parsing results and populating map
        for (BscsPaymentMethod method : bscsPaymentMethods) {
            String id = method.getId();
            if (id != null) {
                paymentMethods.put(id, method);
            }
        }
	}

    /**
     * Get a BscsPaymentMethod from its code
     * 
     * @param id
     * @return
     * @throws EnumTechnicalException
     */
    public BscsPaymentMethod getPaymentMethodById(String id) throws EnumTechnicalException {
        BscsPaymentMethod result = paymentMethods.get(id);

		if (result == null) {
            logger.error("Payment method not found for id: [{}]", id);
            throw new EnumTechnicalException("Payment method not found for id: " + id);
		}

		return result;
	}

    /**
     * Get a BscsBillCycle from its number
     * 
     * @param billCycleNumber
     * @return
     * @throws EnumTechnicalException
     */
    public BscsBillCycle getBillCycle(String billCycleNumber) throws EnumTechnicalException {
        BscsBillCycle result = billCycles.get(billCycleNumber);

        if (result == null) {
            logger.error("Bill cycle not found for number: [{}]", billCycleNumber);
            throw new EnumTechnicalException("Bill cycle not found for number: " + billCycleNumber);
        }

        return result;
    }

}
