package tests.login;

import data.Time;
import io.restassured.RestAssured;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import tests.BaseTestClass;
import utils.DateTimeUtils;



public class LoginTestExample extends BaseTestClass {




    @Test
    public void testExample1 () {
       WebDriver driver = setUpDriver();

       LoginPage loginPage = new LoginPage(driver).open();
       DateTimeUtils.wait(Time.TIME_SHORT);
       loginPage.typeUserName("Petar");
       DateTimeUtils.wait(Time.TIME_LONG);
       String username = loginPage.getUserName();
       log.info("Username: " + username);

       quitDriver(driver);





    }



}
