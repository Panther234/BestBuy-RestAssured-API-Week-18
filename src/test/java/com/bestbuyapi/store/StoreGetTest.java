package com.bestbuyapi.store;

import com.bestbuyapi.testbase.TestBaseStore;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoreGetTest extends TestBaseStore {

    @Test
    public void getAllStoresInfo() {
        Response response = given()
                .when()
                .get(); // get list of all Stores
        response.then().statusCode(200); //to validate statusCode
        response.prettyPrint(); //to print response into console
    }

    @Test
    public void getSingleStoreById(){
        Response response = given()
                .pathParam("id", 11)
                .when()
                .get("{id}"); // get data of single Store by ID
        response.then().statusCode(200); //to validate statusCode
        response.prettyPrint(); //to print response into console
    }
}
