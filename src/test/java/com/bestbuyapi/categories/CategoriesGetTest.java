package com.bestbuyapi.categories;

import com.bestbuyapi.testbase.TestBaseCategories;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CategoriesGetTest extends TestBaseCategories {

    @Test
    public void getAllCategoriesInfo() {
        Response response = given()
                .when()
                .get(); // get list of all Categories
        response.then().statusCode(200); //to validate statusCode
        response.prettyPrint(); //to print response into console
    }

    @Test
    public void getSingleCategoryById(){
        Response response = given()
                .pathParam("id", "abcat0020004")
                .when()
                .get("{id}"); // get data of single Category by ID
        response.then().statusCode(200); //to validate statusCode
        response.prettyPrint(); //to print response into console
    }
}
