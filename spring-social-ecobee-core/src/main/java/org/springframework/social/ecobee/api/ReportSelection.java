package org.springframework.social.ecobee.api;

import java.util.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@Data
@RequiredArgsConstructor
public class ReportSelection {

	private final SelectionType selection;
	private final Date startDate;
	private final Date endDate;
	private final String columns;

	public String getStartDate() {
		return EcobeeUtility.dateToString(this.startDate);
	}

	public String getEndDate() {
		return EcobeeUtility.dateToString(this.endDate);
	}

	public static ReportSelection thermostats(Date startDate, Date endDate, String columns, String... thermostatIds) {
		return new ReportSelection(new SelectionType("thermostats",
				StringUtils.arrayToCommaDelimitedString(thermostatIds)), startDate, endDate, columns);
	}

	public static ReportSelection allThermostats(Date startDate, Date endDate, String columns) {
		return new ReportSelection(new SelectionType("registered", ""), startDate, endDate, columns);
	}

}
