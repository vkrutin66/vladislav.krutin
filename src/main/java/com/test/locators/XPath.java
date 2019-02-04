package com.test.locators;

public class XPath extends Locator implements IXpath {

    public XPath(String value) {
        super(Type.XPATH, value);
    }

    @Override
    public String getXpath(Object... args) {
        return toString(args);
    }
}
