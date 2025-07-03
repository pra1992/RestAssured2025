package week3.assignment;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class DataDrivenCreateCRMultiPayloads {

    @DataProvider
     public Object[][] getData(){
        return new Object[][]{
               {"Create CR for Request 1","Desc1","true"},
                {"Create CR for Request 2","Desc2","true"}
       };
    }

    @Test(dataProvider ="getData")
    public void createMultiCR(String short_description, String description, String active){
        ChangeRequestInputPayloadPojo changeRequestInputPayloadPojo = new ChangeRequestInputPayloadPojo();
        changeRequestInputPayloadPojo.setShort_description(short_description);
        changeRequestInputPayloadPojo.setDescription(description);
        changeRequestInputPayloadPojo.setActive(active);
        given()
                .log()
                .all()
                .baseUri("https://dev214398.service-now.com/")
                .basePath("api/now/table/{tableName}")
                .pathParam("tableName", "change_request")
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin","gz^1@wDVaL3B")
                .body(changeRequestInputPayloadPojo)
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
