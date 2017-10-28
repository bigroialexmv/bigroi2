package com.bigroi.shop.web.test;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ProductsPageTest {
	
	private static WebDriver webDriver ;

	@BeforeClass
	public static void setUp() throws Exception {
		System.out.println("setUp");
		System.setProperty("webdriver.chrome.driver", "C:\\temp\\chromedriver.exe");

//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("window-size=1024,768");
//
//		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		
		webDriver = new ChromeDriver();
//		webDriver.get("http://google.com/");
//		if (webDriver instanceof JavascriptExecutor) {
//			((JavascriptExecutor) webDriver)
//				.executeScript("alert('hello world');");
//		}
	}

	@AfterClass
	public static void tearDown() throws Exception {
		System.out.println("tearDown");
		webDriver.close();
	}

	@Test
	public void test() {
		System.out.println("test");
		 webDriver.get("http://localhost:8080/bigroi_shop_web/products");
		 System.out.println(webDriver.getPageSource());
	}

}
