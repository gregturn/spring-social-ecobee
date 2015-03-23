package org.springframework.social.ecobee.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EcobeeRuntime {

	private final boolean connected;
	private final String lastModified;
	private final int actualTemperature;
	private final int actualHumidity;
	private final int desiredHeat;
	private final int desiredCool;
	private final int desiredHumidity;
	private final int desiredDehumidity;
	private final String desiredFanMode;

}
