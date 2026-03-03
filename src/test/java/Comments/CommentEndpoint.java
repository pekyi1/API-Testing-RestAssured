package Comments;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Encapsulates all /comments API endpoint calls.
 */
public class CommentEndpoint {

    private static final String COMMENTS = "/comments";
    private static final String COMMENT_BY_ID = "/comments/{id}";

    public static Response getAllComments() {
        return given()
                .when()
                .get(COMMENTS);
    }

    public static Response getCommentById(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .get(COMMENT_BY_ID);
    }

    public static Response getCommentsByPostId(int postId) {
        return given()
                .queryParam("postId", postId)
                .when()
                .get(COMMENTS);
    }

    public static Response createComment(Object body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(COMMENTS);
    }

    public static Response updateComment(int id, Object body) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(body)
                .when()
                .put(COMMENT_BY_ID);
    }

    public static Response deleteComment(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .delete(COMMENT_BY_ID);
    }
}
