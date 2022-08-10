package com.assignment.tests;

import com.assignment.pages.ECommercePage;
import com.assignment.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ECommerceTest extends BaseTest {
    public ECommerceTest() {
        super(Constants.E_COMMERCE_URL);
    }

    @Test(priority = 0)
    public void Test_001_signIn() {
        page.getInstance(ECommercePage.class).executeSignIn(Constants.USER_EMAIL, Constants.PASSWORD);
        String customerName = page.getInstance(ECommercePage.class).getCustomerName();
        System.out.println("customerName : " + customerName);

        Assert.assertEquals(customerName, Constants.USER);
    }

    @Test(priority = 1)
    public void Test_002_searchProducts() {
        page.getInstance(ECommercePage.class).enableFilters();
        int filterCount = page.getInstance(ECommercePage.class).getSelectedCategoryCount();

        System.out.println("filterCount : " + filterCount);
        Assert.assertEquals(filterCount, 3);
    }

    @Test(priority = 1, dependsOnMethods = {"Test_002_searchProducts"})
    public void Test_003_addItemToCart() {
        page.getInstance((ECommercePage.class)).addFirstItemToCart();
        String successMessage = page.getInstance((ECommercePage.class)).getSuccessMessage();
        System.out.println("successMessage : " + successMessage);
        Assert.assertEquals(successMessage, Constants.ADD_TO_CART_SUCCESS_MESSAGE);

        page.getInstance((ECommercePage.class)).clickProceedCheckout();
        int itemCount = page.getInstance((ECommercePage.class)).getItemQuantity();
        System.out.println("item count in cart : " + itemCount);
        Assert.assertTrue(itemCount > 0);
    }

    @Test(priority = 1, dependsOnMethods = {"Test_003_addItemToCart"})
    public void Test_004_checkoutProcess() {
        page.getInstance((ECommercePage.class)).executeCheckout();
        String successfulOrderMessage = page.getInstance((ECommercePage.class)).getSuccessfulOrderMessage();

        System.out.println("successfulOrderMessage : " + successfulOrderMessage);
        Assert.assertEquals(successfulOrderMessage, Constants.ORDER_SUCCESSFUL_MESSAGE);
    }

    @Test(priority = 1, dependsOnMethods = {"Test_001_signIn"})
    public void Test_005_signOut() {
        page.getInstance((ECommercePage.class)).signOut();
        Assert.assertTrue(page.getInstance((ECommercePage.class)).isSignInAvailable());
    }
}
