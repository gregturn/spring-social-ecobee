package com.greglturnquist.social.ecobee.api.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
abstract class EcobeeObjectMixin {

	EcobeeObjectMixin() {
	}

}
