package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.AddPlaceResponse;
import pojo.Location;

public class TestDataBuild {
	
	AddPlace ap;
	
	public AddPlace addPlacePayLoad(String name, String language, String address) {
		
		ap = new AddPlace();
		Location l = new Location();
		l.setLat("-38.383494");
		l.setLng("33.427362");
		ap.setLocation(l);
		ap.setAccuracy("55");
		ap.setName(name);
		ap.setPhone_number("007");
		ap.setAddress(address);
		List<String> T = new ArrayList<String>();
		T.add("shoe par");
		T.add("shop");
		ap.setTypes(T);
		ap.setWebsite("http://google.com");
		ap.setLanguage(language);
		
		return ap;
		
	}
	
	public String deletePlacePayload(String placeid) {
		
		
		return "{\r\n" + 
				"    \"place_id\":\""+placeid+"\"\r\n" + 
				"}\r\n" + 
				"";
		
	}
	
	

}
