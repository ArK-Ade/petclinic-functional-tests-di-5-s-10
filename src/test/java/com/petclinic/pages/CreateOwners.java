package com.petclinic.pages;

import com.petclinic.GeneralTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.StringTokenizer;

public class CreateOwners extends GeneralTest {

    @FindBy(xpath = "//*[@id=\"search-owner-form\"]/a")
    protected WebElement addOwnerButton;

    @FindBy(xpath = "//*[@id=\"firstName\"]")
    protected WebElement inputFirstName;

    @FindBy(xpath = "//*[@id=\"lastName\"]")
    protected WebElement inputLastName;

    @FindBy(xpath = "//*[@id=\"address\"]")
    protected WebElement inputAddress;

    @FindBy(xpath = "//*[@id=\"city\"]")
    protected WebElement inputCity;

    @FindBy(xpath = "//*[@id=\"telephone\"]")
    protected WebElement inputTelephone;

    @FindBy(xpath = "//*[@id=\"add-owner-form\"]/div[2]/div/button")
    protected WebElement submitOwnerCreationForm;

    @FindBy(xpath = "//*[@id=\"search-owner-form\"]/div[2]/div/button")
    protected WebElement findOwnersButton;

    @FindBy(xpath = "//*[@id=\"lastName\"]")
    protected WebElement findOwnerInput;

    @FindBy(xpath = "/html/body/div/div/a[2]")
    protected WebElement addPet;

    @FindBy(xpath = "//*[@id=\"name\"]")
    protected WebElement inputPetName;

    @FindBy(xpath = "//*[@id=\"birthDate\"]")
    protected WebElement inputBirthDate;

    @FindBy(xpath = "/html/body/div/div/form/div[2]/div/button")
    protected WebElement submitPetCreationForm;

    @FindBy(xpath = "/html/body/div/div/a[1]")
    protected WebElement editOwner;

    @BeforeClass
    public void BeforeMethodCreateOwners() {
        waitAction.until(ExpectedConditions.elementToBeClickable(ownersMenu));
        ownersMenu.click();
        waitAction.until(ExpectedConditions.urlContains("/owners"));

        Assert.assertTrue(driver.getCurrentUrl().contains("/owners"));
    }

    @Test
    public void addOwner() {
        waitAction.until(ExpectedConditions.elementToBeClickable(addOwnerButton));
        addOwnerButton.click();

        waitAction.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"add-owner-form\"]")));

        inputFirstName.sendKeys("John");
        inputLastName.sendKeys("Doe");
        inputAddress.sendKeys("221B Baker Street");
        inputCity.sendKeys("London");
        inputTelephone.sendKeys("0607080910");

        WebElement addButton = driver.findElement(By.xpath("//*[@id=\"add-owner-form\"]/div[2]/div/button"));
        addButton.click();

        WebElement OwnerTableInfo = driver.findElement(By.xpath("/html/body/div/div/table[1]/tbody/tr[1]/td/b"));
        Assert.assertTrue(OwnerTableInfo.getAttribute("innerHTML").contains("John Doe"));
    }

    @Test
    public void addOwnerNumericalName() {
        waitAction.until(ExpectedConditions.elementToBeClickable(addOwnerButton));
        addOwnerButton.click();

        waitAction.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"add-owner-form\"]")));

        inputFirstName.sendKeys("1111");
        inputLastName.sendKeys("Doe");
        inputAddress.sendKeys("221B Baker Street");
        inputCity.sendKeys("London");
        inputTelephone.sendKeys("0607080910");

        WebElement addButton = driver.findElement(By.xpath("//*[@id=\"add-owner-form\"]/div[2]/div/button"));
        addButton.click();

        WebElement helpMessage = driver.findElement(By.xpath("//*[@id=\"add-owner-form\"]/div[1]/div[1]/div/span[2]"));
        Assert.assertTrue(helpMessage.getAttribute("innerHTML").contains("valeur ne dois pas contenir de chiffres"));
    }

    @Test
    public void addOwnerNonNumericalPhone() {
        waitAction.until(ExpectedConditions.elementToBeClickable(addOwnerButton));
        addOwnerButton.click();

        waitAction.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"add-owner-form\"]")));

        inputFirstName.sendKeys("John");
        inputLastName.sendKeys("Doe");
        inputAddress.sendKeys("221B Baker Street");
        inputCity.sendKeys("London");
        inputTelephone.sendKeys("aaaaaaaaaa");

        waitAction.until(ExpectedConditions.elementToBeClickable(submitOwnerCreationForm));
        submitOwnerCreationForm.click();

        WebElement helpMessage = driver.findElement(By.xpath("//*[@id=\"add-owner-form\"]/div[1]/div[5]/div/span[2]"));
        Assert.assertTrue(helpMessage.getAttribute("innerHTML").contains("valeur numérique hors limites"));
    }

    @Test
    public void addPet() {
        waitAction.until(ExpectedConditions.elementToBeClickable(findOwnersButton));
        findOwnerInput.sendKeys("Franklin");
        findOwnersButton.click();

        List<WebElement> table = driver.findElements(By.xpath("/html/body/div/div/table[2]/tbody/tr"));
        int beforeAddSize = table.size();

        waitAction.until(ExpectedConditions.elementToBeClickable(addPet));
        addPet.click();

        waitAction.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/form")));

        inputPetName.sendKeys("kiki");
        inputBirthDate.sendKeys("01/01/1999");

        waitAction.until(ExpectedConditions.elementToBeClickable(submitPetCreationForm));
        submitPetCreationForm.click();

        table = driver.findElements(By.xpath("/html/body/div/div/table[2]/tbody/tr"));
        Assert.assertTrue(table.size() > beforeAddSize);

    }

    @Test
    public void editOwner() {
        waitAction.until(ExpectedConditions.elementToBeClickable(findOwnersButton));
        findOwnerInput.sendKeys("Franklin");
        findOwnersButton.click();

        List<WebElement> table = driver.findElements(By.xpath("/html/body/div/div/table[2]/tbody/tr"));
        int beforeAddSize = table.size();

        waitAction.until(ExpectedConditions.elementToBeClickable(editOwner));
        editOwner.click();

        waitAction.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"add-owner-form\"]")));

        WebElement firstNameEdit = driver.findElement(By.xpath("//*[@id=\"firstName\"]"));
        waitAction.until(ExpectedConditions.visibilityOf(firstNameEdit));
        firstNameEdit.clear();
        firstNameEdit.sendKeys("Edited_Firstname");

        WebElement submit = driver.findElement(By.xpath("//*[@id=\"add-owner-form\"]/div[2]/div/button"));
        waitAction.until(ExpectedConditions.elementToBeClickable(submit));
        submit.click();

        String newName = driver.findElement(By.xpath("/html/body/div/div/table[1]/tbody/tr[1]/td/b")).getAttribute("innerHTML");

        Assert.assertTrue(newName.equals("Edited_Firstname Franklin"));

        table = driver.findElements(By.xpath("/html/body/div/div/table[2]/tbody/tr"));
        Assert.assertTrue(table.size() == beforeAddSize);

    }
}
