package com.bway.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bway.springproject.model.Student;
import com.bway.springproject.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{
	Teacher findByUsernameAndPasswordAndCode(String username, String password, String code);
}
