package org.springframework.social.ecobee.connect;

import org.springframework.social.ecobee.api.Ecobee;
import org.springframework.social.ecobee.api.EcobeeUserProfile;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.web.client.HttpClientErrorException;

public class EcobeeAdapter implements ApiAdapter<Ecobee> {

	@Override
	public boolean test(Ecobee ecobee) {

		try {
			ecobee.userOperations().getUserProfile();
			return true;
		} catch (HttpClientErrorException e) {
			return false;
		}
	}

	@Override
	public void setConnectionValues(Ecobee ecobee, ConnectionValues values) {

		EcobeeUserProfile profile = ecobee.userOperations().getUserProfile();
		values.setDisplayName(profile.getDisplayName());
	}

	@Override
	public UserProfile fetchUserProfile(Ecobee ecobee) {

		EcobeeUserProfile profile = ecobee.userOperations().getUserProfile();
		return new UserProfileBuilder()
				.setUsername(profile.getDisplayName())
				.setFirstName(profile.getFirstName())
				.setLastName(profile.getLastName())
				.setEmail(profile.getUserName())
				.setName(profile.getFirstName() + " " + profile.getLastName())
				.build();
	}

	@Override
	public void updateStatus(Ecobee ecobee, String s) {
		 // Not supported
	}
}