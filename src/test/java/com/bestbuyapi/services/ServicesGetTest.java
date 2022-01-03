package com.bestbuyapi.services;

import com.bestbuyapi.testbase.TestBaseServices;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ServicesGetTest extends TestBaseServices {

    @Test
    public void getAllServicesInfo() {
        Response response = given()
                .when()
                .get(); // get list of all Services
        response.then().statusCode(200); //to validate statusCode
        response.prettyPrint(); //to print response into console
    }

    @Test
    public void getSingleServiceById(){
        Response response = given()
                .pathParam("id", 2)
                .when()
                .get("{id}"); // get data of single Service by ID
        response.then().statusCode(200); //to validate statusCode
        response.prettyPrint(); //to print response into console
    }
}
