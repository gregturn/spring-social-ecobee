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
package com.greglturnquist.spring.social.ecobee.api.impl;

import com.greglturnquist.spring.social.ecobee.api.Ecobee;
import com.greglturnquist.spring.social.ecobee.api.impl.json.EcobeeModule;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import com.greglturnquist.spring.social.ecobee.api.ThermostatOperations;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.OAuth2Version;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Greg Turnquist
 */
public class EcobeeTemplate extends AbstractOAuth2ApiBinding implements Ecobee {

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
		this.thermostatOperations = new ThermostatTemplate(getRestTemplate(), this.isAuthorized(),
				this.getJsonMessageConverter());
	}

}
