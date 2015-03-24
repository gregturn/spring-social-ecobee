package org.springframework.social.ecobee.api;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RuntimeReport {

	List<RuntimeSensorReport> sensorList;

}
