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
package org.springframework.social.ecobee.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.ecobee.api.Ecobee;
import org.springframework.web.client.HttpClientErrorException;

public class EcobeeAdapter implements ApiAdapter<Ecobee> {

	@Override
	public boolean test(Ecobee ecobee) {

		try {
			ecobee.thermostatOperations().getThermostatSummary();
			return true;
		} catch (HttpClientErrorException e) {
			return false;
		}
	}

	@Override
	public void setConnectionValues(Ecobee ecobee, ConnectionValues values) {
		values.setDisplayName(ecobee.thermostatOperations().getAllThermostats().get(0).getName());
	}

	@Override
	public UserProfile fetchUserProfile(Ecobee ecobee) {

		return new UserProfileBuilder()
				.setUsername("**Protected from 3rd party access**")
				.setFirstName("**Protected from 3rd party access**")
				.setLastName("**Protected from 3rd party access**")
				.setEmail("**Protected from 3rd party access**")
				.setName("**Protected from 3rd party access**")
				.build();
	}

	@Override
	public void updateStatus(Ecobee ecobee, String s) {
		 // Not supported
	}
}