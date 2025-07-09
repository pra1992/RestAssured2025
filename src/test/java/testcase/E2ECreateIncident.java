package testcase;

import org.testng.annotations.Test;

public class E2ECreateIncident extends BaseClass {

    @Test(priority = 1)
    public void createNewRecord(){
        requestBodyPojo.setShort_description("Create record using service model");
        requestBodyPojo.setDescription("Create record using service model");
        requestBodyPojo.setActive("true");
       response = incidentClass.createNewIncident(requestSpecBuilder.build(), requestBodyPojo);
       incidentClass.validateResponse(response, 201, "Created", "application/json");
        Sys_Id = incidentClass.extractValueFromResponse(response, "result.sys_id");
    }

    @Test(priority = 2)
    public void retriveARecord(){
       response= incidentClass.retriveAnIncident(requestSpecBuilder.build(), Sys_Id);
       incidentClass.validateResponse(response, 200, "OK", "application/json");
       incidentClass.validateResponseBody(response, Sys_Id, "result.sys_id");
    }

    @Test(priority = 3)
    public void updateRecord(){
        requestBodyPojo.setShort_description("Update record using service model");
        requestBodyPojo.setDescription("Update record using service model");
        requestBodyPojo.setActive("true");
        response = incidentClass.updateIncident(requestSpecBuilder.build(), Sys_Id,requestBodyPojo);
        incidentClass.validateResponse(response,200, "OK", "application/json" );
        incidentClass.validateResponseBody(response, Sys_Id, "result.sys_id");
    }

    @Test(priority = 4)
    public void partiallyUpdateRecord(){
        requestBodyPojo.setShort_description("Partially update record using service model");
        requestBodyPojo.setDescription("Partially update record using service model");
        requestBodyPojo.setActive("true");
        response = incidentClass.partiallyUpdateIncident(requestSpecBuilder.build(),Sys_Id, requestBodyPojo);
        incidentClass.validateResponse(response, 200, "OK", "application/json");
        incidentClass.validateResponseBody(response, Sys_Id, "result.sys_id");

    }
    @Test(priority = 5)
    public void deleteIncident(){
       response = incidentClass.deleteIncident(requestSpecBuilder.build(), Sys_Id);
       incidentClass.validateResponse(response,204, "No Content");
    }

    @Test(priority = 6)
    public void retrieveDeletedIncident(){
        response = incidentClass.retriveAnIncident(requestSpecBuilder.build(), Sys_Id);
        incidentClass.validateResponse(response, 404, "Not Found", "application/json");
    }
}
