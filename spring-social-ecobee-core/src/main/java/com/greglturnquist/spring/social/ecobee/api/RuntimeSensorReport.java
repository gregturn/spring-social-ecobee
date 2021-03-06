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
package com.greglturnquist.spring.social.ecobee.api;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Greg Turnquist
 */
@Data
@RequiredArgsConstructor
public class RuntimeSensorReport {

	private final String thermostatIdentifier;
	private final List<RuntimeSensorMetadata> sensors;
	private final List<String> columns;
	private final List<String> data;

}
