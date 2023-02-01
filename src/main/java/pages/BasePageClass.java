package pages;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.LoggerUtils;
import utils.PropertiesUtils;
import utils.WebDriverUtils;

import java.time.Duration;
import java.util.function.Function;

public abstract class BasePageClass extends LoggerUtils {

    protected WebDriver driver;
    private static final String BASE_URL = PropertiesUtils.getBaseUrl();

    protected BasePageClass(WebDriver driver) {
        Assert.assertFalse(WebDriverUtils.hasDriverQuit(driver), "Driver instance has quit!");
        this.driver = driver;
    }


    public static String getBaseUrl () {
        return BASE_URL;

    }

    protected void openUrl (String url) {
        log.trace("openUrl(" + url +")");
        driver.get(url);

    }

    protected String getPageURl (String sPath) {
        log.trace("getPageUrl(" + sPath + ")");
        return getBaseUrl() + sPath;

    }

    protected WebElement getWebElement (By locator) {
        log.trace("getWebElement(" + locator + ")");
        return driver.findElement(locator);
    }

    protected WebElement getWebElement (By locator, int timeout) {
        log.trace("getWebElement(" + locator + ", " + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

    }

    protected void typeTextToWebElement (WebElement element, String text) {
        log.trace("typeTextToWebElement(" + element + ", " + text + ")");
        element.sendKeys(text);
    }

    protected void clearAndTypeTextToWebElement (WebElement element, String text) {
        log.trace("clearAndTypeTextToWebElement(" + element + ", " + text + ")");
        element.clear();
        element.sendKeys(text);
    }

    protected String getAttributeFromWebElement (WebElement element, String attribute) {
        log.trace("getAttributeFromWebElement(" + element + ", " + attribute + ")");
        return element.getAttribute(attribute);
    }

    protected String getValueFromWebElement (WebElement element) {
        log.trace("getAttributeFromWebElement(" + element + ")");
        return getAttributeFromWebElement(element, "value");
    }

    protected String getValueFromWebElementJS (WebElement element) {

        log.trace("getValueFromWebElementJS(" + element + ")");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].value", element);
    }

    protected boolean isWebElementDisplayed (By locator) {
        log.trace("isWebElementDisplayed(" + locator + ")");
        try {
            WebElement element = getWebElement(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    protected boolean isWebElementDisplayed (WebElement element) {
        log.trace("isWebElementDisplayed(" + element + ")");
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    private WebDriverWait getWebDriverWait (int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout));

    }

    protected boolean waitForUrlChange (String url, int timeout) {
        log.trace("waitForUrlChange(" +  url + ", " + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.urlContains(url));


    }

    protected boolean waitUntilPageIsReady (int timeout) {
        log.trace("waitForUrlChange("  + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete")
        );
    }




}
