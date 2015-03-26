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
package org.springframework.social.ecobee.api;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Greg Turnquist
 */
@Data
@RequiredArgsConstructor
public class ThermostatSummary {

	private final int thermostatCount;
	private final List<String> revisionList;
	private final List<String> statusList;
	private final Status status;

	private List<ThermostatDetails> parsedRevisionList;
	private List<ThermostatDetails> parsedStatusList;

	public List<ThermostatDetails> getParsedRevisionList() {

		if (this.parsedRevisionList == null) {
			this.parsedRevisionList = new ArrayList<>();

			for (String revision : revisionList) {
				String[] parts = revision.split(":");
				this.parsedRevisionList.add(new ThermostatDetails(parts[0],
						parts[1], Boolean.parseBoolean(parts[2]), parts[3], parts[4], parts[5], parts[6]));
			}

			return this.parsedRevisionList;
		} else {
			return this.parsedRevisionList;
		}
	}
}
