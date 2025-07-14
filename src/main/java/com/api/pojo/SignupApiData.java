package com.api.pojo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

@Data
public class SignupApiData {
	
	
	@NotNull(message = "Name required")
	@Size(min=3,message="Min 3 characters required")
	private String name;
	
	@NotNull(message="Email required")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "Email is invalid")
	private String email;
	
	@NotNull(message = "password required")
	@Size(min=8,message="Min 8 characters required")
	private String password;
	
	@NotNull(message = "Mobile Number required")
	@Size(min=10,message = "Min 10 digit ")
	private String mobile;

	
	
	
	

}
