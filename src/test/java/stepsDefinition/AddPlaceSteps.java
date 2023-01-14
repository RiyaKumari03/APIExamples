package stepsDefinition;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.BeforeClass;

import POMAPIPayload.PayloadBuilder;
import Utils.APISpecifications;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import resources.APIResources;

public class AddPlaceSteps extends APISpecifications {
	
	PayloadBuilder builder = new PayloadBuilder();	;
	JsonPath path;
	Response response;
	RequestSpecification spec;
	static String place_id;

	@Given("Add Place payload with {string},{string},{string},{string} and {string}")
	public void add_place_payload(String name, String language, String address,
			String phone, String website) throws IOException {
		
		
		spec = requestSpec().body(builder.addPlacePayload(name,language,
			   address,phone,website));
	    
	}
	
	@When("User calls {string} with {string} Http request")
	public void user_calls_api_with_http_request(String api, String request) throws IOException {
	    
		response = 
				given().log().all().spec(spec)
			   .when().post(APIResources.valueOf(api).getAPIResources())
		       .then().assertThat().spec(responseSpec()).log()
		       .all().extract().response(); 
		
	}
	@Then("The API call should be success with status code {int}")
	public void the_api_call_should_be_success_with_status_code(int status) {
	    
		Assert.assertEquals(status, response.getStatusCode());
		
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
	    
		
		
		Assert.assertEquals(getJsonPath(response, key), value);
		
	}
	@Then("Verify place_id created maps to {string} using {string}")
	public void verifyGetMap(String name, String api) throws IOException{
		place_id = getJsonPath(response,"place_id");
		spec = requestSpec().queryParam("place_id", place_id);
		response = 
				given().log().all().spec(spec)
				   .when().get(APIResources.valueOf(api).getAPIResources())
			       .then().assertThat().spec(responseSpec()).log()
			       .all().extract().response(); 
		Assert.assertTrue(name.equalsIgnoreCase(getJsonPath(response, "name")));
		
	}
	@Given("Delete Place Payload")
	public void deletePlacePayload() throws IOException{
		
		spec = requestSpec();
		System.out.println("Spec "+spec);
	}
	
	@When("User calls {string} with post http request")
	public void deletePlaceAPI(String deleteAPI){
		
		System.out.println(" api "+APIResources.valueOf(deleteAPI).getAPIResources());
		System.out.println("Inside post request ");
		System.out.println("Delete api "+APIResources.valueOf(deleteAPI).getAPIResources());
		System.out.println("Payload "+builder.deletePlace(place_id));
		response = given().log().all().spec(spec).body(builder.deletePlace(place_id)).
				when().post(APIResources.valueOf(deleteAPI).getAPIResources())
				 .then().assertThat().spec(responseSpec()).log()
			       .all().extract().response(); 
		System.out.println("Response "+response);
	
	}			
				
	
}
