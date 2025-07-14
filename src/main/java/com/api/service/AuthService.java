package com.api.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.entity.User;
import com.api.pojo.LoginApiData;
import com.api.pojo.SignupApiData;
import com.api.repository.UserRepository;


@Service
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
	
	private final JwtService jwtservice;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,JwtService jwtservice) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder; 
        this.jwtservice=jwtservice;
    }

    public User handleCreateAccount(SignupApiData signupApiData) throws Exception {
        Optional<User> dbData = userRepository.findByEmail(signupApiData.getEmail());
        
        if (dbData.isEmpty()) {
            User userObj = new User();
            userObj.setName(signupApiData.getName());
            userObj.setEmail(signupApiData.getEmail());
            userObj.setPassword(passwordEncoder.encode(signupApiData.getPassword()));
            userObj.setMobile(signupApiData.getMobile());
            
            return userRepository.save(userObj);
        } else {
            throw new Exception("User already exists. Please login");
        }
    }
    
 public Map<String, Object> handleLogin(LoginApiData loginApiData) throws Exception {
    	
    	Optional<User> dbData = userRepository.findByEmail(loginApiData.getEmail());
    	
    	if(dbData.isEmpty()) {
    		throw new Exception("Email is not registered with us. Please Signup");
    	}else {
    		User userData = dbData.get();
    		Boolean isMatching=passwordEncoder.matches(loginApiData.getPassword(), userData.getPassword());
    	  
    		if(isMatching==true) {
    			String jwtToken = jwtservice.generateJwtToken(userData);
    			
    			Map<String, Object> response = new HashMap<String, Object>();
    			response.put("token", jwtToken);
    			response.put("userData", userData);
    			return response;
    		}else {
    			throw new Exception("Password is not matching,Please try again.");
    		}
    	
    	}
    	
    	
    }
}




