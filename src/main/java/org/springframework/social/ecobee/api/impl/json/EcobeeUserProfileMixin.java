package org.springframework.social.ecobee.api.impl.json;

import com.fasterxml.jackson.annotation.JsonProperty;

abstract class EcobeeUserProfileMixin extends EcobeeObjectMixin {

	@JsonProperty("userName")
	String userName;

	@JsonProperty("firstName")
	String firstName;

	@JsonProperty("lastName")
	String lastName;

	@JsonProperty("displayName")
	String displayName;

	EcobeeUserProfileMixin(@JsonProperty("userName") String username,
						   @JsonProperty("firstName")String firstName,
						   @JsonProperty("lastName")String lastName,
						   @JsonProperty("displayName")String displayName) {}

}
