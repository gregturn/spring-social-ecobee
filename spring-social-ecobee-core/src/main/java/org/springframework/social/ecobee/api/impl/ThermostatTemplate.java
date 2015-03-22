package org.springframework.social.ecobee.api.impl;

import java.net.URI;
import java.util.List;

import org.springframework.social.ecobee.api.Selection;
import org.springframework.social.ecobee.api.SelectionType;
import org.springframework.social.ecobee.api.Thermostat;
import org.springframework.social.ecobee.api.ThermostatOperations;
import org.springframework.social.ecobee.api.ThermostatSummary;
import org.springframework.social.ecobee.api.Thermostats;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
	public Thermostat getThermostat(String identifier) throws Exception {
		final Selection selection = new Selection(new SelectionType("thermostats", identifier));
		final String selectionStr = this.jsonMessageConverter.getObjectMapper().writeValueAsString(selection);
		URI uri = UriComponentsBuilder.fromHttpUrl(buildUri("/thermostat"))
				.queryParam("json", selectionStr).build().toUri();
		return this.restTemplate.getForObject(uri, Thermostats.class).getThermostats().get(0);
	}

	@Override
	public List<Thermostat> getThermostats() throws Exception {
		final Selection selection = new Selection(new SelectionType("registered", ""));
		final String selectionStr = this.jsonMessageConverter.getObjectMapper().writeValueAsString(selection);
		URI uri = UriComponentsBuilder.fromHttpUrl(buildUri("/thermostat"))
				.queryParam("json", selectionStr).build().toUri();
		return this.restTemplate.getForObject(uri, Thermostats.class).getThermostats();
	}

	@Override
	public ThermostatSummary getThermostatSummary() throws Exception {
		final Selection selection = new Selection(new SelectionType("registered", ""));
		final String selectionStr = this.jsonMessageConverter.getObjectMapper().writeValueAsString(selection);
		URI uri = UriComponentsBuilder.fromHttpUrl(buildUri("/thermostatSummary"))
				.queryParam("json", selectionStr).build().toUri();
		return this.restTemplate.getForObject(uri, ThermostatSummary.class);

	}
}
