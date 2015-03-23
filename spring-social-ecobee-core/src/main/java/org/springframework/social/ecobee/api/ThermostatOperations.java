package org.springframework.social.ecobee.api;

import java.util.List;

public interface ThermostatOperations {

	Thermostat getThermostat(String identifier);

	List<Thermostat> getAllThermostats();

	ThermostatSummary getThermostatSummary();

	void resume(String identifier);

	void sendMessage(String identifier, String message);

}
