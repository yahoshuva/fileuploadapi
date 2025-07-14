package com.api.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="peoples_data")
public class PeopleData {
	
	//Index	User Id	First Name	Last Name	Sex	Email	Phone	Date of birth	Job Title

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int indexId;
	private String userId;
	private String firstName;
	private String lastName;
	private String sex;
	private String email;
	private String phoneno;
	private String dateofBirth;
	private String jobdesc;
	
	
	
 
}
