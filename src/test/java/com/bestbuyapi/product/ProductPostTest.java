package com.bestbuyapi.product;

import com.bestbuyapi.model.ProductPojo;
import com.bestbuyapi.testbase.TestBaseProduct;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductPostTest extends TestBaseProduct {

    @Test
    public void createNewProduct() {

        ProductPojo productPojo = new ProductPojo(); // create object of ProductPojo class
        productPojo.setName("Apple - MacBook Pro");
        productPojo.setType("PC");
        productPojo.setPrice(1299.99);
        productPojo.setShipping(10);
        productPojo.setUpc("012333424000");
        productPojo.setDescription("2020 Apple iMac 21.5 All-in-One");
        productPojo.setManufacturer("Apple");
        productPojo.setModel("86030105");
        productPojo.setUrl("https://www.bestbuy.com/site/apple-macbook-pro-13-display-with-touch-bar-intel-core-i5-16gb-memory-512gb-ssd-space-gray/6287726.p?skuId=6287726");
        productPojo.setImage("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6287/6287726_sd.jpg;maxHeight=640;maxWidth=550");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(productPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }
}


