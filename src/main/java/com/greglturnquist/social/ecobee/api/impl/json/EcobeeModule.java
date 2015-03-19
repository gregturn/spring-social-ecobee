package com.greglturnquist.social.ecobee.api.impl.json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.greglturnquist.social.ecobee.api.EcobeeUserProfile;
import com.greglturnquist.social.ecobee.api.Settings;
import com.greglturnquist.social.ecobee.api.Thermostat;
import com.greglturnquist.social.ecobee.api.Thermostats;

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
	}
}
