package com.bigroi.shop.dao.test.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bigroi.shop.model.User;

public class LoggingTestApp {
	
	private static Logger logger = LoggerFactory.getLogger("com.bigroi");
	
	public static void main(String[] args) {
		logger.trace("trace message");
		logger.debug("debug message");
		logger.info("info message");
		logger.warn("warn message");
		logger.error("error message");
	}

}
