package com.bigroi.shop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCart {
	
	private List<ShoppingCartItem> items = 
			Collections.synchronizedList(new ArrayList<ShoppingCartItem>());
	
	public void addShoppingItem(ShoppingCartItem item) {
		items.add(item);
	}
	
	public ShoppingCartItem[] getItems() {
		return items.toArray(new ShoppingCartItem[0]);
	}

}
