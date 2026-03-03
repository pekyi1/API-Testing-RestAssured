package Todos;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Encapsulates all /todos API endpoint calls.
 */
public class TodoEndpoint {

    private static final String TODOS = "/todos";
    private static final String TODO_BY_ID = "/todos/{id}";

    public static Response getAllTodos() {
        return given()
                .when()
                .get(TODOS);
    }

    public static Response getTodoById(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .get(TODO_BY_ID);
    }

    public static Response createTodo(Object body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(TODOS);
    }

    public static Response updateTodo(int id, Object body) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(body)
                .when()
                .put(TODO_BY_ID);
    }

    public static Response deleteTodo(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .delete(TODO_BY_ID);
    }
}
