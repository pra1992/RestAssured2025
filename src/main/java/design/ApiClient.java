package design;

import io.restassured.specification.RequestSpecification;

import java.util.Map;

public interface ApiClient {

    ResponseAPI get(RequestSpecification request);
    ResponseAPI get(RequestSpecification request, String endPoint);
    ResponseAPI post(RequestSpecification request, String endPoint);
    ResponseAPI post(RequestSpecification request, Object Payload);
    ResponseAPI post(RequestSpecification request, String endPoint, Object body);
    ResponseAPI post(RequestSpecification request, Map<String, String> formParams);
    ResponseAPI put(RequestSpecification request, String endPoint, Object body);
    ResponseAPI delete(RequestSpecification request, String endPoint);
}
