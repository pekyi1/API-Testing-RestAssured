package Todos;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides test data for Todo-related API tests.
 */
public class TodoDataProvider {

    public static Map<String, Object> createTodoData(int userId, String title, boolean completed) {
        Map<String, Object> todoData = new HashMap<>();
        todoData.put("userId", userId);
        todoData.put("title", title);
        todoData.put("completed", completed);
        return todoData;
    }

    public static Map<String, Object> defaultCreateTodoData() {
        return createTodoData(1, "Complete API testing lab", false);
    }

    public static Map<String, Object> defaultUpdateTodoData() {
        return createTodoData(1, "Updated todo title", true);
    }

    public static int getExistingTodoId() {
        return 1;
    }

    public static int getTodoIdToUpdate() {
        return 1;
    }

    public static int getTodoIdToDelete() {
        return 1;
    }
}
