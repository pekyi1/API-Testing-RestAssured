package Users;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Encapsulates all /users API endpoint calls.
 */
public class UserEndpoint {

    private static final String USERS = "/users";
    private static final String USER_BY_ID = "/users/{id}";

    public static Response getAllUsers() {
        return given()
                .when()
                .get(USERS);
    }

    public static Response getUserById(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .get(USER_BY_ID);
    }

    public static Response createUser(Object body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(USERS);
    }

    public static Response updateUser(int id, Object body) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(body)
                .when()
                .put(USER_BY_ID);
    }

    public static Response deleteUser(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .delete(USER_BY_ID);
    }
}
