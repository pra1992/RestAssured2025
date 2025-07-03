package week3.day1;

import io.restassured.RestAssured;

import java.util.HashMap;
import java.util.Map;

public class SettingPathVariable {
    public static void main(String[] args){
    Map<String,Object> pathParams = new HashMap<>();
    pathParams.put("tableName","incident");
    pathParams.put("sysid","f424428083a22210557ae5d0deaad397");
      RestAssured.
              given().
              baseUri("https://dev214398.service-now.com/").
              basePath("api/now/table/{tableName}/{sysid}")
              .pathParams(pathParams)
              .auth()
              .basic("admin", "gz^1@wDVaL3B")
              .when()
              .get()
              .then()
              .assertThat()
              .statusCode(200)
              .log()
              .all();



    }
}
