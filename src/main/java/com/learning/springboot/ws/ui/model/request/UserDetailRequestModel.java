package com.learning.springboot.ws.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailRequestModel {

	@NotNull(message = "First name cannot be null")
	private String firstName;
	
	@NotNull(message = "Last name cannot be null")
	@Size(min=5, max=20, message = "Last name should be between 5 and 20 characters long")
	private String lastName;
	
	@Email
	private String email;
	
	
	private String id;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
