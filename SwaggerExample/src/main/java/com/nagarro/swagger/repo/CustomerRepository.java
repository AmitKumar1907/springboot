package com.nagarro.swagger.repo;


import com.nagarro.swagger.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
