package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.entitybean.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	public List<Employee> getAll(int pageNo, String sort) {
		Pageable pageable = PageRequest.of(pageNo, 5, Sort.by(Sort.Direction.ASC, sort));
	    // Iterable<Employee> iterable = employeeRepository.findAll(Sort.by(Sort.Direction.ASC, sort));
		Iterable<Employee> iterable = employeeRepository.findAll(pageable);
	    List<Employee> employee = new ArrayList<Employee>();
	    iterable.forEach(employee::add);
	    return employee;
	}
	
	public Optional<Employee> getByID(int id) {
		return employeeRepository.findById(id);
	}
	
	public Employee save(Employee employee) {
	    return employeeRepository.save(employee);
	}
	
	public Employee update(Employee employee) {
		if (employeeRepository.existsById(employee.getId()) ) {
			return employeeRepository.save(employee);
		}
	    return null;
	}
	
	public void delete(int id) {
	    employeeRepository.deleteById(id);
	}
}
