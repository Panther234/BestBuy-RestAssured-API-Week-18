package com.bestbuyapi.store;

import com.bestbuyapi.testbase.TestBaseStore;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoreDeleteTest extends TestBaseStore {

    @Test
    public void deleteStoreData() {

        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id", 8923) // add store id
                .when()
                .delete("/{id}"); //using Delete
        response.then().statusCode(200); // statusCode 200
        response.prettyPrint();
    }

}
