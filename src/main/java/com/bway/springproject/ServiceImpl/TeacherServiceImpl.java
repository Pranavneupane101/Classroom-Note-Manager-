package com.bway.springproject.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.springproject.model.Teacher;
 
import com.bway.springproject.repository.TeacherRepository;
import com.bway.springproject.service.TeacherService;
@Service

public class TeacherServiceImpl implements TeacherService {

	@Autowired 
	TeacherRepository techRepo;
	@Override
	public void teacherSignup(Teacher tch) {
		// TODO Auto-generated method stub
		techRepo.save(tch);
		
	}
	@Override
	public Teacher teachlogin(String un, String pw, String cd) {
		// TODO Auto-generated method stub
		return techRepo.findByUsernameAndPasswordAndCode(un, pw, cd);
	}
 
	 
 

}
