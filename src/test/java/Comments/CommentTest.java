package Comments;

import config.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static Comments.CommentDataProvider.*;
import static Comments.CommentEndpoint.*;
import static org.hamcrest.Matchers.*;

/**
 * CRUD tests for the /comments resource.
 */
@Epic("API Testing RESTAssured")
@Feature("Comments Endpoint")
public class CommentTest extends BaseTest {

    @Test
    @Story("Get Comments")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test validates the JSON schema of the /comments endpoint")
    @Tag("smoke")
    @DisplayName("GET: Fetch all comments and validate schema")
    public void testGetAllComments() {
        getAllComments().then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("", not(emptyArray()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/comments-schema.json"));
    }

    @ParameterizedTest(name = "GET: Fetch single comment by ID {0}")
    @Story("Get Single Comment")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test fetches a comment by its ID and validates the response object")
    @MethodSource("Comments.CommentDataProvider#getExistingCommentId")
    public void testGetCommentById(int commentId) {
        getCommentById(commentId).then()
                .statusCode(200)
                .body("id", equalTo(commentId))
                .body("postId", notNullValue())
                .body("email", notNullValue());
    }

    @ParameterizedTest(name = "GET: Fetch comments filtered by postId {0}")
    @Story("Get Comments By Post ID")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test verifies that comments map correctly to their respective post ID")
    @MethodSource("Comments.CommentDataProvider#getPostIdForComments")
    public void testGetCommentsByPostId(int postId) {
        getCommentsByPostId(postId).then()
                .statusCode(200)
                .body("", not(emptyArray()))
                .body("[0].postId", equalTo(postId));
    }

    @Test
    @DisplayName("POST: Create a comment and validate response")
    @Story("Create Comment")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test creates a new comment and validates its attributes")
    @Tag("smoke")
    public void testCreateComment() {
        createComment(defaultCreateCommentData()).then()
                .statusCode(201)
                .body("name", equalTo("test comment"))
                .body("id", notNullValue());
    }

    @ParameterizedTest(name = "PUT: Update a comment ID {0}")
    @Story("Update Comment")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test updates an existing comment and verifies the modified title")
    @MethodSource("Comments.CommentDataProvider#getCommentIdToUpdate")
    public void testUpdateComment(int commentId) {
        updateComment(commentId, defaultUpdateCommentData()).then()
                .statusCode(200)
                .body("body", equalTo("Updated body content"));
    }

    @ParameterizedTest(name = "DELETE: Delete a comment ID {0}")
    @Story("Delete Comment")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test deletes a comment by ID and ensures a successful 200 HTTP response")
    @MethodSource("Comments.CommentDataProvider#getCommentIdToDelete")
    public void testDeleteComment(int commentId) {
        deleteComment(commentId).then()
                .statusCode(200);
    }
}
