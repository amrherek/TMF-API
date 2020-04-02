package com.orange.mea.apisi.customerbill.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;

/**
 * The criteria to list customer bills
 *
 * @author Denis Borscia (deyb6792)
 */
public class CustomerBillCriteria {


    /**
     * Customer Bill API parameters
     */

    private Date startDate;
    private Date endDate;
    private Integer wishedResults;

    public CustomerBillCriteria(final Date startDate, final Date endDate, final Integer limit) {
        setStartDate(startDate);
        setEndDate(endDate);
        setWishedResults(limit);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    public void setWishedResults(final Integer wishedResults) {
        this.wishedResults = wishedResults;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Integer getWishedResults() {
        return wishedResults;
    }

    /**
     * Validate that information in the context is correct :
     * <ul>
     * <li>there is a non empty msisdn</li>
     * <li>if an endDate is specified it must be greater than the startDate (if set) </li>
     * </ul>
     *
     * @throws BadParameterValueException if criteria are not correct
     */
    public void validate() throws BadParameterValueException {
        if (endDate != null && startDate != null && endDate.before(startDate)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            throw new BadParameterValueException("endDate", dateFormat.format(endDate),
                    "after " + dateFormat.format(startDate));
        }
        if (wishedResults != null && wishedResults != -1 && wishedResults < 0) {
            throw new BadParameterValueException("limit", wishedResults.toString(), "a positive integer or -1");
        }
    }

    public void transformLimit(Integer defaultLimit) {
        if (wishedResults == null) {
            // default limit
            wishedResults = defaultLimit;
        } else if (wishedResults == -1) {
            // no limit
            wishedResults = null;
        }
    }
}
