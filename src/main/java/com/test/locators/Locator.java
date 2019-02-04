package com.test.locators;

import org.openqa.selenium.By;

public abstract class Locator {

    private final Type type;
    private final String value;
    public Locator(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public By get(Object... args) {
        switch (type) {
            case XPATH:
                return By.xpath(String.format(value, args));
            case CSS:
                return By.cssSelector(String.format(value, args));
            case ID:
                return By.id(String.format(value, args));
            case NAME:
                return By.name(String.format(value, args));
            case CLASSNAME:
                return By.className(String.format(value, args));
        }
        throw new IllegalLocatorException(String.format("Locator type \"%s\" is unknown!", type));
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return value;
    }

    public String toString(Object... args) {
        return String.format(value, args);
    }

    public enum Type {
        XPATH, CSS, ID, NAME, CLASSNAME
    }
}
