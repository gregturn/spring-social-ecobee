package com.greglturnquist.social.ecobee.api.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.util.List;

import com.greglturnquist.social.ecobee.api.Thermostat;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

public class ThermostatTemplateTest extends AbstractEcobeeApiTest {

	@Test
	public void testGetThermostats() throws Exception {

		mockServer.expect(requestTo("https://api.ecobee.com/1/thermostat"))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(jsonResource("thermostats"), MediaType.APPLICATION_JSON));

		final List<Thermostat> thermostats = ecobee.thermostatOperations().getThermostats();
		assertThat(thermostats.size(), equalTo(1));

		final Thermostat thermostat = thermostats.get(0);
		assertThat(thermostat.getIdentifier(), equalTo("161775386723"));
		assertThat(thermostat.getName(), equalTo("My Test Thermostat"));
		assertThat(thermostat.getLastModified(), equalTo("2011-01-28 23:40:25"));
		assertThat(thermostat.getSettings().getHvacMode(), equalTo("heat"));
		assertThat(thermostat.getSettings().getVent(), is(nullValue()));
	}


}