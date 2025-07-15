@Test
public void testCreateResource() {
    String requestBody = "{ \"name\": \"New Item\", \"description\": \"This is a new item.\" }";

    given()
        .contentType(ContentType.JSON)
        .body(requestBody)
        .log().all() // Log the request details
    .when()
        .post("/items") // Assuming baseURI + basePath is "https://api.example.com/v1"
    .then()
        .statusCode(201) // Expect Created status
        .body("name", equalTo("New Item"))
        .log().all(); // Log the response details
}

// Using a POJO
class Item {
    public String name;
    public String description;
}

@Test
public void testCreateResourceWithPOJO() {
    Item newItem = new Item();
    newItem.name = "Another Item";
    newItem.description = "Description for another item.";

    given()
        .contentType(ContentType.JSON)
        .body(newItem)
    .when()
        .post("/items")
    .then()
        .statusCode(201)
        .body("name", equalTo("Another Item"));
}
