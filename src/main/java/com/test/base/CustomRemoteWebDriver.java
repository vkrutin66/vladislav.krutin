package com.test.base;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

/**
 * Remote Web Driver does not implement TakesScreenshot interface by default
 * So we do it by ourself
 */
public class CustomRemoteWebDriver extends RemoteWebDriver implements TakesScreenshot {

    public CustomRemoteWebDriver(URL remoteAddress, Capabilities desiredCapabilities) {
        super(remoteAddress, desiredCapabilities);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        String base64Str = execute(DriverCommand.SCREENSHOT).getValue().toString();
        return target.convertFromBase64Png(base64Str);
    }
}
