package Posts;

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

import static Posts.PostDataProvider.*;
import static Posts.PostEndpoint.*;
import static org.hamcrest.Matchers.*;

/**
 * CRUD tests for the /posts resource.
 */
@Epic("API Testing RESTAssured")
@Feature("Posts Endpoint")
public class PostTest extends BaseTest {

    @Test
    @Story("Get Posts")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test validates the JSON schema of the /posts endpoint")
    @Tag("smoke")
    @DisplayName("GET: Fetch all posts and validate schema")
    public void testGetAllPosts() {
        getAllPosts().then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("", not(emptyArray()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/posts-schema.json"));
    }

    @ParameterizedTest(name = "GET: Fetch single post by ID {0}")
    @Story("Get Single Post")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test fetches a post by its ID and validates its properties")
    @MethodSource("Posts.PostDataProvider#getExistingPostId")
    public void testGetPostById(int postId) {
        getPostById(postId).then()
                .statusCode(200)
                .body("id", equalTo(postId))
                .body("userId", notNullValue())
                .body("title", notNullValue());
    }

    @ParameterizedTest(name = "GET: Fetch comments for a post ID {0} via nested route")
    @Story("Get Post Comments Nested")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test fetches comments using the path parameter /posts/{id}/comments")
    @MethodSource("Posts.PostDataProvider#getPostIdForComments")
    public void testGetPostComments(int postId) {
        getPostComments(postId).then()
                .statusCode(200)
                .body("", not(emptyArray()))
                .body("[0].postId", equalTo(postId));
    }

    @ParameterizedTest(name = "GET: Fetch comments using query parameter for post ID {0}")
    @Story("Get Post Comments by QueryParam")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test fetches comments using query parameter postId={0}")
    @MethodSource("Posts.PostDataProvider#getPostIdForComments")
    public void testGetCommentsWithQueryParam(int postId) {
        getCommentsByPostId(postId).then()
                .statusCode(200)
                .body("", not(emptyArray()))
                .body("[0].postId", equalTo(postId));
    }

    @Test
    @DisplayName("POST: Create a post and validate response")
    @Story("Create Post")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates creation of a new post via the POST method")
    @Tag("smoke")
    public void testCreatePost() {
        createPost(defaultCreatePostData()).then()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"))
                .body("userId", equalTo(1))
                .body("id", notNullValue());
    }

    @ParameterizedTest(name = "PUT: Update a post ID {0} completely")
    @Story("Update Post")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test entirely updates an existing post through a full payload PUT request")
    @MethodSource("Posts.PostDataProvider#getPostIdToUpdate")
    public void testUpdatePost(int postId) {
        updatePost(postId, defaultUpdatePostData()).then()
                .statusCode(200)
                .body("title", equalTo("foo updated"));
    }

    @ParameterizedTest(name = "PATCH: Update a post ID {0} partially")
    @Story("Patch Post")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test partially updates an existing post with a JSON patch structure")
    @MethodSource("Posts.PostDataProvider#getPostIdToUpdate")
    public void testPatchPost(int postId) {
        patchPost(postId, patchPostData()).then()
                .statusCode(200)
                .body("title", equalTo("patched title"));
    }

    @ParameterizedTest(name = "DELETE: Delete a post ID {0}")
    @Story("Delete Post")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test executes the DELETE method to remove a post by ID")
    @MethodSource("Posts.PostDataProvider#getPostIdToDelete")
    public void testDeletePost(int postId) {
        deletePost(postId).then()
                .statusCode(200);
    }
}
