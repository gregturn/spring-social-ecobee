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
