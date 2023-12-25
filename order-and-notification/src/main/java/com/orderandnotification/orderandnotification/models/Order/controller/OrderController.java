package com.orderandnotification.orderandnotification.models.Order.controller;

import com.orderandnotification.orderandnotification.models.Customer.Customer;
import com.orderandnotification.orderandnotification.models.Customer.logic.CustomerRepositorybsl;
import com.orderandnotification.orderandnotification.models.Order.SimpleOrder;
import com.orderandnotification.orderandnotification.models.Order.logic.Orderbsl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private final Orderbsl ordrebsl;
    private final CustomerRepositorybsl customerRepositorybsl;

    public OrderController(Orderbsl ordrebsl, CustomerRepositorybsl customerRepositorybsl) {
        this.ordrebsl = ordrebsl;
        this.customerRepositorybsl = customerRepositorybsl;
    }

    @PostMapping("/{customer}/add-simple-order")
    public String addSimpleOrder(@RequestBody SimpleOrder simpleOrder, @PathVariable("customer") String name) {
        Customer customer = customerRepositorybsl.getCustomer(name);
        simpleOrder.setCustomer(customer);
        customer.makeOrder(simpleOrder);
        return "Order Added";
    }
}
