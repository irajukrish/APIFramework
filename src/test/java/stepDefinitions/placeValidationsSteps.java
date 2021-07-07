package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class placeValidationsSteps extends Utils{
	
	RequestSpecification request;
	Response response;
	static String placeid;
	TestDataBuild TestData=new TestDataBuild();
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String Name, String language, String address) throws IOException {
		
		AddPlace addplace=new AddPlace();
        addplace.setName(Name);
        addplace.setLanguage(language);
        addplace.setAddress(address);
        addplace.setAccuracy("99");
        addplace.setLanguage("Tamil");
        Location location=new Location();
        location.setLat("-38.383494");
        location.setLng("33.427362");
        addplace.setLocation(location);
        addplace.setPhone_number("1007");
        List<String> Types=new ArrayList<String>();
        Types.add("shoes");
        Types.add("shops");
        addplace.setTypes(Types);
        addplace.setWebsite("http://RK.com");

        //POJO

        request = given().log().all().spec(RequestSpecification()).body(addplace);
        
		//Without POJO class
//        request = given().spec(RequestSpecification())
//				.body(TestData.addPlacePayLoad(Name, language, address));		
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		APIResources resourceAPI = APIResources.valueOf(resource);
		
		if(method.equalsIgnoreCase("POST"))
		response = request.when().post(resourceAPI.getResource())
				.then().spec(ResponseSpecification()).extract().response();
		else if(method.equalsIgnoreCase("GET"))
			response = request.when().post(resourceAPI.getResource())
			.then().spec(ResponseSpecification()).extract().response();
		
	}

	@Then("the API call got success with Status code {int}")
	public void the_api_call_got_success_with_status_code(int statuscode) {
		Assert.assertEquals(response.getStatusCode(), statuscode);		
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String ExpectedValue) {
			
		Assert.assertEquals(getJsonPath(response, keyValue), ExpectedValue);
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		placeid=getJsonPath(response, "place_id");
		request = given().spec(RequestSpecification()).queryParam("place_id", placeid);
		user_calls_with_http_request(resource, "GET");
		String actualname = getJsonPath(response, "name");
		Assert.assertEquals(actualname, expectedName);
		
		
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		
		request = given()
				.spec(RequestSpecification()).
				body(TestData.deletePlacePayload(placeid));
		
		
	}

}
