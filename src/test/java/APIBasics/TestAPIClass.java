package APIBasics;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.Utility;

import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import resources.Payload;

import static io.restassured.RestAssured.*;

public class TestAPIClass {

	static String placeId="";
	JsonPath path;
	
	@BeforeClass
	public void before(){
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
	}
	@Test(priority=1)
	public void addPlace() throws IOException{
		
		String response = 
				given().log().all().queryParam("key","qaclick123")
		       .contentType("application/json")
		       .body(new String(Files.readAllBytes(Paths.get("C:\\NeonWorkspace\\APIExamples\\"
		       		+ "src\\test\\java\\resources\\AddPlacePayload.json"))))
		       .when().post("maps/api/place/add/json")
		       .then().assertThat().statusCode(200).log()
		       .all().body("scope", equalTo("APP"))
		       .header("Server", "Apache/2.4.41 (Ubuntu)")
		       .extract().response().asString(); 
		
		System.out.println("Response of Add Place API "+ response);
		
		path = Utility.stringToJson(response);
		placeId = path.getString("place_id");
		
		System.out.println("Place id of Add Place API "+placeId);
	}
	
	@Test(priority=2)
	public void updatePlace(){
		
		String newPlace="Summer walk";
		String response = 
				given().log().all().queryParam("key","qaclick123")
		.contentType("application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newPlace+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200)
		.body("msg",  equalTo("Address successfully updated"))
		.extract().response().asString();
		
		System.out.println("Response of Update Place API "+response);
		
	}
	
	@Test(priority=3)
	public void getPlace(){
		
		String response =
				given().log().all().queryParam("key","qaclick123")
				.queryParam("place_id", placeId)
				.contentType("application/json")
				.when().get("maps/api/place/get/json")
				.then().log().all().assertThat().statusCode(200)
				.extract().response().asString();
		
		 path = Utility.stringToJson(response);
		
		System.out.println("Response of Get Place API "+response);
		System.out.println("New address "+path.getString("address"));
				
	}
	
	
	
}
