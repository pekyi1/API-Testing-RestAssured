package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.InputStream;
import java.util.Properties;

/**
 * Centralized configuration for REST Assured.
 * Reads settings from config.properties and exposes a reusable
 * RequestSpecification.
 */
public class BaseConfig {

    private static final Properties properties = new Properties();
    private static RequestSpecification requestSpec;

    static {
        try (InputStream input = BaseConfig.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (Exception e) {
            System.err.println("Could not load config.properties: " + e.getMessage());
        }
    }

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
     * Returns a preconfigured RequestSpecification with base URI and JSON content
     * type.
     */
    public static RequestSpecification getRequestSpec() {
        if (requestSpec == null) {
            requestSpec = new RequestSpecBuilder()
                    .setBaseUri(getBaseUrl())
                    .setContentType(ContentType.JSON)
                    .setAccept(ContentType.JSON)
                    .build();
        }
        return requestSpec;
    }

    /**
     * Initialises RestAssured globally. Called from BaseTest.setup().
     */
    public static void init() {
        RestAssured.baseURI = getBaseUrl();
    }
}
