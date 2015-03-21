package com.greglturnquist.social.ecobee;

import javax.sql.DataSource;

import org.springframework.social.ecobee.connect.EcobeeConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.AuthenticationNameUserIdSource;

//@Configuration
//@EnableSocial
public class SocialConfig implements SocialConfigurer {

	@Autowired
	DataSource dataSource;

	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment env) {
		configurer.addConnectionFactory(new EcobeeConnectionFactory(env.getProperty("ecobee.apiKey")));
	}

	@Override
	public UserIdSource getUserIdSource() {
		return new AuthenticationNameUserIdSource();
	}

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator locator) {
		return new JdbcUsersConnectionRepository(dataSource, locator, Encryptors.noOpText());
	}

}