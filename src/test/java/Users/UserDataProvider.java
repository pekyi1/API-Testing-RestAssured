package Users;

import utils.TestDataUtils;

import java.util.Map;

import java.util.stream.Stream;

/**
 * Provides test data for User-related API tests.
 */
public class UserDataProvider {

    public static Map<String, Object> defaultCreateUserData() {
        return TestDataUtils.getTestData("user.json");
    }

    public static Map<String, Object> defaultUpdateUserData() {
        Map<String, Object> data = TestDataUtils.getTestData("user.json");
        data.put("email", "zion@example.com");
        return data;
    }

    public static Stream<Integer> getExistingUserId() {
        return Stream.of(1, 2, 3);
    }

    public static Stream<Integer> getUserIdToUpdate() {
        return Stream.of(4, 5, 6);
    }

    public static Stream<Integer> getUserIdToDelete() {
        return Stream.of(7, 8, 9);
    }
}
