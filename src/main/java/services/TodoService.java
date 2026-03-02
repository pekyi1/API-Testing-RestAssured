package services;

import config.Endpoints;
import io.restassured.response.Response;
import utils.RestUtils;

/**
 * Service layer encapsulating all /todos API calls.
 */
public class TodoService {

    public Response getAllTodos() {
        return RestUtils.get(Endpoints.TODOS);
    }

    public Response getTodoById(int id) {
        return RestUtils.getWithPathParam(Endpoints.TODO_BY_ID, "id", id);
    }

    public Response createTodo(Object body) {
        return RestUtils.post(Endpoints.TODOS, body);
    }

    public Response updateTodo(int id, Object body) {
        return RestUtils.put(Endpoints.TODO_BY_ID, "id", id, body);
    }

    public Response deleteTodo(int id) {
        return RestUtils.delete(Endpoints.TODO_BY_ID, "id", id);
    }
}
