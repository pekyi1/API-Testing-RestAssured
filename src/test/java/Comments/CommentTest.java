package Comments;

import config.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Comments.CommentDataProvider.*;
import static Comments.CommentEndpoint.*;
import static org.hamcrest.Matchers.*;

/**
 * CRUD tests for the /comments resource.
 */
public class CommentTest extends BaseTest {

    @Test
    @DisplayName("GET: Fetch all comments and validate schema")
    public void testGetAllComments() {
        getAllComments().then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("", not(emptyArray()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/comments-schema.json"));
    }

    @Test
    @DisplayName("GET: Fetch single comment by ID")
    public void testGetCommentById() {
        getCommentById(getExistingCommentId()).then()
                .statusCode(200)
                .body("id", equalTo(getExistingCommentId()))
                .body("postId", notNullValue())
                .body("email", notNullValue());
    }

    @Test
    @DisplayName("GET: Fetch comments filtered by postId")
    public void testGetCommentsByPostId() {
        getCommentsByPostId(getPostIdForComments()).then()
                .statusCode(200)
                .body("", not(emptyArray()))
                .body("[0].postId", equalTo(getPostIdForComments()));
    }

    @Test
    @DisplayName("POST: Create a comment and validate response")
    public void testCreateComment() {
        createComment(defaultCreateCommentData()).then()
                .statusCode(201)
                .body("name", equalTo("test comment"))
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("PUT: Update a comment and validate change")
    public void testUpdateComment() {
        updateComment(getCommentIdToUpdate(), defaultUpdateCommentData()).then()
                .statusCode(200)
                .body("body", equalTo("Updated body content"));
    }

    @Test
    @DisplayName("DELETE: Delete a comment and validate status")
    public void testDeleteComment() {
        deleteComment(getCommentIdToDelete()).then()
                .statusCode(200);
    }
}
