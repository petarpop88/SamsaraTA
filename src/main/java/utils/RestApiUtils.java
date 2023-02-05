package utils;

import data.ApiCalls;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import pages.BasePageClass;

import javax.swing.text.AbstractDocument;

public class RestApiUtils extends LoggerUtils {


    private static final String BASE_URL = BasePageClass.getBaseUrl();
    private static final String ADMIN_USERNAME = PropertiesUtils.getAdminUsername();
    private static final String ADMIN_PASSWORD = PropertiesUtils.getAdminPassword();


    private static Response checkIfUserExistsApiCall(String sUsername, String sAuthUser, String sAuthPass) {

        String sApiCall = BASE_URL + ApiCalls.createCheckIfUserExistsApiCall(sUsername);
        Response response = null;

        try {
            response = RestAssured.given().auth().basic(sAuthUser, sAuthPass)
                    .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                    .when().get(sApiCall);
        } catch (Exception e) {
            Assert.fail("Exception in getUser(" + sUsername + ") Api Call: " + e.getMessage());
        }
        return response;

    }

    public static boolean checkIfUserExists(String sUsername, String sAuthUser, String sAuthPass) {
        log.debug("checkIfUserExists(" + sUsername + ")");
        Response response = checkIfUserExistsApiCall(sUsername, sAuthUser, sAuthPass);
        int status = response.getStatusCode();
        String sResponseBody = response.getBody().asString();
        Assert.assertEquals(status, 200, "Wrong Response Status Code in getUser(" + sUsername + ") Api Call! Response Body: " + sResponseBody);
        if (!(sResponseBody.toLowerCase().equals("true") || sResponseBody.toLowerCase().equals("false"))) {
            Assert.fail("Cannot convert response body '" + sResponseBody + "' to boolean value!");


        }
        return Boolean.parseBoolean(sResponseBody);
    }




}


