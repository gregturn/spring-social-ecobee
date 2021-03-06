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

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Greg Turnquist
 */
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
		model.addAttribute("authentication", SecurityContextHolder.getContext().getAuthentication());
		return "home";
	}

	public ConnectionRepository getConnectionRepository() {
		return connectionRepository;
	}
}
