package org.springframework.social.ecobee.api.impl.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.ecobee.api.RuntimeSensorReport;

public class RuntimeReportMixin extends EcobeeObjectMixin {

	public RuntimeReportMixin(@JsonProperty("sensorList") List<RuntimeSensorReport> sensorList) {
	}

}
