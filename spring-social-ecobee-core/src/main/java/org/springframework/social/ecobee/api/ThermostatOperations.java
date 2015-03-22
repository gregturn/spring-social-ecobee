package org.springframework.social.ecobee.api;

import java.util.List;

public interface ThermostatOperations {

	Thermostat getThermostat(String identifier) throws Exception;

	List<Thermostat> getThermostats() throws Exception;

	ThermostatSummary getThermostatSummary() throws Exception;
}
