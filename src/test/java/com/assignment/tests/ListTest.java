package com.assignment.tests;

import com.assignment.pages.DataViewPage;
import com.assignment.pages.ListPage;
import com.assignment.pages.SignInPage;
import com.assignment.utils.Constants;
import com.assignment.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ListTest extends BaseTest {
    public ListTest() {
        super(Constants.PRODUCTS_UP_URL);
    }

    @Test(priority = 0)
    public void Test_001_signIn() throws IOException {
        page.getInstance(SignInPage.class).executeSignIn(Constants.USER_EMAIL, Constants.PASSWORD);

        String userName = page.getInstance(SignInPage.class).getUserName();
        System.out.println("user name : " + userName);
        Assert.assertEquals(userName, Constants.USER);
        //Utils.takeScreenShot(driver);
    }

    @Test(priority = 0, dependsOnMethods = {"Test_001_signIn"})
    public void Test_002_openDashboard() {
        page.getInstance(SignInPage.class).openDashboard();
        String title = page.getInstance(SignInPage.class).getDashboardTitle();
        System.out.println("dashboard title : " + title);
    }

    @Test(priority = 0, dependsOnMethods = {"Test_002_openDashboard"})
    public void Test_003_openListUI() throws InterruptedException {
        page.getInstance(ListPage.class).openList();
        String title = page.getInstance(ListPage.class).getListUITitle();
        System.out.println("list UI title : " + title);
    }

    @Test(priority = 0, dependsOnMethods = {"Test_003_openListUI"})
    public void Test_004_addNewList() {
        Constants.LIST_NAME = Utils.generateListName();
        System.out.println("creating list with name : " + Constants.LIST_NAME);
        page.getInstance(ListPage.class).addList(Constants.LIST_NAME);

        Assert.assertTrue(page.getInstance(ListPage.class).isSuccessToastDisplayed());
        if (page.getInstance(ListPage.class).isSuccessToastDisplayed()) {
            String successToastMessage = page.getInstance(ListPage.class).getSuccessToastMessage();
            System.out.println("success Toast Message : " + successToastMessage);

            //String savedTermName = page.getInstance(ListPage.class).getSavedTermName();
            //System.out.println("saved term name : " + savedTermName);
        }
    }

    @Test(priority = 1)
    public void Test_005_openDataViewUI() throws InterruptedException {
        page.getInstance(DataViewPage.class).openDataView();
        page.getInstance(DataViewPage.class).blacklistOperation(Constants.LIST_NAME);
    }
}
