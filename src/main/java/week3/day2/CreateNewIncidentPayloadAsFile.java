package week3.day2;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;

import java.io.File;

public class CreateNewIncidentPayloadAsFile {

    public static void main(String[] args){
        RestAssured
                .given()
                .baseUri("https://dev214398.service-now.com/")
                .basePath("api/now/table/{tableName}")
                .contentType("application/JSON")
                .pathParam("tableName","incident")
                .auth()
                .basic("admin","gz^1@wDVaL3B")
                .contentType("application/JSON")
                .log()
                .all()
                .when()
                .body(new File(".\\src\\main\\resources\\CreateNewIncidentPayload.json"))
                .post()
                .then()
                .assertThat()
                .statusCode(201)
                .statusLine(Matchers.containsString("Created"))
                .log()
                .all();

    }
}
