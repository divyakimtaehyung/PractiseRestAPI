package com.api.pojo;

public class User {
    private String name;
    private String job;

    // Constructors
    public User() {}

    public User(String name, String job) {
        this.name = name;
        this.job = job;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
}





//

package com.api.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.api.pojo.User;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class UserAPITest {

    @Test
    public void createUserTest() {
        // Set base URI
        RestAssured.baseURI = "https://reqres.in/api";

        // Create POJO object
        User user = new User("John", "Engineer");

        // Make POST request using POJO as body
        Response response = RestAssured
            .given()
                .contentType(ContentType.JSON)
                .body(user)
            .when()
                .post("/users")
            .then()
                .statusCode(201)
                .body("name", equalTo("John"))
                .body("job", equalTo("Engineer"))
                .extract().response();

        // Print response
        System.out.println(response.asPrettyString());
    }
}
