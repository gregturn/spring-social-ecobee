package org.springframework.social.ecobee.api.impl.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RuntimeSensorMetadataMixin extends EcobeeObjectMixin {

	public RuntimeSensorMetadataMixin(@JsonProperty("sensorId") String sensorId,
			@JsonProperty("sensorName") String sensorName,
			@JsonProperty("sensorType") String sensorType,
			@JsonProperty("sensorUsage") String sensorUsage) {
	}

}
