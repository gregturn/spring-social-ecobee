package com.greglturnquist.social.ecobee.api.impl;

import com.greglturnquist.social.ecobee.api.EcobeeUserProfile;
import com.greglturnquist.social.ecobee.api.UserOperations;
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
