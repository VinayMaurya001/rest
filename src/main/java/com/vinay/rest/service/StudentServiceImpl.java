package com.vinay.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinay.rest.dao.StudentDao;
import com.vinay.rest.dto.Student;
import com.vinay.rest.exception.StudentNotFoundException;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Override
	public List<Student> findAll() {
		return studentDao.findAll();
	}

	@Override
	public Student save(Student student) {
		return studentDao.save(student);
	}

	@Override
	public Student findOne(Integer id) {
		Student student = studentDao.findOne(id);
		if (student == null) {
			//throw new RuntimeException("Student not found with id: " + id);
			throw new StudentNotFoundException("Student not found with id: " + id);
		}
		return student;
	}

	@Override
	public Student deleteById(Integer id) {
		return studentDao.deleteById(id);
	}

}
