package Comments;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides test data for Comment-related API tests.
 */
public class CommentDataProvider {

    public static Map<String, Object> createCommentData(int postId, String name, String email, String body) {
        Map<String, Object> commentData = new HashMap<>();
        commentData.put("postId", postId);
        commentData.put("name", name);
        commentData.put("email", email);
        commentData.put("body", body);
        return commentData;
    }

    public static Map<String, Object> defaultCreateCommentData() {
        return createCommentData(1, "test comment", "test@example.com", "This is a test comment body");
    }

    public static Map<String, Object> defaultUpdateCommentData() {
        return createCommentData(1, "updated comment", "updated@example.com", "Updated body content");
    }

    public static int getExistingCommentId() {
        return 1;
    }

    public static int getCommentIdToUpdate() {
        return 1;
    }

    public static int getCommentIdToDelete() {
        return 1;
    }

    public static int getPostIdForComments() {
        return 1;
    }
}
