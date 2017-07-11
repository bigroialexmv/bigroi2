package com.bigroi.shop.test.sample;

//import org.junit.Assert;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

public class CalculatorTest { // Test case
	
	private int[] a = new int [] {1,2,3};
	
	private static Calculator calculator;
	
	@BeforeClass
	public static void setUp() {
		System.out.println("@BeforeClass");
		calculator = new Calculator();
	}
	
	@AfterClass
	public static void shutdown() {
		System.out.println("@AfterClass");
	}
	
	@Before
	public void before() {
		System.out.println("@Before");
		calculator = new Calculator();
	}
	
	@After
	public void after() {
		System.out.println("@After");		
	}

	@Test
	public void testSumArray() {
		System.out.println("testSumArray");	
		
		int result = calculator.sum(a);
		
		assertEquals(6, result);
		
		//fail("Not yet implemented");
	}
	
	@Test
	public void testSum() {
		System.out.println("testSum");

		int result = calculator.sum(2, 3);		
		
		assertEquals(6, result);		
	}
	
	

}
