package org.springframework.social.ecobee.api.impl.json;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FunctionMixin extends EcobeeObjectMixin {

	public FunctionMixin(@JsonProperty("type") String type,
			@JsonProperty("params") Map<String, String> params) {
	}

}
