package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.WebDriver;

public class LoginPage extends CommonLoggedOutPage {


        private final String LOGIN_PAGE_URL = getPageURl(PageUrlPaths.LOGIN_PAGE);



        public LoginPage (WebDriver driver) {
            super(driver);
            log.trace("new LoginPAge()");
        }


        public LoginPage open () {
            return open(true);
        }

        public LoginPage open (boolean bVerify) {
            log.debug("Open LoginPage (" + LOGIN_PAGE_URL + ")");
            openUrl (LOGIN_PAGE_URL);
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



/*
        public void clickLoginButton () {
            WebDriverWait wait = new WebDriverWait(driver, 5);
        }
*/
}
