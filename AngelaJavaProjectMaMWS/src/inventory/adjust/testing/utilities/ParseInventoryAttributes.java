package inventory.adjust.testing.utilities;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParseInventoryAttributes {

	public static void InventoryAttributeElements(JSONObject jsonObj1) {
		
		JSONObject jsonObj2;
		JSONObject jsonObj3;
		JSONObject jsonObj4;
		JSONObject jsonObj5;
		
		JSONArray jsonArray0;
		JSONArray jsonArray1;
		JSONArray jsonArray2;
		
		  boolean noToConditionCodes=false;
		  boolean noFromConditionCodes=false;
		  
		int cnter=0; 
		if(jsonObj1.has("InventoryAttributes")) {
		  jsonArray0 = jsonObj1.getJSONArray("InventoryAttributes");
		  for(int z=0;z<jsonArray0.length();z++) { 
			  jsonObj3 = jsonArray0.getJSONObject(z);
    		  String[] ele = JSONObject.getNames(jsonObj3); //get list of element names
    		  
    		  cnter=cnter+1;
			  System.out.println("");
    		  System.out.println(Parse.appendSpaces(" ")+"---InventoryAttributes "+cnter);	
    		  
    		  for(int e=0;e<ele.length;e++) {
    			  String eleNm = ele[e];
    			  
//    			  if("AttributeName".equals(eleNm)) { //attribute element
		    		  jsonObj2 = jsonArray0.getJSONObject(z);
		    		  System.out.println(Parse.appendSpaces(eleNm+":") + jsonObj2.getString(eleNm));
 //   			  }
    		  }
    	   }
		}
		System.out.println(" ");
	}
}
