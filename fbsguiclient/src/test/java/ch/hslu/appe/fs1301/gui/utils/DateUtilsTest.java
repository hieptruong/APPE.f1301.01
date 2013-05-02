package ch.hslu.appe.fs1301.gui.utils;

import static org.fest.assertions.Assertions.assertThat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import ch.hslu.appe.fs1303.gui.utils.DateUtils;

public class DateUtilsTest {

	@Test
	public void returnsCorrectFormattedDate() {
		final String ExpectedString = "10.12.2012";
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.set(2012, 11, 10); // Month is Zero based ... Yeah it is ...
		Date date = cal.getTime();
		
		String result = DateUtils.getDateAsString(date);
		
		assertThat(result).isEqualTo(ExpectedString);
	}
	
	@Test
	public void returnsCorrectFormattedDateTime() {
		final String ExpectedString = "10.12.2012 07:23:10";
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.set(2012, 11, 10, 07, 23, 10); // Month is Zero based ... Yeah it is ...
		Date date = cal.getTime();
		
		String result = DateUtils.getDateTimeAsString(date);
		
		assertThat(result).isEqualTo(ExpectedString);
	}
}
