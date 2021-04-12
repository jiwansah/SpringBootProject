package com.example.repository;

import org.springframework.stereotype.Repository;

import com.example.entitybean.Employee;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	
//	@Query(value = "SELECT e FROM Employee e")
//	List<Employee> findAllEmployee(Sort sort);
}
