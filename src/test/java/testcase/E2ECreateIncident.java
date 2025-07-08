package testcase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.IncidentClass;
import week3.day2.CreateIncidentRequestBodyPojo;

import static io.restassured.RestAssured.basic;
import static io.restassured.RestAssured.given;

public class E2ECreateIncident {
    private String Sys_Id;
    public static Response response=null;
    CreateIncidentRequestBodyPojo requestBodyPojo = new CreateIncidentRequestBodyPojo();
    RequestSpecification requestSpecification;
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    IncidentClass incidentClass = new IncidentClass();
    @BeforeClass
    public void setUp(){
        //Take only the major common items and then only add them in requestSpecification
        requestSpecification = requestSpecBuilder.setBaseUri("https://dev214398.service-now.com/")
                .setBasePath("api/now/table/{tableName}")
                .setAuth(basic("admin","gz^1@wDVaL3B"))
                .addPathParam("tableName","incident").build();// convert the RequestSpecBuilder object to RequestSpecification object
    }

    public CreateIncidentRequestBodyPojo payloadSetup(){
        requestBodyPojo.setShort_description("Create record using service model");
        requestBodyPojo.setDescription("Create record using service model");
        requestBodyPojo.setActive("true");
        return requestBodyPojo;
    }

    @Test(priority = 1)
    public void createNewRecord(){
       response = incidentClass.createNewIncident(requestSpecification, this.payloadSetup());
       incidentClass.validateResponse(response, 201, "Created", "application/json");
        Sys_Id = incidentClass.extractValueFromResponse(response, "result.sys_id");
    }

    @Test(priority = 2)
    public void retriveARecord(){
       response= incidentClass.retriveAnIncident(requestSpecification, Sys_Id);
       incidentClass.validateResponse(response, 200, "OK", "application/json");
    }

    @Test(priority = 3)
    public void updateRecord(){
        incidentClass.updateIncident(requestSpecification, Sys_Id, this.payloadSetup());
    }

    @Test(priority = 4)
    public void partiallyUpdateRecord(){
        requestBodyPojo.setShort_description("Patch update a record using request chaining");
        requestBodyPojo.setDescription("RESTAPI4");
        requestBodyPojo.setActive("true");
        given().spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(requestBodyPojo)
                .log()
                .all()
                .when()
                .patch("/{sys_id}", Sys_Id)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .statusLine(Matchers.containsString("OK"))
                .body("result.sys_id", Matchers.equalTo(Sys_Id))
                .body("result.description", Matchers.equalTo("RESTAPI4"));
    }
    @Test(priority = 5)
    public void deleteIncident(){
        given()
                .log()
                .all().spec(requestSpecification)
                .when()
                .delete("/{sys_id}", Sys_Id)
                .then()
                .log()
                .all()
                .statusCode(204)
                .statusLine(Matchers.containsString("No Content"));
    }
}
