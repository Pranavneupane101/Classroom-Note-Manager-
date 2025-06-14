package com.bway.springproject.model;

import java.util.List;

import jakarta.persistence.Entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="Teach_tbll")

public class Teacher {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String tname;	
	private String faculty;
	private String code;
	private String username;         
	private String password;
	
	@OneToMany(mappedBy = "teacher")
	private List<Note> classNotes;
	}
