package utils;


import org.testng.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

public class PropertiesUtils extends LoggerUtils {


    private static final String sPropertiesPath = "common.properties";
    private static final Properties properties = loadPropertiesFile();

    public static Properties loadPropertiesFile(String sFilePath) {
        InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(sFilePath);
        Properties properties = new Properties();

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            Assert.fail("Cannot load " + sFilePath + " file! Message: " + e.getMessage());
        }

        return properties;

    }

    private static Properties loadPropertiesFile() {

        return loadPropertiesFile(sPropertiesPath);

    }

    private static String getProperty(String sProperty) {
        log.trace("getProperty(" + sProperty + ")");

        String result = properties.getProperty(sProperty);
        Assert.assertNotNull(result, "Cannot find property '" + sProperty + "' in " + sPropertiesPath + " file!");
        return result;

    }

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static String getEnvironment() {
        return getProperty("environment");
    }

    public static boolean getRemote() {
        String sRemote = getProperty("remote").toLowerCase();
        if (!(sRemote.equals("true") || sRemote.equals("false"))) {
            Assert.fail("Cannot convert 'Headless' property value '" + sRemote + "' to boolean value!");
        }
        return Boolean.parseBoolean(sRemote);
    }

    public static boolean getHeadless() {
        String sRemote = getProperty("remote").toLowerCase();
        if (!(sRemote.equals("true") || sRemote.equals("false"))) {
            Assert.fail("Cannot convert 'Headless' property value '" + sRemote + "' to boolean value!");
        }
        return Boolean.parseBoolean(sRemote);

    }

    public static String getHubUrl() {
        return getProperty("hubUrl");

    }

    public static String getDriversFolder() {
        return getProperty("driversFolder");
    }

    public static String getLocalBaseUrl() {
        return getProperty("localBaseUrl");
    }

    public static String getTestBaseUrl() {
        return getProperty("testBaseUrl");
    }

    public static String getProdBaseUrl() {
        return getProperty("prodBaseUrl");
    }

    public static String getBaseUrl() {
        String sEnvoironment = getEnvironment().toLowerCase();
        String sBaseUrl = null;
        switch (sEnvoironment) {
            case "local": {
                sBaseUrl = getLocalBaseUrl();
                break;
            }
            case "test": {
                sBaseUrl = getTestBaseUrl();
                break;
            }
            case "prod": {
                sBaseUrl = getProdBaseUrl();
                break;
            }
            default: {
                Assert.fail("Cannot get BaseUrl! Environment '" + sEnvoironment + "' is not recognized!");
            }

        }
        return sBaseUrl;


    }

}






