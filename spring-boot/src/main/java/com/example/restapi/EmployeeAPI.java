package com.example.restapi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entitybean.Employee;

public interface EmployeeAPI {

	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> employees(@RequestParam(value = "sort", defaultValue = "name") String sort);
	
	@GetMapping("/employee/{id}")
	public Employee employee(@PathVariable("id") int id);
	
	@PostMapping("/employee")
	public Employee saveEmployee(@RequestBody Employee newEmployee);
	 
	@PutMapping("/employee/{id}")
	public Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable("id") int id);

	@DeleteMapping("/employee/{id}")
	public Employee deleteEmployee(@PathVariable("id") int id);
}
