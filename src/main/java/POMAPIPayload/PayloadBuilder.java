package POMAPIPayload;

import java.util.ArrayList;
import java.util.List;

import Pojo.AddPlace;
import Pojo.Location;

public class PayloadBuilder {
	
	AddPlace place;
	
	public AddPlace addPlacePayload(String name, String language, 
			String address, String phone, String website){
		
		place = new AddPlace();
		place.setAccuracy(50);
		place.setName(name);
		place.setLanguage(language);
		place.setAddress(address);
		place.setPhone_number(phone);
		place.setWebsite(website);
		List<String> list = new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		place.setTypes(list);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		place.setLocation(l);
		
		return place;
	}
	
	public String deletePlace(String place_id){
		
		return "{\r\n" + 
				"\r\n" + 
				"    \"place_id\":\""+place_id+"\"\r\n" + 
				"}\r\n" + 
				"";
	}

}
