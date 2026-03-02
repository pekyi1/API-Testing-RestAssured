package tests.post;

import assertions.PostAssertions;
import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import models.request.PostRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.PostService;
import utils.PayloadBuilder;

import static org.hamcrest.Matchers.*;

/**
 * CRUD tests for the /posts and /comments resources.
 */
public class PostTests extends BaseTest {

    private final PostService postService = new PostService();

    @Test
    @DisplayName("GET: Fetch all posts and validate schema")
    public void testGetAllPosts() {
        Response response = postService.getAllPosts();

        response.then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("", not(emptyArray()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/posts-schema.json"));
    }

    @Test
    @DisplayName("GET: Fetch single post by ID")
    public void testGetPostById() {
        Response response = postService.getPostById(1);

        PostAssertions.assertPostById(response, 1);
    }

    @Test
    @DisplayName("GET: Fetch comments for a post via nested route")
    public void testGetPostComments() {
        Response response = postService.getPostComments(1);

        response.then()
                .statusCode(200)
                .body("", not(emptyArray()))
                .body("[0].postId", equalTo(1));
    }

    @Test
    @DisplayName("GET: Fetch comments using query parameter")
    public void testGetCommentsWithQueryParam() {
        Response response = postService.getCommentsByPostId(1);

        response.then()
                .statusCode(200)
                .body("", not(emptyArray()))
                .body("[0].postId", equalTo(1));
    }

    @Test
    @DisplayName("POST: Create a post and validate response")
    public void testCreatePost() {
        PostRequest payload = PayloadBuilder.createPost(1, "foo", "bar");
        Response response = postService.createPost(payload);

        response.then()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"))
                .body("userId", equalTo(1))
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("PUT: Update a post completely")
    public void testUpdatePost() {
        PostRequest payload = PayloadBuilder.createPost(1, "foo updated", "bar updated");
        Response response = postService.updatePost(1, payload);

        PostAssertions.assertPostUpdated(response, "foo updated");
    }

    @Test
    @DisplayName("PATCH: Update a post partially")
    public void testPatchPost() {
        String partialPayload = "{\"title\": \"patched title\"}";
        Response response = postService.patchPost(1, partialPayload);

        PostAssertions.assertPostUpdated(response, "patched title");
    }

    @Test
    @DisplayName("DELETE: Delete a post")
    public void testDeletePost() {
        Response response = postService.deletePost(1);

        PostAssertions.assertPostDeleted(response);
    }
}
