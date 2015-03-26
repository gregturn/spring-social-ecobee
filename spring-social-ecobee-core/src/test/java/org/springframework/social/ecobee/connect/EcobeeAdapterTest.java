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

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.ecobee.api.Ecobee;

/**
 * @author Greg Turnquist
 */
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