package org.springframework.social.ecobee.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SelectionType {

	private final String selectionType;
	private final String selectionMatch;

	private boolean includeRuntime = false;
	private boolean includeSettings = false;
	private boolean includeSensors = false;

}
