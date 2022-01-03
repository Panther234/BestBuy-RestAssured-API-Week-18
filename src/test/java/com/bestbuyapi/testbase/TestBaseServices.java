package com.bestbuyapi.testbase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBaseServices {

    //    http://localhost:3030/services
    //          baseURI   |port|basePath

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/services ";
    }
}
