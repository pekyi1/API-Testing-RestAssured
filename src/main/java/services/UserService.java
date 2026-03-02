package services;

import config.Endpoints;
import io.restassured.response.Response;
import utils.RestUtils;

/**
 * Service layer encapsulating all /users API calls.
 */
public class UserService {

    public Response getAllUsers() {
        return RestUtils.get(Endpoints.USERS);
    }

    public Response getUserById(int id) {
        return RestUtils.getWithPathParam(Endpoints.USER_BY_ID, "id", id);
    }

    public Response createUser(Object body) {
        return RestUtils.post(Endpoints.USERS, body);
    }

    public Response updateUser(int id, Object body) {
        return RestUtils.put(Endpoints.USER_BY_ID, "id", id, body);
    }

    public Response deleteUser(int id) {
        return RestUtils.delete(Endpoints.USER_BY_ID, "id", id);
    }
}
