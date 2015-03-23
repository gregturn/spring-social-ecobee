package org.springframework.social.ecobee.api.impl.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RuntimeMixin extends EcobeeObjectMixin {
	
	public RuntimeMixin(@JsonProperty("connected") boolean connected,
			@JsonProperty("lastModified") String lastModified,
			@JsonProperty("actualTemperature") int actualTemperature,
			@JsonProperty("actualHumidity") int actualHumidity,
			@JsonProperty("desiredHeat") int desiredHeat,
			@JsonProperty("desiredCool") int desiredCool,
			@JsonProperty("desiredHumidity") int desiredHumidity,
			@JsonProperty("desiredDehumidity") int desiredDehumidity,
			@JsonProperty("desiredFanMode") String desiredFanMode) {
	}

}
