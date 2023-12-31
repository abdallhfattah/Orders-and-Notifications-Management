package com.orderandnotification.orderandnotification.models.Customer.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderandnotification.orderandnotification.models.Customer.logic.Customerbsl;
import com.orderandnotification.orderandnotification.models.Order.Order;

@RequestMapping("/{customer}")
@RestController
public class CustomerController {
    private Customerbsl customerbsl;

    public CustomerController(Customerbsl cbsl) {
        customerbsl = cbsl;
    }

    // CHANGE PASSWORD ->
    @GetMapping("/get-orders")
    public List<Order> getOrders(@PathVariable("customer") String name) {
        return customerbsl.getOrders(name);
    }

    // compoundOrder -> (String customer -> simpleorder) 
    public record ProductsM(Map<String, Integer> productsMap) {
    }

    
    @PostMapping("/add-simple-order")
    public String addSimpleOrder(@RequestBody ProductsM products, @PathVariable("customer") String name) {
        // ,@RequestBody String location
        // System.out.println(products.productsMap());
        return customerbsl.addSimpleOrder(products.productsMap(), name);
    }
    @PostMapping("/add-compound-order")
    public String addCompoundOrder(@RequestBody List<String> customers, @PathVariable("customer") String name) {
        // use has to pick location
        return customerbsl.placeCompoundOrder(customers, name);
    }
    public record BalanceDTO(Double balance) {
    }

    @PostMapping("/add-balance")
    public String addBalance(@PathVariable("customer") String name, @RequestBody BalanceDTO balanceDTO) {
        return customerbsl.addBalance(balanceDTO.balance(), name);
    }

    public record Location(String location) {
    }

    @PostMapping("/place-order")
    public String placeOrder(@RequestBody Location location) {
        return customerbsl.placeOrder(location.location());
    }

    @PostMapping("/ship-order")
    public String shipOrder() {
        return customerbsl.shipOrder();
    }
}
