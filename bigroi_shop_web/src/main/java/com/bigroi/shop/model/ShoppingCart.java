package com.bigroi.shop.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ShoppingCart {
	
	private Lock lock = new ReentrantLock();
	
	private Map<Integer, ShoppingCartItem> items = 
			new ConcurrentHashMap<Integer, ShoppingCartItem>();
	
	public void addShoppingItem(ShoppingCartItem item) {
		lock.lock();
		try {
			if (items.containsKey(item.getProductCode())) {
				ShoppingCartItem i = items.get(item.getProductCode());
				i.setQuantity(i.getQuantity() + item.getQuantity());
			} else {
				items.put(item.getProductCode(), item);
			}
		} finally {
			lock.unlock();
		}
	}
	
	public ShoppingCartItem[] getItems() {
		return items.values().toArray(new ShoppingCartItem[0]);
	}
	
	public int getItemsCount() {
		// sum quanities of all items
		if (items.size() == 0) {
			return 0;
		}
		return items.values().stream().map(a -> a.getQuantity()).reduce((a, b) -> a + b ).get();		
	}

}
