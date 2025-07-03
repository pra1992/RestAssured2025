package week3.day2;

import static io.restassured.RestAssured.*;

public class CreateNewIncidentPostPayloadString {

    static String payload= "{\n" +
            "    \"short_description\": \"Creating a table\",\n" +
            "    \"description\": \"API\",\n" +
            "    \"active\": \"true\"\n" +
            "}";
    public static void main(String[] args){
        given().baseUri("https://dev214398.service-now.com/")
                .basePath("api/now/table/{tableName}")
                .log()
                .all()
                .auth()
                .basic("admin","gz^1@wDVaL3B")
                .pathParam("tableName","incident")
                .contentType("application/JSON")
                .when()
                .body(payload)
                .post()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201);
    }
}
