package com.example.restcontroller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entitybean.Employee;
import com.example.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> employees(@RequestParam(value = "sort", defaultValue = "id") String sort,
			@RequestParam(value = "page", defaultValue = "0") int pageNo,
			@RequestParam(value = "gender", defaultValue = "0") String gender,
			@RequestParam(value = "age", defaultValue = "0") int age) {

		List<Employee> employeeList = employeeService.getAll(pageNo, sort);
		return new ResponseEntity<List<Employee>>(employeeList,  HttpStatus.OK);
	}
	

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> employee(@PathVariable("id") int id) {
		
		Optional<Employee> employee = employeeService.getByID(id);
		if(employee.isPresent()) {
			return new ResponseEntity<Employee>(employee.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PostMapping("/employees")
	public Employee saveEmployee(@RequestBody Employee newEmployee) {
		return employeeService.save(newEmployee);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee newEmployee, @PathVariable("id") int id) {
		
		Employee editEmployee = new Employee();
		Optional<Employee> employee = employeeService.getByID(id);
		if (employee.isPresent()) {
			editEmployee = employee.get();
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		editEmployee.setId(id);
		editEmployee.setDesignation(newEmployee.getDesignation());
		editEmployee.setSalary(newEmployee.getSalary());
		editEmployee.setBusinessUnit(newEmployee.getBusinessUnit());
		editEmployee.setAccountName(newEmployee.getAccountName());
		editEmployee.setProjectId(newEmployee.getProjectId());
		editEmployee.setReportingOfficerId(newEmployee.getReportingOfficerId());
		
		return new ResponseEntity<Employee>(employeeService.update(editEmployee), HttpStatus.CREATED);
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") int id) {
		Optional<Employee> employee = employeeService.getByID(id);
		if (!employee.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		employeeService.delete(id);
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	
	
}
