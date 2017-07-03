package com.bigroi.shop.dao.test.app;

import java.io.Closeable;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bigroi.shop.dao.UserDao;
import com.bigroi.shop.model.User;

public class AppContextTestApp {

	public static void main(String[] args) throws Exception {
		
//		PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
//        
//		Properties properties = new Properties();
//        properties.load(Properties.class.getResourceAsStream("/datasource.properties"));        
//        configurer.setProperties(properties);
         
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("dao-config.xml");
//        appContext.addBeanFactoryPostProcessor(configurer);
//        appContext.setConfigLocation("dao-config.xml");
//        appContext.refresh();
		
		DataSource shopDataSource = appContext.getBean("dataSource", DataSource.class);
		try (Connection con = shopDataSource.getConnection()) {
			System.out.println("Connection obtained");	
		}
		
		UserDao userDao = appContext.getBean("userDao", UserDao.class);
		List<User> users = userDao.findAll();
		
		System.out.println("There are the following users in the database: ");
		for(User user : users) {
			System.out.println(user);
		}
		
		
		if (appContext instanceof Closeable) {
			((Closeable) appContext).close();
		}
	}

}
