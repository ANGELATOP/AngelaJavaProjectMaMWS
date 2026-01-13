package inventory.adjust.testing.utilities;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import aa.common.code.CreateOutputFile;
import aa.common.code.ListFileNames;
import aa.common.code.RetrieveTextFile;

public class ParseManhattanMsg {

	static List<String> output = new ArrayList<String>();
	
	public static void main(String[] args) {
		
		System.out.println("ParseManhattanMsg: start ");

		String directory      ="C:\\Users\\atopp\\Documents\\eclipseWorkspace-git\\AngelaJavaProjectMaMWS\\AngelaJavaProjectMaMWS\\src\\inventory\\adjust\\testing20260109b\\";

		parseMsgs(directory);
		
		System.out.println("ParseManhattanMsg: end");
	}

	public static void parseMsgs(String directory) {
		List<String> files = ListFileNames.getFileNames(directory);
		List<String> output = new ArrayList<String>();
		
		int fileCnter = 0;
		String outputFileNm;
		
		JSONObject jsonObj0;
		JSONObject jsonObj1;
		JSONObject jsonObj2;
		
		JSONArray jsonArray1;
		
		for(String fileNm:files) {
			
			if(fileNm.contains("ManhattanMsg_")) {
				System.out.println(" "); //add blank line
				System.out.println("-----------------------------------------------------------"); //add line
				System.out.println("-- "+fileNm);
				System.out.println("-----------------------------------------------------------"); //add line

				//build the JSON multiple records into one string
				String jsonString = RetrieveTextFile.concatenateRecs(directory+fileNm);
				
			    jsonObj0 = new JSONObject(jsonString);
			    jsonArray1 = jsonObj0.getJSONArray("ExportDocuments");
			    
			    int size = jsonArray1.length();
		    	 
			    for (int i = 0; i < size; i++){
			    	
			      jsonObj1 = jsonArray1.getJSONObject(i);
			      String[] elementNames = JSONObject.getNames(jsonObj1);
			      
			      for(int x=0;x<elementNames.length;x++) {
			    	  String elementName = elementNames[x];

			    	  if("SourceTransactionType".equals(elementName)) { //attribute element
			    		  System.out.println(appendSpaces(elementName+":") + jsonObj1.getString(elementName));
			    	  }else
			    	  if("SourceEventName".equals(elementName)) { //attribute element
			    		  System.out.println(appendSpaces(elementName+":") + jsonObj1.getString(elementName));
			    	  }else
			    	  if("SequenceNumber".equals(elementName)) { //attribute element
			    		  System.out.println(appendSpaces(elementName+":") + jsonObj1.getInt(elementName));
			    	  }else
			    	  if("TransactionNumber".equals(elementName)) { //attribute element
			    		  System.out.println(appendSpaces(elementName+":") + jsonObj1.getString(elementName));
			    	  }else
			    	  if("Facility".equals(elementName)) { //attribute element
			    		  System.out.println(appendSpaces(elementName+":") + jsonObj1.getString(elementName));
			    	  }else
			    	  if("ItemDefinition".equals(elementName)) { //jsonObject element
			    		  jsonObj2 = jsonObj1.getJSONObject("ItemDefinition");
	    				  System.out.println(appendSpaces("itemId"+":") + jsonObj2.getString("ItemId"));
			    	  }else
			    	  if("PIXFields".equals(elementName)) { //jsonObject element
			    		  PixElement(jsonObj1.getJSONObject("PIXFields"));
			    	  }
			    	}
			    }
		    }
		}
	}
	public static void PixElement(JSONObject jsonObj1) {
		
		JSONObject jsonObj2;
		JSONObject jsonObj3;
		JSONObject jsonObj4;
		JSONObject jsonObj5;
		
		JSONArray jsonArray0;
		JSONArray jsonArray1;
		JSONArray jsonArray2;

		  jsonArray0 = jsonObj1.getJSONArray("ConditionCodes");
		  boolean noToConditionCodes=false;
		  boolean noFromConditionCodes=false;
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
		    			  System.out.println(appendSpaces("ToConditionCodeId"+":") + jsonObj4.getString("ConditionCodeId"));
		    			  noToConditionCodes=true;
		    		  }
    			  }
    			  if("FromConditionCodes".equals(eleNm)) { //attribute element
		    		  jsonObj2 = jsonArray0.getJSONObject(z);
		    		  jsonArray2 = jsonObj2.getJSONArray("FromConditionCodes");
		    		  for(int j=0;j<jsonArray2.length();j++) {
		    			  jsonObj5 = jsonArray2.getJSONObject(j);
		    			  System.out.println(appendSpaces("FromConditionCodeId"+":") + jsonObj5.getString("ConditionCodeId"));
		    			  noFromConditionCodes=true;
		    		  }
    			  }
    		  }
    	}
		  
		if(noToConditionCodes==false) {
			   System.out.println(appendSpaces("ToConditionCodeId"+":") + "emptyList");
		}
		if(noFromConditionCodes==false) {
		  System.out.println(appendSpaces("FromConditionCodeId"+":") + "emptyList");
		}
		System.out.println(" ");
	}
	public static String appendSpaces(String data) {
		int size = data.length();
		
		int diff = 24-size;
		StringBuilder spaces = new StringBuilder();
		for(int i=0;i<diff;i++) {
			spaces.append(" ");
		}
		
		return data+spaces.toString();
	}
	public static void parseManhattanMsgs(String directory) {


		List<String> records;
		
//		List<String> output = new ArrayList<String>();
		
		List<String> files = ListFileNames.getFileNames(directory);
		for(String fileNm:files) {
			if(fileNm.contains("decodedManhattanMsg")) {
				records = RetrieveTextFile.retrieveTextFileTrim(directory+fileNm);
	
				output.add(" "); //add blank line
				output.add("-----------------------------------------------------------"); //add line
				output.add("-- "+fileNm);
				output.add("-----------------------------------------------------------"); //add line
				
				for(String recs:records) {
					if(recs.contains("SourceTransactionType")) {
						output.add(" "); //add blank line
						output.add(recs);
					}
					if(recs.contains("SourceEventName")) {
						output.add(recs);
					}
					if(recs.contains("ToConditionCodes")) {
						output.add(recs);
					}
					if(recs.contains("ConditionCodeId")) {
						output.add(recs);
					}
					if(recs.contains("FromConditionCodes")) {
						output.add(recs);
					}
					if(recs.contains("ItemId")) {
						output.add(recs);
					}
					if(recs.contains("SequenceNumber")) {
						output.add(recs);
					}
					if(recs.contains("TransactionNumber")) {
						output.add(recs);
					}
	//				if(recs.contains("")) {
	//					output.add(recs);
	//				}
	
				
				}
			}
		}
		if(output.size()>0)
			CreateOutputFile.createOutputFile(directory+"Inventory Adj Inputs.txt", output);
		
	}
	
}
