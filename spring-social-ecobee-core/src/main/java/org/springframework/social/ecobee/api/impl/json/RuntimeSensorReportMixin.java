package org.springframework.social.ecobee.api.impl.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.ecobee.api.RuntimeSensorMetadata;

public class RuntimeSensorReportMixin extends EcobeeObjectMixin {
	
	public RuntimeSensorReportMixin(@JsonProperty("thermostatIdentifer") String thermostatIdentifier,
			@JsonProperty("sensors") List<RuntimeSensorMetadata> sensors,
			@JsonProperty("columns") List<String> columns,
			@JsonProperty("data") List<String> data) {
	}

}
