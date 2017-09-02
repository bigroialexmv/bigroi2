package com.bigroi.shop.model;

public enum OrderStatus {
    NEW(0), APPROVED(1), DELIVERED(2), CANCELLED(3);
	
	private int status;
	
	OrderStatus(int status) {
		this.status = status;
	}
	
	int getStatus() {
		return status;
	}
	void setStatus(int status) {
		this.status = status;
	}
	
	static OrderStatus findByStatus(int status) {
		OrderStatus [] orderStatuses = OrderStatus.values();
		OrderStatus os = null;
		for(OrderStatus elem : orderStatuses) {
			if (elem.getStatus() == status)
				os = elem;
		}
		 return os;
		
	
}
}
