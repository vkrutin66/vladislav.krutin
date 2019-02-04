package com.test.locators;

public class ID extends Locator implements IXpath {
    public ID(String value) {
        super(Type.ID, value);
    }

    @Override
    public String getXpath(Object... args) {
        return "//*[@id='" + toString() + "']";
    }
}
