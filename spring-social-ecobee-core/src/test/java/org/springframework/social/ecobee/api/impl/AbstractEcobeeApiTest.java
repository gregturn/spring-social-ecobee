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
package org.springframework.social.ecobee.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

public abstract class AbstractEcobeeApiTest {

	protected EcobeeTemplate ecobee;
	protected EcobeeTemplate unauthorizedEcobee;
	protected MockRestServiceServer mockServer;
	protected HttpHeaders responseHeaders;

	@Before
	public void setUp() {

		this.ecobee = new EcobeeTemplate("ACCESS_TOKEN");
		this.mockServer = MockRestServiceServer.createServer(ecobee.getRestTemplate());

		this.responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);

		this.unauthorizedEcobee = new EcobeeTemplate();

		// Create a mock server just to avoid hitting real Ecobee if something gets past the authorization check.
		MockRestServiceServer.createServer(unauthorizedEcobee.getRestTemplate());
	}

	protected Resource jsonResource(String filename) {
		return new ClassPathResource(filename + ".json", getClass());
	}

	protected ObjectMapper getObjectMapper() {
		return this.ecobee.getJsonMessageConverter().getObjectMapper();
	}

}
