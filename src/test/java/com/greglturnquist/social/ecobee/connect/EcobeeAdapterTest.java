package com.greglturnquist.social.ecobee.connect;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

import com.greglturnquist.social.ecobee.api.EcobeeUserProfile;
import org.junit.Test;
import org.mockito.Mockito;

import com.greglturnquist.social.ecobee.api.Ecobee;
import com.greglturnquist.social.ecobee.api.UserOperations;
import org.springframework.social.connect.UserProfile;

public class EcobeeAdapterTest {

	private EcobeeAdapter apiAdapter = new EcobeeAdapter();

	private Ecobee ecobee = Mockito.mock(Ecobee.class);

	@Test
	public void fetchProfile() {

		UserOperations userOperations = Mockito.mock(UserOperations.class);
		when(ecobee.userOperations()).thenReturn(userOperations);
		when(userOperations.getUserProfile()).thenReturn(createProfile("gregturn", "Greg", "Turnquist", "gturnquist@pivotal.io"));
		UserProfile profile = apiAdapter.fetchUserProfile(ecobee);
		assertThat(profile.getEmail(), equalTo("gturnquist@pivotal.io"));
		assertThat(profile.getFirstName(), equalTo("Greg"));
		assertThat(profile.getLastName(), equalTo("Turnquist"));
		assertThat(profile.getUsername(), equalTo("gregturn"));
		assertThat(profile.getName(), equalTo("Greg Turnquist"));
	}

	private EcobeeUserProfile createProfile(String username, String firstName, String lastName, String email) {
		return new EcobeeUserProfile(email, firstName, lastName, username);
	}
}