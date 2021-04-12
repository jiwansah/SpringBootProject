package com.example.repository;

import org.springframework.stereotype.Repository;

import com.example.entitybean.Customer;

import org.springframework.data.repository.CrudRepository;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	
	
	
}
