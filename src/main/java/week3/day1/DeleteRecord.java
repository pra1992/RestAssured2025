package week3.day1;

import org.hamcrest.Matchers;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;


public class DeleteRecord {

    public static void main(String[] args){
        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put("tableName","incident");
        pathParams.put("sysid","0611428083a22210557ae5d0deaad392");

        given().log()
                .all()
                .baseUri("https://dev214398.service-now.com/")
                .basePath("api/now/table/{tableName}/{sysid}")
                .pathParams(pathParams)
                .auth()
                .basic("admin","gz^1@wDVaL3B")
                .when()
                .delete()
                .then()
                .assertThat()
                .statusCode(204)
                .statusLine(Matchers.containsString("No Content"))
                .log()
                .all();

    }
}
