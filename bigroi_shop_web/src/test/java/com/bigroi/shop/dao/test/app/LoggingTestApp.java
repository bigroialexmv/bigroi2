package com.bigroi.shop.dao.test.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bigroi.shop.dao.impl.UserDaoImpl;

public class LoggingTestApp {
	
	private static Logger logger = LoggerFactory.getLogger(LoggingTestApp.class);
	
	public static void main(String[] args) {
		logger.debug("debug message");
	}

}
