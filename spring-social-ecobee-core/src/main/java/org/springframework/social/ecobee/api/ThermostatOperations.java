package org.springframework.social.ecobee.api;

import java.util.Date;
import java.util.List;

public interface ThermostatOperations {

	Thermostat getThermostat(String identifier);

	List<Thermostat> getAllThermostats();

	ThermostatSummary getThermostatSummary();

	List<RuntimeSensorReport> getAllSensorReports(Date startDate, Date endDate, String columns, String... identifiers);

	void resume(String identifier);

	void sendMessage(String identifier, String message);

}
