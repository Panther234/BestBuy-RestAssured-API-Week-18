package com.bestbuyapi.store;

import com.bestbuyapi.model.StorePojo;
import com.bestbuyapi.testbase.TestBaseStore;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StorePutTest extends TestBaseStore {

    @Test
    public void updateStoreDetails() {
        StorePojo storePojo = new StorePojo(); // create object of StorePojo class

        storePojo.setName("EastCote");//name update
        storePojo.setType("MiniStore");//type update
        storePojo.setAddress("111, Pinner road"); //address update
        storePojo.setAddress2("EastCote");//address update
        storePojo.setCity("London");
        storePojo.setState("London");
        storePojo.setZip("123456");
        storePojo.setLat(45.126179);
        storePojo.setLng(-93.261429);
        storePojo.setHours("Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8");

        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id", 8923)
                .body(storePojo)
                .when()
                .put("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

}
