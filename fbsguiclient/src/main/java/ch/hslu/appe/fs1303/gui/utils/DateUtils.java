package ch.hslu.appe.fs1303.gui.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static SimpleDateFormat DATE_FORMAT;
	public static SimpleDateFormat DATE_TIME_FORMAT;
	
	static {
		DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
		DATE_FORMAT.setLenient(false);
		DATE_TIME_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		DATE_TIME_FORMAT.setLenient(false);
	}
	
	public static String getDateAsString(Date date) {
		if (date == null) return "";
		return DATE_FORMAT.format(date);
	}
	
	public static String getDateTimeAsString(Date datetime) {
		if (datetime == null) return "";
		return DATE_TIME_FORMAT.format(datetime);
	}
}
