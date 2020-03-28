package com.vinay.rest.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.vinay.rest.dto.Student;

@Repository
public class StudentDaoImpl implements StudentDao {

	private static List<Student> students = new ArrayList<>();
	private static int usersCount = 0;

	static {
		students.add(new Student(++usersCount, "Adam", new Date()));
		students.add(new Student(++usersCount, "Eve", new Date()));
		students.add(new Student(++usersCount, "Jack", new Date()));
	}

	@Override
	public List<Student> findAll() {
		return students;
	}

	@Override
	public Student save(Student student) {
		if (student.getId() == null) {
			student.setId(++usersCount);
		}
		students.add(student);
		return student;
	}

	@Override
	public Student findOne(Integer id) {
		for (Student student : students) {
			if (student.getId().compareTo(id) == 0) {
				return student;
			}
		}
		return null;
	}

	public Student deleteById0(Integer id) {
		for (Student student : students) {
			if (student.getId().compareTo(id) == 0) {
				students.remove(id);
				return student;
			}
		}
		return null;
	}

	@Override
	public Student deleteById(Integer id) {
		Iterator iterator = students.iterator();
		while (iterator.hasNext()) {
			Student student = (Student) iterator.next();
			if (student.getId().compareTo(id) == 0) {
				iterator.remove();
				return student;
			}
		}
		return null;
	}

}
