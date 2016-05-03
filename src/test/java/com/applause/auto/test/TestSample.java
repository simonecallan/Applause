package com.applause.auto.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.applause.auto.framework.test.BaseWebDriverTest;
import com.applause.auto.pageframework.testdata.TestConstants;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;



public class TestSample extends BaseWebDriverTest {

    private static final Logger logger = Logger.getLogger(TestSample.class);

    AppiumDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void testSetup() throws MalformedURLException {

        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "/wholefoods");
        File app = new File(appDir, "com.wholefoods.wholefoodsmarket.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(CapabilityType.BROWSER_NAME, "LOCAL_ANDROID_TABLET");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability(CapabilityType.VERSION, "5.1");
        capabilities.setCapability("deviceName", "training");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.wholefoods.wholefoodsmarket");
        capabilities.setCapability("appActivity", "com.wholefoods.wholefoodsmarket.application.activities.WFMSplashActivity");
        this.driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test(groups = { TestConstants.TestNGGroups.REG }, description = "")
    public void testSearchForCoffee() {

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement searchField = driver.findElement(By.id("etHomeSearch"));
        searchField.clear();
        searchField.sendKeys("coffee");

        WebElement searchButton = driver.findElement(By.id("imgSearch"));
        searchButton.click();

        Boolean pageTitleIsPresent = driver.findElement(By.id("header_title")).getText().equals("SEARCH");
        Boolean searchResultIsPresent = driver.findElements(By.id("recipesSearchResultsGrid")).size() > 0;

}
    }
