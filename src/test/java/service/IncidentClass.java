package service;

import design.ResponseAPI;
import implementations.RestAssuredRequestImpl;
import implementations.RestAssuredResponseImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import week3.day2.CreateIncidentRequestBodyPojo;


public class IncidentClass extends RestAssuredRequestImpl {
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
        return post(requestSpecification, Payload);

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
        return get(requestSpecification,"/"+sys_id);
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
        return put(requestSpecification,"/"+ sys_id, Payload);
    }

    public ResponseAPI partiallyUpdateIncident(RequestSpecification requestSpecification, String sys_id, Object Payload){
//        return given().spec(requestSpecification)
//                .contentType(ContentType.JSON)
//                .body(Payload)
//                .log()
//                .all()
//                .when()
//                .patch("/{sys_id}", sys_id);
        return put(requestSpecification,"/"+sys_id,Payload);
    }

    public ResponseAPI deleteIncident(RequestSpecification requestSpecification, String sys_id){
//        return given()
//                .log()
//                .all().spec(requestSpecification)
//                .when()
//                .delete("/{sys_id}", sys_id);
        return delete(requestSpecification,"/"+sys_id);
    }

    public void validateResponse(ResponseAPI response, int StatusCode, String StatusMessage, String ContentType){
//         response.then()
//                 .log()
//                 .all()
//                 .assertThat()
//                 .statusCode(StatusCode)
//                 .statusLine(Matchers.containsString(StatusMessage))
//                 .contentType(ContentType);
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(StatusCode));
        MatcherAssert.assertThat(response.getStatusMessage(), Matchers.equalTo(StatusMessage));
        MatcherAssert.assertThat(response.getContentType(), Matchers.equalTo(ContentType));
    }

    public void validateResponse(ResponseAPI response, int StatusCode, String StatusMessage){
//        response.then()
//                .log()
//                .all()
//                .assertThat()
//                .statusCode(StatusCode)
//                .statusLine(Matchers.containsString(StatusMessage));
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(StatusCode));
        MatcherAssert.assertThat(response.getStatusMessage(), Matchers.equalTo(StatusMessage));
    }

    public String extractValueFromResponse(ResponseAPI response, String jsonpath){
//        return response.then().extract()
//                .jsonPath()
//                .getString(jsonpath);
        JsonPath json = new JsonPath(response.getBody());
        return json.getString(jsonpath);
    }

    public void validateResponseBody(ResponseAPI response, String jsonPath, String expectedValue){
       // response.then().assertThat().body(jsonPath, Matchers.equalTo(expectedValue));
        JsonPath json = new JsonPath(response.getBody());// json is called validatable response body
        MatcherAssert.assertThat(json.getString(jsonPath), Matchers.equalTo(expectedValue));
    }

}

