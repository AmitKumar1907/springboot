package com.nagarro.AccountManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.nagarro.AccountManagement.domain.Account;
import com.nagarro.AccountManagement.dto.AccountCustomerDetail;
import com.nagarro.AccountManagement.dto.AccountDetail;
import com.nagarro.AccountManagement.dto.Customer;
import com.nagarro.AccountManagement.exception.InvalidFieldException;
import com.nagarro.AccountManagement.repo.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private WebClient.Builder webClientBuilder;

	@Value("${customermanagement.url}")
	private String customerManagementUrl;

	public void depositMoney(AccountDetail accountDetail) throws InvalidFieldException {
		Customer customer = webClientBuilder.build().get().uri(customerManagementUrl + accountDetail.getCustomerId())
				.retrieve().bodyToMono(Customer.class).block();

		if (customer != null && accountDetail.getAccountNumber().equals(customer.getAccountNumber())) {

			Account account = accountRepo.getByAccountNumber(accountDetail.getAccountNumber());

			if (account != null) {

				int currentBalance = account.getBalance();
				account.setBalance(currentBalance + accountDetail.getBalance());

				accountRepo.save(account);
			}
		} else {
			throw new InvalidFieldException("Invalid customer or account details");
		}
	}

	public void withdrawlMoney(AccountDetail accountDetail) throws InvalidFieldException {
		Customer customer = webClientBuilder.build().get().uri(customerManagementUrl + accountDetail.getCustomerId())
				.retrieve().bodyToMono(Customer.class).block();

		if (customer != null && accountDetail.getAccountNumber().equals(customer.getAccountNumber())) {

			Account account = accountRepo.getByAccountNumber(accountDetail.getAccountNumber());

			if (account != null) {

				int currentBalance = account.getBalance();
				account.setBalance(currentBalance - accountDetail.getBalance());

				accountRepo.save(account);
			}
		} else {
			throw new InvalidFieldException("Invalid customer or account details");
		}
	}

	public void deleteAccount(String accountNumber) {
		Account acc = accountRepo.getByAccountNumber(accountNumber);

		if (acc != null) {
			accountRepo.delete(acc);
		} else {

			throw new RuntimeException("Account with account number " + accountNumber + " not found");
		}

	}

	public List<Account> findAllAccounts() {
		List<Account> allaccounts = accountRepo.findAll();

		return allaccounts;
	}

	public AccountCustomerDetail getAccount(String accountNumber) {
		Customer customer = webClientBuilder.build().get().uri(customerManagementUrl + "?accountNumber=" + accountNumber)
				.retrieve().bodyToMono(Customer.class).block();
		Account accountDetail = accountRepo.getByAccountNumber(accountNumber);
		AccountCustomerDetail accountCustomer = new AccountCustomerDetail();
		accountCustomer.setAccountNumber(accountNumber);
		accountCustomer.setBalance(accountDetail.getBalance());
		accountCustomer.setContact(customer.getContact());
		accountCustomer.setCustomerId(customer.getCustomerId());
		accountCustomer.setName(customer.getName());
		accountCustomer.setEmail(customer.getEmail());

		return accountCustomer;

	}

}
