package com.petclinic;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestsUtils {

//    private static final Logger LOGGER = LoggerFactory.getLogger(TestsUtils.class);

    private static WebDriver driver = null;

    private static String environment = "";

    private static String headless = "";

    public static Properties loadApplicationPropertiesFile() {
        return loadPropertiesFile("./app.properties");
    }

    public static Properties loadPropertiesFile(String filePath) {
        Properties properties = new Properties();
        try (InputStream resourceAsStream = TestsUtils.class.getClassLoader().getResourceAsStream(filePath)) {
            properties.load(resourceAsStream);
        }
        catch (IOException e) {
            System.err.println("Unable to load properties file : " + filePath);
        }
        return properties;
    }

    public static WebDriver getDriver() {
        final Properties properties = loadApplicationPropertiesFile();

        final String chromeVersion = properties.getProperty("chrome-version");
//        LOGGER.info("Chrome version: {}", chromeVersion);
        WebDriverManager driverManager = WebDriverManager.chromedriver().driverVersion(chromeVersion);
        driverManager.setup();
        String chromeDriverPath = driverManager.getBinaryPath();

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        final ChromeOptions chromeOptions = new ChromeOptions();
        // en does not work, but de does
        chromeOptions.addArguments("--lang=fr");

        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(chromeOptions);
        return driver;
    }

    public static void quitDriver() {
        driver.quit();
    }

}
