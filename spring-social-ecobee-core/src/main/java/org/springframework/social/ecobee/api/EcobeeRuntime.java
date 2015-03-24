package org.springframework.social.ecobee.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EcobeeRuntime {

	private final boolean connected;
	private final String lastModified;
	private final int actualTemperatureRaw;
	private final int actualHumidity;
	private final int desiredHeatRaw;
	private final int desiredCoolRaw;
	private final int desiredHumidity;
	private final int desiredDehumidity;
	private final String desiredFanMode;

	/**
	 * Ecobee temperatures are represented as DEGREE * 10. This transforms the raw temperature.
	 */
	public double getActualTemperature() {
		return this.actualTemperatureRaw / 10.0;
	}

	/**
	 * Ecobee temperatures are represented as DEGREE * 10. This transforms the raw temperature.
	 */
	public double getDesiredHeat() {
		return this.desiredHeatRaw / 10.0;
	}

	/**
	 * Ecobee temperatures are represented as DEGREE * 10. This transforms the raw temperature.
	 */
	public double getDesiredCool() {
		return this.desiredCoolRaw / 10.0;
	}

}
