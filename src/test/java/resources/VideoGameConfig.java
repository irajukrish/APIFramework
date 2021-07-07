package resources;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.lessThan;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class VideoGameConfig {
	
	public static RequestSpecification videoGame_requestSpec;
    public static ResponseSpecification videoGame_responseSpec;
    PrintStream log;

	@Before("@getAllGames")
	public void setup() throws FileNotFoundException  {
		
				
		log = new PrintStream(new FileOutputStream("logging.txt"));

		videoGame_requestSpec = new RequestSpecBuilder()
				.setBaseUri("http://localhost")
				.setBasePath("/app/")
				.setPort(8080)
				.addHeader("Content-Type", "application/json")
				.addHeader("Accept", "application/json")
				.addFilter(new RequestLoggingFilter())
				.addFilter(new ResponseLoggingFilter())
				.build();

		videoGame_responseSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectResponseTime(lessThan(3000L))
				.build();
		
		RestAssured.requestSpecification = videoGame_requestSpec;
        RestAssured.responseSpecification = videoGame_responseSpec;
        
        System.out.println("Logger logger logger : " + log.toString());
	}
	
	@Before(value="@CreateNewGame")
	public static void setup1() throws FileNotFoundException {
		
		//log = new PrintStream(new FileOutputStream("logging.txt"));
		
		String gameBodyJson = "{\n" +
                "  \"id\": 13,\n" +
                "  \"name\": \"GTA V\",\n" +
                "  \"releaseDate\": \"2021-05-30T10:02:58.768Z\",\n" +
                "  \"reviewScore\": 98,\n" +
                "  \"category\": \"Shooter\",\n" +
                "  \"rating\": \"Mature\"\n" +
                "}";

		videoGame_requestSpec = new RequestSpecBuilder()
				.setBaseUri("http://localhost")
				.setBasePath("/app/")
				.setPort(8080)
				.addHeader("Content-Type", "application/json")
				.addHeader("Accept", "application/json")
				.setBody(gameBodyJson)
				.addFilter(new RequestLoggingFilter())
				.addFilter(new ResponseLoggingFilter())
				.build();

		videoGame_responseSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectResponseTime(lessThan(3000L))
				.build();
		
		RestAssured.requestSpecification = videoGame_requestSpec;
        RestAssured.responseSpecification = videoGame_responseSpec;

	}
	
	@Before(value="@UpdateGame")
	public static void setup2() throws FileNotFoundException {
		
		//log = new PrintStream(new FileOutputStream("logging.txt"));
		
		String gameBodyJson = "{\n" +
                "  \"id\": 13,\n" +
                "  \"name\": \"GTA V\",\n" +
                "  \"releaseDate\": \"2019-12-03T10:02:58.768Z\",\n" +
                "  \"reviewScore\": 99,\n" +
                "  \"category\": \"Driving\",\n" +
                "  \"rating\": \"Universal\"\n" +
                "}";

		videoGame_requestSpec = new RequestSpecBuilder()
				.setBaseUri("http://localhost")
				.setBasePath("/app/")
				.setPort(8080)
				.addHeader("Content-Type", "application/json")
				.addHeader("Accept", "application/json")
				.setBody(gameBodyJson)
				.addFilter(new RequestLoggingFilter())
				.addFilter(new ResponseLoggingFilter())
				.build();

		videoGame_responseSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectResponseTime(lessThan(3000L))
				.build();
		
		RestAssured.requestSpecification = videoGame_requestSpec;
        RestAssured.responseSpecification = videoGame_responseSpec;

	}
	
	@Before("@DeleteGame")
	public static void setup3() throws FileNotFoundException {
		
		//log = new PrintStream(new FileOutputStream("logging.txt"));

		videoGame_requestSpec = new RequestSpecBuilder()
				.setBaseUri("http://localhost")
				.setBasePath("/app/")
				.setPort(8080)
				.addHeader("Content-Type", "application/json")
				.addHeader("Accept", "application/json")
				.addFilter(new RequestLoggingFilter())
				.addFilter(new ResponseLoggingFilter())
				.build();

		videoGame_responseSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectResponseTime(lessThan(3000L))
				.build();
		
		RestAssured.requestSpecification = videoGame_requestSpec;
        RestAssured.responseSpecification = videoGame_responseSpec;

	}
}
