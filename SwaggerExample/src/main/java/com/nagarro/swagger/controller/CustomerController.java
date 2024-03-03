package com.nagarro.swagger.controller;

import com.nagarro.swagger.model.Customer;
import com.nagarro.swagger.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/Customer")
public class CustomerController {
	@Autowired
	private CustomerService customerservice;

	@PostMapping("/")//use customer dto
	public Customer addCustomer(@RequestBody Customer customer) {
		customerservice.saveCustomer(customer);
		return customer;
	}

	@DeleteMapping("/{id}")
	public String deleteCustomer(@PathVariable int id) {

		customerservice.deleteCustomer(id);
		return "deleted";

	}

	@GetMapping("/getCustomer")

	public List<Customer> getCustomers() {
		return customerservice.findAllCustomers();

	}

	@PutMapping("/update")
	public Customer updateCustomer(Customer customer) {
		customerservice.updateCustomer(customer);
		return customer;
	}



}
