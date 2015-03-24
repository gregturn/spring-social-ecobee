package org.springframework.social.ecobee.api.impl.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EcobeeRuntimeMixin extends EcobeeObjectMixin {
	
	public EcobeeRuntimeMixin(@JsonProperty("connected") boolean connected,
							  @JsonProperty("lastModified") String lastModified,
							  @JsonProperty("actualTemperature") int actualTemperatureRaw,
							  @JsonProperty("actualHumidity") int actualHumidity,
							  @JsonProperty("desiredHeat") int desiredHeatRaw,
							  @JsonProperty("desiredCool") int desiredCoolRaw,
							  @JsonProperty("desiredHumidity") int desiredHumidity,
							  @JsonProperty("desiredDehumidity") int desiredDehumidity,
							  @JsonProperty("desiredFanMode") String desiredFanMode) {
	}

}
