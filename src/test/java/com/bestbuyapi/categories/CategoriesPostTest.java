package com.bestbuyapi.categories;

import com.bestbuyapi.model.CategoryPojo;
import com.bestbuyapi.testbase.TestBaseCategories;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CategoriesPostTest extends TestBaseCategories {

    @Test
    public void createNewCategory() {
        CategoryPojo categoryPojo = new CategoryPojo(); // create object of CategoryPojo class

        categoryPojo.setName("Alphabet");
        categoryPojo.setId("XYZ123456");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(categoryPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }
}
