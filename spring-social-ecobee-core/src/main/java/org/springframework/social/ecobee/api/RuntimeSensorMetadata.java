package org.springframework.social.ecobee.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RuntimeSensorMetadata {

	private final String sensorId;
	private final String sensorName;
	private final String sensorType;
	private final String sensorUsage;

}
