package Users;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides test data for User-related API tests.
 */
public class UserDataProvider {

    public static Map<String, Object> createUserData(String name, String username, String email) {
        Map<String, Object> userData = new HashMap<>();
        userData.put("name", name);
        userData.put("username", username);
        userData.put("email", email);
        return userData;
    }

    public static Map<String, Object> defaultCreateUserData() {
        return createUserData("morpheus", "morpheus", "morpheus@example.com");
    }

    public static Map<String, Object> defaultUpdateUserData() {
        return createUserData("morpheus", "morpheus", "zion@example.com");
    }

    public static int getExistingUserId() {
        return 1;
    }

    public static int getUserIdToUpdate() {
        return 2;
    }

    public static int getUserIdToDelete() {
        return 2;
    }
}
