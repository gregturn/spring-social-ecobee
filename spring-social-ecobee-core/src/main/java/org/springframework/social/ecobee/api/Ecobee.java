package org.springframework.social.ecobee.api;

import org.springframework.social.ApiBinding;

public interface Ecobee extends ApiBinding {

	UserOperations userOperations();

	ThermostatOperations thermostatOperations();
}
