package org.springframework.social.ecobee.api;

import java.util.Map;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Function {

	private final String type;
	private final Map<String, String> params;

}
