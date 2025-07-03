package week3.assignment;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class CreateChangeRequestUsingFile {
    @Test
    public void createChangeRequestUsingFile(){
          given()
             .log()
             .all()
             .baseUri("https://dev214398.service-now.com/")
             .basePath("api/now/table/{tableName}")
             .pathParam("tableName", "change_request")
             .contentType(ContentType.JSON)
             .auth()
             .basic("admin","gz^1@wDVaL3B")
             .body(new File(".\\src\\main\\resources\\CreateNewCRPayload.json"))
             .when()
                  .post()
                  .then()
                  .log()
                  .all()
                  .assertThat()
                  .statusCode(201)
                  .statusLine(Matchers.containsString("Created"));
    }
}
