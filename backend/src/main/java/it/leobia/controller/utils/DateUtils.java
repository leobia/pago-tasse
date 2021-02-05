package it.leobia.controller.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private static final String DATE_OUTPUT_FORMAT = "dd-MM-yyyy";

	public static String formatDate(Date inputDate) {
		return internalFormatDate(inputDate, DATE_OUTPUT_FORMAT);
	}


	private static String internalFormatDate(Date inputDate, String format) {
		String output = null;
		if (inputDate != null) {
			DateFormat df = new SimpleDateFormat(format);
			output = df.format(inputDate);
		}
		return output;
	}

}
