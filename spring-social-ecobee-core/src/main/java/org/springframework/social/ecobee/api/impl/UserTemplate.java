package org.springframework.social.ecobee.api.impl;

import org.springframework.social.ecobee.api.EcobeeUserProfile;
import org.springframework.social.ecobee.api.UserOperations;
import org.springframework.web.client.RestTemplate;

public class UserTemplate extends AbstractEcobeeOperations implements UserOperations {

	private final RestTemplate restTemplate;

	public UserTemplate(RestTemplate restTemplate, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.restTemplate = restTemplate;
	}

	@Override
	public EcobeeUserProfile getUserProfile() {
		return null;
	}
}
