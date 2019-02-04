package com.test.pages;

import com.test.base.BasePage;
import com.test.locators.CSS;
import com.test.locators.Locator;

public class SearchResult extends BasePage {

    public Locator getLocatorName(int i){
        return new CSS("#result_" + i + " h2");
    }
    public Locator getLocatorAuthor(int i){
        return new CSS("#result_" + i + " .a-spacing-small .a-row:nth-child(2)");
    }
    public Locator getLocatorPrice1(int i){
        return new CSS("#result_" + i + " .sx-price .sx-price-whole");
    }
    public Locator getLocatorPrice2(int i){
        return new CSS("#result_" + i + " .sx-price .sx-price-fractional");
    }
    public CSS getLocatorStars(int i){
        return new CSS("#result_" + i + " .a-popover-trigger .a-icon-alt");
    }
    Locator items = new CSS(".s-result-item");

    public String getName(int i){
        if( isElementPresent(getLocatorName(i)) ){
            System.out.println(getElementText("Getting text", getLocatorName(i)));
            return getElementText("Getting text", getLocatorName(i));
        }
        return "Unknown";
    }
    public String getAuthor(int i){
        if( isElementPresent(getLocatorAuthor(i)) ){
            return getElementText("Getting text", getLocatorAuthor(i));
        }
        return "Unknown";
    }
    public float getPrice(int i){
        String s1 = "0", s2 = "0";
        if( isElementPresent(getLocatorPrice1(i)) ){
            s1 = getElementText("Getting price", getLocatorPrice1(i));
        }
        if( isElementPresent(getLocatorPrice2(i)) ){
            s2 = getElementText("Getting price", getLocatorPrice2(i));
        }
        return Float.parseFloat(s1) + Float.parseFloat(s2)/100;
    }
    public float getStars(int i){
        String stars = "0.0";
        if(isElementPresent(getLocatorStars(i))){
            stars = getElementTextUsingJSByCss("Getting stras", getLocatorStars(i) );
        }
        return Float.parseFloat(stars.substring(0, 2));
    }
    public boolean getBestseller (int i) {
        if(getStars(i) == 5){
            return true;
        } else {
            return false;
        }
    }

    public int getItemsNum(){
        return getElements(items).size();
    }




}
