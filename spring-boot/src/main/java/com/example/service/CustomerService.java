package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entitybean.Customer;
import com.example.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	
	public List<Customer> getAll() {
	    Iterable<Customer> iterable = customerRepository.findAll();
	    List<Customer> customers = new ArrayList<Customer>();
	    iterable.forEach(customers::add);
	    return customers;
	}
	
	public Customer save(Customer customer) {
	    return customerRepository.save(customer);
	}
	
	
}
