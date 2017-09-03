package com.bigroi.shop.filters;

public class PageableFilter {

	private static final int DEFAULT_START = 0;

	private static final int DEFAULT_COUNT = 10;

	private int start = DEFAULT_START;
	
	private int count = DEFAULT_COUNT;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
