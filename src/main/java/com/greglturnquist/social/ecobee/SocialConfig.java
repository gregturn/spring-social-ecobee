package com.greglturnquist.social.ecobee;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.ecobee.connect.EcobeeConnectionFactory;

@Configuration
public class SocialConfig {

	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator(EcobeeConnectionFactory factory) {

		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(factory);
		return registry;
	}

	@Bean
	public EcobeeConnectionFactory ecobeeConnectionFactory(Environment env) {
		return new EcobeeConnectionFactory(env.getProperty("ecobee.apiKey"));
	}

	@Bean
	public UsersConnectionRepository usersConnectionRepository(DataSource dataSource,
		ConnectionFactoryLocator connectionFactoryLocator) {
		return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
	}

}