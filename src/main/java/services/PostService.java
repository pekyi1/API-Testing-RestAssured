package services;

import config.Endpoints;
import io.restassured.response.Response;
import utils.RestUtils;

/**
 * Service layer encapsulating all /posts and /comments API calls.
 */
public class PostService {

    public Response getAllPosts() {
        return RestUtils.get(Endpoints.POSTS);
    }

    public Response getPostById(int id) {
        return RestUtils.getWithPathParam(Endpoints.POST_BY_ID, "id", id);
    }

    public Response getPostComments(int postId) {
        return RestUtils.getWithPathParam(Endpoints.POST_COMMENTS, "id", postId);
    }

    public Response getCommentsByPostId(int postId) {
        return RestUtils.getWithQueryParam(Endpoints.COMMENTS, "postId", postId);
    }

    public Response createPost(Object body) {
        return RestUtils.post(Endpoints.POSTS, body);
    }

    public Response updatePost(int id, Object body) {
        return RestUtils.put(Endpoints.POST_BY_ID, "id", id, body);
    }

    public Response patchPost(int id, Object body) {
        return RestUtils.patch(Endpoints.POST_BY_ID, "id", id, body);
    }

    public Response deletePost(int id) {
        return RestUtils.delete(Endpoints.POST_BY_ID, "id", id);
    }
}
