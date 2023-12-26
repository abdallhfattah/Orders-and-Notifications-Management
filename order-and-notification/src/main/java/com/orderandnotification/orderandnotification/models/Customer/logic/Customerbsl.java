package com.orderandnotification.orderandnotification.models.Customer.logic;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orderandnotification.orderandnotification.models.Customer.Customer;
import com.orderandnotification.orderandnotification.models.Order.Order;
import com.orderandnotification.orderandnotification.models.Order.SimpleOrder;

@Service
public class Customerbsl {
	private Customer customer;
	private CustomerRepositorybsl customersRepository;
	private SimpleOrder simpleOrder;

	public Customerbsl(Customer customer, CustomerRepositorybsl cbsl) {
		this.customer = customer;
		customersRepository = cbsl;
	}

	public List<Order> getOrders(String name) {
		customer = customersRepository.getCustomer(name);
		return customer.getOrders();
	}

	public String addSimpleOrder(SimpleOrder simpleOrder, String name) {
		this.simpleOrder = simpleOrder;
		this.customer = customersRepository.getCustomer(name);

		if (customer == null) {
			return "there is no such a user";
		}

		 this.simpleOrder.setCustomer(customer); // this line casues error
		
		/*  at com.fasterxml.jackson.databind.ser.impl.IndexedListSerializer.serialize(IndexedListSerializer.java:79) ~[jackson-databind-2.15.3.jar:2.15.3]
			at com.fasterxml.jackson.databind.ser.impl.IndexedListSerializer.serialize(IndexedListSerializer.java:18) ~[jackson-databind-2.15.3.jar:2.15.3]
			at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732) ~[jackson-databind-2.15.3.jar:2.15.3]
			at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772) ~[jackson-databind-2.15.3.jar:2.15.3]
			at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178) ~[jackson-databind-2.15.3.jar:2.15.3]
			at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732) ~[jackson-databind-2.15.3.jar:2.15.3]
			at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772) ~[jackson-databind-2.15.3.jar:2.15.3]
			at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178) ~[jackson-databind-2.15.3.jar:2.15.3]
			at com.fasterxml.jackson.databind.ser.impl.IndexedListSerializer.serializeContents(IndexedListSerializer.java:119) ~[jackson-databind-2.15.3.jar:2.15.3]
		 */
		customer.makeOrder(simpleOrder);

		return "Order added Successfully";
	}

}
