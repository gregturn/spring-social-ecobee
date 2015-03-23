package org.springframework.social.ecobee.api;

import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ThermostatFunction {

	private final Selection selection;
	private final List<Function> functions;

	/**
	 * Alternative constructor that converts a single {@link org.springframework.social.ecobee.api.Function} into a
	 * List.
	 * @param selection
	 * @param function
	 */
	public ThermostatFunction(Selection selection, Function function) {
		this(selection, Arrays.asList(new Function[]{function}));
	}

}
