package com.bestbuyapi.product;

import com.bestbuyapi.model.ProductPojo;
import com.bestbuyapi.testbase.TestBaseProduct;
import com.bestbuyapi.utils.TestUtils;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ProductCRUDTest extends TestBaseProduct {

    static String name = "Apple - MacBook Pro" + TestUtils.getRandomName();
    static String type = "Laptop" + TestUtils.getRandomName();
    static Double price = 1200.99;
    static Integer shipping = 10;
    static String upc = "012333424000";
    static String description = "Apple iMac 21.5 All-in-One" + TestUtils.getRandomValue();
    static String manufacturer = "Apple";
    static String model = TestUtils.getRandomName();
    static String url = "https://www.bestbuy.com/site/apple-macbook-pro-13-display-with-touch-bar-intel-core-i5-16gb-memory-512gb-ssd-space-gray/6287726.p?skuId=6287726";
    static String image = "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6287/6287726_sd.jpg;maxHeight=640;maxWidth=550";
    static int productId;

    @Test
    public void test001() {

        ProductPojo productPojo = new ProductPojo(); // create object of ProductPojo class
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(productPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void test002() {
        String p1 = "data.findAll{it.name='";
        String p2 = "'}.get(0)";

        HashMap value =
                given()
                        .when()
                        .get()
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + name + p2);
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(value));
        productId = (int) value.get("id");
    }

    @Test
    public void test003() {
        String p1 = "data.findAll{it.name='";
        String p2 = "'}.get(0)";

        //update
        name = name + "_updated";   //name update
        type = type + "_updated";//type update

        ProductPojo productPojo = new ProductPojo(); // create object of ProductPojo class
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);


        given()
                .header("Content-Type", "application/json")
                .pathParam("productId", productId)
                .body(productPojo)
                .when()
                .put("/{productId}")
                .then().log().all().statusCode(200);

        //verify that response through id with after  extract by Name
        HashMap<String, Object> value =

                given()
                        .when()
                        .get()
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + name + p2);
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(value));
    }

    //delete new added product record and verify that record deleted successfully
    @Test
    public void test004(){

        given()
                .pathParam("productId", productId)
                .when()
                .delete("/{productId}")
                .then()
                .statusCode(200);

        given()
                .pathParam("productId", productId)
                .when()
                .get("/{productId}")
                .then()
                .statusCode(404);
    }

}


