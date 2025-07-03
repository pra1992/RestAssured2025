package week3.day1;

import io.restassured.RestAssured;

import java.util.HashMap;
import java.util.Map;

public class SettingUpRequestWithMultiplePathQueryParams {

    public static void main(String[] args){
        Map<String,Object> pathParams = new HashMap<>();
        Map<String,Object> queryParams = new HashMap<>();

        pathParams.put("tableName", "incident");
        pathParams.put("sysid","798bb54083622210557ae5d0deaad379");

        queryParams.put("sysparm_fields","task_effective_number,number,category");
        queryParams.put("sysparm_query_no_domain","true");
        queryParams.put("category","inquiry");

        RestAssured.given()
                .baseUri("https://dev214398.service-now.com/")
                .basePath("api/now/table/{tableName}/{sysid}")
                .pathParams(pathParams)
                .queryParams(queryParams)
                .auth()
                .basic("admin   ","gz^1@wDVaL3B")
                .log()
                .all()
                .when()
                .get()
                .then()
                .assertThat()
                .statusCode(200)
                .log()
                .all();
    }
}
