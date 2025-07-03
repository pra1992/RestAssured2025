package week3.day2;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class E2ECreateIncident {
    private String Sys_Id;
    CreateIncidentRequestBodyPojo requestBodyPojo = new CreateIncidentRequestBodyPojo();

    @Test(priority = 1)
    public void createNewRecord(){
        requestBodyPojo.setShort_description("Create a record using request chaining");
        requestBodyPojo.setDescription("RESTAPI2");
        requestBodyPojo.setActive("true");
        Sys_Id = given()
               .log()
               .all()
               .baseUri("https://dev214398.service-now.com/")
               .basePath("api/now/table/{tableName}")
               .pathParam("tableName","incident")
               .auth()
               .basic("admin","gz^1@wDVaL3B")
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
                .baseUri("https://dev214398.service-now.com")
                .basePath("/api/now/table/{tableName}/{sys_id}")
                .pathParam("tableName","incident")
                .pathParam("sys_id",Sys_Id)
                .auth()
                .basic("admin","gz^1@wDVaL3B")
                .log()
                .all()
                .when()
                .get()
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
                .baseUri("https://dev214398.service-now.com")
                .basePath("/api/now/table/{tableName}/{sys_id}")
                .pathParam("tableName","incident")
                .pathParam("sys_id",Sys_Id)
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin","gz^1@wDVaL3B")
                .log()
                .all()
                .when()
                .put()
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
        given()
                .baseUri("https://dev214398.service-now.com/")
                .basePath("api/now/table/{tableName}/{sys_id}")
                .pathParam("tableName","incident")
                .pathParam("sys_id",Sys_Id)
                .auth()
                .basic("admin","gz^1@wDVaL3B")
                .contentType(ContentType.JSON)
                .body(requestBodyPojo)
                .log()
                .all()
                .when()
                .patch()
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
                .all()
                .baseUri("https://dev214398.service-now.com/")
                .basePath("api/now/table/{tableName}/{sys_id}")
                .pathParam("tableName","incident")
                .pathParam("sys_id",Sys_Id)
                .auth()
                .basic("admin","gz^1@wDVaL3B")
                .when()
                .delete()
                .then()
                .log()
                .all()
                .statusCode(204)
                .statusLine(Matchers.containsString("No Content"));
    }
}
