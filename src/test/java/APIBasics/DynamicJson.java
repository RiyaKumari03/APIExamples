package APIBasics;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.Utility;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import resources.Payload;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	JsonPath path;
	String id;

	@BeforeClass
	public void before(){
		RestAssured.baseURI="https://rahulshettyacademy.com";
	}
	@DataProvider(name = "BooksData")
	public Object[][] getdata(){
		return new Object[][]{{"yykl","7676"},{"ojojj","2369"},{"shkn","9377"}};
	}
	
	@Test(dataProvider="BooksData")
	public void AddPlace(String isbn, String aisle){
		
		String response = given().log().all().contentType("application/json")
				          .body(Payload.addBook(isbn,aisle))
				          .when().post("/Library/Addbook.php").then().extract().response().asString();
		path = Utility.stringToJson(response);
		id = path.getString("ID");
		System.out.println("Add Book ID "+id);
		
		
	}
}
