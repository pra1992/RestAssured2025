package week3.assignment;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class RetrieveRecordUsingMultipleQueryParamater {
      private static String sys_id= null;
      Map<String,Object> queryParam = new HashMap<>();
    Map<String,Object> pathParam = new HashMap<>();
    ChangeRequestInputPayloadPojo cr = new ChangeRequestInputPayloadPojo();
    @Test(priority = 1)
    public void createCR(){
        cr.setShort_description("Creating the CR for Query Parameter");
        cr.setDescription("CR Query Parameter");
        cr.setActive("true");
        sys_id=given().log().all()
                .baseUri("https://dev214398.service-now.com/")
                .basePath("api/now/table/{tableName}")
                .pathParam("tableName", "change_request")
                .auth()
                .basic("admin","gz^1@wDVaL3B")
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201)
                .statusLine(Matchers.containsString("Created"))
                .contentType(ContentType.JSON)
                .extract()
                .jsonPath()
                .getString("result.sys_id");
    }

    @Test(priority = 2)
    public void retrieveCR(){
        pathParam.put("tableName", "change_request");
        pathParam.put("sys_id", sys_id);
        queryParam.put("sysparm_exclude_reference_link", "True");
        queryParam.put("sysparm_fields", "description,sys_id");
        given().log()
                .all()
                .baseUri("https://dev214398.service-now.com/")
                .basePath("api/now/table/{tableName}/{sys_id}")
                .pathParams(pathParam)
                .queryParams(queryParam)
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin", "gz^1@wDVaL3B")
                .when()
                .get()
                .then()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(200)
                .statusLine(Matchers.containsString("OK"))
                .body("result.sys_id", Matchers.equalTo(sys_id));
    }

}
