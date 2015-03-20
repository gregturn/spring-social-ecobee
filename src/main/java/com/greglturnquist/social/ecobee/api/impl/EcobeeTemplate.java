package com.greglturnquist.social.ecobee.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greglturnquist.social.ecobee.api.Ecobee;
import com.greglturnquist.social.ecobee.api.ThermostatOperations;
import com.greglturnquist.social.ecobee.api.UserOperations;
import com.greglturnquist.social.ecobee.api.impl.json.EcobeeModule;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.OAuth2Version;

public class EcobeeTemplate extends AbstractOAuth2ApiBinding implements Ecobee {

	private UserOperations userOperations;
	private ThermostatOperations thermostatOperations;

	public EcobeeTemplate() {
		super();
		initSubApis();
	}

	public EcobeeTemplate(String accessToken) {
		super(accessToken);
		initSubApis();
	}

	@Override
	protected OAuth2Version getOAuth2Version() {
		return OAuth2Version.BEARER;
	}

	@Override
	public UserOperations userOperations() {
		return this.userOperations;
	}

	@Override
	public ThermostatOperations thermostatOperations() {
		return this.thermostatOperations;
	}

	@Override
	protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {

		MappingJackson2HttpMessageConverter converter = super.getJsonMessageConverter();
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new EcobeeModule());
		converter.setObjectMapper(mapper);
		return converter;
	}

	private void initSubApis() {
		this.userOperations = new UserTemplate(getRestTemplate(), this.isAuthorized());
		this.thermostatOperations = new ThermostatTemplate(getRestTemplate(), this.isAuthorized(),
				this.getJsonMessageConverter());
	}

}
