package com.bestbuyapi.services;

import com.bestbuyapi.model.ServicePojo;
import com.bestbuyapi.testbase.TestBaseServices;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ServicesPostTest extends TestBaseServices {

    @Test
    public void createNewService() {
        ServicePojo servicePojo = new ServicePojo(); // create object of ServicePojo class

        servicePojo.setName("Laptop Repair Services");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(servicePojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }

}
