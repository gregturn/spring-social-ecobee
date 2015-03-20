package com.greglturnquist.social.ecobee.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Status {

	private final int code;
	private final String message;

}
