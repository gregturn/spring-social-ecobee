package com.greglturnquist.social.ecobee;

import java.security.Principal;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.ecobee.api.Ecobee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		model.addAttribute("authentication", SecurityContextHolder.getContext().getAuthentication());
		model.addAttribute("thermostats", connection.getApi().thermostatOperations().getAllThermostats());
		return "ecobee/thermostats";
	}
}