package com.orange.mea.apisi.billingaccount.backend.bscs;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.billingaccount.model.BillStructure;
import com.orange.apibss.billingaccount.model.BillingAccount;
import com.orange.apibss.billingaccount.model.BillingCycleSpecificationRef;
import com.orange.apibss.billingaccount.model.CustomerBill;
import com.orange.apibss.billingaccount.model.PaymentMethodRef;
import com.orange.apibss.billingaccount.model.StateValues;
import com.orange.apibss.common.exceptions.technical.EnumTechnicalException;
import com.orange.bscs.model.BscsBillCycle;
import com.orange.bscs.model.BscsBillingAccount;
import com.orange.bscs.model.BscsCustomer;
import com.orange.bscs.model.BscsPaymentArrangement;
import com.orange.bscs.model.BscsPaymentMethod;
import com.orange.bscs.model.billing.BSCSBillCycle.EnumBillCycleIntervalType;
import com.orange.bscs.model.billing.EnumBillingAccountStatus;
import com.orange.mea.apisi.billingaccount.backend.bscs.service.BscsCacheService;

/**
 * <p>
 * It takes a ServiceRetrieve object , result of six calls to bscs in input, and
 * return an BillingAccount object in output.
 * </p>
 * 
 * @author ecus6396
 */
@Service
public class BscsToBillingAccountTransformer {

    @Autowired
    private BscsCacheService bscsCacheService;

    public BillingAccount doTransform(BscsBillingAccount billingAccountRead,
            BscsPaymentArrangement paymentArrangement, BscsCustomer customer) throws EnumTechnicalException {
		BillingAccount billingAccount = new BillingAccount();
        if (billingAccountRead != null) {
            billingAccount.setId(billingAccountRead.getCode());
            // only 1 billing account version because we set read mode to 'latest'
            billingAccount.setState(transformStatus(billingAccountRead.getVersions().get(0).getStatus()));
        }

        CustomerBill bill = new CustomerBill();
        if (customer.getLastBillingCycleDate() != null) {
            bill.setBillDate(new DateTime(customer.getLastBillingCycleDate()));
            billingAccount.setLastCustomerBill(bill);
        }
        if (customer.getBillingCycle() != null) {
            BillStructure billStructure = new BillStructure();
            BillingCycleSpecificationRef cycleSpec = new BillingCycleSpecificationRef();
            BscsBillCycle bscsBillCycle = bscsCacheService.getBillCycle(customer.getBillingCycle());
            cycleSpec.setFrequency(transformPeriod(bscsBillCycle.getLength(), bscsBillCycle.getIntervalType()));
            Calendar nextBillDate = nextBillDate(customer.getLastBillingCycleDate(), bscsBillCycle.getLength(),
                    bscsBillCycle.getIntervalType());
            if (nextBillDate != null) {
                bill.setNextBillDate(new DateTime(nextBillDate.getTime()));
                // extract day
                cycleSpec.setDateShift(nextBillDate.get(Calendar.DAY_OF_MONTH));
            }
            billStructure.setCycleSpecification(cycleSpec);
            billingAccount.setBillStructure(billStructure);
        }

        if (null != paymentArrangement) {
            PaymentMethodRef paymentMean = new PaymentMethodRef();
            BscsPaymentMethod method = bscsCacheService
                    .getPaymentMethodById(paymentArrangement.getPaymentMethodId());
            paymentMean.setName(method.getDescription());
            billingAccount.setDefaultPaymentMethod(paymentMean);
		}

        return billingAccount;
	}

    private StateValues transformStatus(EnumBillingAccountStatus status) {
        StateValues res = null;
        if (status != null) {
            switch (status) {
            case ACTIVE:
                res = StateValues.ACTIVE;
                break;
            case CLOSED:
                res = StateValues.CLOSED;
                break;
            case INACTIVE:
                res = StateValues.INACTIVE;
                break;
            default:
                break;
            }
        }
        return res;
    }

    private String transformPeriod(Integer interval, EnumBillCycleIntervalType intervalType) {
        if (interval == null || intervalType == null) {
            return null;
        }
        String res = interval.toString();
        switch (intervalType) {
        case DAYS:
            res += " day";
            break;
        case MONTHS:
            res += " month";
            break;
        }
        return res;
    }

    private Calendar nextBillDate(Date date, Integer length, EnumBillCycleIntervalType interval) {
        Calendar cal = null;
        if (date != null) {
            cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            cal.setTime(date);
            // calculate next bill date
            if (interval == EnumBillCycleIntervalType.DAYS) {
                // add interval days to last bill cycle
                cal.add(Calendar.DAY_OF_MONTH, length);
            } else if (interval == EnumBillCycleIntervalType.MONTHS) {
                // add interval months to last bill cycle
                cal.add(Calendar.MONTH, length);
            }
        }
        return cal;
    }


}
