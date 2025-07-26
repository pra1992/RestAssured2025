package implementations;

import design.ApiClient;
import design.ResponseAPI;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.Map;

public class RestAssuredRequestImpl implements ApiClient {
    private Response response;

    public RequestSpecification given(RequestSpecification requestSpecification){
        return RestAssured.given().spec(requestSpecification);
    }
    @Override
    public ResponseAPI get(RequestSpecification request) {
        return new RestAssuredResponseImpl(given(request).get());
    }

    @Override
    public ResponseAPI get(RequestSpecification request, String endPoint) {
        response = given(request).get(endPoint);
        return new RestAssuredResponseImpl(response);
    }

    @Override
    public ResponseAPI post(RequestSpecification request, String endPoint) {
        response = given(request).contentType(ContentType.JSON).post(endPoint);
        return new RestAssuredResponseImpl(response);
    }

    @Override
    public ResponseAPI post(RequestSpecification request, Object Payload) {
        if(Payload instanceof File){
            File file = (File) Payload;
            response = given(request).contentType(ContentType.JSON).body(file).post();
        } else if (Payload instanceof String) {
            String String= (String) Payload;
            response = given(request).contentType(ContentType.JSON).body(String).post();
        }
        else {
            response = given(request).contentType(ContentType.JSON).body(Payload).post();
        }
        return new RestAssuredResponseImpl(response);
    }

//    @Override
//    public ResponseAPI post(RequestSpecification request, Object Payload) {
//
//    }

    @Override
    public ResponseAPI post(RequestSpecification request, String endPoint, Object body) {
        if(body instanceof File){
            File file = (File) body;
            response = given(request).contentType(ContentType.JSON).body(file).post(endPoint);
        } else if (body instanceof String) {
            String String= (String) body;
            response = given(request).contentType(ContentType.JSON).body(String).post(endPoint);
        }
        else {
            response = given(request).contentType(ContentType.JSON).body(body).post(endPoint);
        }
        return new RestAssuredResponseImpl(response);
    }

    @Override
    public ResponseAPI post(RequestSpecification request, Map<String, String> formParams) {
        return new RestAssuredResponseImpl(
                given(request)
                        .contentType(ContentType.URLENC)
                        .formParams(formParams)
                        .post());
    }

    @Override
    public ResponseAPI put(RequestSpecification request, String endPoint, Object body) {
        if(body instanceof File){
            File file = (File) body;
            response = given(request).contentType(ContentType.JSON).body(file).put(endPoint);
        } else if (body instanceof String) {
            String String= (String) body;
            response = given(request).contentType(ContentType.JSON).body(String).put(endPoint);
        }
        else {
            response = given(request).contentType(ContentType.JSON).body(body).put(endPoint);
        }
        return new RestAssuredResponseImpl(response);
    }

    @Override
    public ResponseAPI delete(RequestSpecification request, String endPoint) {
        return new RestAssuredResponseImpl(given(request).delete(endPoint));
    }
}
