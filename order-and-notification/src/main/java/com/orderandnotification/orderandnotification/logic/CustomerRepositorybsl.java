package com.orderandnotification.orderandnotification.logic;

import com.orderandnotification.orderandnotification.models.Customer.Customer;
import com.orderandnotification.orderandnotification.models.Customer.CustomersRepository;
import com.orderandnotification.orderandnotification.models.Product;
import org.springframework.stereotype.Service;

@Service
public class CustomerRepositorybsl {
    private final CustomersRepository customersRepository;
    CustomerRepositorybsl(CustomersRepository customersRepository){
        this.customersRepository = new CustomersRepository();
    }
    public String addCustomer(Customer customer) {
        for (Customer customersRepositoryCustomer : customersRepository.getCustomers()) {
            if (customersRepositoryCustomer.getUsername().equals(customer.getUsername()) ){
                return "This username is used before";
            }
        }
        customersRepository.addCustomer(customer);
        return "Customer added";
    }
    public Customer getCustomer(String name){
        for (Customer customer : customersRepository.getCustomers()) {
            if (customer.getUsername().equals(name)){
                return customer;
            }
        }
        return null;
    }
}
