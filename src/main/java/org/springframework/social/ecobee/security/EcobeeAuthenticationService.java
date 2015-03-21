package org.springframework.social.ecobee.security;

import org.springframework.social.ecobee.api.Ecobee;
import org.springframework.social.ecobee.connect.EcobeeConnectionFactory;
import org.springframework.social.security.provider.OAuth2AuthenticationService;

public class EcobeeAuthenticationService extends OAuth2AuthenticationService<Ecobee> {

	public EcobeeAuthenticationService(String apiKey) {
		super(new EcobeeConnectionFactory(apiKey));
	}

}
