package com.greglturnquist.social.ecobee;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.ecobee.api.Ecobee;
import org.springframework.social.ecobee.api.RuntimeSensorReport;
import org.springframework.social.ecobee.api.Thermostat;
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
		// TODO Update repository of connections with new token
		if (connection.hasExpired()) {
			connection.refresh();
		}

		Date startDate = new Date();
		Date endDate = startDate;
		String columns = "zoneAveTemp";

		model.addAttribute("authentication", SecurityContextHolder.getContext().getAuthentication());
		final List<Thermostat> allThermostats = connection.getApi().thermostatOperations().getAllThermostats();
		List<String> thermostatIds = allThermostats.stream()
				.map(t -> t.getIdentifier())
				.collect(Collectors.toList());
		model.addAttribute("thermostats", allThermostats);
		final List<RuntimeSensorReport> allSensorReports = connection.getApi().thermostatOperations().getAllSensorReports(
				startDate, endDate, columns, thermostatIds.toArray(new String[]{}));
		model.addAttribute("runtimeReports",
				allSensorReports);
		return "ecobee/thermostats";
	}

}
