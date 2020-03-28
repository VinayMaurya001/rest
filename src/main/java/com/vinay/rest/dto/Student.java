package com.vinay.rest.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//Package name may be model/bean/dto
//@ApiModel(value="User Details", description="Contains all details of a user")
//@JsonIgnoreProperties(value={"birthDate","id"})
//@JsonFilter("StudentFilter")
@Entity
public class Student {
	@Id
	@GeneratedValue
	private Integer id;
	
	//@JsonIgnore
	@Size(min = 2, message = "Name should have atleast 2 characters")
	//@ApiModelProperty(notes = "Name should have atleast 2 characters")
	private String name;

	@Past
	//@JsonIgnore
	//@ApiModelProperty(notes = "Birth Date should be in the Past")
	private Date birthDate;

	public Student() {
		super();
	}

	public Student(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

}
