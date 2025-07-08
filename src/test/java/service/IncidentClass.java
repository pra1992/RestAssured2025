package service;

import com.sun.net.httpserver.Request;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import week3.day2.CreateIncidentRequestBodyPojo;

import static io.restassured.RestAssured.given;

public class IncidentClass {
    CreateIncidentRequestBodyPojo requestBodyPojo = new CreateIncidentRequestBodyPojo();

    public Response createNewIncident(RequestSpecification requestSpecification, Object Payload){
           return given()
                .spec(requestSpecification)
                .log()
                .all()
                .body(Payload)
                .contentType(ContentType.JSON)
                .when()
                .post();
    }

    public Response retriveAllIncidents(RequestSpecification requestSpecification){
        return given()
                .spec(requestSpecification)
                .log()
                .all()
                .when()
                .get();
    }

    public Response retriveAnIncident(RequestSpecification requestSpecification, String sys_id){
        return given()
                .spec(requestSpecification)
                .log()
                .all()
                .when()
                .get("/{sys_id}", sys_id);
    }

    public Response updateIncident(RequestSpecification requestSpecification, String sys_id, Object Payload){
        return given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .spec(requestSpecification)
                .body(Payload)
                .when()
                .put("/{sys_id}", sys_id);
    }

    public Response partiallyUpdateIncident(RequestSpecification requestSpecification, String sys_id, Object Payload){
        return given().spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(Payload)
                .log()
                .all()
                .when()
                .patch("/{sys_id}", sys_id);
    }

    public Response deleteIncident(RequestSpecification requestSpecification, String sys_id){
        return given()
                .log()
                .all().spec(requestSpecification)
                .when()
                .delete("/{sys_id}", sys_id);
    }

    public void validateResponse(Response response, int StatusCode, String StatusMessage, String ContentType){
         response.then()
                 .log()
                 .all()
                 .assertThat()
                 .statusCode(StatusCode)
                 .statusLine(Matchers.containsString(StatusMessage))
                 .contentType(ContentType);
    }

    public String extractValueFromResponse(Response response, String jsonpath){
        return response.then().extract()
                .jsonPath()
                .getString(jsonpath);
    }

}

