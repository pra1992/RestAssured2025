package week5.day2;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class CreateWiremockStub {
    public static void main(String[] args){
        //Base url --> https://localhost:8080/greetings
        //As first step creates Static Request Stubbing
        MappingBuilder mappingBuilder = WireMock.get("/greetings");

        //As second step created a static response stubbing
        ResponseDefinitionBuilder responseMocking = WireMock.aResponse()
                .withBody("Hi! welcome to wiremocking");

        //Now map the request and response by creating Wiremock Stub
        WireMock.stubFor(mappingBuilder.willReturn(responseMocking));
    }
}
