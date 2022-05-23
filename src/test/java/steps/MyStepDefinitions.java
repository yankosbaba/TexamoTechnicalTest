package steps;

import baseClient.GoHttpClient;
import converter.GoJsonConverter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.request.CreateUserRequest;
import models.response.CreateUserResponse;
import org.junit.Assert;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MyStepDefinitions {
    private GoHttpClient client;
    private String requestBodyStr = "";
    private CreateUserRequest requestObject;
    private GoJsonConverter<CreateUserRequest> jsonConverter = new GoJsonConverter<CreateUserRequest>();
    private HttpResponse response;
    private String email = "Michael";
    @Given("I setup My HTTPBuilder and its headers")
    public void setupBuilderAndHeaders() {
        client = new GoHttpClient();
        client.setupHTTPBuilder();
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/json");
        headers.put("Authorization", "Bearer a6c03b914ed0f0e83142630a873cbaa33fd6bf65b838009ceac7deea9a552d45");
        client.addHeaders(headers);
    }
    public String setUserRequestBody(){
        Random r = new Random();
        email +=  r.nextInt(1000000) + "@gmail.com";
        requestObject = new CreateUserRequest("Michael Adedeji",email, "male", "active" );
        String mystr =  jsonConverter.convertToJsonString(requestObject);
        return mystr;
    }

    public String setUserRequestBodyResend(){
        requestObject = new CreateUserRequest("Michael Adedeji",email, "male", "active" );
        String mystr =  jsonConverter.convertToJsonString(requestObject);
        return mystr;
    }

    public String setUserRequestBodyInvalid(){
        requestObject = new CreateUserRequest("Michael","michael1.adedeji@gmai", "male", "active" );
        return jsonConverter.convertToJsonString(requestObject);
    }

    @Given("I access the endpoint {string} and set request POST method with valid data")
    public void accessEndpointAndSetPostMethod(String endpoint){
        client.createEndpoint(endpoint);
        client.addPostMethod(setUserRequestBody());
    }

    @When("I access the endpoint {string} and set request POST method with valid data retry")
    public void accessEndpointAndSetPostMethodRetry(String endpoint){
        client.createEndpoint(endpoint);
        client.addPostMethod(setUserRequestBodyResend());
    }

    @Given("I access the endpoint {string} and set request POST method with invalid data")
    public void accessEndpointAndSetPostMethodInvalidData(String endpoint){
        client.createEndpoint(endpoint);
        client.addPostMethod(setUserRequestBodyInvalid());
    }

    @When("I build the request")
    public void createRequest(){
        client.createRequest();
    }

    @When("I send the request to the server")
    public void getResponse(){
        response = client.getResponse(client.getRequest());
    }

    @Then("I validate that user was created if it exists and the returned status is {int}")
    public void validateResponse(int statusCode){
        Assert.assertEquals( statusCode , response.statusCode());
        if(response.statusCode() == 201){
            CreateUserResponse userResponse = (CreateUserResponse) response.body();
            Assert.assertEquals(userResponse.getEmail(), requestObject.getEmail());
            Assert.assertEquals(userResponse.getGender(), requestObject.getGender());
            Assert.assertEquals(userResponse.getName(), requestObject.getName());
            Assert.assertEquals(userResponse.getStatus(), requestObject.getStatus());
            Assert.assertNotNull(userResponse.getId());
        }
    }

}
