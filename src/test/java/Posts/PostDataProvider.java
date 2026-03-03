package Posts;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides test data for Post-related API tests.
 */
public class PostDataProvider {

    public static Map<String, Object> createPostData(int userId, String title, String body) {
        Map<String, Object> postData = new HashMap<>();
        postData.put("userId", userId);
        postData.put("title", title);
        postData.put("body", body);
        return postData;
    }

    public static Map<String, Object> defaultCreatePostData() {
        return createPostData(1, "foo", "bar");
    }

    public static Map<String, Object> defaultUpdatePostData() {
        return createPostData(1, "foo updated", "bar updated");
    }

    public static String patchPostData() {
        return "{\"title\": \"patched title\"}";
    }

    public static int getExistingPostId() {
        return 1;
    }

    public static int getPostIdToUpdate() {
        return 1;
    }

    public static int getPostIdToDelete() {
        return 1;
    }

    public static int getPostIdForComments() {
        return 1;
    }
}
