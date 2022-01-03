package com.bestbuyapi.product;

import com.bestbuyapi.testbase.TestBaseProduct;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductDeleteTest extends TestBaseProduct {

    @Test
    public void deleteSingleProduct() {

        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id", 9999693) // add product ID
                .when()
                .delete("/{id}"); //using Delete
        response.then().statusCode(200); // statusCode 200
        response.prettyPrint();
    }
}
