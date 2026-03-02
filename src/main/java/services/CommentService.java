package services;

import config.Endpoints;
import io.restassured.response.Response;
import utils.RestUtils;

/**
 * Service layer encapsulating all /comments API calls.
 */
public class CommentService {

    public Response getAllComments() {
        return RestUtils.get(Endpoints.COMMENTS);
    }

    public Response getCommentById(int id) {
        return RestUtils.getWithPathParam(Endpoints.COMMENT_BY_ID, "id", id);
    }

    public Response getCommentsByPostId(int postId) {
        return RestUtils.getWithQueryParam(Endpoints.COMMENTS, "postId", postId);
    }

    public Response createComment(Object body) {
        return RestUtils.post(Endpoints.COMMENTS, body);
    }

    public Response updateComment(int id, Object body) {
        return RestUtils.put(Endpoints.COMMENT_BY_ID, "id", id, body);
    }

    public Response deleteComment(int id) {
        return RestUtils.delete(Endpoints.COMMENT_BY_ID, "id", id);
    }
}
