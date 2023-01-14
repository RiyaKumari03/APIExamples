package SerializeDeserialize;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pojo.AddPlace;
import Pojo.Location;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SerializeTest {
	AddPlace place;
	
	@BeforeClass
	public void before(){
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		 place = new AddPlace();
		place.setAccuracy(50);
		place.setName("Frontline house");
		place.setLanguage("French-IN");
		place.setAddress("29, side layout, cohen 09");
		place.setPhone_number("(+91) 983 893 3937");
		place.setWebsite("http://google.com");
		List<String> list = new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		place.setTypes(list);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		place.setLocation(l);
	}
	
	@Test
	public void addPlaceAPI(){
		
		String response = given().log().all().queryParam("key","qaclick123").body(place)
				          .contentType("application/json").when().post("/maps/api/place/add/json")
				          .then().assertThat().statusCode(200).log().all().extract().response().asString();
	}

}
