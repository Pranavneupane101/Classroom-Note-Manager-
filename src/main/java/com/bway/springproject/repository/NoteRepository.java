package com.bway.springproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bway.springproject.model.Note;
 
 

public interface NoteRepository extends JpaRepository<Note, Integer> {
	List<Note> findByTeacher_Id(int tid);
}
