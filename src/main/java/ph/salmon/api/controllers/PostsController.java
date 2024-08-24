package ph.salmon.api.controllers;
import io.restassured.response.Response;
import ph.salmon.api.ApiRequests;
import ph.salmon.api.entity.DataItem;
import ph.salmon.api.utils.JacksonUtils;

import java.util.List;

import static ph.salmon.api.applications.SalmonBaseEndpoints.POSTS;

public class PostsController extends ApiRequests {
    public PostsController(String url) {
        super(url);
    }

    public List<DataItem> getAllPosts() {
        return super.get(getEndpoint(POSTS), 200);

    }

    public Response createPost() {
        List<DataItem> postEntities = super.get(getEndpoint(POSTS), 200);
        int userId = postEntities.get(1).getUserId();
        DataItem dataItem = DataItem.builder().id(userId)
                .title("laboriosam dolor voluptates")
                .body("quo deleniti praesentium dicta non quod\\naut est molestias\\nmolestias et officia quis nihil\\nitaque dolorem quia")
                .build();
        String payLoad = JacksonUtils.fromObjectToJson(dataItem);
        return super.post(getEndpoint(POSTS), payLoad,201);

    }

    public Response editPost() {
        List<DataItem> postEntities = super.get(getEndpoint(POSTS), 200);
        int postId = postEntities.get(postEntities.size()-1).getId();
        int userId = postEntities.get(1).getUserId();
        DataItem dataItem = DataItem.builder().id(userId)
                .title("laboriosam dolor voluptates")
                .body("quo deleniti praesentium dicta non quod\\naut est molestias\\nmolestias et officia quis nihil\\nitaque dolorem quia")
                .build();
        String payLoad = JacksonUtils.fromObjectToJson(dataItem);
        return super.put(getEndpoint(POSTS),postId,payLoad,200);

    }



    public Response deletePostById() {
        List<DataItem> postEntities =  super.get(getEndpoint(POSTS), 200);
        int id = postEntities.get(0).getId();
        return super.delete(getEndpoint(POSTS), id);
    }


}
