package config;

import io.restassured.RestAssured;

import java.io.InputStream;
import java.util.Properties;

/**
 * Centralized configuration for REST Assured.
 * Reads settings from config.properties and exposes a reusable
 * RequestSpecification.
 */
public class BaseConfig {

    private static final Properties properties = new Properties();

    public static String getBaseUrl() {
        return System.getProperty("baseUrl",
                properties.getProperty("base.url", "https://jsonplaceholder.typicode.com"));
    }

    /**
     * Initialises RestAssured globally. Called from BaseTest.setup().
     */
    public static void init() {
        RestAssured.baseURI = getBaseUrl();
    }
}
