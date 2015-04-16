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
package com.greglturnquist.spring.social.ecobee.api.impl.json;

import com.greglturnquist.spring.social.ecobee.api.EcobeeRuntime;
import com.greglturnquist.spring.social.ecobee.api.EcobeeUserProfile;
import com.greglturnquist.spring.social.ecobee.api.Function;
import com.greglturnquist.spring.social.ecobee.api.RemoteSensorCapability;
import com.greglturnquist.spring.social.ecobee.api.RuntimeReport;
import com.greglturnquist.spring.social.ecobee.api.Status;
import com.greglturnquist.spring.social.ecobee.api.ThermostatSummary;
import com.greglturnquist.spring.social.ecobee.api.Thermostats;
import com.greglturnquist.spring.social.ecobee.api.RemoteSensor;
import com.greglturnquist.spring.social.ecobee.api.RuntimeSensorMetadata;
import com.greglturnquist.spring.social.ecobee.api.RuntimeSensorReport;
import com.greglturnquist.spring.social.ecobee.api.Settings;
import com.greglturnquist.spring.social.ecobee.api.Thermostat;
import com.greglturnquist.spring.social.ecobee.api.ThermostatFunction;

import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * @author Greg Turnquist
 */
public class EcobeeModule extends SimpleModule {

	public EcobeeModule() {
		super("EcobeeModule");
	}

	@Override
	public void setupModule(SetupContext context) {
		context.setMixInAnnotations(EcobeeUserProfile.class, EcobeeUserProfileMixin.class);
		context.setMixInAnnotations(Thermostats.class, ThermostatsMixin.class);
		context.setMixInAnnotations(Thermostat.class, ThermostatMixin.class);
		context.setMixInAnnotations(Settings.class, SettingsMixin.class);
		context.setMixInAnnotations(Status.class, StatusMixin.class);
		context.setMixInAnnotations(ThermostatSummary.class, ThermostatSummaryMixin.class);
		context.setMixInAnnotations(EcobeeRuntime.class, EcobeeRuntimeMixin.class);
		context.setMixInAnnotations(ThermostatFunction.class, ThermostatFunctionMixin.class);
		context.setMixInAnnotations(Function.class, FunctionMixin.class);
		context.setMixInAnnotations(RuntimeReport.class, RuntimeReportMixin.class);
		context.setMixInAnnotations(RuntimeSensorReport.class, RuntimeSensorReportMixin.class);
		context.setMixInAnnotations(RuntimeSensorMetadata.class, RuntimeSensorMetadataMixin.class);
		context.setMixInAnnotations(RemoteSensor.class, RemoteSensorMixin.class);
		context.setMixInAnnotations(RemoteSensorCapability.class, RemoteSensorCapabilityMixin.class);
	}
}
