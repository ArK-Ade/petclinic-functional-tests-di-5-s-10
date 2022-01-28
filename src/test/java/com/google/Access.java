package com.google;

import com.TestsUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Access extends TestsUtils {

    @Test
    public void testGoogleAccess(){
        WebDriver driver = TestsUtils.getDriver();
        WebDriverWait waitAction = new WebDriverWait(driver, 40);
        PageFactory.initElements(driver, this);
        driver.get("https://google.com");
    }
}
