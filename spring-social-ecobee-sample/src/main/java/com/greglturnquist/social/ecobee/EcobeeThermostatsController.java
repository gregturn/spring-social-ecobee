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
package com.greglturnquist.social.ecobee;

import java.security.Principal;
import java.util.List;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.ecobee.api.Ecobee;
import org.springframework.social.ecobee.api.Thermostat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Greg Turnquist
 */
@Controller
@Log
public class EcobeeThermostatsController {

	private final ConnectionRepository connectionRepository;

	@Autowired
	public EcobeeThermostatsController(ConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}

	@RequestMapping(value="/ecobee", method = RequestMethod.GET)
	public String home(Principal currentUser, Model model) throws Exception {

		Connection<Ecobee> connection = connectionRepository.findPrimaryConnection(Ecobee.class);
		if (connection == null) {
			return "redirect:/connect/ecobee";
		}

		// TODO Update repository of connections with new token
		if (connection.hasExpired()) {
			connection.refresh();
		}

		model.addAttribute("authentication", SecurityContextHolder.getContext().getAuthentication());
		final List<Thermostat> allThermostats = connection.getApi().thermostatOperations().getAllThermostats();
		allThermostats.forEach(t -> t.getRemoteSensors().forEach(s -> s.getCapability().forEach(c -> log.info(c.toString()))));
		model.addAttribute("thermostats", allThermostats);
		return "ecobee/thermostats";
	}

}
