package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Created to perform CRUD operations. [Create, Retrieve, Update and Delete]
public class UserEndpoints {
    public static Response createUser(User payload) {
        Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
                .post(Routes.post_URL);
        return response;
    }

    public static Response readUser(String username) {
        Response response = given().pathParam("username", username).when().get(Routes.get_URL);
        return response;
    }

    public static Response updateUser(User payload, String username) {
        Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .pathParam("username", username).body(payload).when().put(Routes.update_URL);
        return response;
    }

    public static Response deleteUser(String username) {
        Response response = given().pathParam("username", username).when().delete(Routes.delete_URL);
        return response;
    }

}
