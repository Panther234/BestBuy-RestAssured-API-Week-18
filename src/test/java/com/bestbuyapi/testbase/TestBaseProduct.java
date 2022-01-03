package com.bestbuyapi.testbase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBaseProduct {

    //    http://localhost:3030/products
    //          baseURI   |port|basePath

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/products";
    }
}
