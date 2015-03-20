package com.greglturnquist.social.ecobee;

import java.net.URI;
import java.net.URISyntaxException;

import com.fasterxml.jackson.databind.JsonNode;
import com.greglturnquist.social.ecobee.api.Thermostat;
import com.greglturnquist.social.ecobee.api.ThermostatDetails;
import com.greglturnquist.social.ecobee.api.ThermostatSummary;
import com.greglturnquist.social.ecobee.api.impl.EcobeeTemplate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class EcobeeCommandLine implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(EcobeeCommandLine.class);

	private RestTemplate restTemplate = new RestTemplate();

	@Value("${spring.ecobee.pin.apiKey:foo}")
	private String pinApiKey;

	@Value("${spring.ecobee.authorizationCode.apiKey:foo}")
	private String authorizationCodeApiKey;

	@Value("${ecobee.token}")
	private String ecobeeToken;

	@Value("${spring.ecobee.username:foo}")
	private String username;

	@Value("${spring.ecobee.password:foo}")
	private String password;

	String apiUrl = "https://api.ecobee.com";
	String webUrl = "https://www.ecobee.com";

	@Override
	public void run(String... strings) throws Exception {

		//requestPinCode();
		//requestAuthorizationCode();
		lookupThermstatsUsingAccessToken();
	}

	private void requestPinCode() {

		URI pinRequest = UriComponentsBuilder.fromHttpUrl(apiUrl + "/authorize")//
				.queryParam("response_type", "ecobeePin")//
				.queryParam("client_id", pinApiKey)//
				.queryParam("scope", "smartRead").build().toUri();

		ResponseEntity<JsonNode> pinResponse = restTemplate.getForEntity(pinRequest, JsonNode.class);

		log.info(pinResponse.getStatusCode().toString());
		pinResponse.getHeaders().entrySet().stream().forEach(h -> log.info(h.getKey() + " -> " + h.getValue()));
		log.info(pinResponse.getBody().toString());

		String ecobeePin = pinResponse.getBody().findValue("ecobeePin").asText();
		String code = pinResponse.getBody().findValue("code").asText();
		String scope = pinResponse.getBody().findValue("scope").asText();

		log.info("Pin " + ecobeePin);
		log.info("Code " + code);
		log.info("Scope = " + scope);

		URI tokenRequest = UriComponentsBuilder.fromHttpUrl(apiUrl + "/token")
				.queryParam("grant_type", "ecobeePin")
				.queryParam("code", code)
				.queryParam("client_id", pinApiKey).build().toUri();


		try {
			ResponseEntity<String> tokenResponse = restTemplate.postForEntity(tokenRequest, null, String.class);

			log.info(tokenResponse.getStatusCode().toString());
			tokenResponse.getHeaders().entrySet().stream().forEach(h -> log.info(h.getKey() + " -> " + h.getValue()));
			log.info(tokenResponse.getBody().toString());
		} catch (HttpClientErrorException e) {
			for (StackTraceElement element : e.getStackTrace()) {
				log.error(element.toString());
			}
			log.error(e.getLocalizedMessage());
		}
	}

	private void requestAuthorizationCode() throws URISyntaxException {

		URI pinRequest = UriComponentsBuilder.fromHttpUrl(apiUrl + "/authorize")//
				.queryParam("response_type", "code")//
				.queryParam("client_id", authorizationCodeApiKey)//
				.queryParam("redirect_uri", "http://ecobee.greglturnquist.com")//
				.queryParam("scope", "smartRead").build().toUri();

		ResponseEntity<String> codeResponse = restTemplate.getForEntity(pinRequest, String.class);

		log.info(codeResponse.getStatusCode().toString());
		codeResponse.getHeaders().entrySet().stream().forEach(h -> log.info(h.getKey() + " -> " + h.getValue()));
		log.info(codeResponse.getBody().toString());

		Document doc = Jsoup.parse(codeResponse.getBody().toString());

		Element form = doc.select("form").first();

		String action = form.attr("action");
		log.info("POST " + action);

		Elements inputs = form.select("input");
		inputs.forEach(input -> log.info(input.attr("name")));

		String nonceName = "p";
		String nonce = form.select("input[name=" + nonceName + "]").first().attr("value");

		log.info("Nonce => " + nonce);

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("userName", username);
		map.add("password", password);
		map.add(nonceName, nonce);

		URI loginRequest = UriComponentsBuilder.fromHttpUrl(webUrl + action)
				.buildAndExpand(map).toUri();

		ResponseEntity<String> loginResponse = restTemplate.postForEntity(loginRequest, map, String.class);

		log.info(loginResponse.getStatusCode().toString());
		loginResponse.getHeaders().entrySet().stream().forEach(h -> log.info(h.getKey() + " -> " + h.getValue()));

		if (loginResponse.getStatusCode().is3xxRedirection()) {
			URI loginURI = loginResponse.getHeaders().getLocation();
			URI securedLoginURI = new URI("https://" + loginURI.getHost() + loginURI.getPath());
			loginRequest = UriComponentsBuilder.fromUri(securedLoginURI)
					.buildAndExpand(map).toUri();
			log.info("Going to " + loginRequest);
			loginResponse = restTemplate.postForEntity(loginRequest, map, String.class);
			log.info(loginResponse.getStatusCode().toString());
			loginResponse.getHeaders().entrySet().stream().forEach(h -> log.info(h.getKey() + " -> " + h.getValue()));
			log.info(loginResponse.getBody());
		}
	}

	private void lookupThermstatsUsingAccessToken() throws Exception {

		EcobeeTemplate ecobeeTemplate = new EcobeeTemplate(this.ecobeeToken);

		ThermostatSummary thermostatSummary = ecobeeTemplate.thermostatOperations().getThermostatSummary();

		log.info("You have " + thermostatSummary.getThermostatCount() + " thermostat(s)");

		for (ThermostatDetails thermostatDetails : thermostatSummary.getParsedRevisionList()) {
			log.info(thermostatDetails.toString());
		}

		for (Thermostat thermostat : ecobeeTemplate.thermostatOperations().getThermostats()) {
			log.info(thermostat.toString());
		}
	}
}
