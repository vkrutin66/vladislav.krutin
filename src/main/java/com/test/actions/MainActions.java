package com.test.actions;

import com.test.base.BaseActions;
import com.test.util.Constants;
import org.testng.Reporter;

import static com.test.base.BaseTest.driver;

public class MainActions extends BaseActions {

    public void openMainPage(){
        Reporter.log("Opening main page: " + Constants.BASE_URL);
        driver.get(Constants.BASE_URL);
    }

    public void clearSession() {
        driver.manage().deleteAllCookies();
    }

    public void openPage(String url){
        Reporter.log("Opening page: " + url);
        driver.get(url);
    }

    public void search(){

    }

}
