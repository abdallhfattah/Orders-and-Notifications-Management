package com.orderandnotification.orderandnotification.controllers.customersControllers;

import com.orderandnotification.orderandnotification.logic.CustomerRepositorybsl;
import com.orderandnotification.orderandnotification.models.Customer.Customer;
import com.orderandnotification.orderandnotification.models.Customer.CustomersRepository;
import com.orderandnotification.orderandnotification.models.Product;
import org.springframework.web.bind.annotation.*;


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
}
