/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greglturnquist.spring.social.ecobee.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Greg Turnquist
 */
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
