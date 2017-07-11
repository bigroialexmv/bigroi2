package com.bigroi.shop.test.sample;

public class Calculator {
	
	public int sum(int[] a) {
		int result = 0;
		for (int i = 0; i < a.length; i++) {
			result = result + a[i];
		}
		return result;
	}
	
	public int sum(int a, int b) {
		return a + b;
	}

}
