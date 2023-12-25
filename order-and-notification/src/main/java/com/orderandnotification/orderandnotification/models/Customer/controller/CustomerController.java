package com.orderandnotification.orderandnotification.models.Customer.controller;

import java.util.List;

import com.orderandnotification.orderandnotification.models.Order.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderandnotification.orderandnotification.models.Customer.Customer;
import com.orderandnotification.orderandnotification.models.Customer.logic.CustomerRepositorybsl;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerRepositorybsl customersRepository;
    CustomerController(CustomerRepositorybsl customersRepositorybsl){
        this.customersRepository =  customersRepositorybsl;
    }

    @PostMapping("/add")
    public String addCustomer(@RequestBody Customer customer) {
        return customersRepository.addCustomer(customer);
    }

    @GetMapping("/get/{name}")
    public Customer getCustomer(@PathVariable("name") String name){
        return customersRepository.getCustomer(name);
    }

    @GetMapping("/get")
    public List<Customer> getCustomers(){
        return customersRepository.getCustomers();
    }

    @GetMapping("/{customer}/get-orders")
    public List<Order> getOrders(@PathVariable("customer") String name) {
        return customersRepository.getCustomer(name).getOrders();
    }

}
