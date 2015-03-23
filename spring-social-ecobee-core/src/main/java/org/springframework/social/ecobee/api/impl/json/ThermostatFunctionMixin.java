package org.springframework.social.ecobee.api.impl.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.ecobee.api.Function;
import org.springframework.social.ecobee.api.Selection;

public class ThermostatFunctionMixin extends EcobeeObjectMixin {

	public ThermostatFunctionMixin(@JsonProperty("selection") Selection selection,
			@JsonProperty("functions") List<Function> functions) {
	}

}
