package com.assignment.tests;

import com.assignment.pages.DataViewPage;
import com.assignment.pages.ListPage;
import com.assignment.pages.SignInPage;
import com.assignment.utils.Constants;
import com.assignment.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class ListTest extends BaseTest {
    public ListTest() {
        super(Constants.PRODUCTS_UP_URL);
    }

    SoftAssert softAssert = new SoftAssert();

    @Test(priority = 0)
    public void Test_001_signIn() throws IOException {
        page.getInstance(SignInPage.class).executeSignIn(Constants.USER_EMAIL, Constants.PASSWORD);

        String userName = page.getInstance(SignInPage.class).getUserName();
        System.out.println("user name : " + userName);
        softAssert.assertEquals(userName, Constants.USER);
        Utils.takeScreenShot(driver, "Test_001_signIn");
    }

    @Test(priority = 0, dependsOnMethods = {"Test_001_signIn"})
    public void Test_002_openDashboard() throws IOException {
        page.getInstance(SignInPage.class).openDashboard();
        String title = page.getInstance(SignInPage.class).getDashboardTitle();
        System.out.println("dashboard title : " + title);
        Utils.takeScreenShot(driver, "Test_002_openDashboard");
        softAssert.assertEquals(title, Constants.UITitles.DASHBOARD_TITLE);
    }

    @Test(priority = 0, dependsOnMethods = {"Test_002_openDashboard"})
    public void Test_003_openListUI() throws InterruptedException, IOException {
        page.getInstance(ListPage.class).openList();
        String title = page.getInstance(ListPage.class).getListUITitle();
        System.out.println("list UI title : " + title);
        Utils.takeScreenShot(driver, "Test_003_openListUI");
        softAssert.assertEquals(title, Constants.UITitles.LIST_UI_TITLE);
    }

    @Test(priority = 0, dependsOnMethods = {"Test_003_openListUI"})
    public void Test_004_addNewList() throws IOException {
        Constants.LIST_NAME = Utils.generateListName();
        System.out.println("creating list with name : " + Constants.LIST_NAME);
        page.getInstance(ListPage.class).addList(Constants.LIST_NAME);
        Utils.takeScreenShot(driver, "Test_004_addNewList");
        Assert.assertTrue(page.getInstance(ListPage.class).isSuccessToastDisplayed());
        if (page.getInstance(ListPage.class).isSuccessToastDisplayed()) {
            String successToastMessage = page.getInstance(ListPage.class).getSuccessToastMessage();
            System.out.println("success Toast Message : " + successToastMessage);

            softAssert.assertEquals(successToastMessage, Constants.LIST_ADD_SUCCESS_MESSAGE);

            String savedTermName = page.getInstance(ListPage.class).getSavedTermName();
            System.out.println("saved term name : " + savedTermName);
            softAssert.assertEquals(savedTermName, Constants.TERM);
        }
    }

    @Test(priority = 1)
    public void Test_005_openDataViewUI() throws IOException {
        String dataViewTitle = page.getInstance(DataViewPage.class).openDataView();
        Utils.takeScreenShot(driver, "Test_005_openDataViewUI");
        System.out.println("Data View UI Title : " + dataViewTitle);
        softAssert.assertEquals(dataViewTitle, Constants.UITitles.DATA_VIEW_UI_TITLE);
    }

    @Test(priority = 1, dependsOnMethods = {"Test_005_openDataViewUI"})
    public void Test_006_blackListOperation() throws InterruptedException, IOException {
        int blackListItemCount = page.getInstance(DataViewPage.class).blacklistOperation(Constants.LIST_NAME);
        Assert.assertEquals(blackListItemCount, 1);
        Utils.takeScreenShot(driver, "Test_006_blackListOperation");
        if (blackListItemCount == 1) {
            String backGroundColor = page.getInstance(DataViewPage.class).getBlackListBackgroundColor();
            System.out.println("Blacklist item background color : " + backGroundColor);
            softAssert.assertEquals(backGroundColor, Constants.BLACKLIST_BACKGROUND_COLOR);
        }
    }
}
