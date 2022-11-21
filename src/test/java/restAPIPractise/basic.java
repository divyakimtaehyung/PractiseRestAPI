package restAPIPractise;

import Resources.AddPlacePayLoad;
import io.restassured.RestAssured;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class basic {

    public static  void main(String args[]){

        RestAssured.baseURI="https://rahulshettyacademy.com";
        Response res=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(AddPlacePayLoad.AddPlace()).when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).
                header("server", "Apache/2.4.18 (Ubuntu)").and().extract().response();
        String placeid=res.path("place_id");
        System.out.println(placeid);

        //Update Place
        String newAddress = "Winter Walk, China";

        given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
                .body("{\r\n" +
                        "\"place_id\":\""+placeid+"\",\r\n" +
                        "\"address\":\""+newAddress+"\",\r\n" +
                        "\"key\":\"qaclick123\"\r\n" +
                        "}").
                when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

        Response res1= given().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id",placeid)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).and().extract().response();
        String actualAddress=res1.path("address");
        System.out.println(actualAddress);
    }

}
