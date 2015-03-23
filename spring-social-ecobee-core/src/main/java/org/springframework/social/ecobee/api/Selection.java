package org.springframework.social.ecobee.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@Data
@RequiredArgsConstructor
public class Selection {

	private final SelectionType selection;

	public static Selection thermostats(String... thermostatIds) {
		return new Selection(new SelectionType("thermostats",
				StringUtils.arrayToCommaDelimitedString(thermostatIds)));
	}

	public static Selection allThermostats() {
		return new Selection(new SelectionType("registered", ""));
	}


}
