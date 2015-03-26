package org.springframework.social.ecobee.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Thermostat {

	private final String identifier;
	private final String name;
	private final String lastModified;
	private final Settings settings;
	private final EcobeeRuntime runtime;
	private final List<RemoteSensor> remoteSensors;

}
