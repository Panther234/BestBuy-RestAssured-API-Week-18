package com.bestbuyapi.categories;

import com.bestbuyapi.model.CategoryPojo;
import com.bestbuyapi.testbase.TestBaseCategories;
import com.bestbuyapi.utils.TestUtils;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CategoriesCRUDTest extends TestBaseCategories {

    static String name = "Electronics" + TestUtils.getRandomName();
    static String id = TestUtils.getRandomName();
    static String categoryId;

    @Test
    public void test001() {

        CategoryPojo categoryPojo = new CategoryPojo(); // create object of ProductPojo class
        categoryPojo.setName(name);
        categoryPojo.setId(id);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(categoryPojo)
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
        categoryId = (String) value.get("id");
    }

    @Test
    public void test003() {
        String p1 = "data.findAll{it.name='";
        String p2 = "'}.get(0)";

        //update
        name = name + "_updated";   //name update

        CategoryPojo categoryPojo = new CategoryPojo(); // create object of ProductPojo class
        categoryPojo.setName(name);
        categoryPojo.setId(id);

        given()
                .header("Content-Type", "application/json")
                .pathParam("categoryId", categoryId)
                .body(categoryPojo)
                .when()
                .put("/{categoryId}")
                .then().log().all().statusCode(200);

        //verify that response through id with after extract by Name
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

    //delete new added Categories record and verify that record deleted successfully
    @Test
    public void test004(){

        given()
                .pathParam("categoryId", categoryId)
                .when()
                .delete("/{categoryId}")
                .then()
                .statusCode(200);

        given()
                .pathParam("categoryId", categoryId)
                .when()
                .get("/{categoryId}")
                .then()
                .statusCode(404);
    }

}
