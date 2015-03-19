package com.greglturnquist.social.ecobee.connect;

import com.greglturnquist.social.ecobee.api.Ecobee;
import com.greglturnquist.social.ecobee.api.impl.EcobeeTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

public class EcobeeServiceProvider extends AbstractOAuth2ServiceProvider<Ecobee> {

	public EcobeeServiceProvider(String apiKey) {
		super(createOAuth2Template(apiKey, ""));
	}

	private static OAuth2Template createOAuth2Template(String clientId, String clientSecret) {
		OAuth2Template oAuth2Template = new OAuth2Template(clientId, clientSecret,
				"https://api.ecobee.com/authorize", "https://api.ecobee.com/token");
		oAuth2Template.setUseParametersForClientAuthentication(true);
		return oAuth2Template;
	}

	@Override
	public Ecobee getApi(String accessToken) {
		return new EcobeeTemplate(accessToken);
	}
}
