package com.bestbuyapi.product;

import com.bestbuyapi.testbase.TestBaseProduct;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductGetTest extends TestBaseProduct {

    @Test
    public void getAllProductInfo() {
        Response response = given()
                .when()
                .get(); // get list of all product
        response.then().statusCode(200); //to validate statusCode
        response.prettyPrint(); //to print response into console
    }
    @Test
    public void getSingleProductById(){
        Response response = given()
                .pathParam("id", 9999693)
                .when()
                .get("{id}"); // get data of single product by id
        response.then().statusCode(200); //to validate statusCode
        response.prettyPrint(); //to print response into console
    }
//    @Test
//    public void getSingleProductByLimit(){
//        Response response = given()
//                .queryParam("limit", 2)
//                .when()
//                .get("{?limit}"); // get data of single product by id
//        response.then().statusCode(200); //to validate statusCode
//        response.prettyPrint(); //to print response into console
//    }

}
