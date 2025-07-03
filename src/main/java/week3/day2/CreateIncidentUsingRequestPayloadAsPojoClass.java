package week3.day2;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;

public class CreateIncidentUsingRequestPayloadAsPojoClass {

    public static void main(String[] args){
        CreateIncidentRequestBodyPojo requestBodyPojo = new CreateIncidentRequestBodyPojo();
        requestBodyPojo.setShort_description("Create Incident Using POJO Class second Incident");
        requestBodyPojo.setDescription("SecondBodyAsPojo");
        requestBodyPojo.setActive("true");

        RestAssured.given()
                .baseUri("https://dev214398.service-now.com/")
                .basePath("api/now/table/{tableName}")
                .pathParam("tableName", "incident")
                .auth()
                .basic("admin", "gz^1@wDVaL3B")
                .body(requestBodyPojo)
                .contentType("application/JSON")
                .log()
                .all()
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(201)
                .log()
                .all()
                .statusLine(Matchers.containsString("Created"));
    }

}
