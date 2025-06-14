package com.bway.springproject.service;

 
import com.bway.springproject.model.Teacher;

public interface TeacherService {
	void teacherSignup(Teacher tch);
	Teacher teachlogin(String un,String pw, String cd );
	
}
