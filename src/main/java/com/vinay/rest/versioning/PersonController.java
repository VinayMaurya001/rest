package com.vinay.rest.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

	@GetMapping("v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("Abhi Maurya1");
	}

	@GetMapping("v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Abhi", "Maurya2"));
	}
	
	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Bob Charlie1");
	}

	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Bob", "Charlie2"));
	}

	@GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
}
