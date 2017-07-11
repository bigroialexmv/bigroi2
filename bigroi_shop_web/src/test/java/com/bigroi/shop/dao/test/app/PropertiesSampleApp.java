package com.bigroi.shop.dao.test.app;

import java.io.IOException;
import java.util.Properties;

public class PropertiesSampleApp {

	public static void main(String[] args) throws IOException {
		Properties properties = new Properties();
		properties.load(PropertiesSampleApp.class.getResourceAsStream("/datasource.properties"));      
		System.out.println(properties.get("database.url")); 
     

	}

}
