package testcase;

import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import service.IncidentClass;
import week3.day2.CreateIncidentRequestBodyPojo;

import static io.restassured.RestAssured.basic;

public class BaseClass {

    protected static String Sys_Id= null;
    protected static Response response=null;
    protected  CreateIncidentRequestBodyPojo requestBodyPojo = new CreateIncidentRequestBodyPojo();
    protected RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    protected IncidentClass incidentClass = new IncidentClass();

//    public void setUp(){
//        //Take only the major common items and then only add them in requestSpecification
//        requestSpecification = requestSpecBuilder.setBaseUri("https://dev214398.service-now.com/")
//                .setBasePath("api/now/table/{tableName}")
//                .setAuth(basic("admin","gz^1@wDVaL3B"))
//                .addHeader("Content-Type", "application/json")
//                .addPathParam("tableName","incident").build();// convert the RequestSpecBuilder object to RequestSpecification objec
//    }
}
