package org.springframework.social.ecobee.api.impl.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.ecobee.api.Thermostat;

public class ThermostatsMixin extends EcobeeObjectMixin {

	public ThermostatsMixin(@JsonProperty("thermostatList") List<Thermostat> thermostats) {
	}
}
