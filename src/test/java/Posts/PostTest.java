package Posts;

import config.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Posts.PostDataProvider.*;
import static Posts.PostEndpoint.*;
import static org.hamcrest.Matchers.*;

/**
 * CRUD tests for the /posts resource.
 */
public class PostTest extends BaseTest {

    @Test
    @DisplayName("GET: Fetch all posts and validate schema")
    public void testGetAllPosts() {
        getAllPosts().then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("", not(emptyArray()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/posts-schema.json"));
    }

    @Test
    @DisplayName("GET: Fetch single post by ID")
    public void testGetPostById() {
        getPostById(getExistingPostId()).then()
                .statusCode(200)
                .body("id", equalTo(getExistingPostId()))
                .body("userId", notNullValue())
                .body("title", notNullValue());
    }

    @Test
    @DisplayName("GET: Fetch comments for a post via nested route")
    public void testGetPostComments() {
        getPostComments(getPostIdForComments()).then()
                .statusCode(200)
                .body("", not(emptyArray()))
                .body("[0].postId", equalTo(getPostIdForComments()));
    }

    @Test
    @DisplayName("GET: Fetch comments using query parameter")
    public void testGetCommentsWithQueryParam() {
        getCommentsByPostId(getPostIdForComments()).then()
                .statusCode(200)
                .body("", not(emptyArray()))
                .body("[0].postId", equalTo(getPostIdForComments()));
    }

    @Test
    @DisplayName("POST: Create a post and validate response")
    public void testCreatePost() {
        createPost(defaultCreatePostData()).then()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"))
                .body("userId", equalTo(1))
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("PUT: Update a post completely")
    public void testUpdatePost() {
        updatePost(getPostIdToUpdate(), defaultUpdatePostData()).then()
                .statusCode(200)
                .body("title", equalTo("foo updated"));
    }

    @Test
    @DisplayName("PATCH: Update a post partially")
    public void testPatchPost() {
        patchPost(getPostIdToUpdate(), patchPostData()).then()
                .statusCode(200)
                .body("title", equalTo("patched title"));
    }

    @Test
    @DisplayName("DELETE: Delete a post")
    public void testDeletePost() {
        deletePost(getPostIdToDelete()).then()
                .statusCode(200);
    }
}
