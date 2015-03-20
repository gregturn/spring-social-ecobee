package com.greglturnquist.social.ecobee.api;

import java.util.List;

public interface ThermostatOperations {

	List<Thermostat> getThermostats();

	ThermostatSummary getThermostatSummary();
}
