package org.springframework.social.ecobee.api.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RequestBody {

	private final String operation;
	private final String setPath;
	private final boolean includePrivileges;
	private final boolean recursive;

}
