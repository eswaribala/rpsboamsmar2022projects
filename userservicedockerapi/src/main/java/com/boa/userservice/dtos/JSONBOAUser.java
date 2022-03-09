package com.boa.userservice.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class JSONBOAUser {
	
	private long userId;
	
	private String userName;

	private String password;
	
	private LocalDate dob;
	
	private FullName fullName;

	private Gender gender;

}
