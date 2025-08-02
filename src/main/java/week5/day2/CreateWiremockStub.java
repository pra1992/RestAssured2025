package week5.day2;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.testng.annotations.Test;

public class CreateWiremockStub {

    @Test(enabled = false)
   public void createSimpleWireMock(){
       //Base url --> https://localhost:8080/greetings
       //As first step creates Static Request Stubbing
        String jsonBody = "{\n" +
                "  \"message\": \"Welcome to the wiremock\"\n" +
                "}";
        MappingBuilder mappingBuilder = WireMock.get("/greetings");

        //As second step created a static response stubbing
        ResponseDefinitionBuilder responseMocking = WireMock.aResponse()
                .withBody(jsonBody)
                        .withHeader("Content-Type", "application/json");
        //Now map the request and response by creating Wiremock Stub
        WireMock.stubFor(mappingBuilder.willReturn(responseMocking));
    }

   @Test(priority = 1)
   public void createWireMockForRestFulBooker(){
        String RequestBody = "{\n" +
                "\"firstname\":\"Mark\",\n" +
                "\"lastname\": \"Brown\", \"totalprice\": 111, \"depositpaid\": true, \"bookingdates\": {\n" +
                "\"checkin\":\"2018-01-01\",\n" +
                "\"checkout\":\"2019-01-01\"\n" +
                "},\n" +
                "\"additionalneeds\":\"Breakfast\"\n" +
                "}\n";

        String ResponseBody = "{\n" +
                "\"bookingid\": 204, \"booking\": {\n" +
                "\"firstname\":\"Mark\",\n" +
                "\"lastname\": \"Brown\", \"totalprice\": 111, \"depositpaid\": true, \"bookingdates\": {\n" +
                "\"checkin\":\"2018-01-01\",\n" +
                "\"checkout\":\"2019-01-01\"\n" +
                "},\n" +
                "\"additionalneeds\":\"Breakfast\"\n" +
                "}\n" +
                "}\n";

        MappingBuilder mappingBuilder = WireMock.post("/booking")
                .withHeader("Content-Type", WireMock.equalTo("application/json"))
                .withRequestBody(WireMock.equalToJson(RequestBody));

        ResponseDefinitionBuilder responseDefinitionBuilder =WireMock.aResponse().withHeader("Content-Type", "application/json")
                .withBody(ResponseBody)
                .withStatus(200)
                .withStatusMessage("Created");
        WireMock.stubFor(mappingBuilder.willReturn(responseDefinitionBuilder));




   }
}
