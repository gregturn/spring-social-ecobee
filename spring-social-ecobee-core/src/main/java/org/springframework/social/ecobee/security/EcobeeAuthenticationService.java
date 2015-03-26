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
package org.springframework.social.ecobee.security;

import org.springframework.social.ecobee.api.Ecobee;
import org.springframework.social.ecobee.connect.EcobeeConnectionFactory;
import org.springframework.social.security.provider.OAuth2AuthenticationService;

/**
 * @author Greg Turnquist
 */
public class EcobeeAuthenticationService extends OAuth2AuthenticationService<Ecobee> {

	public EcobeeAuthenticationService(String apiKey) {
		super(new EcobeeConnectionFactory(apiKey));
	}

}
