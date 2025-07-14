package com.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.service.FileUploadService;
import com.api.service.JwtService;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/upload")
public class FileUploadController {
	
	
	/*
	 * CSV file upload
	 * column names and create entity with same column names
	 * Create entity (it will create table in the database)
	 * Create repository
	 * Create service
	 * 
	 * Create end point
	 * 
	 */
	
	@Autowired
	private JwtService jwtService;
	
	/*
	 * After login
	 * Receive Authorization header
	 * Check if it is null 
	 * Check if it start with Bearer
	 * Get token without Bearer
	 * Check it is valid or not
	 * Check if it is expired or not
	 * if all are okay,proceed to operation else throw error
	 * 
	 * 
	 * 
	 */
	
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@PostMapping("/images")
	public ResponseEntity<?> uploadImages(@RequestHeader("Authorization") String jwtToken,  @RequestParam("file") MultipartFile inputFile) throws Exception{
		
		System.out.println(jwtToken);
		
		if(jwtToken==null || jwtToken.startsWith("Bearer") == false) {
		throw new Exception("Unauthorized. You are not allowed to do this operation.");	
			
		}
		
		jwtToken = jwtToken.substring(7);
		
		Boolean isTokenVaild = jwtService.verifyJwtToken(jwtToken);
		System.out.println(isTokenVaild);
		
		Claims claims = jwtService.getJwtClaims(jwtToken);
		
		
		System.out.println(claims);
		
		fileUploadService.handleImageUpload(inputFile);
		
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("result", "success");
		responseMap.put("message", "image uploaded successfully");
		
		return ResponseEntity.status(HttpStatus.OK).body(responseMap);
		
	}
	

	@PostMapping("/pdfs")
	public ResponseEntity<?> uploadPDFs(@RequestParam("pdf_file") MultipartFile inputFile) throws Exception{
		
		fileUploadService.handleImageUpload(inputFile);
		
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("result", "success");
		responseMap.put("message", "PDF uploaded successfully");
		
		return ResponseEntity.status(HttpStatus.OK).body(responseMap);
		
	}

}
