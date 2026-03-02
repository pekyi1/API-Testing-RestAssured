package utils;

import io.restassured.response.Response;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

/**
 * Generic REST utility methods that wrap common HTTP operations.
 */
public class RestUtils {

    public static Response get(String endpoint) {
        return given()
                .when()
                .get(endpoint);
    }

    public static Response getWithPathParam(String endpoint, String paramName, Object paramValue) {
        return given()
                .pathParam(paramName, paramValue)
                .when()
                .get(endpoint);
    }

    public static Response getWithQueryParam(String endpoint, String paramName, Object paramValue) {
        return given()
                .queryParam(paramName, paramValue)
                .when()
                .get(endpoint);
    }

    public static Response post(String endpoint, Object body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint);
    }

    public static Response put(String endpoint, String paramName, Object paramValue, Object body) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam(paramName, paramValue)
                .body(body)
                .when()
                .put(endpoint);
    }

    public static Response patch(String endpoint, String paramName, Object paramValue, Object body) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam(paramName, paramValue)
                .body(body)
                .when()
                .patch(endpoint);
    }

    public static Response delete(String endpoint, String paramName, Object paramValue) {
        return given()
                .pathParam(paramName, paramValue)
                .when()
                .delete(endpoint);
    }
}
