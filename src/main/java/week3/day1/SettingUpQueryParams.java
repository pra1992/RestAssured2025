package week3.day1;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;

public class SettingUpQueryParams {
    public static void main(String[] args){
        RestAssured.given().baseUri("https://dev214398.service-now.com")
                .basePath("/api/now/table")
                .pathParam("tableName", "incident")
                .queryParam("sysparm_limit", "1")
                .auth().basic("admin", "gz^1@wDVaL3B")
                .header("Accept","application/XML")
                .log()
                .all()
                .when()
                .get("/{tableName}")
                .then()
                .assertThat()
                .statusCode(200)
                .time(Matchers.lessThanOrEqualTo(5000L))
                .log()
                .all();

    }
}
