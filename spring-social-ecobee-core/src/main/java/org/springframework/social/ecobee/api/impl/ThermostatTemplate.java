package org.springframework.social.ecobee.api.impl;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

import lombok.extern.java.Log;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.ecobee.api.Function;
import org.springframework.social.ecobee.api.Selection;
import org.springframework.social.ecobee.api.Thermostat;
import org.springframework.social.ecobee.api.ThermostatFunction;
import org.springframework.social.ecobee.api.ThermostatOperations;
import org.springframework.social.ecobee.api.ThermostatSummary;
import org.springframework.social.ecobee.api.Thermostats;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Log
public class ThermostatTemplate extends AbstractEcobeeOperations implements ThermostatOperations {

	private final RestTemplate restTemplate;
	private final MappingJackson2HttpMessageConverter jsonMessageConverter;

	public ThermostatTemplate(RestTemplate restTemplate, boolean isAuthorizedUser,
							  MappingJackson2HttpMessageConverter jsonMessageConverter) {

		super(isAuthorizedUser);
		this.restTemplate = restTemplate;
		this.jsonMessageConverter = jsonMessageConverter;
	}

	@Override
	public Thermostat getThermostat(String identifier) {

		try {
			final Selection selection = Selection.thermostats(identifier);
			selection.getSelection().setIncludeRuntime(true);
			selection.getSelection().setIncludeSettings(true);
			selection.getSelection().setIncludeSensors(true);
			final String selectionStr = this.jsonMessageConverter.getObjectMapper().writeValueAsString(selection);
			URI uri = UriComponentsBuilder.fromHttpUrl(buildUri("/thermostat"))
					.queryParam("json", selectionStr).build().toUri();
			return this.restTemplate.getForObject(uri, Thermostats.class).getThermostats().get(0);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Thermostat> getAllThermostats() {

		try {
			final Selection selection = Selection.allThermostats();
			selection.getSelection().setIncludeRuntime(true);
			selection.getSelection().setIncludeSettings(true);
			selection.getSelection().setIncludeSensors(true);
			final String selectionStr = this.jsonMessageConverter.getObjectMapper().writeValueAsString(selection);
			URI uri = UriComponentsBuilder.fromHttpUrl(buildUri("/thermostat"))
					.queryParam("json", selectionStr).build().toUri();
			return this.restTemplate.getForObject(uri, Thermostats.class).getThermostats();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ThermostatSummary getThermostatSummary() {

		try {
			final Selection selection = Selection.allThermostats();
			final String selectionStr = this.jsonMessageConverter.getObjectMapper().writeValueAsString(selection);
			URI uri = UriComponentsBuilder.fromHttpUrl(buildUri("/thermostatSummary"))
					.queryParam("json", selectionStr).build().toUri();
			return this.restTemplate.getForObject(uri, ThermostatSummary.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void resume(String identifier) {

		ThermostatFunction function = new ThermostatFunction(
				Selection.thermostats(identifier), new Function("resumeProgram", new HashMap<String,String>()));
		URI uri = UriComponentsBuilder.fromHttpUrl(buildUri("/thermostat")).build().toUri();
		this.restTemplate.postForObject(uri, function, String.class);
	}

	@Override
	public void sendMessage(String identifier, String message) {

		final HashMap<String, String> params = new HashMap<>();
		params.put("text", message);

		final Function sendMessage = new Function("sendMessage", params);
		final ThermostatFunction function = new ThermostatFunction(Selection.thermostats(identifier), sendMessage);

		this.restTemplate.postForObject(buildUri("/thermostat"), function, String.class);
	}
}
