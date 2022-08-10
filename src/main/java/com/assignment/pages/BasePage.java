package com.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage extends Page {

	public BasePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String getPageTitle() {
		return driver.getTitle();
	}

	@Override
	public String getPageHeader(By locator) {
		return getElementBy(locator).getText();
	}

	@Override
	public WebElement getElementBy(By locator) {
		WebElement element = null;
		
		try {
			element = driver.findElement(locator);
			return element;
		}catch(Exception e) {
			System.out.println("Some Error Occured while creating element"+locator.toString());
			e.printStackTrace();
		}
		
		return element;
	}

	@Override
	public void waitForElementPresent(By locator) {

	}

	@Override
	public void waitForPageTitle(String title) {

	}


}
