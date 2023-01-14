package Base;

import io.restassured.path.json.JsonPath;

public class Utility {

	
	public static JsonPath stringToJson(String response){
		
		return new JsonPath(response);
		
	}
}
