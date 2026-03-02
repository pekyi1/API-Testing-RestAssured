package tests.todo;

import assertions.TodoAssertions;
import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import models.request.TodoRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.TodoService;

import static org.hamcrest.Matchers.*;

/**
 * CRUD tests for the /todos resource.
 */
public class TodoTests extends BaseTest {

    private final TodoService todoService = new TodoService();

    @Test
    @DisplayName("GET: Fetch all todos and validate schema")
    public void testGetAllTodos() {
        Response response = todoService.getAllTodos();

        response.then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("", not(emptyArray()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/todos-schema.json"));
    }

    @Test
    @DisplayName("GET: Fetch single todo by ID")
    public void testGetTodoById() {
        Response response = todoService.getTodoById(1);

        response.then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", notNullValue())
                .body("title", notNullValue())
                .body("completed", notNullValue());
    }

    @Test
    @DisplayName("POST: Create a todo and validate response")
    public void testCreateTodo() {
        TodoRequest payload = new TodoRequest(1, "Complete API testing lab", false);
        Response response = todoService.createTodo(payload);

        TodoAssertions.assertTodoCreated(response, "Complete API testing lab");
    }

    @Test
    @DisplayName("PUT: Update a todo and validate change")
    public void testUpdateTodo() {
        TodoRequest payload = new TodoRequest(1, "Updated todo title", true);
        Response response = todoService.updateTodo(1, payload);

        TodoAssertions.assertTodoUpdated(response, "Updated todo title");
    }

    @Test
    @DisplayName("DELETE: Delete a todo and validate status")
    public void testDeleteTodo() {
        Response response = todoService.deleteTodo(1);

        TodoAssertions.assertTodoDeleted(response);
    }
}
