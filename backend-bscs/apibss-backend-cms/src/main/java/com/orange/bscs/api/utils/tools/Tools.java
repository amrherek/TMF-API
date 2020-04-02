package com.orange.bscs.api.utils.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.orange.bscs.api.utils.config.Constants;

public final class Tools {
	
	private static final String BIRTHDATE_FORMAT = "yyyy-MM-dd";


	/**
	 * Data type factory for convert a date into XMLGregorianCalendar.
	 */
	private static DatatypeFactory datatypeFactory;

	
	/** Constant, not often used and without modification (in some map), No need to duplicate for each call.
	 **/
	private static Date dateByDefault;
	
	/**
	 * Initialization of a data type factory in the goal to have only one
	 * instance of factory for convert a date into XMLGregorianCalendar.
	 */
	static {
		try {
			datatypeFactory = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		
		try {
			dateByDefault = new SimpleDateFormat(Constants.CST_DATE_BY_DEFAULT_FORMAT).parse(Constants.CST_DATE_BY_DEFAULT);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/** Hide utility class constructor */
	private Tools(){}
	
	
	
	/**
	 * @author YBOULSAN
	 * @param parameter
	 * @return true if the param string is null or equals to "" 
	 */
	public static boolean isNullOrEmpty(String parameter) {
		return parameter == null || parameter.equals("");
	}
	
	
	/**
	 * @author EASSA
	 * @param parameter
	 * @return true if the param  is null. This simple method centralizes the input reqpuest checking "" 
	 */
	public static boolean isNull(String parameter){
		return parameter == null;
	}
	
	
	/**
	 * @author VALLEAUM
	 * @param parameter
	 * @return true if the parameter is empty (equals to "")
	 */
	public static boolean isEmpty(String parameter)
	{
		return Constants.CST_EMPTY.equals(parameter);
	}
	
	/**
	 * @author YBOULSAN
	 * @param list
	 * @param param
	 * @return a value linked to a key in a list of Strings like key+separator+value or value+separator+key
	 */
	public static String getParamFromList(String [] tab, String param, String separator) {
		
		String result = null;
		
		String[] cursorSplited;
		String key;
		String value;
		
		for (String cursor : tab) {
			// the separator between keys and values is "|"
			cursorSplited = cursor.split(separator);
			key = cursorSplited[0];
			value = cursorSplited[1];
			
			if (key.equals(param)) {
				result = value;
				break;
			}
			else if(value.equals(param)) {
				result = key;
				break;
			}
			
		}
		return result;
	}
	
	/**
	 * This function checks if the date matches with format date in BSCS (MMYY)
	 * example of correct dates: "0310"; "0399"..
	 * example of incorrect dates: "0010"; "53210"; "3115"; .. 
	 * @author VALLEAUM
	 * @param date 
	 * @return true if the format of date is correct
	 */
	public static boolean checkDate(String date)
	{
		boolean res = true;
		int dateInt;
		try {
			dateInt = Integer.parseInt(date);
			int year = dateInt % 100;
			int month= dateInt / 100;
			if (month<1 || month>12){
				res = false;
			}			
			if (year<0 || year>99){
				res = false;
			}			
			return res;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 * Verifies that a given date as String matches a birth date format (yyyy-MM-dd).
	 * 
	 * @param birthDate the date to check
	 * @return {@code true} if the string has the right format
	 */
	public static Date parseBirthDate(String birthDate) {
		try {
		    return buildDateFormat(BIRTHDATE_FORMAT).parse(birthDate);
		} catch (ParseException exception) {
			return null;
		}
	}

	/**
	 * Builds a new {@code SimpleDateFormat} with a specified pattern.
	 * <p>
	 * {@code SimpleDateFormat} class is not threadsafe, therefore a format is created with each use.
	 * It could be optimized with a thread local cached format.
	 * 
	 * @param dateFormat date format pattern
	 * @return the newly created date format
	 */
	private static SimpleDateFormat buildDateFormat(String dateFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		formatter.setLenient(false);
		return formatter;
	}
	
	/**
	 * @author otaktak
	 * @param java.Util.Date
	 * @return XMLGregorianCalendar
	 * @throws DatatypeConfigurationException
	 * This method converts a java.Util.Date object into an XMLGregorianCalendar object
	 * which may be necessary to meet the WSDL and XSD specifiactions of data model
	 */
	public static XMLGregorianCalendar dateToXMLGregorianCalendar(Date date) throws DatatypeConfigurationException{
		if(null==date){
			return null;
		}
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);		
		return datatypeFactory.newXMLGregorianCalendar(calendar);
	}
	
	/**
	 * @author otaktak
	 * @param java.Util.Date
	 * @return XMLGregorianCalendar
	 * @throws DatatypeConfigurationException
	 * This method converts  an XMLGregorianCalendar object into a java.Util.Date object.
	 */
	public static Date xmlGregorianCalendarToDate(XMLGregorianCalendar xmlGregorianCalendar){
	    if(null==xmlGregorianCalendar){ return null;}
		return xmlGregorianCalendar.toGregorianCalendar().getTime();
	}


	/**
	 * 
	 * @param paymentMeans : Constants.CST_CASH_CARD, Constants.CST_DIRECT_DEBIT or Constants.CST_BANK_TRANSFER or Constants.CST_CASH_PM
	 * @return the payment code of this spec. "" if not found
	 */
	public static String getPaymentMeansCodeFromSpec(String paymentMeans)
	{
		String res = "";
		
		if (paymentMeans.equals(Constants.CST_CASH_CARD)){
			res = Constants.CST_CASH_CARD_CODE;
		}
		
		if (paymentMeans.equals(Constants.CST_DIRECT_DEBIT)){
			res = Constants.CST_DIRECT_DEBIT_CODE;
		}
			
		if (paymentMeans.equals(Constants.CST_BANK_TRANSFER)){
			res = Constants.CST_BANK_TRANSFER_CODE;
		}
			
		if (paymentMeans.equals(Constants.CST_CASH_PM) || paymentMeans.equals(Constants.CST_CHEQUE_PM)
				|| paymentMeans.equals(Constants.CST_OTHER_PM)) {
			res = Constants.CST_CASH_PM_CODE;
		}
			
		return res;
		
	}

	
	/**
	 * @author YBOULSAN
	 * @param dateOne
	 * @param dateTwo
	 * @return the difference in month between two dates
	 */
    public static int getDiffDatesInMonth(Date dateOne, Date dateTwo) { 

    	GregorianCalendar date1 = new GregorianCalendar();  
        GregorianCalendar date2 = new GregorianCalendar();
        long a = 1000;
        long b = 60;
        long c = 24;
        long d = 30;
    	
        int result = 0;
        
        date1.setTime(dateOne);
        date2.setTime(dateTwo);  
        
        long diff = (date1.getTimeInMillis() - date2.getTimeInMillis())/(a*b*b*c);
        result = (int) Math.abs(diff / d);
        
        return result;
    } 
    
	/**
	 * @author euassa
	 * @param party ID and sequence number concated as following CUST_PUB|SEQ_NUM
	 * @return String Array
	 * This method split a string paramater formated as following string1|string2
	 * Result is a String array with the both strings or null is the input parameter does not match
	 */
		public static String[] splitPartyId(String parameter) {
			String[] empty = new String[]{};
			
			if(null==parameter){
				return empty;
			}
			
			boolean pipeisfound = parameter.indexOf('|') > -1 ;
			String[] strtab = parameter.split("\\|");
			
			if(pipeisfound && strtab.length==1){
				return empty;
			}
					
			
			if(strtab ==null||strtab.length>2||strtab.length<1 || parameter.length() <1 ){
				return empty;
			}
				
			
			//Check if the sequence number is numeric
			if(strtab.length > 1 && !isIntNumber(strtab[1])){
				return empty;
			}
			return strtab;
		}
		
		/**
		 * @author euassa
		 * @param String
		 * @return boolean
		 * This method check if the given string is integer
		 */
				
	public static boolean isIntNumber(String num){
		    try{
		        Integer.parseInt(num);
		    } catch(NumberFormatException nfe) {
		        return false;
		    }
		    return true;
		}
	
	
	/**
	 * 
	 * @param param
	 * @return the param between quotes
	 */
	public static String betweenQuotes(String param) {
		
		return Constants.CST_QUOTE + param + Constants.CST_QUOTE ;
	}
    
	/**
	 * 
	 * @param double
	 * @param round
	 * @return double with <$round> number of decimals
	 */
	public static double round(double initial, int round){
		  double p = Math.pow(10,round);
		  double mult = initial * p;
		  float tmp = Math.round(mult);
		  return tmp/p;
	}
	
	public static Date getDefaultDate()
	{
		return (Date)dateByDefault.clone();
	}


	public static String getDaysOfMonth(String yymmDate) {
		// Get the year from input
		String result = yymmDate;
		String inputYear = yymmDate.substring(0,2);
		String inputMonth = yymmDate.substring(2);
		int month = Integer.parseInt(inputMonth);
		
		// Get the two first days of the year
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		int thisYear = calendar.get(Calendar.YEAR);
		String yy = Integer.toString(thisYear).substring(0, 2);
		yy += inputYear;
		int finalYear = Integer.parseInt(yy);
		calendar.set(Calendar.YEAR, finalYear);
		
		String nbOfDays = Constants.CST_EMPTY;
		// return the YYMMDD date format, where DD is the last day of MM
		
		int monthAsCalendar=month-1;
		switch (monthAsCalendar){
		case Calendar.JANUARY:
		case Calendar.MARCH:
		case Calendar.MAY:
		case Calendar.JULY:
		case Calendar.AUGUST:
		case Calendar.OCTOBER:
		case Calendar.DECEMBER:
			nbOfDays = Constants.CST_THIRTY_ONE_DAYS;
			break;
		case Calendar.FEBRUARY:
			if(calendar.isLeapYear(finalYear)){
				nbOfDays = Constants.CST_TWENTY_NINE_DAYS;
			}
			else{
				nbOfDays = Constants.CST_TWENTY_EIGHT_DAYS;
			}
			break;
		default:
			nbOfDays = Constants.CST_THIRTY_DAYS;
		}
		result += nbOfDays;
		
		return result;		
	}	
	
	public static String concatenateString(String stg1, String stg2){
		return concatenateString(new StringBuilder(), stg1, stg2);
	}
	
	public static String concatenateString(StringBuilder buf, String stg1, String stg2){		
		
		buf.append(stg1);
		buf.append(Constants.CST_PIPE);
		buf.append(stg2);
		String res = buf.toString();
		buf.delete(0, buf.length());
		
		return res;
		
	}

	/**
	 * Safe casting of a long value to an int value.
	 * NOTE ! This method was created when correcting the defect below :
	 * Defect #72 FindAndGetLoyaltyAccount - Burn history
	 * @param l the long to cast to int.
	 * @return the int value of the long casting.
	 * @throws IllegalArgumentException throw if the long parameter is lower
	 * than Integer.MIN_VALUE or greater than Integer.MAX_VALUE.
	 */
	public static int safeLongToInt(long l) {
		if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
	        throw new IllegalArgumentException(l + " cannot be cast to int.");
	    }
	    return (int) l;
	}
	
}