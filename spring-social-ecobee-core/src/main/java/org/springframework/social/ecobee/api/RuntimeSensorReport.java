package org.springframework.social.ecobee.api;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RuntimeSensorReport {

	private final String thermostatIdentifier;
	private final List<RuntimeSensorMetadata> sensors;
	private final List<String> columns;
	private final List<String> data;

}
