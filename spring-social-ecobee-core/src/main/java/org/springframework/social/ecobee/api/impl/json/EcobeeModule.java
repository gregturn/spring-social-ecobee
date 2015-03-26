package org.springframework.social.ecobee.api.impl.json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.social.ecobee.api.EcobeeRuntime;
import org.springframework.social.ecobee.api.EcobeeUserProfile;
import org.springframework.social.ecobee.api.Function;
import org.springframework.social.ecobee.api.RemoteSensor;
import org.springframework.social.ecobee.api.RemoteSensorCapability;
import org.springframework.social.ecobee.api.RuntimeReport;
import org.springframework.social.ecobee.api.RuntimeSensorMetadata;
import org.springframework.social.ecobee.api.RuntimeSensorReport;
import org.springframework.social.ecobee.api.Settings;
import org.springframework.social.ecobee.api.Status;
import org.springframework.social.ecobee.api.Thermostat;
import org.springframework.social.ecobee.api.ThermostatFunction;
import org.springframework.social.ecobee.api.ThermostatSummary;
import org.springframework.social.ecobee.api.Thermostats;

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
