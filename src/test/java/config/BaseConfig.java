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

//    static {
//        try (InputStream input = BaseConfig.class.getClassLoader()
//                .getResourceAsStream("config.properties")) {
//            if (input != null) {
//                properties.load(input);
//            }
//        } catch (Exception e) {
//            System.err.println("Could not load config.properties: " + e.getMessage());
//        }
//    }

    /**
     * Returns the base URL, giving priority to:
     * 1. System property (mvn test -DbaseUrl=...)
     * 2. config.properties value
     * 3. Hard-coded default
     */
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
