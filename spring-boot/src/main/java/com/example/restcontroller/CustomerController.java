package com.example.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entitybean.Customer;
import com.example.service.CustomerService;


@RestController
public class CustomerController {

	@Autowired
    private CustomerService customerService;
	
	
	@GetMapping("/customer")
	public List<Customer> customer() {

		System.out.println("GET Customer");
		List<Customer> customerList = customerService.getAll();
		return customerList;
	}
	

	@PostMapping("/customer")
	public Customer newCustomer(@RequestBody Customer newCustomer) {
		return customerService.save(newCustomer);
	}

	
}
