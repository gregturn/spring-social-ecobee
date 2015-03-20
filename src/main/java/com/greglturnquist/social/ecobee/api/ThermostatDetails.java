package com.greglturnquist.social.ecobee.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ThermostatDetails {

	private final String identifier;
	private final String name;
	private final boolean connected;
	private final String thermostatRevision;
	private final String alertsRevision;
	private final String runtimeRevision;
	private final String intervalRevision;

}
