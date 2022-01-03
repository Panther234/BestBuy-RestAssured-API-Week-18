package com.bestbuyapi.product;

import com.bestbuyapi.model.ProductPojo;
import com.bestbuyapi.testbase.TestBaseProduct;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductPutTest extends TestBaseProduct {

    @Test
    public void updateProduct() {

        ProductPojo productPojo = new ProductPojo(); // create object of ProductPojo class
        productPojo.setName("Apple - MacBook Pro");
        productPojo.setType("Laptop"); // laptop
        productPojo.setPrice(1000.99); //price change
        productPojo.setShipping(10);
        productPojo.setUpc("012333424000");
        productPojo.setDescription("2021 Apple iMac 21.5 All-in-One"); //2021
        productPojo.setManufacturer("Apple");
        productPojo.setModel("86030105");
        productPojo.setUrl("https://www.bestbuy.com/site/apple-macbook-pro-13-display-with-touch-bar-intel-core-i5-16gb-memory-512gb-ssd-space-gray/6287726.p?skuId=6287726");
        productPojo.setImage("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6287/6287726_sd.jpg;maxHeight=640;maxWidth=550");

        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id", 9999693)
                .body(productPojo)
                .when()
                .put("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }


}


