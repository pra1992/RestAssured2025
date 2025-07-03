package week3.day2;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenApproach {
    //Since JSON has 3 keys which can be considered as 3 columns and 1 entry of single set is considered as a single row, we are using Object[][]

    @DataProvider
    public Object[][] getData(){
        return new Object[][]{
                {"REST","API","true"},
                {"REST1","API1","true"}
        };
    }

    @Test(dataProvider = "getData" )
    public void createIncidents(String short_description, String description, String active){
        CreateIncidentRequestBodyPojo requestBodyPojo = new CreateIncidentRequestBodyPojo();
        requestBodyPojo.setShort_description(short_description);
        requestBodyPojo.setDescription(description);
        requestBodyPojo.setActive(active);

        RestAssured.given()
                .log()
                .all()
                .baseUri("https://dev214398.service-now.com/")
                .basePath("api/now/table/{tableName}")
                .contentType("application/JSON")
                .auth()
                .basic("admin", "gz^1@wDVaL3B")
                .pathParam("tableName","incident")
                .body(requestBodyPojo)
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
