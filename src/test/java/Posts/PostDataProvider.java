package Posts;

import utils.TestDataUtils;

import java.util.Map;

import java.util.stream.Stream;

/**
 * Provides test data for Post-related API tests.
 */
public class PostDataProvider {

    public static Map<String, Object> defaultCreatePostData() {
        return TestDataUtils.getTestData("post.json");
    }

    public static Map<String, Object> defaultUpdatePostData() {
        Map<String, Object> data = TestDataUtils.getTestData("post.json");
        data.put("title", "foo updated");
        data.put("body", "bar updated");
        return data;
    }

    public static String patchPostData() {
        return "{\"title\": \"patched title\"}";
    }

    public static Stream<Integer> getExistingPostId() {
        return Stream.of(1, 2, 3);
    }

    public static Stream<Integer> getPostIdToUpdate() {
        return Stream.of(4, 5, 6);
    }

    public static Stream<Integer> getPostIdToDelete() {
        return Stream.of(7, 8, 9);
    }

    public static Stream<Integer> getPostIdForComments() {
        return Stream.of(1, 2, 3);
    }
}
