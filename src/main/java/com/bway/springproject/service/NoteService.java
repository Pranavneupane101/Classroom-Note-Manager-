package com.bway.springproject.service;

import java.util.List;

import com.bway.springproject.model.Note;
 
 

public interface NoteService {
	void NoteSignup(Note nn);
	List <Note> findNotes(int teacherid);
	void deletefile(int id);
	 
}
