package com.greglturnquist.social.ecobee.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SelectionType {

	private final String selectionType;
	private final String selectionMatch;

}
