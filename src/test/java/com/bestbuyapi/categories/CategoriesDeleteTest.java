package com.bestbuyapi.categories;

import com.bestbuyapi.testbase.TestBaseCategories;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CategoriesDeleteTest extends TestBaseCategories {
    @Test
    public void deleteCategory() {

        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id", "XYZ123456") // add Category id
                .when()
                .delete("/{id}"); //using Delete
        response.then().statusCode(200); // statusCode 200
        response.prettyPrint();
    }

}
