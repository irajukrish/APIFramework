package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	public static RequestSpecification req;
	ResponseSpecification res;
	Properties prop;
	PrintStream log;
	
	public RequestSpecification RequestSpecification() throws IOException {
		
		if(req==null) {
		 log= new PrintStream(new FileOutputStream("logging.txt"));
		
		 req = new RequestSpecBuilder()
				.setBaseUri(getGlobalValue("baseUri"))
				.addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON)
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		 return req;
		}
		return req;		
	}
	
	public ResponseSpecification ResponseSpecification() {
		
		res = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();
		return res;
		
	}
	
	public static String getGlobalValue(String key) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\Raju Krish\\eclipse-workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
		
	}
	
	public RequestSpecification requestBookStore() throws IOException {
		if(req==null) {
			 log= new PrintStream(new FileOutputStream("logging.txt"));
		req = new RequestSpecBuilder()
				.setBaseUri(getGlobalValue("baseUribs"))
				.setContentType(ContentType.JSON)
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return req;
		}
				
				
		return req;
		
	}

}
