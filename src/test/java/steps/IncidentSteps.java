package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.*;

public class IncidentSteps {
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    Response response;
    @Given("user set the baseuri {string} of the service now instance")
    public void user_set_the_baseuri_of_the_service_now_instance(String BaseURI) {
      requestSpecBuilder.setBaseUri(BaseURI);
    }
    @Given("user set the basepath {string}")
    public void user_set_the_basepath(String BasePath) {
      requestSpecBuilder.setBasePath(BasePath);
    }
    @Given("user set the pathparameter for {string} as {string}")
    public void user_set_the_pathparameter_for_as(String string, String string2) {
        requestSpecBuilder.addPathParam("tableName", "incident");
    }
    @Given("user set the basic authentication for username and password as {string} and {string}")
    public void user_set_the_basic_authentication_for_username_and_password_as_and(String UserName, String Password) {
        requestSpecBuilder.setAuth(basic("admin","gz^1@wDVaL3B"));
    }
    @When("user hit the GET method")
    public void user_logs_the_precondition() {
       response = given().log().all().spec(requestSpecBuilder.build()).when().get();
    }
    @Then("validate user successfully received the response")
    public void validate_user_successfully_received_the_response() {
         response.then().log().all().assertThat().statusCode(200).statusLine(Matchers.containsString("OK")).contentType("application/json");

    }
}
