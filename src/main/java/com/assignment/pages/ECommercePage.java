package com.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ECommercePage extends BasePage {
    public ECommercePage(WebDriver driver) {
        super(driver);
    }

    //region signin

    By signIn = By.xpath("//a[normalize-space()='Sign in']");
    By email = By.xpath("//input[@id='email']");
    By password = By.xpath("//input[@id='passwd']");
    By signInBtn = By.xpath("//span[normalize-space()='Sign in']");
    By customerLink = By.xpath("//a[@title='View my customer account']");
    By signOut = By.xpath("//a[@title='Log me out']");

    public void openSignIn() {
        driver.findElement(signIn).click();
    }

    public void insertEmail(String mail) {
        driver.findElement(email).sendKeys(mail);
    }

    public void insertPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    public void clickSignInBtn() {
        driver.findElement(signInBtn).click();
    }

    public void executeSignIn(String mail, String pass) {
        openSignIn();
        insertEmail(mail);
        insertPassword(pass);
        clickSignInBtn();
    }

    public String getCustomerName() {
        return driver.findElement(customerLink).getText();
    }

    public void signOut() {
        driver.findElement(signOut).click();
    }

    public boolean isSignInAvailable() {
        return driver.findElement(signIn).isDisplayed();
    }

    //endregion

    //region search

    By dressLink = By.xpath("//body/div[@id='page']/div[@class='header-container']/header[@id='header']/div/div[@class='container']/div[@class='row']/div[@id='block_top_menu']/ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li[2]/a[1]");
    By inStockCategory = By.xpath("//ul[@id='ul_layered_quantity_0']//a[1]");
    By compositorCategory = By.xpath("//label[@for='layered_id_feature_1']//a[1]");
    By sizeCategory = By.xpath("//label[@for='layered_id_attribute_group_3']//span[1]");
    By conditionCategory = By.xpath("//input[@id='layered_condition_new']");
    By enableFilter = By.xpath("//div[@id='enabled_filters']/ul/li");

    public void openDressMenu() {
        driver.findElement(dressLink).click();
    }

    public void chooseCategory() {
        driver.findElement(inStockCategory).click();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        //driver.findElement(compositorCategory).click();
        driver.findElement(conditionCategory).click();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        driver.findElement(sizeCategory).click();
        //driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }

    public void enableFilters() {
        openDressMenu();
        chooseCategory();
    }

    public int getSelectedCategoryCount() {
        return driver.findElements(enableFilter).size();
    }

    //endregion

    //region add to cart

    By addToCartBtn = By.xpath("//span[normalize-space()='Add to cart']");
    By addToCartBtnItems = By.xpath("//span[contains(text(),'Add to cart')]");
    By continueShoppingBtn = By.xpath("//span[@title='Continue shopping']//span[1]");
    By proceedCheckout = By.xpath("//span[normalize-space()='Proceed to checkout']");
    By successMessage = By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/span[2]");
    By itemQuantity = By.xpath("//span[@id='summary_products_quantity']");
    By firstProduct = By.xpath("//*[@id=\"product_list\"]/li[1]/div");
    By cartPopup = By.xpath("//div[@id='layer_cart']");

    public void openFirstItem() {
        /*
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(firstProduct);
        action.moveToElement(element).perform();
        */
        driver.findElement(firstProduct).click();
    }

    public List<WebElement> getAddToCartButtons() {
        return driver.findElements(addToCartBtnItems);
    }

    public void clickAddToCart() {
        ExpectedConditions.presenceOfElementLocated(addToCartBtn);
        //String btnText = driver.findElement(addToCartBtn).getText();
        //System.out.println("btnText : " + btnText);
        driver.findElement(addToCartBtn).click();
    }

    public void clickContinueShopping() {
        driver.findElement(continueShoppingBtn).click();
    }

    public void clickProceedCheckout() {
        driver.findElement(proceedCheckout).click();
    }

    public void addFirstItemToCart() {
        openFirstItem();
        driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
        clickAddToCart();
        driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
    }

    public String getSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartPopup));
        return driver.findElement(successMessage).getText();
    }

    public int getItemQuantity() {
        String quantityText = driver.findElement(itemQuantity).getText();
        quantityText = quantityText.substring(0, quantityText.indexOf("p"));
        quantityText = quantityText.replaceAll("\\s", "");
        return Integer.parseInt(quantityText);
    }

    //endregion

    //region checkout

    By checkoutBtn = By.xpath("//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]");
    By addressCheckout = By.xpath("//button[@name='processAddress']//span[contains(text(),'Proceed to checkout')]");
    By agreeToTerms = By.xpath("//input[@id='cgv']");
    By shippingCheckout = By.xpath("//button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]");
    By payByCheck = By.xpath("//a[@title='Pay by check.']");
    By confirmOrder = By.xpath("//span[normalize-space()='I confirm my order']");
    By successOrderMessage = By.xpath("//p[@class='alert alert-success']");

    public void clickCheckoutBtn() {
        driver.findElement(checkoutBtn).click();
    }

    public void clickAddressCheckoutBtn() {
        driver.findElement(addressCheckout).click();
    }

    public void setAgreeToTerms() {
        driver.findElement(agreeToTerms).click();
    }

    public void clickShippingCheckoutBtn() {
        driver.findElement(shippingCheckout).click();
    }

    public void selectPayByCheck() {
        driver.findElement(payByCheck).click();
    }

    public void setConfirmOrder() {
        driver.findElement(confirmOrder).click();
    }

    public void executeCheckout() {
        clickCheckoutBtn();
        clickAddressCheckoutBtn();
        setAgreeToTerms();
        clickShippingCheckoutBtn();
        selectPayByCheck();
        setConfirmOrder();
    }

    public String getSuccessfulOrderMessage() {
        return driver.findElement(successOrderMessage).getText();
    }

    //endregion
}
