package com.bigroi.shop.filters;

import java.util.List;

public class Page<F extends PageableFilter, D> {
	
	private F filter;
	
	private List<D> items;
	
	private int totalItemsCount;
	
	public Page(F filter, List<D> dataRecords, int allRecordsCount) {
		this.filter = filter;
		this.items = dataRecords;
		this.totalItemsCount = allRecordsCount;
	}

	public F getFilter() {
		return filter;
	}

	public List<D> getItems() {
		return items;
	}

	public int getTotalItemsCount() {
		return totalItemsCount;
	}
	
	public int getAllPagesCount() {
		int allPagesCount = totalItemsCount / filter.getCount();
		if (totalItemsCount % filter.getCount() != 0) {
			allPagesCount = allPagesCount + 1;
		}
		return allPagesCount;
	}
	
	public int getEnd() {
		int end = filter.getStart() + filter.getCount();
		if (end < totalItemsCount) {
			return end;
		}
		return totalItemsCount;
	}
	
	public int getPageIndex() {
		return filter.getStart() / filter.getCount() + 1;
	}
	
	public boolean hasNextPage() {
		return (getEnd() < totalItemsCount);		
	}
	
	public boolean hasPreviousPage() {
		return (filter.getStart() > 0);
	}
	
		
}
