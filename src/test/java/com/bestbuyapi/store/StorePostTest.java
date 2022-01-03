package com.bestbuyapi.store;

import com.bestbuyapi.model.StorePojo;
import com.bestbuyapi.testbase.TestBaseStore;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StorePostTest extends TestBaseStore {

    @Test
    public void createNewStore() {

        StorePojo storePojo = new StorePojo(); // create object of StorePojo class

        storePojo.setName("Pinner");
        storePojo.setType("SuperStore");
        storePojo.setAddress("1, Pinner road");
        storePojo.setAddress2("Harrow");
        storePojo.setCity("London");
        storePojo.setState("London");
        storePojo.setZip("123456");
        storePojo.setLat(45.126179);
        storePojo.setLng(-93.261429);
        storePojo.setHours("Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(storePojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }
}
