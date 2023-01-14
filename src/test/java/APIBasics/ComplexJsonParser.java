package APIBasics;

import java.util.List;

import org.testng.annotations.Test;

import Base.Utility;
import PayloadBody.CourseResponse;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;
import resources.Payload;

public class ComplexJsonParser {

	@Test
	public void complesJsonResponse(){
		
		JsonPath path = Utility.stringToJson(Payload.coursePrice());
		
		/*List<CourseResponse> courses = path.getList("courses");
		System.out.println("Number of courses "+courses.size());
		
		//System.out.println("Purchase Amount "+path.getInt("purchaseAmount"));
		System.out.println("Title of first course "+courses.get(0).getTitle());
		
		int sum=0;
		for(CourseResponse res : courses){
			
			System.out.println("Course Title and Prices "+res.getTitle()+" "+res.getPrice());
			sum = sum+res.getCopies()*res.getPrice();
			if(res.getTitle().equalsIgnoreCase("RPA"))
				System.out.println("Number of copies for RPA Course "+res.getCopies());
		}
		
		Assert.assertEquals(path.getInt("purchaseAmount"), sum);*/
		int n = path.getInt("courses.size()");
		System.out.println("Count of courses "+path.getInt("courses.size()"));
		System.out.println("Purchase amount "+path.getInt("dashboard.purchaseAmount"));
		System.out.println("Title of first course "+path.getString("courses[0].title"));
		int sum =0;
		for(int i=0;i<n;i++){
			
			System.out.println("Course title and prices "+path.getString("courses["+i+"].title")+"  "+
					path.getInt("courses["+i+"].price"));
			sum = sum+path.getInt("courses["+i+"].price")*path.getInt("courses["+i+"].copies");
			
			if(path.getString("courses["+i+"].title").equalsIgnoreCase("RPA"))
				System.out.println("Count files for RPA course "+path.getInt("courses["+i+"].copies"));
			
		}
		
		Assert.assertEquals(path.getInt("dashboard.purchaseAmount"), sum);
	}
}
