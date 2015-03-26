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
package org.springframework.social.ecobee.api;

/**
 * @author Greg Turnquist
 */
public enum HoldType {

	/**
	 * Use the provided startDate, startTime, endDate and endTime for the event.
	 * If start date/time is not provided, it will be assumed to be right now.
	 * End date/time is required.
	 */
	DATE_TIME,

	/**
	 * The end date/time will be set to the next climate transition in the program.
	 */
	NEXT_TRANSITION,

	/**
	 * The hold will not end and require to be cancelled explicitly.
	 */
	INDEFINITE,

	/**
	 * Use the value in the "holdHours" parameter to set the end date/time for the event.
	 */
	HOLD_HOURS;

}
