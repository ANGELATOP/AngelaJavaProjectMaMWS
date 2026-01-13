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


public class ParseGoogleMsg2 {

	
	public static void main(String[] args) {
		
		System.out.println("ParseGoogleMsg: start ");

		String directory      ="C:\\Users\\atopp\\Documents\\eclipseWorkspace-git\\AngelaJavaProjectMaMWS\\AngelaJavaProjectMaMWS\\src\\inventory\\adjust\\testing20260109b\\";

		//step 1 - parse google message
		parseGoogleMsgs(directory);
		
		//step 2 - manually format each manhattan messages
		
		//step 3 - parse Manhattan messages
//		parseManhattanMsgs();
		
		
		System.out.println("ParseGoogleMsg: end");
	}

	public static void parseGoogleMsgs(String directory) {
		List<String> files = ListFileNames.getFileNames(directory);
		List<String> records;
		StringBuilder jsonData = new StringBuilder();
		List<String> output = new ArrayList<String>();
		
		int fileCnter = 0;
		String outputFileNm;
		 
		for(String fileNm:files) {
			jsonData = new StringBuilder();
			
			if(fileNm.contains("googleMsg")) {
				System.out.println(" "); //add blank line
				System.out.println("-----------------------------------------------------------"); //add line
				System.out.println("-- "+fileNm);
				System.out.println("-----------------------------------------------------------"); //add line

				
				records = RetrieveTextFile.retrieveTextFileTrim(directory+fileNm);
				for(String recs:records) {
					jsonData.append(recs);
				}
				
			    JSONObject outerObject = new JSONObject(jsonData.toString());
			    JSONArray receivedMsgs = outerObject.getJSONArray("receivedMessages");
			    
			    int size = receivedMsgs.length();
		    	 
			    for (int i = 0; i < size; i++)
			    {
			      JSONObject objectInArray = receivedMsgs.getJSONObject(i);
			      String[] elementNames = JSONObject.getNames(objectInArray);
			      
			      for(int x=0;x<elementNames.length;x++) {
			    	  String elementName = elementNames[x];
			    	  
			    	  if("message".equals(elementName)) { //Object Element
			    	  
					      //---------------------------------------------------------------------------------
					      //display specific google information that may be useful
					      //---------------------------------------------------------------------------------
			    		  JSONObject msgs = objectInArray.getJSONObject("message");
//			    		  System.out.println("data:      " + msgs.getString("data")); //no need to display the coded Manhattan data
			    		  String decode = convertBase64ToString(msgs.getString("data"));
			    		  output = new ArrayList<String>();
			    		  output.add(decode);
			    		  fileCnter=fileCnter+1;
			    		  outputFileNm = "step_3 decodedManhattanMsg_"+fileCnter+".json";
			  			  CreateOutputFile.createOutputFile(directory+outputFileNm, output);


			    		  JSONObject attribList = msgs.getJSONObject("attributes");
			    		  System.out.println("MSG_ID_PK:                     " + attribList.getString("MSG_ID_PK"));
			    		  System.out.println("QueueName:                     " + attribList.getString("QueueName"));

					      //---------------------------------------------------------------------------------
					      //create decoded Manhattan Message
					      //---------------------------------------------------------------------------------
			    		  System.out.println("generated Manhattan File Name: " + outputFileNm);
					      System.out.println();

					      //---------------------------------------------------------------------------------
					      //create soap request for each message so I don't have to manually do it
					      //---------------------------------------------------------------------------------
					      output = new ArrayList<String>();
					      output.add("{   ");
					      output.add("	\"messages\": [   ");
					      output.add(msgs.toString());	
					      output.add("	]   ");
					      output.add("}   ");			    	  
					      outputFileNm = "step_1 soapRequest_"+fileCnter+".json";
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
