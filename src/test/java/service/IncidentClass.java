package service;

import design.ResponseAPI;
import implementations.RequestImpl;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import week3.day2.CreateIncidentRequestBodyPojo;


public class IncidentClass extends RequestImpl {
    CreateIncidentRequestBodyPojo requestBodyPojo = new CreateIncidentRequestBodyPojo();

    public ResponseAPI createNewIncident(RequestSpecification requestSpecification, Object Payload){
//           return given()
//                .spec(requestSpecification)
//                .log()
//                .all()
//                .body(Payload)
//                .contentType(ContentType.JSON)
//                .when()
//                .post();

        return post(requestSpecification,Payload);
    }

    public ResponseAPI retriveAllIncidents(RequestSpecification requestSpecification){
//        return given()
//                .spec(requestSpecification)
//                .log()
//                .all()
//                .when()
//                .get();
        return get(requestSpecification);
    }

    public ResponseAPI retriveAnIncident(RequestSpecification requestSpecification, String sys_id){
//        return given()
//                .spec(requestSpecification)
//                .log()
//                .all()
//                .when()
//                .get("/{sys_id}", sys_id);
        return get(requestSpecification,sys_id);
    }

    public ResponseAPI updateIncident(RequestSpecification requestSpecification, String sys_id, Object Payload){
//        return given()
//                .contentType(ContentType.JSON)
//                .log()
//                .all()
//                .spec(requestSpecification)
//                .body(Payload)
//                .when()
//                .put("/{sys_id}", sys_id);
        return put(requestSpecification, sys_id, Payload);
    }

    public ResponseAPI partiallyUpdateIncident(RequestSpecification requestSpecification, String sys_id, Object Payload){
//        return given().spec(requestSpecification)
//                .contentType(ContentType.JSON)
//                .body(Payload)
//                .log()
//                .all()
//                .when()
//                .patch("/{sys_id}", sys_id);
        return put(requestSpecification,sys_id,Payload);
    }

    public Response deleteIncident(RequestSpecification requestSpecification, String sys_id){
//        return given()
//                .log()
//                .all().spec(requestSpecification)
//                .when()
//                .delete("/{sys_id}", sys_id);
        return delete(requestSpecification,sys_id);
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

    public void validateResponse(Response response, int StatusCode, String StatusMessage){
        response.then()
                .log()
                .all()
                .assertThat()
                .statusCode(StatusCode)
                .statusLine(Matchers.containsString(StatusMessage));
    }

    public String extractValueFromResponse(Response response, String jsonpath){
        return response.then().extract()
                .jsonPath()
                .getString(jsonpath);
    }

    public void validateResponseBody(Response response, String jsonPath, String expectedValue){
        response.then().assertThat().body(jsonPath, Matchers.equalTo(expectedValue));
    }

}

