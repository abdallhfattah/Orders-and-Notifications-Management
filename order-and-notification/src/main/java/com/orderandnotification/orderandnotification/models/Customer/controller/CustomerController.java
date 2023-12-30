package com.orderandnotification.orderandnotification.models.Customer.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.orderandnotification.orderandnotification.models.Customer.logic.Customerbsl;
import com.orderandnotification.orderandnotification.models.Order.Order;

@RestController
public class CustomerController {
    private Customerbsl customerbsl;

    public CustomerController(Customerbsl cbsl) {
        customerbsl = cbsl;
    }


    // CHANGE PASSWORD ->
    @GetMapping("/{customer}/get-orders")
    public List<Order> getOrders(@PathVariable("customer") String name)
    {
        return customerbsl.getOrders(name);
    }

    public record ProductsM(Map<String, Integer> productsMap) {
    }

    @PostMapping("/{customer}/add-simple-order")
    public String addSimpleOrder(@RequestBody ProductsM products,@RequestBody String location , @PathVariable("customer") String name) {
        System.out.println(products.productsMap());
        return customerbsl.addSimpleOrder(products.productsMap(), name);
        // return customerbsl.addSimpleOrder(simpleOrder , name);
    }

    public record BalanceDTO(Double balance) {}
    
    @PostMapping("/{customer}/add-balance")
    public String addBalance(@PathVariable("customer") String name, @RequestBody BalanceDTO balanceDTO) {
        return customerbsl.addBalance(balanceDTO.balance(), name);
    }
}
