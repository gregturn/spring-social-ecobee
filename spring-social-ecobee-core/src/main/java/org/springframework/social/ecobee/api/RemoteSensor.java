package org.springframework.social.ecobee.api;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RemoteSensor {

	private final String id;
	private final String name;
	private final String type;
	private final String code;
	private final boolean inUse;
	private final List<RemoteSensorCapability> capability;

}
