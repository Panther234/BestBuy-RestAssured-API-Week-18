package com.bestbuyapi.extractionresponsedata;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SearchJsonPath {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores") // get all stores data
                .then().statusCode(200);// validate status code
//              .then().log().all();//if you want to print all log in console
    }

    @Test
    public void test001() {
        //     Extract the limit
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of Limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test002() {
        //    Extract the total
        int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of Total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test003() {
        //    Extract the name of 5th store
        String name = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Name of 5th store : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test004() {
        //  Extract the names of all the store
        List<String> storeNames = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Names of all stores : " + new GsonBuilder().setPrettyPrinting().create().toJson(storeNames));
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test005() {
        //   Extract the storeId of all the store
        List<String> storeIds = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ID of all stores : " + new GsonBuilder().setPrettyPrinting().create().toJson(storeIds));
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test006() {
        //   Print the size of the data list
        List<Integer> list = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of Data List : " + list.size());
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test007() {
        //  Get all the value of the store where store name = St Cloud
        List<String> values = response.extract().path("data.findAll{it.name=='St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Values of store name St Cloud : " + new GsonBuilder().setPrettyPrinting().create().toJson(values));
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test008() {
        //  Get the address of the store where store name = Rochester
        String address = response.extract().path("data[8].address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Address of Store name Rochester : " + new GsonBuilder().setPrettyPrinting().create().toJson(address));
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test009() {
        //  Get all the services of 8th store
        List<LinkedHashMap> services = response.extract().path("data[7].services");
        System.out.println("------- -----------StartingTest---------------------------");
        System.out.println("Services of 8th Store : " + new GsonBuilder().setPrettyPrinting().create().toJson(services));
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test010() {
        //  Get storeservices of the store where service name = Windows Store
        List<LinkedHashMap> services = response.extract().path("data.findAll{it.name='Windows Store'}.services");
        String jsonPath = "$.*.services[?(@.name=='Windows Store')].storeservices"; // JSON Path for locating storeservices where service name is Windows Store
        System.out.println("-----------------Starting Test---------------------------");
        System.out.println("Store Service of the store where service name = Windows Store : " + new GsonBuilder().setPrettyPrinting().create().toJson(services));
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test011() {
        // Get all the storeId of all the store
        List<String> storeId = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all store storeId : " + new GsonBuilder().setPrettyPrinting().create().toJson(storeId));
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test012() {
        //Get id of all the store
        List<String> id = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all store Id : " + new GsonBuilder().setPrettyPrinting().create().toJson(id));
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test013() {
        // Find the store names Where state = ND
        List<LinkedHashMap> services = response.extract().path("data.findAll{it.state=='ND'}.name");
        System.out.println("------- -----------StartingTest---------------------------");
        System.out.println("Name of the store where state = ND : " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test014() {
        // Find the Total number of services for the store where store name = Rochester
        List<List<Integer>> totalServices = response.extract().path("data.findAll{it.name=='Rochester'}.services.id");
        System.out.println("------- -----------StartingTest---------------------------");
        System.out.println("Total Services of the store where name = Rochester : " + totalServices.get(0).size());
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test015() {
        //Find the createdAt for all services whose name = “Windows Store”
        List<List<LinkedHashMap>> createdAt = response.extract().path("data.findAll{it.name!=''}");
        String jsonPath = "$.*.services[?(@.name=='Windows Store')].createdAt"; // JSON Path for locating services where name is Windows Store
        System.out.println("-----------------Starting Test---------------------------");
        System.out.println("CreatedAt of the store where name = Windows Store : " + new GsonBuilder().setPrettyPrinting().create().toJson(JsonPath.read(new Gson().toJson(createdAt, List.class), jsonPath), List.class));
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test016() {
        //Find the name of all services Where store name = “Fargo”
        List<LinkedHashMap> servicesName = response.extract().path("data.findAll{it.name=='Fargo'}.services.name");
        System.out.println("-----------------Starting Test---------------------------");
        System.out.println("Name of all services Where store name = Fargo : " + new GsonBuilder().setPrettyPrinting().create().toJson(servicesName));
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test017() {
        //Find the zip of all the store
        List<Integer> zip = response.extract().path("data.zip");
        System.out.println("-----------------Starting Test---------------------------");
        System.out.println("Zip of all the store : " + new GsonBuilder().setPrettyPrinting().create().toJson(zip));
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test018() {
        //Find the zip of store name = Roseville
        String zip = response.extract().path("data[2].zip");
        System.out.println("-----------------Starting Test---------------------------");
        System.out.println("Zip of store name = Roseville : " + zip);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test019() {
        // Find the storeservices details of the service name = Magnolia Home Theater
        List<LinkedHashMap> storeServices = response.extract().path("data.findAll{it.name!=''}");
        String jsonPath = "$.*.services[?(@.name=='Magnolia Home Theater')].storeservices"; // JSON Path for locating storeservices where service name is Magnolia Home Theater
        System.out.println("-----------------Starting Test---------------------------");
        System.out.println("StoreServices details of the service name = Magnolia Home Theater : " + new GsonBuilder().setPrettyPrinting().create().toJson(JsonPath.read(new Gson().toJson(storeServices, List.class), jsonPath), List.class));
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test020() {
        //Find the lat of all the stores
        List<String> lat = response.extract().path("data.lat");
        System.out.println("-----------------Starting Test---------------------------");
        System.out.println("Latitude of all the stores : " + new GsonBuilder().setPrettyPrinting().create().toJson(lat));
        System.out.println("------------------End of Test---------------------------");
    }
}
