package week3.day1;

import static io.restassured.RestAssured.*;

public class MyFirstRestAssuredCode {
    public static void main(String[] args){
        //prerequisite
        given().baseUri("https://dev214398.service-now.com/").basePath("api/now/table").auth().basic("admin", "gz^1@wDVaL3B").accept("application/JSON")
               //Action or CRUD Operation
                .when().log().all().get("/incident")
                //Response
                .then().assertThat().statusCode(200).log().all();
    }
}
