package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import week3.day2.CreateIncidentRequestBodyPojo;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class IncidentSteps {
    CreateIncidentRequestBodyPojo createIncidentRequestBodyPojo = new CreateIncidentRequestBodyPojo();
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    public static Response response = null;
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
       response = given().log().all().spec(requestSpecBuilder.build()).when().get();
    }
    @Then("validate user successfully received the response")
    public void validate_user_successfully_received_the_response() {
         response.then().log().all().assertThat().statusCode(200).statusLine(Matchers.containsString("OK")).contentType("application/json");
    }

    @Given("user gives the Short Description as {string}")
    public void user_gives_the_short_description_as(String ShortDescription) {
       createIncidentRequestBodyPojo.setShort_description(ShortDescription);
    }
    @Given("user gives the Description as {string}")
    public void user_gives_the_description_as(String Description) {
        createIncidentRequestBodyPojo.setDescription(Description);
    }
    @Given("user gives Active as {string}")
    public void user_gives_active_as(String Active) {
       createIncidentRequestBodyPojo.setActive(Active);
    }

    @When("user hit the POST method")
    public void user_hit_the_post_method() {
        response = given().log().all().spec(requestSpecBuilder.build()).contentType(ContentType.JSON).body(createIncidentRequestBodyPojo).when().post();
    }
    @Then("validate record is successfully created")
    public void validate_record_is_successfully_created() {
        response.then().assertThat().statusCode(201).statusLine(Matchers.containsString("Created")).contentType(ContentType.JSON);
    }

    @Given("user set the multiple pathparameters")
    public void user_set_the_multiple_pathparameters(DataTable PathParams) {
//       List<List<String>> asLists = PathParams.asLists();
//       for (int i=0; i<asLists.size(); i++){
//           requestSpecBuilder.addPathParam(asLists.get(i).get(0), asLists.get(i).get(1));
//       }
        Map<String, String> maps= PathParams.asMap();
        requestSpecBuilder.addPathParam("tableName", maps.get("tableName"));
        requestSpecBuilder.addPathParam("sys_id", maps.get("sys_id"));
    }

    @Then("validate user successfully received the response with the correct sysid")
    public void validate_user_successfully_received_the_response_with_the_correct_sysid(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap();
        response.then()
                .log()
                .all()
                .assertThat()
                .statusCode(Integer.parseInt(map.get("StatusCode")))
                .statusLine(Matchers.containsString(map.get("StatusMessage")))
                .contentType(map.get("Content-Type"))
                .body("result.sys_id", Matchers.equalTo(map.get("sys_id")));
        }
    }

