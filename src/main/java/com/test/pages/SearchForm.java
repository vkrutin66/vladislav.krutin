package com.test.pages;

import com.test.base.BasePage;
import com.test.locators.ID;
import com.test.locators.Locator;
import com.test.locators.XPath;

public class SearchForm extends BasePage {

    Locator searchInput = new ID("twotabsearchtextbox");

    Locator searchButton = new XPath("//input[@class='nav-input']");

    public void typeSearchField(String value){
        type( "Type:" + value , value, searchInput);
    }

    public void submitSearchForm(){
        click("Search button click", searchButton);
    }

}
