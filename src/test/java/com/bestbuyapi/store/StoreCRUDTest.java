package com.bestbuyapi.store;

import com.bestbuyapi.model.StorePojo;
import com.bestbuyapi.testbase.TestBaseStore;
import com.bestbuyapi.utils.TestUtils;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class StoreCRUDTest extends TestBaseStore {

    static String name = "Pinner" + TestUtils.getRandomValue();
    static String type = "SuperStore" + TestUtils.getRandomValue();
    static String address = TestUtils.getRandomValue() + ", Pinner road";
    static String address2 = "Harrow";
    static String city = "London";
    static String state = "London";
    static String zip = "123456";
    static Double lat = 45.126179;
    static Double lng = -93.261429;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int storeId;


    // create new store and get StoreId from response and store in storeId variable
    @Test
    public void test001() {

        StorePojo storePojo = new StorePojo(); // create object of StorePojo class
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(storePojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
        String responseBody = response.getBody().asString();
        storeId = response.jsonPath().get("id");
        System.out.println("StoreID is in Test 001: " + storeId);


    }

    //Get Store details of new added Store in Test001 using StoreId variable
    @Test
    public void test002() {

        Response response = given()
                .pathParam("storeID", storeId)
                .when()
                .get("/{storeID}"); // get data of single Store by ID
        response.then().statusCode(200); //to validate statusCode
        response.prettyPrint(); //to print response into console

//Get Store id of new added Store in Test001 and store in StoreId variable
//        String p1 = "data.findAll{it.id='";
//        String p2 = "'}.get(0)";
//
//
//        HashMap value =
//                given()
//                        .when()
//                        .get()
//                        .then()
//                        .statusCode(200)
//                        .extract()
//                        .path(p1 + storeId + p2);
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(value));
//        storeId = (int) value.get("id");
    }

    //update name,type,address using storeId -- (PUT) method and verify that its updated
    @Test
    public void test003() {
        String p1 = "data.findAll{it.id='";
        String p2 = "'}.get(0)";

        //update
        name = name + "_updated";   //name update
        type = type + "_updated";//type update
        address = address + "_updated";//address update
        address2 = address2 + "_updated";//address update

        StorePojo storePojo = new StorePojo(); // create object of StorePojo class
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        given()
                .header("Content-Type", "application/json")
                .pathParam("storeID", storeId)
                .body(storePojo)
                .when()
                .put("/{storeID}") // get data of single Store by ID
                .then().statusCode(200); //to validate statusCode

//        //verify that response through id with after  extract by Name
        HashMap<String, Object> value =

                given()
                        .when()
                        .get()
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + storeId + p2);
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(value));
    }

    //delete new added store record and verify that record deleted successfully
    @Test
    public void test004() {

        given()
                .pathParam("storeID", storeId)
                .when()
                .delete("/{storeID}")
                .then()
                .statusCode(200);
        System.out.println();
        given()
                .pathParam("storeID", storeId)
                .when()
                .get("/{storeID}")
                .then()
                .statusCode(404);
    }
}