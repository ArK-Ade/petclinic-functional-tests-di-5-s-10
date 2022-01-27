package com.petclinic.pages ;

import com.petclinic.GeneralTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


public class SearchOwners extends GeneralTest {

    @FindBy(xpath = "//*[@id=\"search-owner-form\"]/div[2]/div/button")
    protected WebElement findOwnersButton;

    @FindBy(xpath = "//*[@id=\"lastName\"]")
    protected WebElement findOwnerInput;

    @FindBy(xpath = "//*[@id=\"owners\"]")
    protected WebElement ownersTable;

    @BeforeClass
    public void BeforeClassOfOwners() {
        initElements();
    }

    @BeforeMethod
    public void BeforeMethodOfSearchOwners() {
        waitAction.until(ExpectedConditions.elementToBeClickable(ownersMenu));
        ownersMenu.click();
        waitAction.until(ExpectedConditions.urlContains("/owners"));

        Assert.assertTrue(driver.getCurrentUrl().contains("/owners"));
    }

    @Test
    public void searchOwnerEmptyString() {
        waitAction.until(ExpectedConditions.visibilityOf(findOwnerInput));
        findOwnerInput.sendKeys("");

        waitAction.until(ExpectedConditions.elementToBeClickable(findOwnersButton));
        findOwnersButton.click();

        waitAction.until(ExpectedConditions.visibilityOf(ownersTable));

        List<WebElement> table = driver.findElements(By.xpath("//*[@id=\"owners\"]/tbody/tr"));
        Assert.assertTrue(table.size() > 1);
    }

    @Test
    public void searchExistingOwner() {
        waitAction.until(ExpectedConditions.visibilityOf(findOwnerInput));
        findOwnerInput.sendKeys("Franklin");

        waitAction.until(ExpectedConditions.elementToBeClickable(findOwnersButton));
        findOwnersButton.click();

        WebElement ownerTableInfo = driver.findElement(By.xpath("/html/body/div/div/table[1]/tbody/tr[1]/td/b"));
        waitAction.until(ExpectedConditions.visibilityOf(ownerTableInfo));

        Assert.assertTrue(ownerTableInfo.getAttribute("innerHTML").contains("George Franklin"));
    }

    @Test
    public void searchNonExistentOwner() {
        waitAction.until(ExpectedConditions.visibilityOf(findOwnerInput));
        findOwnerInput.sendKeys("FalseUserName");

        waitAction.until(ExpectedConditions.elementToBeClickable(findOwnersButton));
        findOwnersButton.click();

        WebElement helpMessage = driver.findElement(By.xpath("//*[@id=\"lastNameGroup\"]/div/span/div/p"));
        waitAction.until(ExpectedConditions.visibilityOf(helpMessage));

        Assert.assertTrue(helpMessage.getAttribute("innerHTML").contains("has not been found"));
    }

    @Test
    public void searchExistingOwnersWithTemplate() {
        waitAction.until(ExpectedConditions.visibilityOf(findOwnerInput));
        findOwnerInput.sendKeys("Dav");

        waitAction.until(ExpectedConditions.elementToBeClickable(findOwnersButton));
        findOwnersButton.click();

        List<WebElement> table = driver.findElements(By.xpath("//*[@id=\"owners\"]/tbody/tr[1]"));
        waitAction.until(ExpectedConditions.visibilityOf(ownersTable));

        Assert.assertTrue(table.size() == 2);
    }

}
