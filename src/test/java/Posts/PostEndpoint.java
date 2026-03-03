package Posts;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Encapsulates all /posts API endpoint calls.
 */
public class PostEndpoint {

    private static final String POSTS = "/posts";
    private static final String POST_BY_ID = "/posts/{id}";
    private static final String POST_COMMENTS = "/posts/{id}/comments";
    private static final String COMMENTS = "/comments";

    public static Response getAllPosts() {
        return given()
                .when()
                .get(POSTS);
    }

    public static Response getPostById(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .get(POST_BY_ID);
    }

    public static Response getPostComments(int postId) {
        return given()
                .pathParam("id", postId)
                .when()
                .get(POST_COMMENTS);
    }

    public static Response getCommentsByPostId(int postId) {
        return given()
                .queryParam("postId", postId)
                .when()
                .get(COMMENTS);
    }

    public static Response createPost(Object body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(POSTS);
    }

    public static Response updatePost(int id, Object body) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(body)
                .when()
                .put(POST_BY_ID);
    }

    public static Response patchPost(int id, Object body) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(body)
                .when()
                .patch(POST_BY_ID);
    }

    public static Response deletePost(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .delete(POST_BY_ID);
    }
}
