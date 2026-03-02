package tests.comment;

import assertions.CommentAssertions;
import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import models.request.CommentRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.CommentService;

import static org.hamcrest.Matchers.*;

/**
 * CRUD tests for the /comments resource.
 */
public class CommentTests extends BaseTest {

    private final CommentService commentService = new CommentService();

    @Test
    @DisplayName("GET: Fetch all comments and validate schema")
    public void testGetAllComments() {
        Response response = commentService.getAllComments();

        response.then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("", not(emptyArray()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/comments-schema.json"));
    }

    @Test
    @DisplayName("GET: Fetch single comment by ID")
    public void testGetCommentById() {
        Response response = commentService.getCommentById(1);

        response.then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("postId", notNullValue())
                .body("email", notNullValue());
    }

    @Test
    @DisplayName("GET: Fetch comments filtered by postId")
    public void testGetCommentsByPostId() {
        Response response = commentService.getCommentsByPostId(1);

        response.then()
                .statusCode(200)
                .body("", not(emptyArray()))
                .body("[0].postId", equalTo(1));
    }

    @Test
    @DisplayName("POST: Create a comment and validate response")
    public void testCreateComment() {
        CommentRequest payload = new CommentRequest(1, "test comment", "test@example.com",
                "This is a test comment body");
        Response response = commentService.createComment(payload);

        CommentAssertions.assertCommentCreated(response, "test comment");
    }

    @Test
    @DisplayName("PUT: Update a comment and validate change")
    public void testUpdateComment() {
        CommentRequest payload = new CommentRequest(1, "updated comment", "updated@example.com",
                "Updated body content");
        Response response = commentService.updateComment(1, payload);

        CommentAssertions.assertCommentUpdated(response, "Updated body content");
    }

    @Test
    @DisplayName("DELETE: Delete a comment and validate status")
    public void testDeleteComment() {
        Response response = commentService.deleteComment(1);

        CommentAssertions.assertCommentDeleted(response);
    }
}
