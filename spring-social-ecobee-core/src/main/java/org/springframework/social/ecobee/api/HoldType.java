package org.springframework.social.ecobee.api;

public enum HoldType {

	/**
	 * Use the provided startDate, startTime, endDate and endTime for the event.
	 * If start date/time is not provided, it will be assumed to be right now.
	 * End date/time is required.
	 */
	DATE_TIME,

	/**
	 * The end date/time will be set to the next climate transition in the program.
	 */
	NEXT_TRANSITION,

	/**
	 * The hold will not end and require to be cancelled explicitly.
	 */
	INDEFINITE,

	/**
	 * Use the value in the "holdHours" parameter to set the end date/time for the event.
	 */
	HOLD_HOURS;

}
