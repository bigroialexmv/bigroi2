package dp;

import java.util.ArrayList;

public class OrderBuilder {
	
	private Order order = new Order();
	
	public OrderBuilder setId(String id) {
		order.setId(id);
		return this;
	}
	
	public OrderBuilder setPrice(double id) {
		order.setPrice(id);
		return this;
	}
	
	public OrderBuilder addProduct(Product p) {
		if (order.getProducts() == null) {
			order.setProducts(new ArrayList<Product>());
		}
		order.getProducts().add(p);
		return this;
	}
	
	public Order getOrder() { // build()
		return this.order;
	}

}
