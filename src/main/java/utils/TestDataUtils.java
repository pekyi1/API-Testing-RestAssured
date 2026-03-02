package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

/**
 * Helper for loading external test-data files from the classpath
 * (test/resources/testdata/).
 */
public class TestDataUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Reads a JSON file from the classpath and returns it as a Jackson JsonNode.
     * Example: TestDataUtils.readJson("testdata/user.json")
     */
    public static JsonNode readJson(String resourcePath) {
        try (InputStream is = TestDataUtils.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new RuntimeException("Resource not found: " + resourcePath);
            }
            return mapper.readTree(is);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON resource: " + resourcePath, e);
        }
    }

    /**
     * Reads a JSON file and maps it to the given POJO class.
     */
    public static <T> T readJson(String resourcePath, Class<T> clazz) {
        try (InputStream is = TestDataUtils.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new RuntimeException("Resource not found: " + resourcePath);
            }
            return mapper.readValue(is, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON resource: " + resourcePath, e);
        }
    }
}
