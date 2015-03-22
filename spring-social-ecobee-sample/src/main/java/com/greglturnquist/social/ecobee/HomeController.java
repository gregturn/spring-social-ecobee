package com.greglturnquist.social.ecobee;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	private final ConnectionRepository connectionRepository;

	@Autowired
	public HomeController(ConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}

	@RequestMapping("/")
	public String home(Principal currentUser, Model model) {

		model.addAttribute("connectionsToProviders", getConnectionRepository().findAllConnections());
		return "home";
	}

	public ConnectionRepository getConnectionRepository() {
		return connectionRepository;
	}
}
