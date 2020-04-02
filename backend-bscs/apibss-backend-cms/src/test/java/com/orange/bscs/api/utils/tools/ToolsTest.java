package com.orange.bscs.api.utils.tools;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ToolsTest {
	
	private Date date1;
	private Date date2;
	int result;
	
	
	@Before
	public void beforeTest() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			date1 = dateFormat.parse("01-01-2011");
			date2 = dateFormat.parse("31-12-2011");
	}
	
	
	@Test
	public void getDiffDatesInMonth() {

		result = Tools.getDiffDatesInMonth(date1, date2);

		 Assert.assertEquals("Diff between 1/1 and 31/12",12, result);
	}
	
	@Test
	public void should_fail_with_wrong_dates_format() {
		 Assert.assertFalse(Tools.checkDate("53210"));
		 Assert.assertFalse(Tools.checkDate("3115"));
		 Assert.assertFalse(Tools.checkDate("invalid date"));
	}
	
	@Test
	public void should_validate_right_dates_format() {
		 Assert.assertTrue(Tools.checkDate("0310"));
		 Assert.assertTrue(Tools.checkDate("0399"));
	}

}
