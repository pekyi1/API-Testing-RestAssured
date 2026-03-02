package base;

import config.BaseConfig;
import org.junit.jupiter.api.BeforeAll;

/**
 * Base class for all test classes.
 * Initialises RestAssured configuration before any test runs.
 */
public class BaseTest {

    @BeforeAll
    public static void setup() {
        BaseConfig.init();
    }
}
