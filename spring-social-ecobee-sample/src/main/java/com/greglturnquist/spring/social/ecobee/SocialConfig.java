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
package com.greglturnquist.spring.social.ecobee;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.SignInAdapter;
import com.greglturnquist.spring.social.ecobee.connect.EcobeeConnectionFactory;

/**
 * @author Greg Turnquist
 */
@Configuration
public class SocialConfig {

	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {
		return new ConnectionFactoryRegistry();
	}

	@Bean
	public EcobeeConnectionFactory ecobeeConnectionFactory(ConnectionFactoryRegistry registry, Environment env) {
		final EcobeeConnectionFactory ecobeeConnectionFactory = new EcobeeConnectionFactory(env.getProperty("ecobee.apiKey"));
		registry.addConnectionFactory(ecobeeConnectionFactory);
		return ecobeeConnectionFactory;
	}

	@Bean
	public UsersConnectionRepository usersConnectionRepository(DataSource dataSource,
		ConnectionFactoryLocator connectionFactoryLocator) {
		return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
	}

	@Bean
	public SignInAdapter signInAdapter() {
		return new SimpleSignInAdapter(new HttpSessionRequestCache());
	}

}