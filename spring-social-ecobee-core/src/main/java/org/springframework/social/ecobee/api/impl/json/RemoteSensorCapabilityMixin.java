package org.springframework.social.ecobee.api.impl.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoteSensorCapabilityMixin extends EcobeeObjectMixin {

	public RemoteSensorCapabilityMixin(@JsonProperty("id") String id,
			@JsonProperty("type") String type,
			@JsonProperty("value") String value) {
	}

}
