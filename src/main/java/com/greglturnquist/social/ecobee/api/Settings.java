package com.greglturnquist.social.ecobee.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Settings {

	private final String hvacMode;
	private final String vent;
}
