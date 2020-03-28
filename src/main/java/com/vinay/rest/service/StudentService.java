package com.vinay.rest.service;

import java.util.List;

import com.vinay.rest.dto.Student;

//StudentBusinessObject
public interface StudentService {

	List<Student> findAll();

	Student save(Student student);

	Student findOne(Integer id);

	Student deleteById(Integer id);


}
