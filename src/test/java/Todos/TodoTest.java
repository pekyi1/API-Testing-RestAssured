package Todos;

import config.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static Todos.TodoDataProvider.*;
import static Todos.TodoEndpoint.*;
import static org.hamcrest.Matchers.*;

/**
 * CRUD tests for the /todos resource.
 */
@Epic("API Testing RESTAssured")
@Feature("Todos Endpoint")
public class TodoTest extends BaseTest {

    @Test
    @Story("Get Todos")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test validates the JSON schema of the /todos endpoint")
    @Tag("smoke")
    @DisplayName("GET: Fetch all todos and validate schema")
    public void testGetAllTodos() {
        getAllTodos().then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("", not(emptyArray()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/todos-schema.json"));
    }

    @ParameterizedTest(name = "GET: Fetch single todo by ID {0}")
    @Story("Get Single Todo")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test fetches a to-do item by its ID and validates its properties")
    @MethodSource("Todos.TodoDataProvider#getExistingTodoId")
    public void testGetTodoById(int todoId) {
        getTodoById(todoId).then()
                .statusCode(200)
                .body("id", equalTo(todoId))
                .body("userId", notNullValue())
                .body("title", notNullValue())
                .body("completed", notNullValue());
    }

    @Test
    @DisplayName("POST: Create a todo and validate response")
    @Story("Create Todo")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test simulates the creation of a new to-do task")
    @Tag("smoke")
    public void testCreateTodo() {
        createTodo(defaultCreateTodoData()).then()
                .statusCode(201)
                .body("title", equalTo("Complete API testing lab"))
                .body("id", notNullValue());
    }

    @ParameterizedTest(name = "PUT: Update a todo ID {0}")
    @Story("Update Todo")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test updates a to-do task (e.g. marking it true on completed field)")
    @MethodSource("Todos.TodoDataProvider#getTodoIdToUpdate")
    public void testUpdateTodo(int todoId) {
        updateTodo(todoId, defaultUpdateTodoData()).then()
                .statusCode(200)
                .body("title", equalTo("Updated todo title"));
    }

    @ParameterizedTest(name = "DELETE: Delete a todo ID {0}")
    @Story("Delete Todo")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test ensures a to-do task can be cleanly deleted")
    @MethodSource("Todos.TodoDataProvider#getTodoIdToDelete")
    public void testDeleteTodo(int todoId) {
        deleteTodo(todoId).then()
                .statusCode(200);
    }
}
