package restAPIPractise;

import Resources.AddPlacePayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class libraryDynamicJson {

    @Test

    public void addBook() {
        RestAssured.baseURI = "http://216.10.245.166";
        String resp = given().log().all().
                header("Content-Type", "application/json").

                body("\"name\":\"Learn Appium Automation with Java\",\n" +
                        "\"isbn\":\"bcd\",\n" +
                        "\"aisle\":\"227\",\n" +
                        "\"author\":\"John foe\"").

                when().

                post("/Library/Addbook.php").

                then().log().all().assertThat().statusCode(200).and().extract().response().asString();
        JsonPath js =new JsonPath(resp);
        String id = js.get("ID");
        System.out.println(id);


        //deleteBOok

    }
//
//    @DataProvider(name="BooksData")
//
//    public Object[][]  getData()
//
//    {
//
////array=collection of elements
//
////multidimensional array= collection of arrays
//
//        return new Object[][]
//                {"ojfwty","9363"},{"cwetee","4253"}, {"okmfet","533"} };

}
