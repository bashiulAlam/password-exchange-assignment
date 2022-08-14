package com.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DataViewPage extends BasePage {
    public DataViewPage(WebDriver driver) {
        super(driver);
    }

    By viewFrame = By.className("viewFrame");
    By dataViewIcon = By.xpath("//pds-icon[@icon='ic-table']//span[@class='pds-icon ng-binding']//*[name()='svg']");
    By dataViewLink = By.xpath("//li[9]//a[1]");
    By dropDownButton = By.xpath("//div[@class='btn-group stage-menu-container']");
    By intermediateOption = By.xpath("//span[normalize-space()='Intermediate']");
    By editButton = By.xpath("//th[@class='product-column-day']//button[@class='btn btn-xs btn-default btn-edit ng-binding ng-scope'][normalize-space()='Edit']");

    By editFrame = By.xpath("//iframe[@name='editFrame']");
    By largeViewButton = By.xpath("//button[normalize-space()='Large View']");
    By dropBox = By.xpath("//ul[@id='dataflow-ul-output']//li[@class='drop-area ui-sortable-handle'][normalize-space()='Drop Box Here']");
    By saveBlackListButton = By.xpath("//button[@class='btn btn-success btn-sm js-save-process']");
    By blackListOption = By.xpath("//li[normalize-space()='Blacklist']");
    By searchOptionBox = By.xpath("//div[@id='box_select_chosen']//input[@type='text']");
    By optionSelectDropDown = By.xpath("//a[@class='chosen-single']//div//b");
    String xPathForList = "//li[normalize-space()='{listName}']";
    By listItem = By.xpath("//a[@class='chosen-single chosen-default']");

    By wrenchIcon = By.xpath("//button[@class='btn btn-xs btn-info pull-right js-btn-options btn-options']");
    By deleteBox = By.xpath("//button[@title='Delete Box']");

    By dayColumnValues = By.xpath("//tbody//td[4]");
    By tableRows = By.xpath("//tbody//tr");

    By blackListItem = By.xpath("//tr[@class='ng-scope blacklist']");

    public String openDataView() {
        driver.switchTo().defaultContent();
        CommonItemsPage commonItemsPage = new CommonItemsPage(driver);
        commonItemsPage.hoverOnLogo();
        driver.findElement(dataViewLink).click();
        String title = commonItemsPage.getUITitle();
        driver.switchTo().frame(driver.findElement(viewFrame));

        return title;
    }

    public int blacklistOperation(String listName) throws InterruptedException {
        driver.findElement(dropDownButton).click();
        driver.findElement(intermediateOption).click();
        driver.findElement(editButton).click();
        driver.switchTo().frame(driver.findElement(editFrame));

        /*if (driver.findElement(wrenchIcon).isDisplayed()) {
            driver.findElement(wrenchIcon).click();
            driver.findElement(deleteBox).click();
        }*/

        driver.findElement(optionSelectDropDown).click();
        driver.findElement(searchOptionBox).sendKeys("Blacklist");
        driver.findElement(blackListOption).click();
        driver.findElement(listItem).click();
        driver.findElement(By.xpath(xPathForList.replace("{listName}", listName))).click();
        driver.findElement(saveBlackListButton).click();

        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(viewFrame));

        Thread.sleep(10000);

        return driver.findElements(blackListItem).size();
    }

    public String getBlackListBackgroundColor() {
        return driver.findElement(blackListItem).getCssValue("background-color");
    }
}
