package com.api.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.api.entity.PeopleData;
import com.api.repository.PeoplesDataRepository;
import com.opencsv.CSVReader;

@Service
public class CsvToDBService {
	
	/*
	 * validate file type -> if not csv, throw error
	 * add opencsv dependency
	 * use third party library and get whole data as array
	 * upload to table
	 * 
	 * 
	 * 
	 */
	
	@Autowired
	private PeoplesDataRepository peoplesDataRepository;
	
	public void handlePeopleDataCsvUpload(MultipartFile inputCsvFile) throws Exception {
		String fileName = StringUtils.cleanPath(inputCsvFile.getOriginalFilename());
		String fileExtension  =StringUtils.getFilenameExtension(fileName);
		
		System.out.println(fileExtension);
		
		if(fileExtension.equals("csv")== false) {
			throw new Exception("Please upload CSV file.");
			
		}
		
		try(	Reader reader = new BufferedReader(new InputStreamReader(inputCsvFile.getInputStream()));
				
				CSVReader csvReader = new CSVReader(reader);
			) {
			
		//	System.out.println(Arrays.toString(csvReader.readNext()));
			
	    //		System.out.println(Arrays.toString(csvReader.readNext()));

			
			String[] csvRow = csvReader.readNext();
			csvRow = csvReader.readNext();
			
			List<PeopleData> peoplesdatalist = new ArrayList<>();
			
			while(csvRow!=null) {
				//System.out.println(Arrays.toString(csvRow));
				
				
				PeopleData data = new PeopleData();
				
				data.setIndexId(Integer.parseInt(csvRow[0]));
				data.setUserId(csvRow[1]);
				data.setFirstName(csvRow[2]);
				data.setLastName(csvRow[3]);
				data.setSex(csvRow[4]);
				data.setEmail(csvRow[5]);
				data.setDateofBirth((csvRow[6]));
				data.setPhoneno(csvRow[7]);
				data.setJobdesc(csvRow[8]);
				
//			//	peoplesDataRepository.save(data);
				
				peoplesdatalist.add(data);
				
				if(peoplesdatalist.size()==500) {
					peoplesDataRepository.saveAll(peoplesdatalist);
					peoplesdatalist.clear();
				}
				
				csvRow = csvReader.readNext();
				
			}
			
			if(peoplesdatalist.size()>0) {
				peoplesDataRepository.saveAll(peoplesdatalist);
				peoplesdatalist.clear();
			}
			
//			csvReader.close();
//			reader.close();
//			
			
		}catch(Exception e) {
			throw new Exception("Unable to upload csv data.");
			
		}
		

		
		
		
		
		
	}

}
