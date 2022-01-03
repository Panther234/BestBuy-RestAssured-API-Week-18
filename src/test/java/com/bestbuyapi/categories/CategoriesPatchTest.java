package com.bestbuyapi.categories;

import com.bestbuyapi.model.CategoryPojo;
import com.bestbuyapi.testbase.TestBaseCategories;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CategoriesPatchTest extends TestBaseCategories {

    @Test
    public void updateNameInCategory() {
        CategoryPojo categoryPojo = new CategoryPojo(); // create object of CategoryPojo class

        categoryPojo.setName("Numeric");

        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id", "XYZ123456")
                .body(categoryPojo)
                .when()
                .patch("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
}


