package ph.salmon.api;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import ph.salmon.api.entity.DataItem;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Data
public abstract class ApiRequests {
    protected String url;
    protected RequestSpecification reqSpec;
    protected Response response;

    public ApiRequests(String url) {
        this.url = url;
        this.reqSpec = new RequestSpecBuilder()
                .setBaseUri(this.url)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    public static String getEndpoint(String... args) {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(args).forEach(
                a -> builder.append(a).append("/")
        );
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    private void logResponse() {
        log.warn("Response is:");
        Allure.step("Response is:");
        log.warn(this.response.getBody().asString());
        Allure.step(this.response.getBody().asString());
        log.warn("Status code is: {}", this.response.getStatusCode());
        Allure.step("Response is: "+this.response.getStatusCode());
    }

    public List<DataItem> get(String endPoint, int expectedStatusCode) {
        log.info("Performed GET {}", endPoint);
        Allure.step("Performed GET with fallowing endPoint "+ endPoint);
        this.response = RestAssured.given()
                .spec(reqSpec)
                .get(endPoint);
        logResponse();
        Assertions.assertEquals(expectedStatusCode, response.getStatusCode());
        return response.getBody().as(new TypeRef<List<DataItem>>() {});
    }

    public Response post(String endPoint, String body, int expectedStatusCode) {
        log.info("Performed POST {}", endPoint);
        Allure.step("Performed POST with fallowing endPoint "+ endPoint);
        log.info("Body is {}", body);
        Allure.step("Body is "+ body);
        this.response = RestAssured.given()
                .spec(reqSpec)
                .body(body)
                .post(endPoint);
        logResponse();
        Assertions.assertEquals(expectedStatusCode, response.getStatusCode());
        return this.response;
    }


    public Response put(String endPoint, int postId, String body, int expectedStatusCode) {
        String form = String.format(endPoint+"/%d",postId);
        log.info("Performed PUT {}", form);
        Allure.step("Performed PUT with fallowing endPoint "+ form);
        log.info("Body is {}", body);
        Allure.step("Body is "+ body);
        this.response = RestAssured.given()
                .spec(reqSpec)
                .body(body)
                .put(form);
        Assertions.assertEquals(expectedStatusCode, response.getStatusCode());
        return this.response;
    }

    public Response delete(String endPoint, int id) {
        String form = String.format(endPoint+"/%d",id);
        log.info("Performed DELETE {}", form);
        Allure.step("Performed PUT with fallowing endPoint "+ form);
        this.response = RestAssured.given()
                .spec(reqSpec)
                .delete(form);
        logResponse();
        Assertions.assertEquals(200, response.getStatusCode());
        return this.response;
    }

}
