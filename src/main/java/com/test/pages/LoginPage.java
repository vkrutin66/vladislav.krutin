package com.test.pages;


import com.test.base.BasePage;
import com.test.locators.CSS;
import com.test.locators.ID;
import com.test.locators.Locator;
import com.test.locators.XPath;

public class LoginPage extends BasePage {

    Locator loginPage = new CSS("asdasd");

    Locator loginField = new XPath("//input[@class='login_field']");
    Locator passwordField = new XPath("//input[@class='password_field']");

    Locator logIngButton = new XPath("//input[@class='login_button']");

    Locator errorMessage = new XPath("//input[@class='error_message']");


    public void waitForPageLoaded(){
        waitForElementVisibility(loginPage);
    }

    public void setLogin(String login){
        type("Set login: " + login, login, loginField);
    }

    public void setPassword(String password){
        type("Set password: " + password, password, passwordField);
    }

    public void clickLoginButton(){
        click("Click login button   ", logIngButton);
    }

    public boolean isErrorMessageVisible(){
        return isElementPresent( errorMessage );
    }

}
