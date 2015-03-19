package com.greglturnquist.social.ecobee.connect;

import com.greglturnquist.social.ecobee.api.Ecobee;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

public class EcobeeConnectionFactory extends OAuth2ConnectionFactory<Ecobee> {

	public EcobeeConnectionFactory(String apiKey) {
		super("ecobee", new EcobeeServiceProvider(apiKey), new EcobeeAdapter());
	}

}
