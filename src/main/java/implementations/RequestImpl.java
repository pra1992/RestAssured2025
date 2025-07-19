package implementations;

import design.ApiClient;
import design.ResponseAPI;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseLogSpecification;

import java.util.Map;

public class RequestImpl implements ApiClient {
    private Response response;

    private RequestSpecification given(RequestSpecification request){
      return RestAssured.given().
              spec(request);
            //  .filters(new RestAssuredListener(), new AllureRestAssured());
    }
    @Override
    public ResponseAPI get(RequestSpecification request) {
        return new ResponseImpl(given(request).get());
    }

    @Override
    public ResponseAPI get(RequestSpecification request, String endPoint) {
       response = given(request).get(endPoint);
       return  new ResponseImpl(response);
    }

    @Override
    public ResponseAPI post(RequestSpecification request, String endPoint) {
        response = given(request).contentType(ContentType.JSON).post(endPoint);
        return new ResponseImpl(response);
    }

    @Override
    public ResponseAPI post(RequestSpecification request, Object Payload) {
        return new ResponseImpl(given(request).contentType(ContentType.JSON).post());
    }

    @Override
    public ResponseAPI post(RequestSpecification request, String endPoint, Object body) {
        response = given(request).contentType(ContentType.JSON).body(body).post(endPoint);
        return new ResponseImpl(response);
    }

    @Override
    public ResponseAPI post(RequestSpecification request, Map<String, String> formParams) {
        return new ResponseImpl(given(request).contentType(ContentType.JSON).formParams(formParams).post());
    }

    @Override
    public ResponseAPI put(RequestSpecification request, String endPoint, Object body) {
        return new ResponseImpl(given(request).contentType(ContentType.JSON).body(body).put(endPoint));
    }

    @Override
    public ResponseAPI delete(RequestSpecification request, String endPoint) {
        return new ResponseImpl(given(request).delete(endPoint));
    }
}
