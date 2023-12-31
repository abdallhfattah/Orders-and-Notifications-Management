package com.orderandnotification.orderandnotification.models.Order.controller;

import org.springframework.web.bind.annotation.RestController;

import com.orderandnotification.orderandnotification.models.Customer.logic.CustomerRepositorybsl;
import com.orderandnotification.orderandnotification.models.Order.logic.Orderbsl;

@RestController
public class OrderController {
    private final Orderbsl ordrebsl;
    private final CustomerRepositorybsl customerRepositorybsl;

    public OrderController(Orderbsl ordrebsl, CustomerRepositorybsl customerRepositorybsl) {
        this.ordrebsl = ordrebsl;
        this.customerRepositorybsl = customerRepositorybsl;
    }
}
