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