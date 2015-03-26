package org.springframework.social.ecobee.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RemoteSensorCapability {

	private final String id;
	private final String type;
	private final String value;

	public String getValue() {

		try {
			int val = Integer.parseInt(this.value);
			return String.valueOf(val / 10.0);
		} catch (NumberFormatException e) {
			return this.value;
		}
	}

}
