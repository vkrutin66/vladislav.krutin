package com.test.base;

import com.test.util.Constants;
import com.test.util.reporter.Reporter;
import org.testng.Assert;

import java.util.Set;

public class BaseActions {

    public static void waitForTabsCount(int minTabsCount) {
        Reporter.log("Waiting for >= " + minTabsCount + " tabs opened (up to " + Constants.ELEMENT_EXTRALONG_TIMEOUT_SECONDS + " seconds)..");
        long timeBefore = System.currentTimeMillis();
        do {
            wait(Constants.ELEMENT_MICRO_TIMEOUT_SECONDS);
        }
        while (BaseTest.driver.getWindowHandles().size() < minTabsCount && System.currentTimeMillis() - timeBefore <= Constants.ELEMENT_EXTRALONG_TIMEOUT_SECONDS * 1000);

        Assert.assertTrue(BaseTest.driver.getWindowHandles().size() >= minTabsCount, "Number of opened tabs is not more or equals to " + minTabsCount);
    }

    public static void closeOtherTabs() {
        Reporter.log("Closing other browser tabs");
        String currentHandle = BaseTest.driver.getWindowHandle();
        Set<String> handles = BaseTest.driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(currentHandle)) {
                BaseTest.driver.switchTo().window(handle);
                BaseTest.driver.close();
            }
        }
        BaseTest.driver.switchTo().window(currentHandle);
    }


    public static void wait(int waitInSeconds) {
        try {
            Thread.sleep(waitInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
