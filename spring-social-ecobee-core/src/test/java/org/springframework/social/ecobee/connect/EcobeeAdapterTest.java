package org.springframework.social.ecobee.connect;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.ecobee.api.Ecobee;

public class EcobeeAdapterTest {

	private EcobeeAdapter apiAdapter = new EcobeeAdapter();

	private Ecobee ecobee = Mockito.mock(Ecobee.class);

	@Test
	public void fetchProfile() {

		UserProfile profile = apiAdapter.fetchUserProfile(ecobee);
		assertThat(profile.getEmail(), equalTo("**Protected from 3rd party access**"));
		assertThat(profile.getFirstName(), equalTo("**Protected"));
		assertThat(profile.getLastName(), equalTo("access**"));
		assertThat(profile.getUsername(), equalTo("**Protected from 3rd party access**"));
		assertThat(profile.getName(), equalTo("**Protected from 3rd party access**"));
	}

}