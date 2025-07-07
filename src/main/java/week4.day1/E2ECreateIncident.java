package week4.day1;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import week3.day2.CreateIncidentRequestBodyPojo;

import static io.restassured.RestAssured.*;

public class E2ECreateIncident {
    private String Sys_Id;
    CreateIncidentRequestBodyPojo requestBodyPojo = new CreateIncidentRequestBodyPojo();
    RequestSpecification requestSpecification;
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

    @BeforeClass
    public void setUp(){
        //Take only the major common items and then only add them in requestSpecification
        requestSpecification = requestSpecBuilder.setBaseUri("https://dev214398.service-now.com/")
                .setBasePath("api/now/table/{tableName}")
                .setAuth(basic("admin","gz^1@wDVaL3B"))
                .addPathParam("tableName","incident").build();// convert the RequestSpecBuilder object to RequestSpecification object
    }

    @Test(priority = 1)
    public void createNewRecord(){
        requestBodyPojo.setShort_description("Create a record using request chaining");
        requestBodyPojo.setDescription("RESTAPI2");
        requestBodyPojo.setActive("true");
        Sys_Id = given()
                .spec(requestSpecification)
               .log()
               .all()
               .body(requestBodyPojo)
               .contentType(ContentType.JSON)
               .when()
               .post()
               .then()
               .log()
               .all()
               .assertThat()
               .statusCode(201)
               .statusLine(Matchers.containsString("Created"))
               .extract()
               .jsonPath()
               .getString("result.sys_id");
    }

    @Test(priority = 2)
    public void retriveRecord(){
        given()
                .spec(requestSpecification)
                .log()
                .all()
                .when()
                .get("/{sys_id}", Sys_Id)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .statusLine(Matchers.containsString("OK"))
                .contentType(ContentType.JSON)
                .body("result.sys_id", Matchers.equalTo(Sys_Id))
                .body("result.description", Matchers.equalTo("RESTAPI2"));
    }

    @Test(priority = 3)
    public void updateRecord(){
        given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .spec(requestSpecification)
                .when()
                .put("/{sys_id}", Sys_Id)
                .then()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(200)
                .statusLine(Matchers.containsString("OK"))
                .body("result.sys_id",Matchers.equalTo(Sys_Id));
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
