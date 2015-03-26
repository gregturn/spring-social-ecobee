package org.springframework.social.ecobee.api.impl.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.ecobee.api.RemoteSensorCapability;

public class RemoteSensorMixin extends EcobeeObjectMixin {

	public RemoteSensorMixin(@JsonProperty("id") String id,
			@JsonProperty("name") String name,
			@JsonProperty("type") String type,
			@JsonProperty("code") String code,
			@JsonProperty("inUse") boolean inUse,
			@JsonProperty("capability") List<RemoteSensorCapability>capability) {
	}

}
