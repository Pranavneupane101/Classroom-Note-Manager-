package com.bway.springproject.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.bway.springproject.model.Student;
import com.bway.springproject.repository.StudentRepository;
 
import com.bway.springproject.service.StrudenService;
@Service

public class StudentServiceImpl implements StrudenService {
	@Autowired
	StudentRepository stuRepo; 
        
	@Override
	public void studentSignup(Student std) {
		// TODO Auto-generated method stub
		
		stuRepo.save(std);
		
		
	}

	@Override
	public Student userlogin(String un, String pw) {
		
		return stuRepo.findByUsernameAndPassword(un, pw); 
	}

	

}
