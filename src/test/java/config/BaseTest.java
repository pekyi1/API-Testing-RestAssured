package config;

import org.junit.jupiter.api.BeforeAll;

/**
 * Base test class that all API test classes should extend.
 * Handles common setup such as initialising the REST Assured configuration.
 */
public abstract class BaseTest {

    @BeforeAll
    public static void setup() {
        BaseConfig.init();
    }
}
