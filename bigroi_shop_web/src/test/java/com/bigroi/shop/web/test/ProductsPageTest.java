package com.bigroi.shop.web.test;

import org.junit.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

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
		Thread.sleep(10000);
		webDriver.close();
	}

	@Test
	public void test() throws InterruptedException {
		WebElement inputName = null;
		String productName = null;
		System.out.println("test");
		 webDriver.get("http://localhost:8080/bigroi_shop_web/products");
		 System.out.println(webDriver.getPageSource());
		 webDriver.findElement(By.cssSelector("a[href*='product?code=41']")).click();
		 webDriver.findElement(By.cssSelector("input[value='Edit']")).click();
		
//		 ChaneProductPage page = PageFactory.initElements(webDriver, ChaneProductPage.class);
//		 Assert.assertEquals("Inconsistent product name", 
//				 "Xiaomi Redmi Note 4X 32GB Black", page.getNameTextField() );
//		 page.setNameTextField("Xiaomi Redmi Note 4X 32GB Black - Updated");
//		 page.submit();
		 
		 inputName = webDriver.findElement( By.cssSelector("input[name='name']") );
		 productName = inputName.getAttribute("value");
		 Assert.assertEquals("Inconsistent product name", 
				 "Xiaomi Redmi Note 4X 32GB Black", productName);
		 inputName.clear(); 
		 inputName.sendKeys("Xiaomi Redmi Note 4X 32GB Black - Updated");
		 Thread.sleep(3000);
		 WebElement saveButton = webDriver.findElement(By.cssSelector("input[value='Save']"));
		 
		 System.out.println(saveButton);
		 saveButton.submit();
		 //saveButton.click();
		 webDriver.findElement(By.cssSelector("input[value='Edit']")).click();
		 
		 inputName = webDriver.findElement( By.cssSelector("input[name='name']") );
		 productName = inputName.getAttribute("value");
		 Assert.assertEquals("Inconsistent product name", 
				 "Xiaomi Redmi Note 4X 32GB Black - Updated", productName);
	}

}
