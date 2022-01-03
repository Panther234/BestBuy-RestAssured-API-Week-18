package com.bestbuyapi.categories;

import com.bestbuyapi.model.CategoryPojo;
import com.bestbuyapi.testbase.TestBaseCategories;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CategoriesPutTest extends TestBaseCategories {

    @Test
    public void updateNameInCategory() {
        CategoryPojo categoryPojo = new CategoryPojo(); // create object of CategoryPojo class

        categoryPojo.setName("Car");
        categoryPojo.setId("ABCD123456");

        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id", "XYZ123456")
                .body(categoryPojo)
                .when()
                .put("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
}
