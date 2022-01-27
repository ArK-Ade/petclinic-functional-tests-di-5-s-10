package com.petclinic ;

import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

public class GeneralTest extends TestsUtils {

    protected WebDriver driver;

    protected WebDriverWait waitAction;

    protected JavascriptExecutor jse;

    @FindBy(xpath = "//*[@id=\"main-navbar\"]/ul/li[2]/a")
    protected WebElement ownersMenu;

    public void loadAttributesBeforeTest() {
        driver = TestsUtils.getDriver();
    }

    public void initElements() {
        waitAction = new WebDriverWait(driver, 40);
        PageFactory.initElements(driver, this);
        jse = (JavascriptExecutor) driver;
    }

    @BeforeSuite
    public void beforeSuiteSetUpEnvironments() {
        loadAttributesBeforeTest();
        initElements();
        driver.get("http://localhost:8080");
    }

}
