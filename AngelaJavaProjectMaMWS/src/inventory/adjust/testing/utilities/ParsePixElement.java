package inventory.adjust.testing.utilities;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParsePixElement {

	public static ParsePixElementObj PixElement(JSONObject jsonObj1) {
		
		ParsePixElementObj obj = new ParsePixElementObj();
		
		JSONObject jsonObj2;
		JSONObject jsonObj3;
		JSONObject jsonObj4;
		JSONObject jsonObj5;
		
		JSONArray jsonArray0;
		JSONArray jsonArray1;
		JSONArray jsonArray2;
		
		  boolean noToConditionCodes=false;
		  boolean noFromConditionCodes=false;

		String invAdjType="ADD";  
	    if(jsonObj1.has("InventoryAdjustmentType")) {
			  System.out.println(Parse.appendSpaces("InventoryAdjustmentType"+":") + jsonObj1.getString("InventoryAdjustmentType"));
			  invAdjType=jsonObj1.getString("InventoryAdjustmentType");
			  obj.setInventoryAdjustmentType(invAdjType);
	    }  
	    if(jsonObj1.has("InventoryAdjustmentQty")) {
	    	  String qty = jsonObj1.getString("InventoryAdjustmentQty");
	    	  String qtyFmt = Convert.formatAdjQty(invAdjType, qty);
			  System.out.println(Parse.appendSpaces("InventoryAdjustmentQty"+":") + qty +"  (format will be: "+qtyFmt+")");
			  obj.setInventoryAdjustmentQty(qty);
		}  
	    if(jsonObj1.has("FromInventoryBucket")) {
			  System.out.println(Parse.appendSpaces("FromInventoryBucket"+":") + jsonObj1.getString("FromInventoryBucket")+"   (inventory status for qty msg)");
	    }  
	    if(jsonObj1.has("ReasonCodeId")) {
			  System.out.println(Parse.appendSpaces("ReasonCodeId"+":") + jsonObj1.getString("ReasonCodeId"));
	    }  
		  
		if(jsonObj1.has("ConditionCodes")) {
		  jsonArray0 = jsonObj1.getJSONArray("ConditionCodes");
		  for(int z=0;z<jsonArray0.length();z++) { 
			  jsonObj3 = jsonArray0.getJSONObject(z);
    		  String[] ele = JSONObject.getNames(jsonObj3); //get list of element names
    		  
    		  for(int e=0;e<ele.length;e++) {
    			  String eleNm = ele[e];
    			  
    			  if("ToConditionCodes".equals(eleNm)) { //attribute element
		    		  jsonObj2 = jsonArray0.getJSONObject(z);
		    		  jsonArray1 = jsonObj2.getJSONArray("ToConditionCodes");
		    		  for(int j=0;j<jsonArray1.length();j++) {
		    			  jsonObj4 = jsonArray1.getJSONObject(j);
		    			  System.out.println(Parse.appendSpaces("ToConditionCodeId"+":") + jsonObj4.getString("ConditionCodeId"));
		    			  noToConditionCodes=true;
		    		  }
    			  }
    			  if("FromConditionCodes".equals(eleNm)) { //attribute element
		    		  jsonObj2 = jsonArray0.getJSONObject(z);
		    		  jsonArray2 = jsonObj2.getJSONArray("FromConditionCodes");
		    		  for(int j=0;j<jsonArray2.length();j++) {
		    			  jsonObj5 = jsonArray2.getJSONObject(j);
		    			  System.out.println(Parse.appendSpaces("FromConditionCodeId"+":") + jsonObj5.getString("ConditionCodeId"));
		    			  noFromConditionCodes=true;
		    		  }
    			  }
    		  }
    	}
		
			if(noToConditionCodes==false) {
				   System.out.println(Parse.appendSpaces("ToConditionCodeId"+":") + "emptyList-only used for Inventory Status Message");
			}
			if(noFromConditionCodes==false) {
			  System.out.println(Parse.appendSpaces("FromConditionCodeId"+":") + "emptyList-only used for Inventory Status Message");
			}
		}else
		{
		   System.out.println(Parse.appendSpaces("ConditionCodes"+":") + "element does not exist-only used for Inventory Status Message");
		}
		System.out.println(" ");

		return obj;

	}
}
