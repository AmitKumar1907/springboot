package com.nagarro.CustomerManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.nagarro.CustomerManagement.domain.Customer;
import com.nagarro.CustomerManagement.repo.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private WebClient.Builder webClientBuilder;
	@Value("${accountmanagement.url}")
	private String accountManagementUrl;

	public void saveCustomer(Customer customer) {
		customerRepo.save(customer);
	}

	public void deleteCustomer(String accountNumber) {
		deleteCustomerAccount(accountNumber);
		Customer customer = customerRepo.findByAccountNumber(accountNumber);
		customerRepo.delete(customer);
	}

	private void deleteCustomerAccount(String accountNumber) {
		webClientBuilder.build().delete().uri(accountManagementUrl + accountNumber).retrieve().bodyToMono(Void.class)
				.block();

	}

	public List<Customer> findAllCustomers() {
		List<Customer> allcustomers = customerRepo.findAll();
		return allcustomers;
	}

	public Customer getCustomerByAccountNumber(String accountNumber) {
		return customerRepo.findByAccountNumber(accountNumber);
	}

	public Customer updateCustomer(int customerId, Customer updatedCustomer) {
		Customer existingCustomer = customerRepo.findByCustomerId(customerId);

		existingCustomer.setName(updatedCustomer.getName());
		existingCustomer.setContact(updatedCustomer.getContact());
		existingCustomer.setEmail(updatedCustomer.getEmail());

		return customerRepo.save(existingCustomer);

	}

	public Customer getCustomer(int id) {

		return this.customerRepo.findByCustomerId(id);

	}

}
