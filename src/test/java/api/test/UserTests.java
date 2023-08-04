package api.test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UserTests {

    Faker faker;
    User userPayload;

    @BeforeClass
    public void setUpDate() {
        faker = new Faker();
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
    }

    @Test(priority = 1)
    public void testPostUser(){
        Response response = UserEndpoints.createUser(userPayload);
        response.then().log().all();
        assertEquals(response.getStatusCode(), 200);
    }
    @Test(priority = 2)
    public void testReadUser() {
        Response response = UserEndpoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        assertEquals(response.getStatusCode(), 200);
    }



    @Test(priority = 3)

    public void testUpdateTest() {
        this.userPayload.setFirstname(faker.name().firstName());
        this.userPayload.setLastname(faker.name().lastName());
        this.userPayload.setEmail(faker.internet().safeEmailAddress());
        Response response = UserEndpoints.updateUser(userPayload, this.userPayload.getUsername());
        response.then().log().all();
        assertEquals(response.getStatusCode(), 200);
        response = UserEndpoints.readUser(userPayload.getUsername());
        response.then().log().all();
        assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4)
    public void testDeleteUser() {
        Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        assertEquals(response.getStatusCode(), 200);
    }

}

