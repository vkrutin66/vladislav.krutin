package com.test.pages;

import com.test.base.BasePage;
import com.test.locators.CSS;
import com.test.locators.Locator;

public class SearchResult extends BasePage {

    private Locator getLocatorName = new CSS("#result_%s h2");

    private Locator getLocatorAuthor = new CSS("#result_%s .a-spacing-small .a-row:nth-child(2)");

    private Locator getLocatorPrice1 = new CSS("#result_%s .sx-price .sx-price-whole");

    private Locator getLocatorPrice2 = new CSS("#result_%s .sx-price .sx-price-fractional");

    private CSS getLocatorStars = new CSS("#result_%s .a-popover-trigger .a-icon-alt");

    private Locator items = new CSS(".s-result-item");

    public String getBookName(int i){
        return getElementText("Getting text", getLocatorName, i);
    }
    public boolean isBookNamePresent(int i){
        return isElementPresent( getLocatorName, i );
    }

    public String getAuthor(int i){
        return getElementText("Getting text", getLocatorAuthor, i);
    }
    public boolean isAuthorPresent(int i){
        return isElementPresent(getLocatorAuthor, i);
    }

    public float getPrice(int i){
        return Float.parseFloat(getElementText("Getting price", getLocatorPrice1, i)) + Float.parseFloat(getElementText("Getting price", getLocatorPrice2, i))/100;
    }
    public boolean isPricePresent(int i){
        return isElementPresent(getLocatorPrice1, i) && isElementPresent(getLocatorPrice2, i);
    }

    public float getStars(int i){
        return Float.parseFloat(getElementTextUsingJSByCss("Getting stras", getLocatorStars, i ).substring(0, 2));
    }
    public boolean isStarsPresent(int i){
        return isElementPresent(getLocatorStars, i);
    }

    public int getItemsNum(){
        return getElements(items).size();
    }

}
