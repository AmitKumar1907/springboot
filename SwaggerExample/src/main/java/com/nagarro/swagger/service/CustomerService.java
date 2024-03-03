package com.nagarro.swagger.service;

import com.nagarro.swagger.model.Customer;
import com.nagarro.swagger.repo.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerrepo;

	public void saveCustomer(Customer customer) {
		customerrepo.save(customer);

	}

	public void deleteCustomer(int id) {
		Customer customer = customerrepo.getOne(id);
		customerrepo.delete(customer);

	}

	public List<Customer> findAllCustomers() {
		List<Customer> allCustomers = customerrepo.findAll();

		return allCustomers;
	}

	public void updateCustomer(Customer customer) {
		customerrepo.save(customer);

	}





}
