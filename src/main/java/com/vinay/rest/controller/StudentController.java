package com.vinay.rest.controller;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.vinay.rest.dto.Student;
import com.vinay.rest.exception.StudentNotFoundException;
import com.vinay.rest.service.StudentService;

//StudentResource
//@Controller
//@ResponseBody
@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	MessageSource messageSource;

	@GetMapping("/students")
	// @ResponseBody
	List<Student> findAll() {
		return studentService.findAll();
	}

	@PostMapping("/students")
	public ResponseEntity save(@Valid @RequestBody Student student) {
		Student studentDb = studentService.save(student);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(studentDb.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	@GetMapping("/students/{id}")
	EntityModel<Student> findOne(@PathVariable Integer id) {
		Student student = studentService.findOne(id);
		/*
		 * Resource<Student> resource = new Resource<Student>(student);
		 * ControllerLinkBuilder linkTo = linkTo((
		 * ControllerLinkBuilder.methodOn(this.getClass())).findAll());
		 * resource.add(linkTo.withRel("all-students")); return resource;
		 */

		// Since >=2.2.0 boot
		EntityModel<Student> model = new EntityModel<>(student);
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll());
		model.add(linkTo.withRel("all-students"));
		return model;

	}

	@DeleteMapping("/students/{id}")
	Student deleteById(@PathVariable Integer id) {
		Student student = studentService.deleteById(id);
		if (student == null) {
			// throw new RuntimeException("Student not found with id: " + id);
			throw new StudentNotFoundException("Student not found with id: " + id);
		}
		return student;
	}

	@GetMapping("/getInternationalizedMessage")
	public String getInternationalizedMessage(
			@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}

	@GetMapping("/getInternationalizedMessage2")
	public String getInternationalizedMessage2() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}

	@GetMapping("/studentsWithFilterNameAndBirthDate/{id}")
	MappingJacksonValue studentsWithFilterNameAndBirthDate(@PathVariable Integer id) {
		Student student = studentService.findOne(id);
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(student);

		mappingJacksonValue.setFilters(getFilterProvider("StudentFilter", "name", "birthDate"));

		return mappingJacksonValue;

	}

	@GetMapping("/studentsWithFilterNameAndId/{id}")
	MappingJacksonValue studentsWithFilterNameAndId(@PathVariable Integer id) {
		Student student = studentService.findOne(id);
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(student);
		mappingJacksonValue.setFilters(getFilterProvider("StudentFilter", "name", "id"));
		return mappingJacksonValue;
	}

	@GetMapping("/studentsWithFilterNameAndId")
	MappingJacksonValue studentsWithFilterNameAndId() {
		List<Student> studentList = studentService.findAll();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(studentList);
		mappingJacksonValue.setFilters(getFilterProvider("StudentFilter", "name", "birthDate"));
		return mappingJacksonValue;
	}

	private FilterProvider getFilterProvider(String filterBeanName, String... fields) {
	//	SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept(fields);
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter(filterBeanName, simpleBeanPropertyFilter);
		return filterProvider;
	}

}
