package com.orderandnotification.orderandnotification.models.Order.logic;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.orderandnotification.orderandnotification.models.Customer.Customer;
import com.orderandnotification.orderandnotification.models.Customer.logic.CustomerRepositorybsl;
import com.orderandnotification.orderandnotification.models.Customer.logic.Customerbsl;
import com.orderandnotification.orderandnotification.models.prodcut.Product;

@Service
public class CompoundOrderbsl {
	Customerbsl customerbsl;

	public CompoundOrderbsl(Customerbsl customerbsl) {
		this.customerbsl = customerbsl;
	}

	public void placeCurrentOrder(Customer customer, int customersInCompoundOrder,
			Map<Product, Integer> customerOrder) {

		customerOrder = customer.getCurrentOrder().getCart();
		customer.getCurrentOrder().setLocation(customer.getLocation());
		customer.getCurrentOrder()
				.setShippingFee(customer.getCurrentOrder().getShippingFee() / customersInCompoundOrder);
		// customerbsl.setSimpleOrderbsl(simpleOrder);
		customerbsl.placeOrder(customer.getLocation());
	}

	public String placeCompoundOrder(CustomerRepositorybsl customersRepository, List<String> customerNames) {

		int customersInCompoundOrder = (customerNames.size());
		// current customer
		String customersParticpated = new String();

		// customers
		for (String customerName : customerNames) {
			Customer customer = customersRepository.getCustomer(customerName);
			customersParticpated += customer.getUsername() + " ";
			customerbsl.setCustomer(customer);
			SimpleOrderbsl simpleOrderbsl = new SimpleOrderbsl(customer); 
			customerbsl.placeCurrentOrder(customer, customersInCompoundOrder ,simpleOrderbsl);
		}

		return "order is placed for " + customersParticpated;
	}

}
