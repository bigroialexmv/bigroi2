package com.bigroi.shop.filters;

import java.util.List;

public class Page<T> {
	
	private PageableFilter filter;
	
	private List<T> items;
	
	private int totalItemsCount;
	
	public Page(List<T> items, int totalItemsCount, PageableFilter filter) {
		this.items = items;
		this.totalItemsCount = totalItemsCount;
		this.filter = filter;
	}

	public List<T> getItems() {
		return items;
	}

	public int getTotalItemsCount() {
		return totalItemsCount;
	}
	
	public PageableFilter getFilter() {
		return filter;
	}

	public int getTotalPagesCount() {
		int totalPagesCount = totalItemsCount / filter.getCount();
		if (totalItemsCount % filter.getCount() != 0) {
			totalPagesCount ++;
		}		
		return totalPagesCount;
	}

}
