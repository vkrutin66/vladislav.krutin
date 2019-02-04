package com.test.actions;

import com.test.base.BaseActions;
import com.test.pages.Pages;
import com.test.util.Constants;
import com.test.util.Random;
import org.testng.Reporter;

import static com.test.base.BaseTest.driver;

public class LoginActions extends BaseActions {

    public void login(String userName, String password) {
        Pages.loginPage().waitForPageLoaded();

        Pages.loginPage().setLogin(userName);
        Pages.loginPage().setPassword(password);

        Pages.loginPage().clickLoginButton();
    }

}
