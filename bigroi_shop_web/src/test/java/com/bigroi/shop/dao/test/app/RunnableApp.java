package com.bigroi.shop.dao.test.app;

import java.util.Scanner;

public class RunnableApp {

	public static void main(String[] args) {
		for(String arg : args) {
			System.out.println(arg);
		}
		System.out.println("Enter your name:");
		try(Scanner s = new Scanner(System.in)) {
			String name = s.nextLine();
			System.out.println("Hello " + name);
		}

	}

}
