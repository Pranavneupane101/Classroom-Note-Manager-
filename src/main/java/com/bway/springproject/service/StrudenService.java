package com.bway.springproject.service;

import com.bway.springproject.model.Student;
 

public interface StrudenService {
	
	void studentSignup(Student std);
	Student userlogin(String un,String pw);
	
	

}
