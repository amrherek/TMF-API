package com.orange.apibss.cucumber.tools;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.http.HttpHeaders;

import com.orange.apibss.cucumber.config.Header;
import com.orange.apibss.party.model.ContactMedium;
import com.orange.apibss.party.model.ContactMediumType;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.ProductOrder;

/**
 * Generic tools for test
 * @author Thibault Duperron
 */
public class Tools {

    /**
     * Generate a date between two bounds of year in the past
     * @param minYearInPast minimum year in past for the result
     * @param maxYearInPast maximum year in past for the result
     * @return a random date
     */
    public static LocalDate randomDate(final int minYearInPast, final int maxYearInPast){
        Random r = new Random();
        int diff = maxYearInPast-minYearInPast;
        DateTime now = DateTime.now();
        int year = now.getYear();
        if(diff > 0){
            year-=r.nextInt(diff);
        }
        year-=minYearInPast;
        DateTime time = new DateTime();
        time = time.withYear(year);
        int maxDayOfYear;
        if(now.getYear()==year){
            maxDayOfYear = time.getDayOfYear();
        } else {
            maxDayOfYear = time.dayOfYear().getMaximumValue();
        }

        int dayOfYear = r.nextInt(maxDayOfYear) + 1;
        time = time.withDayOfYear(dayOfYear);
        return time.withTimeAtStartOfDay().toLocalDate();
    }

    /**
     * Generate a date on futrue bound with maxYearInFutur
     * @param maxYearInFutur maximum year in future
     * @return a random date
     */
    public static LocalDate randomDate(final int maxYearInFutur){
        Random r = new Random();
        DateTime now = DateTime.now();
        int year = now.getYear();
        year += r.nextInt(maxYearInFutur);

        DateTime time = new DateTime();
        time = time.withYear(year);
        int minDayOfYear;
        if(now.getYear()==year){
            minDayOfYear = time.getDayOfYear();
        } else {
            minDayOfYear = 0;
        }

        int dayOfYear = r.nextInt(time.dayOfYear().getMaximumValue()-minDayOfYear) + 1 + minDayOfYear;
        time = time.withDayOfYear(dayOfYear);
        return time.withTimeAtStartOfDay().toLocalDate();
    }

    /**
     * Return a random string
     * @param maxSize max size of the string
     * @return random string
     */
    public static String randomString(int maxSize){
        String random = randomString();
        if(random.length() > maxSize)
            return random.substring(0, maxSize);
        else {
            return random;
        }
    }
    /**
     * Return a random string
     * @return random string
     */
    public static String randomString(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Find the email on a list of {@link }ContactMedium}
     * @param contacts list of mediums
     * @return the email
     * @throws IllegalArgumentException throw exception if no email found
     */
    public static String extractEmail(List<ContactMedium> contacts) throws IllegalArgumentException{
        String email = null;
        for(ContactMedium c : contacts){
            if (ContactMediumType.EMAILADDRESS.equals(c.getType())) {
                email = c.getMedium().getEmailAddress();
                break;
            }
        }
        if(null == email) {
            throw new IllegalArgumentException("No email on " + contacts);
        }
        return email;
    }

    /**
     * Return the next available order id
     * @param productOrder
     * @return
     */
    public static String getNextOrderId(ProductOrder productOrder){
        long max = 0L;
        if (productOrder.getOrderItem() != null) {
            for (OrderItem orderItem : productOrder.getOrderItem()) {
                // Finding current max value
                Long id = Long.parseLong(orderItem.getId());
                if (id > max) {
                    max = id;
                }
            }
        }
        return Long.toString(max + 1L);
    }

    public static void addHeaders(List<Header> headersToAdd, HttpHeaders headers) {
        if (headersToAdd != null) {
            for (Header header : headersToAdd) {
                headers.add(header.getName(), header.getValue());
            }
        }
    }
}
