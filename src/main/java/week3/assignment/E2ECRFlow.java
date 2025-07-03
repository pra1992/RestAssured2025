package week3.assignment;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class E2ECRFlow {
    private static String Sys_id;
    ChangeRequestInputPayloadPojo changeRequestInputPayloadPojo = new ChangeRequestInputPayloadPojo();
    @Test(priority = 1)
    public void createCR(){
        changeRequestInputPayloadPojo.setShort_description("Creating the CR");
        changeRequestInputPayloadPojo.setDescription("CR-Desc");
        changeRequestInputPayloadPojo.setActive("true");
      Sys_id = given()
                .log().ifValidationFails()
                .baseUri("https://dev214398.service-now.com/")
                .basePath("api/now/table/{tableName}")
                .pathParam("tableName","change_request")
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin","gz^1@wDVaL3B")
                .body(changeRequestInputPayloadPojo)
                .when()
                .post()
                .then()
                .log()
                .ifValidationFails()
                .assertThat()
                .statusCode(201)
                .statusLine(Matchers.containsString("Created"))
                .contentType(ContentType.JSON)
                .extract()
                .jsonPath()
                .getString("result.sys_id");
    }

    @Test(priority = 2)
    public void retriveCR(){
        given()
                .log()
                .ifValidationFails()
                .baseUri("https://dev214398.service-now.com/")
                .basePath("api/now/table/{tableName}/{sys_id}")
                .pathParam("tableName","change_request")
                .pathParam("sys_id",Sys_id)
                .queryParam("sys_id")
                .auth()
                .basic("admin","gz^1@wDVaL3B")
                .when()
                .get()
                .then()
                .log()
                .ifValidationFails()
                .assertThat()
                .statusCode(200)
                .statusLine(Matchers.containsString("OK"))
                .contentType(ContentType.JSON)
                .body("result.sys_id", Matchers.equalTo(Sys_id));
    }

    @Test(priority = 3)
    public void putCR(){
        changeRequestInputPayloadPojo.setShort_description("Updating the CR");
        changeRequestInputPayloadPojo.setDescription("Update CR-Desc");
        changeRequestInputPayloadPojo.setActive("true");
        given()
                .baseUri("https://dev214398.service-now.com")
                .basePath("/api/now/table/{tableName}/{sys_id}")
                .pathParam("tableName","change_request")
                .pathParam("sys_id",Sys_id)
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin","gz^1@wDVaL3B")
                .log()
                .all()
                .when()
                .put()
                .then()
                .log()
                .ifValidationFails()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(200)
                .statusLine(Matchers.containsString("OK"))
                .body("result.sys_id",Matchers.equalTo(Sys_id));
//                .body("result.description", Matchers.equalTo("Update CR-Desc"));

    }

    @Test(priority = 4)
    public void patchCR(){
        changeRequestInputPayloadPojo.setShort_description("Patching the CR");
        changeRequestInputPayloadPojo.setDescription("Patching CR-Desc");
        changeRequestInputPayloadPojo.setActive("true");
        given()
                .baseUri("https://dev214398.service-now.com")
                .basePath("/api/now/table/{tableName}/{sys_id}")
                .pathParam("tableName","change_request")
                .pathParam("sys_id",Sys_id)
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin","gz^1@wDVaL3B")
                .log()
                .all()
                .when()
                .patch()
                .then()
                .log()
                .ifValidationFails()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(200)
                .statusLine(Matchers.containsString("OK"))
                .body("result.sys_id",Matchers.equalTo(Sys_id));
//                .body("result.description", Matchers.equalTo("Patching CR-Desc"));
    }

    @Test(priority = 5)
    public void deleteCR(){
        given()
                .baseUri("https://dev214398.service-now.com")
                .basePath("/api/now/table/{tableName}/{sys_id}")
                .pathParam("tableName","change_request")
                .pathParam("sys_id",Sys_id)
                //.contentType(ContentType.JSON)
                .auth()
                .basic("admin","gz^1@wDVaL3B")
                .log()
                .all()
                .when()
                .delete()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(204)
                .statusLine(Matchers.containsString("No Content"));
    }
}
