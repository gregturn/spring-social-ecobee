package org.springframework.social.ecobee.api.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.social.ecobee.api.Function;
import org.springframework.social.ecobee.api.ReportSelection;
import org.springframework.social.ecobee.api.RuntimeSensorMetadata;
import org.springframework.social.ecobee.api.RuntimeSensorReport;
import org.springframework.social.ecobee.api.Selection;
import org.springframework.social.ecobee.api.Thermostat;
import org.springframework.social.ecobee.api.ThermostatDetails;
import org.springframework.social.ecobee.api.ThermostatFunction;
import org.springframework.social.ecobee.api.ThermostatSummary;
import org.springframework.web.util.UriUtils;

public class ThermostatTemplateTest extends AbstractEcobeeApiTest {

	@Test
	public void testGetThermostat() throws Exception {

		final Selection selection = Selection.thermostats("161775386723");
		selection.getSelection().setIncludeSettings(true);
		selection.getSelection().setIncludeRuntime(true);
		selection.getSelection().setIncludeDevice(true);
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
	public void testGetAllThermostats() throws Exception {

		final Selection selection = Selection.allThermostats();
		selection.getSelection().setIncludeSettings(true);
		selection.getSelection().setIncludeRuntime(true);
		selection.getSelection().setIncludeDevice(true);
		final String selectionStr = UriUtils.encodeQueryParam(this.getObjectMapper().writeValueAsString(selection), "UTF-8");
		mockServer.expect(requestTo("https://api.ecobee.com/1/thermostat?json=" + selectionStr))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(jsonResource("thermostats"), MediaType.APPLICATION_JSON));

		final List<Thermostat> thermostats = ecobee.thermostatOperations().getAllThermostats();
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

		final Selection selection = Selection.allThermostats();
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

	@Test
	public void testGetSensorReport() throws Exception {

		final Date startDate = new Date();
		final Date endDate = startDate;
		final String columns = "";

		final ReportSelection selection = ReportSelection.thermostats(startDate, endDate, columns);
		selection.getSelection().setIncludeDevice(true);
		final String selectionStr = UriUtils.encodeQueryParam(this.getObjectMapper().writeValueAsString(selection), "UTF-8");

		mockServer.expect(requestTo("https://api.ecobee.com/1/runtimeReport?json=" + selectionStr))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(jsonResource("sensorReport"), MediaType.APPLICATION_JSON));

		RuntimeSensorReport report = ecobee.thermostatOperations().getAllSensorReports(startDate, endDate, columns).get(0);
		assertThat(report.getThermostatIdentifier(), equalTo("Test thermostat"));

		assertThat(report.getSensors().size(), equalTo(4));
		assertThat(report.getSensors(), hasItems(
				new RuntimeSensorMetadata("1", "Temp sensor 1", "temperature", "indoor"),
				new RuntimeSensorMetadata("2", "Humidity sensor 1", "humidity", "indoor"),
				new RuntimeSensorMetadata("3", "Temp sensor 2", "temperature", "outdoor"),
				new RuntimeSensorMetadata("4", "Humidity sensor 2", "humidity", "outdoor")));

		assertThat(report.getColumns(), hasItems("1", "2", "3", "4"));
		assertThat(report.getData(), hasItems("a", "b", "c", "d"));
	}

	@Test
	public void testResume() throws Exception {

		ThermostatFunction function = new ThermostatFunction(
				Selection.thermostats("161775386723"), new Function("resumeProgram", new HashMap<String,String>()));
		final String functionStr = this.getObjectMapper().writeValueAsString(function);
		mockServer.expect(requestTo("https://api.ecobee.com/1/thermostat"))
				.andExpect(method(HttpMethod.POST))
				.andExpect(content().string(functionStr))
				.andRespond(withSuccess().body(new byte[0]));

		ecobee.thermostatOperations().resume("161775386723");

		mockServer.verify();
	}

	@Test
	public void testSendMessage() throws Exception {

		final HashMap<String,String> params = new HashMap<>();
		params.put("text", "Greetings mate!");

		ThermostatFunction function = new ThermostatFunction(
				Selection.thermostats("161775386723"), new Function("sendMessage", params));
		final String functionStr = this.getObjectMapper().writeValueAsString(function);
		mockServer.expect(requestTo("https://api.ecobee.com/1/thermostat"))
				.andExpect(method(HttpMethod.POST))
				.andExpect(content().string(functionStr))
				.andRespond(withSuccess().body(new byte[0]));

		ecobee.thermostatOperations().sendMessage("161775386723", "Greetings mate!");

		mockServer.verify();
	}

}