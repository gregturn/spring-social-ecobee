package com.greglturnquist.social.ecobee.api.impl.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SettingsMixin extends EcobeeObjectMixin {

	public SettingsMixin(@JsonProperty("hvacMode") String hvacMode, @JsonProperty("vent") String vent) {
	}

}
