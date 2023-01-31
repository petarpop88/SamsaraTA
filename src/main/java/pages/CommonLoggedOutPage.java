package pages;

import org.openqa.selenium.WebDriver;

public class CommonLoggedOutPage extends BasePageClass {

    protected WebDriver driver;

    public CommonLoggedOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

    }
}
