package testcase;

import design.ResponseAPI;
import implementations.RestAssuredRequestImpl;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import service.IncidentClass;
import week3.day2.CreateIncidentRequestBodyPojo;

import static io.restassured.RestAssured.basic;

public class BaseClass extends IncidentClass {

    protected static String Sys_Id= null;
    protected static ResponseAPI response=null;
    protected  CreateIncidentRequestBodyPojo requestBodyPojo = new CreateIncidentRequestBodyPojo();
    protected RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    protected RequestSpecification requestSpecification;

    @BeforeMethod
    public void setUp(){
        //Take only the major common items and then only add them in requestSpecification
        requestSpecification = requestSpecBuilder.setBaseUri("https://dev214398.service-now.com/")
                .setBasePath("api/now/table/{tableName}")
                .setAuth(basic("admin","gz^1@wDVaL3B"))
                .addHeader("Content-Type", "application/json")
                .addPathParam("tableName","incident").build();// convert the RequestSpecBuilder object to RequestSpecification object
    }
}
