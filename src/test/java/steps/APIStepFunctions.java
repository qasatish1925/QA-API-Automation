package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.commonAPIFunctions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class APIStepFunctions {

    @Given("(I )have access to endpoint {string}")
    public void iHaveAccessToEndpoint(String endPoint) {
        commonAPIFunctions.session.put("endpoint" , endPoint);
    }

    @When("(I )perform GET request for {string} endpoint")
    public void iPerformGETRequestForEndpoint(String endPoint) {
        if(endPoint.contains("id")) endPoint = endPoint.replaceAll("id" , commonAPIFunctions.valueStore.get("book id"));
        commonAPIFunctions.response = commonAPIFunctions.requestSetup().header("Authorization", "Bearer " + commonAPIFunctions.valueStore.get("access token"))
                .when().get(endPoint);
    }

    @Then("(I )get a {int} http status code")
    public void iGetAHttpStatusCode(int statusCode) {
        Assert.assertEquals(commonAPIFunctions.response.getStatusCode() , statusCode , "The expect status code did not match");
    }

    @Then("validate the response with Json schema {string}")
    public void validateTheResponseWithJsonSchema(String schemaName) {
        commonAPIFunctions.response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas/" + schemaName));
    }

    @Given("I perform login with {string} endpoint with below body")
    public void iPerformLoginWithEndpointWithBelowBody(String endPoint , String json) {
        String  dt = new SimpleDateFormat("DDDyyHHmmss").format(new Date());;
        if(json.contains("<random>")) json = json.replaceAll("<random>", dt );
        commonAPIFunctions.response =
                commonAPIFunctions.requestSetup().body(json).when().post(endPoint);
    }

    @When("(I )perform Post request for {string} endpoint with below body")
    public void iPerformPostRequestForEndpointWithBelowBody(String endPoint, String json) {
        String  dt = new SimpleDateFormat("DDDyyHHmmss").format(new Date());;
        if(json.contains("<random>")) json = json.replaceAll("<random>", dt );
        if(endPoint.contains("id")) endPoint = endPoint.replaceAll("id" , commonAPIFunctions.valueStore.get("book id"));
        commonAPIFunctions.response =
                commonAPIFunctions.requestSetup().header("Authorization", "Bearer " + commonAPIFunctions.valueStore.get("access token"))
                        .body(json).when().post(endPoint);
    }

    @When("(I )perform PUT request for {string} endpoint with below body")
    public void iPerformPUTRequestForEndpointWithBelowBody(String endPoint, String json) {
        if(endPoint.contains("id")) endPoint = endPoint.replaceAll("id" , commonAPIFunctions.valueStore.get("book id"));
        commonAPIFunctions.response =
                commonAPIFunctions.requestSetup().header("Authorization", "Bearer " + commonAPIFunctions.valueStore.get("access token"))
                        .body(json).when().put(endPoint);
    }


    @Then("(I )validate response object contains the following fields")
    public void iValidateResponseObjectContainsTheFollowingFields(Map<String , String> table) {
        JsonPath jsonPathEvaluator = commonAPIFunctions.response.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        table.forEach((key , value) -> {
            softAssert.assertTrue(jsonPathEvaluator.get(key +"="+value), "did not find the field valuepair " +
                    "in the response["+key+ "->"+value+"]");
        });
        softAssert.assertAll();
    }

    @Then("(I )verify {string} message is displayed")
    public void iVerifyMessageIsDisplayed(String message) {
        JsonPath jsonPathEvaluator = commonAPIFunctions.response.jsonPath();
        String actualMessage = jsonPathEvaluator.get("message");
        Assert.assertEquals(actualMessage , message, "message does not match");
    }

    @Then("store the access token from the response")
    public void storeTheAccessTokenFromTheResponse() {
        JsonPath jsonPathEvaluator = commonAPIFunctions.response.jsonPath();
        commonAPIFunctions.valueStore.put("access token" , jsonPathEvaluator.get("access_token"));
    }

    @When("I perform Delete request for {string} endpoint")
    public void iPerformDeleteRequestForEndpoint(String endpoint) {
        if(endpoint.contains("id")) endpoint = endpoint.replaceAll("id" , commonAPIFunctions.valueStore.get("book id"));
        commonAPIFunctions.response = commonAPIFunctions.requestSetup().header("Authorization", "Bearer " + commonAPIFunctions.valueStore.get("access token"))
                .when().delete(endpoint);
    }


    @And("I get the book id")
    public void iGetTheBookId() {
        JsonPath jsonPathEvaluator = commonAPIFunctions.response.jsonPath();
        commonAPIFunctions.valueStore.put("book id" , jsonPathEvaluator.get("id").toString());
    }

    @And("I wait for {int} seconds")
    public void iWaitForSeconds(int seconds) throws InterruptedException {
        int timeToWat = seconds * 1000;
        Thread.sleep(timeToWat);
    }
}
