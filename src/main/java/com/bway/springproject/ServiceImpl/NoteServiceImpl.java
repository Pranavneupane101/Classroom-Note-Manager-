package com.bway.springproject.ServiceImpl;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.springproject.model.Note;
import com.bway.springproject.repository.NoteRepository;
import com.bway.springproject.service.NoteService;
@Service
public class NoteServiceImpl implements NoteService{
	@Autowired
  NoteRepository nrepo;

	@Override
	public void NoteSignup(Note nn) {
		// TODO Auto-generated method stub
		nrepo.save(nn);
	}

	@Override
	public List<Note> findNotes(int teacherid) {
		return nrepo.findByTeacher_Id(teacherid);
	 
	}

	@Override
	public void deletefile(int id) {
		// TODO Auto-generated method stub
		nrepo.deleteById(id);
	}
   

}
