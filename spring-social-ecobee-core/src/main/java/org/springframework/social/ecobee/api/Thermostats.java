package org.springframework.social.ecobee.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Thermostats {

	List<Thermostat> thermostats;

}
