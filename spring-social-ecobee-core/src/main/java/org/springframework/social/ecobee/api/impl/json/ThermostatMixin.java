package org.springframework.social.ecobee.api.impl.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.ecobee.api.EcobeeRuntime;
import org.springframework.social.ecobee.api.RemoteSensor;
import org.springframework.social.ecobee.api.Settings;

public class ThermostatMixin extends EcobeeObjectMixin {

	public ThermostatMixin(@JsonProperty("identifier") String identifier,
			@JsonProperty("name") String name,
			@JsonProperty("lastModified") String lastModified,
			@JsonProperty("settings") Settings settings,
			@JsonProperty("runtime") EcobeeRuntime runtime,
		   	@JsonProperty("remoteSensors")List<RemoteSensor> remoteSensors) {
	}

}
