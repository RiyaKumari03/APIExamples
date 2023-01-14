package resources;

public enum APIResources {

	AddPlaceAPI("maps/api/place/add/json"),
	UpdatePlaceAPI("maps/api/place/update/json"),
	GetPlaceAPI("maps/api/place/get/json"),
	DeletePlaceAPI("maps/api/place/delete/json");
	private String resources;
	
	APIResources(String resources){
		this.resources = resources;
		
	}
	public String getAPIResources(){
		return resources;
	}
	
}
