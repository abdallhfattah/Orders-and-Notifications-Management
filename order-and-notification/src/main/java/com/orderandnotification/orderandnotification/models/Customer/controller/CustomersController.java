package com.orderandnotification.orderandnotification.models.Customer.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderandnotification.orderandnotification.models.Customer.Customer;
import com.orderandnotification.orderandnotification.models.Customer.logic.CustomerRepositorybsl;


@RestController
@RequestMapping("/customerR")
public class CustomersController {
	private CustomerRepositorybsl customersRepository;

	public CustomersController(CustomerRepositorybsl customersRepositorybsl){
        this.customersRepository =  customersRepositorybsl;
    }

	@PostMapping("/add")
	public String addCustomer(@RequestBody Customer customer) {
		return customersRepository.addCustomer(customer);
	}

	@GetMapping("/get/{name}")
	public Customer getCustomer(@PathVariable("name") String name) {
		return customersRepository.getCustomer(name);
	}

	@GetMapping("/get")
	public List<Customer> getCustomers() {
		return customersRepository.getCustomers();
	}

}
