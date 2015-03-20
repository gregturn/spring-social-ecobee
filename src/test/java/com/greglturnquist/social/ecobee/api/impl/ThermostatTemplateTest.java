package com.greglturnquist.social.ecobee.api.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.util.List;

import com.greglturnquist.social.ecobee.api.Selection;
import com.greglturnquist.social.ecobee.api.SelectionType;
import com.greglturnquist.social.ecobee.api.Thermostat;
import com.greglturnquist.social.ecobee.api.ThermostatDetails;
import com.greglturnquist.social.ecobee.api.ThermostatSummary;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriUtils;

public class ThermostatTemplateTest extends AbstractEcobeeApiTest {

	@Test
	public void testGetThermostat() throws Exception {

		final Selection selection = new Selection(new SelectionType("thermostats", "161775386723"));
		final String selectionStr = UriUtils.encodeQueryParam(this.getObjectMapper().writeValueAsString(selection), "UTF-8");
		mockServer.expect(requestTo("https://api.ecobee.com/1/thermostat?json=" + selectionStr))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(jsonResource("thermostats"), MediaType.APPLICATION_JSON));

		final Thermostat thermostat = ecobee.thermostatOperations().getThermostat("161775386723");
		assertThat(thermostat, notNullValue());
		assertThat(thermostat.getIdentifier(), equalTo("161775386723"));
		assertThat(thermostat.getName(), equalTo("My Test Thermostat"));
		assertThat(thermostat.getLastModified(), equalTo("2011-01-28 23:40:25"));
		assertThat(thermostat.getSettings().getHvacMode(), equalTo("heat"));
		assertThat(thermostat.getSettings().getVent(), is(nullValue()));
	}

	@Test
	public void testGetThermostats() throws Exception {

		final Selection selection = new Selection(new SelectionType("registered", ""));
		final String selectionStr = UriUtils.encodeQueryParam(this.getObjectMapper().writeValueAsString(selection), "UTF-8");
		mockServer.expect(requestTo("https://api.ecobee.com/1/thermostat?json=" + selectionStr))
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

	@Test
	public void testGetThermostatSummary() throws Exception {

		final Selection selection = new Selection(new SelectionType("registered", ""));
		final String selectionStr = UriUtils.encodeQueryParam(this.getObjectMapper().writeValueAsString(selection), "UTF-8");
		mockServer.expect(requestTo("https://api.ecobee.com/1/thermostatSummary?json=" + selectionStr))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(jsonResource("thermostatSummary"), MediaType.APPLICATION_JSON));

		final ThermostatSummary thermostatSummary = ecobee.thermostatOperations().getThermostatSummary();
		final List<ThermostatDetails> thermostatDetailsList = thermostatSummary.getParsedRevisionList();

		assertThat(thermostatDetailsList.size(), equalTo(thermostatSummary.getThermostatCount()));

		final ThermostatDetails thermostatDetails1 = thermostatDetailsList.get(0);
		assertThat(thermostatDetails1.getIdentifier(), equalTo("123456789101"));
		assertThat(thermostatDetails1.getName(), equalTo("MyStat"));
		assertThat(thermostatDetails1.isConnected(), equalTo(true));
		assertThat(thermostatDetails1.getThermostatRevision(), equalTo("071223012334"));
		assertThat(thermostatDetails1.getAlertsRevision(), equalTo("080102000000"));
		assertThat(thermostatDetails1.getRuntimeRevision(), equalTo("080102000000"));
		assertThat(thermostatDetails1.getIntervalRevision(), equalTo("080102000000"));

		final ThermostatDetails thermostatDetails2 = thermostatDetailsList.get(1);
		assertThat(thermostatDetails2.getIdentifier(), equalTo("123456789102"));
		assertThat(thermostatDetails2.getName(), equalTo("Room12"));
		assertThat(thermostatDetails2.isConnected(), equalTo(true));
		assertThat(thermostatDetails2.getThermostatRevision(), equalTo("071223012334"));
		assertThat(thermostatDetails2.getAlertsRevision(), equalTo("080102000000"));
		assertThat(thermostatDetails2.getRuntimeRevision(), equalTo("080102000000"));
		assertThat(thermostatDetails2.getIntervalRevision(), equalTo("080102000000"));

		final ThermostatDetails thermostatDetails3 = thermostatDetailsList.get(2);
		assertThat(thermostatDetails3.getIdentifier(), equalTo("123456789103"));
		assertThat(thermostatDetails3.getName(), equalTo(""));
		assertThat(thermostatDetails3.isConnected(), equalTo(false));
		assertThat(thermostatDetails3.getThermostatRevision(), equalTo("071223012334"));
		assertThat(thermostatDetails3.getAlertsRevision(), equalTo("080102000000"));
		assertThat(thermostatDetails3.getRuntimeRevision(), equalTo("080102000000"));
		assertThat(thermostatDetails3.getIntervalRevision(), equalTo("080102000000"));
	}

}