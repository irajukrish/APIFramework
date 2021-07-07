package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.Utils;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

public class BookStoreSteps extends Utils {
	
	RequestSpecification request;
	//ResponseSpecification res;
	Response response;
	String Token;
	private static final String USER_ID = "9b5f49ab-eea9-45f4-9d66-bcf56a531b85";
	private static String jsonString;
	private static String bookId;
	
	@Given("I am an authorized user")
	public void i_am_an_authorized_user() throws IOException {
		//Request
		request = given().spec(requestBookStore())
				.body("{\r\n" + 
						"  \"userName\": \"TOOLSQA-Test\",\r\n" + 
						"  \"password\": \"Test@@123\"\r\n" + 
						"}");
		//Response
		response = request.when().post("/Account/v1/GenerateToken");
		//Token
		Token = getJsonPath(response, "token");
		//Validation of Status using TestNG
		Assert.assertEquals(getJsonPath(response, "status"), "Success");
		System.out.println("Token is " + getJsonPath(response, "token"));
		System.out.println("Status of the user request is " + getJsonPath(response, "status"));
		//Validation of Status using ResponseSpecification
		response.then().spec(ResponseSpecification());
		
	}

	@Given("A list of books are available")
	public void a_list_of_books_are_available() throws IOException {
		
		request = given().spec(requestBookStore());
		response = request.when().get("/BookStore/v1/Books");
		response.then().spec(ResponseSpecification());
		JsonPath js = new JsonPath(response.asString());
		List<String> books= js.get("books");
		System.out.println("Number of Books are " + books.size());
		Assert.assertTrue(books.size() > 0);
		jsonString = response.asString();
		List<Map<String, String>> bookss = JsonPath.from(jsonString).get("books");
		Assert.assertTrue(books.size() > 0);

		bookId = bookss.get(0).get("isbn");	
	}

	@When("I add a book to my reading list")
	public void i_add_a_book_to_my_reading_list() throws IOException {
		request = given().spec(requestBookStore())
				.header("Authorization", "Bearer " + Token)
				.body("{\r\n" + 
						"  \"userId\": \"" + USER_ID + "\",\r\n" + 
						"  \"collectionOfIsbns\": [\r\n" + 
						"    {\r\n" + 
						"      \"isbn\": \""+ bookId +"\"\r\n" + 
						"    }\r\n" + 
						"  ]\r\n" + 
						"}");
		response = request.when().post("/BookStore/v1/Books");
		//response.then().spec(ResponseSpecification());
		Assert.assertEquals(response.getStatusCode(), 201);
		System.out.println("Status code of I add a book to my reading list "+response.getStatusCode() );
	    
	}

	@Then("The book is added")
	public void the_book_is_added() {
	    
	}

	@When("I remove a book from my reading list")
	public void i_remove_a_book_from_my_reading_list() {
	    
	}

	@Then("The book is removed")
	public void the_book_is_removed() {
	    
	}

}
