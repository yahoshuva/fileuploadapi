package com.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.entity.User;
import com.api.pojo.LoginApiData;
import com.api.pojo.SignupApiData;
import com.api.service.AuthService;
import com.api.service.EmailService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {
	
	
	 @Autowired
	   private final AuthService authService;
	   
	 @Autowired
	 public EmailService emailService;
	   

	    @Autowired
	    public AuthController(AuthService authService) {
	        this.authService = authService;
	    }
	
	@PostMapping("/create-account")
	public ResponseEntity<Map<String, Object>> createAccount(@Valid @RequestBody SignupApiData signupApiData) throws Exception {
		
		User userDataObject = authService.handleCreateAccount(signupApiData);
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("result", "success");
		responseMap.put("data", userDataObject);
		
		return ResponseEntity.status(HttpStatus.OK).body(responseMap);
		
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginApiData loginApiData) throws Exception{
	
		Map<String,Object> userData = authService.handleLogin(loginApiData);
		
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("result", "success");
		responseMap.put("data", userData);
		
		return ResponseEntity.status(HttpStatus.OK).body(responseMap);
		
	}
	@GetMapping("/send-email")
	public ResponseEntity<?> sendEmail(){
		String fromEmail="pasupuletiyahoshuva1999@gmail.com"; // must match spring.mail.username
		String toEmail="pyahoshuva1@gmail.com"; 
		String subject="Test Email";
		String mailBody="Hi Yaho, This is the test email from Springboot.";
		System.out.println(">>> Inside Controller <<<"); // Debug line

		emailService.sendPlainEmail(fromEmail, toEmail, subject, mailBody);

		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("result", "success");
		responseMap.put("data", "Email sent");
		return ResponseEntity.status(HttpStatus.OK).body(responseMap);
	}

	

}
