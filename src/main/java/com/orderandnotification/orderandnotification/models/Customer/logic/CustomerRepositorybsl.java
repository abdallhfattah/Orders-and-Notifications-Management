package com.orderandnotification.orderandnotification.models.Customer.logic;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orderandnotification.orderandnotification.models.Customer.Customer;
import com.orderandnotification.orderandnotification.models.Customer.CustomersRepository;

@Service
public class CustomerRepositorybsl {
    private CustomersRepository customersRepository;

    CustomerRepositorybsl(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    public String addCustomer(Customer customer) {
        for (Customer customersRepositoryCustomer : customersRepository.getCustomers()) {
            if (customersRepositoryCustomer.getUsername().equals(customer.getUsername())) {
                return "This username is used before";
            }
        }
        customersRepository.addCustomer(customer);
        return "Customer added";
    }

    public Customer getCustomer(String name) {
        for (Customer customer : customersRepository.getCustomers()) {
            if (customer.getUsername().equals(name)) {
                return customer;
            }
        }
        return null;
    }

    public List<Customer> getCustomers() {
        return customersRepository.getCustomers();
    }
}
