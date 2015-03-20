package com.greglturnquist.social.ecobee.api.impl.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusMixin extends EcobeeObjectMixin {

	public StatusMixin(@JsonProperty("code") int code, @JsonProperty("message") String message) {
	}

}
