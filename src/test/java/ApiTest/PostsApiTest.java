package ApiTest;
import org.junit.jupiter.api.Test;


public class PostsApiTest extends BaseApiTest {

    @Test()
    public void getAllPosts() {
        postsController.getAllPosts();
    }

    @Test
    public void createPost() {
        postsController.createPost();
    }

    @Test
    public void editPostById() {
        postsController.editPost();
    }

    @Test
    public void deletePostById() {
        postsController.deletePostById();
    }


}
