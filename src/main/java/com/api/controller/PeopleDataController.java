package com.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.service.CsvToDBService;

@RestController
@RequestMapping("/api")
public class PeopleDataController {
	
	
	@Autowired
	private CsvToDBService csvToDBService;
	
	
	@PostMapping("/csvToDb")
	public ResponseEntity<?> uploadCsvToDb(@RequestParam("csv_file") MultipartFile inputCsvFile) throws Exception{
		
		
		csvToDBService.handlePeopleDataCsvUpload(inputCsvFile);
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("result", "success");
		responseMap.put("message", "csv file imported into db successfully");
		
		return ResponseEntity.status(HttpStatus.OK).body(responseMap);
		
		
	}

}
