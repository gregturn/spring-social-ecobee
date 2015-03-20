package com.greglturnquist.social.ecobee.api.impl;

import java.util.List;

import com.greglturnquist.social.ecobee.api.Thermostat;
import com.greglturnquist.social.ecobee.api.ThermostatOperations;
import com.greglturnquist.social.ecobee.api.ThermostatSummary;
import com.greglturnquist.social.ecobee.api.Thermostats;
import org.springframework.web.client.RestTemplate;

public class ThermostatTemplate extends AbstractEcobeeOperations implements ThermostatOperations {

	private final RestTemplate restTemplate;

	public ThermostatTemplate(RestTemplate restTemplate, boolean isAuthorizedUser) {
		super(isAuthorizedUser);
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Thermostat> getThermostats() {
		return this.restTemplate.getForObject(buildUri("/thermostat"), Thermostats.class).getThermostats();
	}

	@Override
	public ThermostatSummary getThermostatSummary() {
		return this.restTemplate.getForObject(buildUri("/thermostatSummary"), ThermostatSummary.class);
	}
}
