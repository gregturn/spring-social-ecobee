package org.springframework.social.ecobee.api;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EcobeeUserProfile implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String firstName;
	private String lastName;
	private String displayName;

}
