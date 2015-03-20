package com.greglturnquist.social.ecobee.api;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

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
