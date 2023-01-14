package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class APISpecifications {

	RequestSpecification spec;
	ResponseSpecification resp;
	Properties prop;
	
	public String getGlobalValue(String key) throws IOException{
		
		FileInputStream fis = new FileInputStream("C:\\NeonWorkspace\\APIExamples\\src\\main\\java\\Utils\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	public RequestSpecification requestSpec() throws IOException{
		
		if(spec == null){
			
		RestAssured.baseURI = getGlobalValue("baseUrl");
		PrintStream stream = new PrintStream(new FileOutputStream("logging.txt"));
		spec = new RequestSpecBuilder().setContentType(ContentType.JSON)
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(stream))
				.addFilter(ResponseLoggingFilter.logResponseTo(stream))
				.build();
		return spec;
		}
		return spec;
	}
	
	public ResponseSpecification responseSpec(){
		
		resp = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		
		return resp;
	}
	
	public String getJsonPath(Response response, String key){
		
		String resp = response.asString();
		JsonPath path = new JsonPath(resp);
		return path.getString(key);
		
		
	}
}
