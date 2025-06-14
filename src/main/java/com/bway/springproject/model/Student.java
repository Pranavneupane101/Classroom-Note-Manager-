package com.bway.springproject.model;

import java.util.Set;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
 
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

@Data
@Entity
@Table(name="stu_tblle")

public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	  private int id;
    private String gender; 
	   private String grade;
	  
	    private String name; 
	    private String password;// Ensure this is a String
        private String rollNo;
	    private String username;
	    private String photoPath;
	    
	    
	    
	    
	 
	    
	     

	}
