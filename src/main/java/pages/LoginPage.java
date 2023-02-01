package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage extends CommonLoggedOutPage {


    private final String LOGIN_PAGE_URL = getPageURl(PageUrlPaths.LOGIN_PAGE);

    private final By usernameTextFieldLocator = By.id("username");
    private final By passwordTextFieldLocator = By.id("password");


    public LoginPage(WebDriver driver) {
        super(driver);
        log.trace("new LoginPAge()");
    }


    public LoginPage open() {
        return open(true);
    }

    public LoginPage open(boolean bVerify) {
        log.debug("Open LoginPage (" + LOGIN_PAGE_URL + ")");
        openUrl(LOGIN_PAGE_URL);
        if (bVerify) {
            verifyLoginPage();
        }
        return this;
    }

    public LoginPage verifyLoginPage() {
        log.debug("verifyLoginPage()");
        waitForUrlChange(LOGIN_PAGE_URL, Time.TIME_SHORTER);
        waitUntilPageIsReady(Time.TIME_SHORT);
        return this;
    }

    public void clickLoginButton() {

    }

    public boolean isUsernameTextFieldDisplayed() {
        log.debug("isUsernameTextFieldDisplayed()");
        return isWebElementDisplayed(usernameTextFieldLocator);

    }


    public void typeUserName(String sUsername) {
        log.debug("typeUsername(" + sUsername + ")");
        Assert.assertTrue(isUsernameTextFieldDisplayed(), "Username Text Field is NOT present on Login Page!");
        WebElement usernameTextField = getWebElement(usernameTextFieldLocator, Time.TIME_SHORT);
        clearAndTypeTextToWebElement(usernameTextField, sUsername);
    }

    public String getUserName() {
        log.debug("getUsername()");
        Assert.assertTrue(isUsernameTextFieldDisplayed(), "Username Text Field is NOT present on Login Page!");
        WebElement usernameTextField = getWebElement(usernameTextFieldLocator);
        return getValueFromWebElementJS(usernameTextField);
    }


}
