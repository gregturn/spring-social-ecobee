package org.springframework.social.ecobee.api;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class EcobeeUtility {

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	private EcobeeUtility() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static String dateToString(Date date) {
		return DATE_FORMAT.format(date);
	}

}
