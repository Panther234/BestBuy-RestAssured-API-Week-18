package com.bestbuyapi.services;

import com.bestbuyapi.testbase.TestBaseServices;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ServicesDeleteTest extends TestBaseServices {

    @Test
    public void deleteService() {
        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id", "25") // add Service id
                .when()
                .delete("/{id}"); //using Delete
        response.then().statusCode(200); // statusCode 200
        response.prettyPrint();
    }

}
