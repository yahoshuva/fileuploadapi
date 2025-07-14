package com.api.pojo;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginApiData {
	
	@NotNull(message = "Email required")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "Email is invalid")
   // @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com$", message = "Email must end with .com")

	private String email;
	
	@NotNull(message= "Password is required")
	@Size(min=8,message="Min 8 characters required")
	private String password;

}

/*
 * 
 * public class EmailValidator {

    public static boolean isValidEmail(String email) {
        // Check if email ends with .com
        if (email != null && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com$")) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String email = "user@example.com";
        if (isValidEmail(email)) {
            System.out.println(email + " is valid and on the whitelist.");
        } else {
            System.out.println(email + " is not a valid .com email.");
        }
    }
}

 */