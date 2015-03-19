package com.greglturnquist.social.ecobee.api.impl;

class AbstractEcobeeOperations {

	private final boolean isAuthorized;

	// Version 1 Ecobee API
	private final static String API_URL_BASE = "https://api.ecobee.com/1";

	public AbstractEcobeeOperations(boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
	}

	protected String buildUri(String path) {
		return API_URL_BASE + path;
	}
}
