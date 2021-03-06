/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greglturnquist.spring.social.ecobee.connect;

import com.greglturnquist.spring.social.ecobee.api.Ecobee;
import com.greglturnquist.spring.social.ecobee.api.impl.EcobeeTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @author Greg Turnquist
 */
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
