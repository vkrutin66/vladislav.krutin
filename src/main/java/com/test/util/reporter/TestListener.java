package com.test.util.reporter;

import com.test.base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


public class TestListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        String imageName = result.getName() + "-" + System.currentTimeMillis() + ".png";
        org.testng.Reporter.setCurrentTestResult(result);
        Reporter.log("Screenshot saved:<br></br><a href = '" + imageName + "'><img src = '" + imageName + "' width='600'/></a>");

        try {
            File scrFile = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(System.getProperty("report.dir") + File.separatorChar + "html" + File.separatorChar + imageName));
        } catch (UnreachableBrowserException ubs) {
            Reporter.log("Unable to save screenshot. Browser unreachable!");
        } catch (Throwable e) {
            Reporter.log("Error saving screenshot!");
        }

        savePageSource(result);
    }

    private void savePageSource(ITestResult result) {
        String source;
        try {
            source = BaseTest.driver.getPageSource();
        } catch (Throwable e) {
            Reporter.log("Can not save page source!");
            System.out.println("Can not save page source!");
            e.printStackTrace();
            return;
        }
        String dirName = System.getProperty("report.dir") + File.separator + "page_sources";
        File dir = new File(dirName);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String testClass = result.getTestClass().getName();
        int index = testClass.lastIndexOf('.' + 1);
        index = index == -1 ? 0 : index;
        testClass = testClass.substring(index);
        File toSave = new File(dirName + File.separator + testClass + "." + result.getName() + ".html");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toSave, false));
            bufferedWriter.write(String.valueOf(source));
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
