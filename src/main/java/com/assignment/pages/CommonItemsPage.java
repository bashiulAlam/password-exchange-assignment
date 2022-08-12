package com.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CommonItemsPage extends BasePage {
    public CommonItemsPage(WebDriver driver) {
        super(driver);
    }

    //common items across all UIs
    By iFrame = By.xpath("//iframe[@id='frame']");
    By uiTitle = By.xpath("//span[@class='caption']");
    By leftMenuLogo = By.xpath("//span[@id='logo-inverse']//*[name()='svg']");

    public void switchToFrame() {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(iFrame));
        driver.switchTo().frame(driver.findElement(iFrame));
    }

    public void switchToWindow() {
        driver.switchTo().defaultContent();
    }

    public String getUITitle() {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(uiTitle));
        return driver.findElement(uiTitle).getText();
    }

    public void hoverOnLogo() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(leftMenuLogo)).perform();
    }
}
