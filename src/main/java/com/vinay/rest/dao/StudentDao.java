package com.vinay.rest.dao;

import java.util.List;

import com.vinay.rest.dto.Student;

public interface StudentDao {
	List<Student> findAll();

	Student save(Student student);

	Student findOne(Integer id);

	Student deleteById(Integer id);

}
