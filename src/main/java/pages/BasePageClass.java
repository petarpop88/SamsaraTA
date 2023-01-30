package pages;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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

    private WebDriver driver;
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
