package com.bway.springproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="note_tbll") 

public class Note {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int idN;
	String Subject;
	String filepath;
	@ManyToOne
	@JoinColumn(name = "id")
	private Teacher teacher;

}
