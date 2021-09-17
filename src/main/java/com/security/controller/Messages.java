package com.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Messages {

	 @GetMapping(value = "/admin/greet/{admin}")
	  public String greetToAdmin(@PathVariable String admin) {
		     return "Hello, " + admin;
	  }
	  
	@GetMapping(value = "/manager/greet/{manager}")
	  public String greetToManager(@PathVariable String manager) {
		     return "Hello, " + manager;
	  }
	  
	@GetMapping(value = "/employee/greet/{employee}")
	  public String greetToEmployee(@PathVariable String employee) {
		     return "Hello, " + employee;
	  }
	
	@GetMapping(value = "/everyone/greet")
	  public String greetToEmployee() {
		     return "Hello Sir/Madam" ;
	  }
}
