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


public class ParseGoogleMsg {

	
	public static void main(String[] args) {
		
		System.out.println("ParseGoogleMsg: start ");

		String directory      ="C:\\Users\\atopp\\Documents\\eclipseWorkspace-git\\AngelaJavaProjectMaMWS\\AngelaJavaProjectMaMWS\\src\\inventory\\adjust\\testing\\utilities\\";

		parseGoogleMsgs(directory);
		
		System.out.println("ParseGoogleMsg: end");
	}

	public static void parseGoogleMsgs(String directory) {
		List<String> files = ListFileNames.getFileNames(directory);
		List<String> output = new ArrayList<String>();
		
		int fileCnter = 0;
		String outputFileNm;
		
		//these are the attributes that I want to get the values for
		List<String> attributes = new ArrayList<String>();	
		attributes.add("MSG_ID_PK");
		attributes.add("QueueName");

		
		JSONObject jsonObj0;
		JSONObject jsonObj1;
		JSONObject jsonObj2;
		JSONObject jsonObj3;
		
		JSONArray jsonArray1;
		
		for(String fileNm:files) {
			
			if(fileNm.contains("googleMsg")) {
				System.out.println(" "); //add blank line
				System.out.println("-----------------------------------------------------------"); //add line
				System.out.println("-- "+fileNm);
				System.out.println("-----------------------------------------------------------"); //add line

				//build the JSON multiple records into one string
				String jsonString = RetrieveTextFile.concatenateRecs(directory+fileNm);
				
				
				
				
				
			    jsonObj0 = new JSONObject(jsonString);
			    jsonArray1 = jsonObj0.getJSONArray("receivedMessages");
			    
			    int size = jsonArray1.length();
		    	 
			    for (int i = 0; i < size; i++)
			    {
			      jsonObj1 = jsonArray1.getJSONObject(i);
			      String[] elementNames = JSONObject.getNames(jsonObj1);
			      
			      for(int x=0;x<elementNames.length;x++) {
			    	  String elementName = elementNames[x];
			    	  
			    	  if("message".equals(elementName)) { //Object Element
			    	  
					      //---------------------------------------------------------------------------------
					      //display specific google information 
					      //---------------------------------------------------------------------------------
			    		  jsonObj2 = jsonObj1.getJSONObject("message");
//			    		  System.out.println("data:      " + msgs.getString("data")); //no need to display the coded Manhattan data

			    		  String decode = convertBase64ToString(jsonObj2.getString("data"));
			    		  output = new ArrayList<String>();
			    		  output.add(decode);
			    		  fileCnter=fileCnter+1;
			    		  outputFileNm = "step_2  ManhattanMsg_"+fileCnter+" from_"+fileNm+".json";
			  			  CreateOutputFile.createOutputFile(directory+outputFileNm, output);


			    		  jsonObj3 = jsonObj2.getJSONObject("attributes");
			    		  for(String a:attributes) {
			    			  if(jsonObj3.has(a)) {
			    				  System.out.println(Parse.appendSpaces(a+":") + jsonObj3.getString(a));
			    			  }
			    		  }

					      //---------------------------------------------------------------------------------
					      //create decoded Manhattan Message File 
					      //---------------------------------------------------------------------------------
			    		  System.out.println(Parse.appendSpaces("ManhattanMsg:") + outputFileNm);
					      System.out.println();

					      //---------------------------------------------------------------------------------
					      //create soap request for each message so I don't have to manually do it
					      //---------------------------------------------------------------------------------
					      output = new ArrayList<String>();
					      output.add("{   ");
					      output.add("	\"messages\": [   ");
					      output.add(jsonObj2.toString());	
					      output.add("	]   ");
					      output.add("}   ");			    	  
					      outputFileNm = "step_1 soapRequest_"+fileCnter+" from_"+fileNm+".json";
			  			  CreateOutputFile.createOutputFile(directory+outputFileNm, output);
			  			  
			    	  };
			      }
			    }
			}
		}
	}
	public static String convertBase64ToString(String inputValue) {
		
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] decodedBytes = decoder.decode(inputValue.getBytes());
		
		String test = new String(decodedBytes).trim();

		String test2 = new String(decodedBytes);

		String test3 = new String(decodedBytes).toString();

		String test4 = test.replace("\r\n", "");

		String test5 = test4.replace(" ", "");
		
		//String test6 = test5.replace("\"","'");

		String removeFirstBracket = test5.substring(0,test5.length());
		String removeLastBracket = removeFirstBracket.substring(1,removeFirstBracket.length()-1);

		return test5;
	}
}
