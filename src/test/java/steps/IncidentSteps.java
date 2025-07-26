package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testcase.BaseClass;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class IncidentSteps extends BaseClass {

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

    @Given("user set the header {string} as {string}")
    public void user_set_the_header_as(String header, String value) {
        requestSpecBuilder.addHeader(header,value);
    }

    @Given("user set the basic authentication for username and password as {string} and {string}")
    public void user_set_the_basic_authentication_for_username_and_password_as_and(String UserName, String Password) {
        requestSpecBuilder.setAuth(basic("admin","gz^1@wDVaL3B"));
    }
    @When("user hit the GET method")
    public void user_logs_the_precondition() {
       //response = given().log().all().spec(requestSpecBuilder.build()).when().get();
        response = retriveAllIncidents(requestSpecBuilder.build());
    }
    @Then("validate user successfully received the response")
    public void validate_user_successfully_received_the_response() {
        validateResponse(response, 200, "OK", "application/json");
    }

    @Given("user gives the Short Description as {string}")
    public void user_gives_the_short_description_as(String ShortDescription) {
        requestBodyPojo.setShort_description(ShortDescription);
    }
    @Given("user gives the Description as {string}")
    public void user_gives_the_description_as(String Description) {
        requestBodyPojo.setDescription(Description);
    }
    @Given("user gives Active as {string}")
    public void user_gives_active_as(String Active) {
        requestBodyPojo.setActive(Active);
    }

    @When("user hit the POST method")
    public void user_hit_the_post_method() {
        response = createNewIncident(requestSpecBuilder.build(), requestBodyPojo);
    }

    @When("user hit the PUT method")
    public void user_hit_the_PUT_method() {
        response = updateIncident(requestSpecBuilder.build(),Sys_Id,requestBodyPojo);
    }

    @Then("validate record is successfully created")
    public void validate_record_is_successfully_created() {
        validateResponse(response, 201, "Created", "application/json");
        Sys_Id = extractValueFromResponse(response, "result.sys_id");
        validateResponseBody(response, "result.sys_id", Sys_Id);
        validateResponseBody(response, "result.short_description", requestBodyPojo.getShort_description());
        validateResponseBody(response, "result.description", requestBodyPojo.getDescription());
    }

    @Then("validate record is successfully Updated")
    public void validate_record_is_successfully_Updated() {
        validateResponse(response, 200, "OK", "application/json");
        validateResponseBody(response, "result.sys_id", Sys_Id);
        validateResponseBody(response, "result.short_description", requestBodyPojo.getShort_description());
        validateResponseBody(response, "result.description", requestBodyPojo.getDescription());
    }

    @Given("user set the multiple pathparameters")
    public void user_set_the_multiple_pathparameters(DataTable PathParams) {
//       List<List<String>> asLists = PathParams.asLists();
//       for (int i=0; i<asLists.size(); i++){
//           requestSpecBuilder.addPathParam(asLists.get(i).get(0), asLists.get(i).get(1));
//       }
        Map<String, String> maps= PathParams.asMap();
        requestSpecBuilder.addPathParam("tableName", maps.get("tableName"));
        requestSpecBuilder.addPathParam("sys_id", Sys_Id);
    }

    @Then("validate user successfully received the response with the correct sysid")
    public void validate_user_successfully_received_the_response_with_the_correct_sysid(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap();
//        response.then()
//                .log()
//                .all()
//                .assertThat()
//                .statusCode(Integer.parseInt(map.get("StatusCode")))
//                .statusLine(Matchers.containsString(map.get("StatusMessage")))
//                .contentType(map.get("Content-Type"))
//                .body("result.sys_id", Matchers.equalTo(map.get("sys_id")));
        validateResponse(response,Integer.parseInt(map.get("StatusCode")), map.get("StatusMessage"), map.get("Content-Type"));
        validateResponseBody(response, "result.sys_id", Sys_Id);
        }
    }

