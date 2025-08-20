package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.Assert;
import pages.commonAPIFunctions;

public class APIStepFunctions {

    @Given("I have access to endpoint {string}")
    public void iHaveAccessToEndpoint(String endPoint) {
        commonAPIFunctions.session.put("endpoint" , endPoint);
    }

    @When("I perform GET request for {string} endpoint")
    public void iPerformGETRequestForEndpoint(String endPoint) {
        commonAPIFunctions.response = commonAPIFunctions.requestSetup().when().get(endPoint);
    }

    @Then("I get a {int} http status code")
    public void iGetAHttpStatusCode(int statusCode) {
        Assert.assertEquals(commonAPIFunctions.response.getStatusCode() , statusCode , "The expect status code did not match");
        System.out.println(commonAPIFunctions.response.prettyPrint());
    }

    @Then("validate the response with Json schema {string}")
    public void validateTheResponseWithJsonSchema(String schemaName) {
        commonAPIFunctions.response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas/" + schemaName));
    }
}
