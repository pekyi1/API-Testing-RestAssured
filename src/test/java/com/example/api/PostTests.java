package com.example.api;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostTests extends BaseTest {

    @Test
    @DisplayName("GET: Fetch all posts")
    public void testGetAllPosts() {
        given()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("", not(emptyArray()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/posts-schema.json"));
    }

    @Test
    @DisplayName("GET: Fetch single post by ID")
    public void testGetPostById() {
        given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", notNullValue())
                .body("title", notNullValue());
    }

    @Test
    @DisplayName("GET: Fetch comments for a post")
    public void testGetPostComments() {
        given()
                .when()
                .get("/posts/1/comments")
                .then()
                .statusCode(200)
                .body("", not(emptyArray()))
                .body("[0].postId", equalTo(1));
    }

    @Test
    @DisplayName("GET: Fetch comments using query parameter")
    public void testGetCommentsWithQueryParam() {
        given()
                .queryParam("postId", 1)
                .when()
                .get("/comments")
                .then()
                .statusCode(200)
                .body("", not(emptyArray()))
                .body("[0].postId", equalTo(1));
    }

    @Test
    @DisplayName("POST: Create a post")
    public void testCreatePost() {
        String payload = "{\n" +
                "    \"title\": \"foo\",\n" +
                "    \"body\": \"bar\",\n" +
                "    \"userId\": 1\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"))
                .body("userId", equalTo(1))
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("PUT: Update a post completely")
    public void testUpdatePost() {
        String payload = "{\n" +
                "    \"id\": 1,\n" +
                "    \"title\": \"foo updated\",\n" +
                "    \"body\": \"bar updated\",\n" +
                "    \"userId\": 1\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("foo updated"))
                .body("body", equalTo("bar updated"));
    }

    @Test
    @DisplayName("PATCH: Update a post partially")
    public void testPatchPost() {
        String payload = "{\n" +
                "    \"title\": \"patched title\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .patch("/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("patched title"));
    }

    @Test
    @DisplayName("DELETE: Delete a post")
    public void testDeletePost() {
        given()
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200);
    }
}
