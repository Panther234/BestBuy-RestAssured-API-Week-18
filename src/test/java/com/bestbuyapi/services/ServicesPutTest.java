package com.bestbuyapi.services;

import com.bestbuyapi.model.ServicePojo;
import com.bestbuyapi.testbase.TestBaseServices;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ServicesPutTest extends TestBaseServices {

    @Test
    public void updateNameInService() {
        ServicePojo servicePojo = new ServicePojo(); // create object of ServicePojo class

        servicePojo.setName("Computer Repair Services");

        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id", 25)
                .body(servicePojo)
                .when()
                .put("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
}
