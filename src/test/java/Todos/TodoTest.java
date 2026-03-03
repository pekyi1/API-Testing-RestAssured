package Todos;

import config.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Todos.TodoDataProvider.*;
import static Todos.TodoEndpoint.*;
import static org.hamcrest.Matchers.*;

/**
 * CRUD tests for the /todos resource.
 */
public class TodoTest extends BaseTest {

    @Test
    @DisplayName("GET: Fetch all todos and validate schema")
    public void testGetAllTodos() {
        getAllTodos().then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("", not(emptyArray()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/todos-schema.json"));
    }

    @Test
    @DisplayName("GET: Fetch single todo by ID")
    public void testGetTodoById() {
        getTodoById(getExistingTodoId()).then()
                .statusCode(200)
                .body("id", equalTo(getExistingTodoId()))
                .body("userId", notNullValue())
                .body("title", notNullValue())
                .body("completed", notNullValue());
    }

    @Test
    @DisplayName("POST: Create a todo and validate response")
    public void testCreateTodo() {
        createTodo(defaultCreateTodoData()).then()
                .statusCode(201)
                .body("title", equalTo("Complete API testing lab"))
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("PUT: Update a todo and validate change")
    public void testUpdateTodo() {
        updateTodo(getTodoIdToUpdate(), defaultUpdateTodoData()).then()
                .statusCode(200)
                .body("title", equalTo("Updated todo title"));
    }

    @Test
    @DisplayName("DELETE: Delete a todo and validate status")
    public void testDeleteTodo() {
        deleteTodo(getTodoIdToDelete()).then()
                .statusCode(200);
    }
}
