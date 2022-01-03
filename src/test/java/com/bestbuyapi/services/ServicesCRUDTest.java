package com.bestbuyapi.services;

import com.bestbuyapi.model.ServicePojo;
import com.bestbuyapi.testbase.TestBaseServices;
import com.bestbuyapi.utils.TestUtils;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ServicesCRUDTest extends TestBaseServices {

    static String name = "Electronics" + TestUtils.getRandomName();
    static Integer serviceId;

    @Test
    public void test001() {
        ServicePojo servicePojo = new ServicePojo(); // create object of ServicePojo class
        servicePojo.setName(name);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(servicePojo)
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
        serviceId = (Integer) value.get("id");
    }

    @Test
    public void test003() {
        String p1 = "data.findAll{it.name='";
        String p2 = "'}.get(0)";

        //update
        name = name + "_updated";   //name update

        ServicePojo servicePojo = new ServicePojo(); // create object of ServicePojo class
        servicePojo.setName(name);

        given()
                .header("Content-Type", "application/json")
                .pathParam("serviceId", serviceId)
                .body(servicePojo)
                .when()
                .put("/{serviceId}")
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

    //delete new added Services record and verify that record deleted successfully
    @Test
    public void test004(){

        given()
                .pathParam("serviceId", serviceId)
                .when()
                .delete("/{serviceId}")
                .then()
                .statusCode(200);

        given()
                .pathParam("serviceId", serviceId)
                .when()
                .get("/{serviceId}")
                .then()
                .statusCode(404);
    }


}
