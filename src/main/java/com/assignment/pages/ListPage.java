package com.assignment.pages;

import com.assignment.utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ListPage extends BasePage {
    public ListPage(WebDriver driver) {
        super(driver);
    }

    By listLink = By.xpath("//li[10]//a[1]");
    By addListButton = By.xpath("//span[normalize-space()='Add list']");
    By standardInclusionExclusionOption = By.xpath("//span[normalize-space()='Standard Inclusion/Exclusion']");
    By continueButton = By.xpath("//span[normalize-space()='Continue']");
    By listName = By.xpath("//input[@placeholder='Type to start']");
    By addButton = By.xpath("//span[normalize-space()='Add']");
    By termName = By.xpath("//input[@placeholder='Enter a term']");
    By addTermIcon = By.xpath("//pup-button[@class='add-button button focusable medium transparent']//button[@type='button']");
    By successToast = By.xpath("//div[@class='toast-content']");
    By savedTermName = By.cssSelector(".modal-input .ng-pristine");

    public void openList() throws InterruptedException {
        CommonItemsPage commonItemsPage = new CommonItemsPage(driver);
        commonItemsPage.hoverOnLogo();
        driver.findElement(listLink).click();
        Thread.sleep(5000);
        CommonItemsPage commonItems = new CommonItemsPage(driver);
        commonItems.switchToFrame();
    }

    public String getListUITitle() {
        CommonItemsPage commonItems = new CommonItemsPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        return commonItems.getUITitle();
    }

    public void addList(String name) {
        driver.findElement(addListButton).click();
        driver.findElement(standardInclusionExclusionOption).click();
        driver.findElement(continueButton).click();
        driver.findElement(listName).sendKeys(name);
        driver.findElement(addButton).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(termName));
        driver.findElement(termName).sendKeys(Constants.TERM);
        driver.findElement(addTermIcon).click();
    }

    public String getSavedTermName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(savedTermName));
        return driver.findElement(savedTermName).getAttribute("value");
    }

    public boolean isSuccessToastDisplayed() {
        return driver.findElement(successToast).isDisplayed();
    }

    public String getSuccessToastMessage() {
        return driver.findElement(successToast).getText();
    }
}
