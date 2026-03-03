package Comments;

import utils.TestDataUtils;

import java.util.Map;

import java.util.stream.Stream;

/**
 * Provides test data for Comment-related API tests.
 */
public class CommentDataProvider {

    public static Map<String, Object> defaultCreateCommentData() {
        return TestDataUtils.getTestData("comment.json");
    }

    public static Map<String, Object> defaultUpdateCommentData() {
        Map<String, Object> data = TestDataUtils.getTestData("comment.json");
        data.put("name", "updated comment");
        data.put("email", "updated@example.com");
        data.put("body", "Updated body content");
        return data;
    }

    public static Stream<Integer> getExistingCommentId() {
        return Stream.of(1, 2, 3);
    }

    public static Stream<Integer> getCommentIdToUpdate() {
        return Stream.of(4, 5, 6);
    }

    public static Stream<Integer> getCommentIdToDelete() {
        return Stream.of(7, 8, 9);
    }

    public static Stream<Integer> getPostIdForComments() {
        return Stream.of(1, 2, 3);
    }
}
