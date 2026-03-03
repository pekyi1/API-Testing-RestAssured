package Todos;

import utils.TestDataUtils;

import java.util.Map;

import java.util.stream.Stream;

/**
 * Provides test data for Todo-related API tests.
 */
public class TodoDataProvider {

    public static Map<String, Object> defaultCreateTodoData() {
        return TestDataUtils.getTestData("todo.json");
    }

    public static Map<String, Object> defaultUpdateTodoData() {
        Map<String, Object> data = TestDataUtils.getTestData("todo.json");
        data.put("title", "Updated todo title");
        data.put("completed", true);
        return data;
    }

    public static Stream<Integer> getExistingTodoId() {
        return Stream.of(1, 2, 3);
    }

    public static Stream<Integer> getTodoIdToUpdate() {
        return Stream.of(4, 5, 6);
    }

    public static Stream<Integer> getTodoIdToDelete() {
        return Stream.of(7, 8, 9);
    }
}
