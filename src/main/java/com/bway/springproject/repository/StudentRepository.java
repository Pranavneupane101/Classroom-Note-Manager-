package com.bway.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import com.bway.springproject.model.Student;

 

public interface StudentRepository extends  JpaRepository<Student, Integer> {
	Student findByUsernameAndPassword(String un, String pw);
}
