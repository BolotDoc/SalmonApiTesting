package ApiTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import ph.salmon.api.controllers.PostsController;
import static ph.salmon.api.applications.SalmonBaseEndpoints.BASE_URI;

@ExtendWith(AllureReportExtension.class)
public abstract class BaseApiTest {
    public PostsController postsController;

    @BeforeEach()
    public void setUpController() {
        this.postsController = new PostsController(BASE_URI);
    }


}
