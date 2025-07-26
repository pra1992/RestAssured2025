package testcase;

import org.testng.annotations.Test;

public class E2ECreateIncident extends BaseClass {

    @Test(priority = 1)
    public void createNewRecord(){
        requestBodyPojo.setShort_description("Create record using service model");
        requestBodyPojo.setDescription("Create record using service model");
        requestBodyPojo.setActive("true");
        response = createNewIncident(requestSpecBuilder.build(), requestBodyPojo);
        validateResponse(response, 201, "Created", "application/json");
        Sys_Id = extractValueFromResponse(response, "result.sys_id");
    }

    @Test(priority = 2)
    public void retriveARecord(){
       response= retriveAnIncident(requestSpecBuilder.build(), Sys_Id);
       validateResponse(response, 200, "OK", "application/json");
       validateResponseBody(response, "result.sys_id", Sys_Id);
    }

    @Test(priority = 3)
    public void updateRecord(){
        requestBodyPojo.setShort_description("Update record using service model");
        requestBodyPojo.setDescription("Update record using service model");
        requestBodyPojo.setActive("true");
        response = updateIncident(requestSpecBuilder.build(), Sys_Id,requestBodyPojo);
        validateResponse(response,200, "OK", "application/json" );
        validateResponseBody(response, "result.sys_id",Sys_Id );
    }

    @Test(priority = 4)
    public void partiallyUpdateRecord(){
        requestBodyPojo.setShort_description("Partially update record using service model");
        requestBodyPojo.setDescription("Partially update record using service model");
        requestBodyPojo.setActive("true");
        response = partiallyUpdateIncident(requestSpecBuilder.build(),Sys_Id, requestBodyPojo);
        validateResponse(response, 200, "OK", "application/json");
        validateResponseBody(response, "result.sys_id", Sys_Id);

    }
    @Test(priority = 5)
    public void deleteIncident(){
       response = deleteIncident(requestSpecBuilder.build(), Sys_Id);
       validateResponse(response,204, "No Content");
    }

    @Test(priority = 6)
    public void retrieveDeletedIncident(){
        response = retriveAnIncident(requestSpecBuilder.build(), Sys_Id);
        validateResponse(response, 404, "Not Found", "application/json");
    }
}
