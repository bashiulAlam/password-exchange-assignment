package com.assignment.tests;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.assignment.pages.Page;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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
			/*DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("incognito");
			desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);*/

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-dev-shm-usage"); 
			options.addArguments("--no-sandbox");
			options.addArguments("--headless");

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
		} else {
			System.out.println("Browser is not defined in xml");
		}
		
//		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();

		// Default value 0 Seconds implicit timeout
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Default value 300 Seconds pageload timeout
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);

		driver.get(this.url);
		
		
//		try {
//			Thread.sleep(6000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		System.out.println("URL: " + this.url);

		page = new BasePage(driver);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
