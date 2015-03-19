package com.greglturnquist.social.ecobee.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Thermostat {

	private final String identifier;
	private final String name;
	private final String lastModified;
	private final Settings settings;

}
