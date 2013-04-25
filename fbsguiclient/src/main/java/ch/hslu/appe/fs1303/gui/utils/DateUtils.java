package ch.hslu.appe.fs1303.gui.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
	public static SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	
	public static String getDateAsString(Date date) {
		return DATE_FORMAT.format(date);
	}
	
	public static String getDateTimeAsString(Date datetime) {
		return DATE_TIME_FORMAT.format(datetime);
	}
}
