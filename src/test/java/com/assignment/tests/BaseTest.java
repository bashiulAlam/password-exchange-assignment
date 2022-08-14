package com.assignment.tests;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.assignment.pages.Page;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.assignment.pages.BasePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	WebDriver driver;
	public Page page;
	
	private String url = "";

	public BaseTest(String url) {
		this.url = url;
	}
	
	@BeforeClass
	@Parameters(value= {"browser"})
	public void setupTest(String browser) {
		if(browser.equals("chrome")) {
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-dev-shm-usage"); 
			options.addArguments("--no-sandbox");
			//options.addArguments("--headless");

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
		} else {
			System.out.println("Browser is not defined in xml");
		}

		// Default value 0 Seconds implicit timeout
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Default value 300 Seconds pageload timeout
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);

		driver.get(this.url);

		System.out.println("URL: " + this.url);

		page = new BasePage(driver);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
