package com.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage extends BasePage {
    public SignInPage(WebDriver driver) {
        super(driver);
    }

    //login frame elements
    By email = By.xpath("//input[@type='text']");
    By password = By.xpath("//input[@type='password']");
    By nextButton = By.xpath("//span[normalize-space()='Next']");
    By logInButton = By.xpath("//span[normalize-space()='Log In']");

    //profile elements
    By profileIcon = By.xpath("//pup-avatar[@class='avatar medium palette-5 regular']");
    By userName = By.xpath("//pup-heading[@class='name heading level-4']");

    //dashboard navigation
    By channelLink = By.xpath("//span[@class='ng-binding']");

    public void executeSignIn(String mail, String pass) {
        CommonItemsPage commonItems = new CommonItemsPage(driver);
        commonItems.switchToFrame();
        driver.findElement(email).sendKeys(mail);
        driver.findElement(nextButton).click();
        driver.findElement(password).sendKeys(pass);
        driver.findElement(logInButton).click();
        commonItems.switchToWindow();
    }

    public String getUserName() {
        driver.findElement(profileIcon).click();
        return driver.findElement(userName).getText();
    }

    public void openDashboard() {
        driver.findElement(channelLink).click();
        driver.findElement(channelLink).click();
    }

    public String getDashboardTitle() {
        CommonItemsPage commonItems = new CommonItemsPage(driver);
        commonItems.switchToFrame();
        String title = commonItems.getUITitle();
        commonItems.switchToWindow();
        return title;
    }
}
