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


public class ParseGoogleAndManhattanMsgs_useThis {

	static int fileCnter = 10;

	public static void main(String[] args) {
		
		System.out.println("ParseGoogleMsg: start ");

		String directory ="C:\\Users\\atopp\\Documents\\eclipseWorkspace-git\\AngelaJavaProjectMaMWS\\AngelaJavaProjectMaMWS\\src\\inventory\\adjust\\testing\\utilities\\";
		String outputDir = "C:\\Users\\atopp\\Downloads\\invAdjTest\\";
		
		parseGoogleMsgs(directory, outputDir);
		
		System.out.println("ParseGoogleMsg: end");
	}

	public static void parseGoogleMsgs(String directory, String outputDir) {
		List<String> files = ListFileNames.getFileNames(directory);
		List<String> output = new ArrayList<String>();
		
		int fileCnter = 10;
		int googleMsgCnter = 0;
		String outputFileNm_manhattan;
		String outputFileNm_soapRequest;
		
		JSONObject jsonObj0;
		JSONObject jsonObj1;
		JSONObject jsonObj2;
		JSONObject jsonObj3;
		
		JSONArray jsonArray1;
		
		for(String fileNm:files) {
			googleMsgCnter = 0;
			
			if(fileNm.contains("googleMsg")) {
				processInventoryGoogleMessages(fileNm, directory, outputDir);
			}

		}
	}
	public static void processInventoryGoogleMessages(String fileNm, String directory, String outputDir) {
		
		List<String> output = new ArrayList<String>();
		
		int googleMsgCnter = 0;
		String outputFileNm_manhattan;
		String outputFileNm_soapRequest;
		
		JSONObject jsonObj0;
		JSONObject jsonObj1;
		JSONObject jsonObj2;
		JSONObject jsonObj3;
		
		JSONArray jsonArray1;
		
		if(fileNm.contains("googleMsg") || fileNm.contains("google_Msg")) {
			
			
			System.out.println(" "); //add blank line
			System.out.println("==========================================================================="); //add line
			System.out.println("-- "+fileNm);
			System.out.println("==========================================================================="); //add line

			//build the JSON multiple records into one string
			String jsonString = RetrieveTextFile.concatenateRecs(directory+fileNm);
			
			
			
			
			
		    jsonObj0 = new JSONObject(jsonString);
		    jsonArray1 = jsonObj0.getJSONArray("receivedMessages");
		    
		    int size = jsonArray1.length();
	    	 
		    for (int i = 0; i < size; i++)
		    {
		    	googleMsgCnter=googleMsgCnter+1;
		    	
		      jsonObj1 = jsonArray1.getJSONObject(i);
		      String[] elementNames = JSONObject.getNames(jsonObj1);
		      
		      for(int x=0;x<elementNames.length;x++) {
		    	  String elementName = elementNames[x];
		    	  
		    	  
		    	  if("message".equals(elementName)) { //Object Element
		    	  
				      //---------------------------------------------------------------------------------
				      //display specific google information 
				      //---------------------------------------------------------------------------------
		    		  jsonObj2 = jsonObj1.getJSONObject("message");
//		    		  System.out.println("data:      " + msgs.getString("data")); //no need to display the coded Manhattan data

		    		  String decode = convertBase64ToString(jsonObj2.getString("data"));
		    		  output = new ArrayList<String>();
		    		  output.add(decode);
		    		  fileCnter=fileCnter+1;
		    		  
//		    		  outputFileNm_manhattan = "step_2 ManhattanMsg_"+fileCnter+" from_"+fileNm+".json";
		    		  outputFileNm_manhattan = fileCnter+"_2_ManhattanMsg.json";
		  			  CreateOutputFile.createOutputFile(outputDir+outputFileNm_manhattan, output);
		  			  
		    		  jsonObj3 = jsonObj2.getJSONObject("attributes");
		    		  String msgIdPk = jsonObj3.getString("MSG_ID_PK");
		    		  String queueName = jsonObj3.getString("QueueName");
//	    			  System.out.println(appendSpaces("MSG_ID_PK:") + msgIdPk);

				      //---------------------------------------------------------------------------------
				      //Log decoded Manhattan Message File info 
				      //---------------------------------------------------------------------------------
//		    		  System.out.println(appendSpaces("ManhattanMsg:") + outputFileNm_manhattan);
//				      System.out.println();

				      //---------------------------------------------------------------------------------
				      //create soap request for each message so I don't have to manually do it
				      //---------------------------------------------------------------------------------
				      output = new ArrayList<String>();
				      output.add("{   ");
				      output.add("	\"messages\": [   ");
				      output.add(jsonObj2.toString());	
				      output.add("	]   ");
				      output.add("}   ");			    	  
				      outputFileNm_soapRequest = fileCnter+"_1_soapRequest.json";
		  			  CreateOutputFile.createOutputFile(outputDir+outputFileNm_soapRequest, output);

		  			  //Parse Manhattan message to get some key info
		  			  parseMsgs(decode, outputFileNm_manhattan, outputFileNm_soapRequest, msgIdPk, googleMsgCnter, queueName);

		    	  };
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
	public static void parseMsgs(String jsonString, String manhattanOuputFileNm, String soapReqOutputFileNm, String msgIdPk, int googleMsgCnter, String queueName) {
		List<String> output = new ArrayList<String>();
		
		int msgCnter = 0;
		String outputFileNm;
		
		JSONObject jsonObj0;
		JSONObject jsonObj1;
		JSONObject jsonObj2;
		
		JSONArray jsonArray1;

          //common info for each Manhattan message
		
		  System.out.println(appendSpaces("","*")+"GoogleMsg "+googleMsgCnter); //add line
		  System.out.println(appendSpaces("MSG_ID_PK:") + msgIdPk);
		  System.out.println(appendSpaces("QueueName:") + queueName);
		  System.out.println(appendSpaces("SoapGoogleRequestFile:")+soapReqOutputFileNm);
	      System.out.println(appendSpaces("ManhattanMsgFile:")+manhattanOuputFileNm);
		  System.out.println(" "); //add line

		
			    jsonObj0 = new JSONObject(jsonString);
			    jsonArray1 = jsonObj0.getJSONArray("ExportDocuments");
			    
			    int size = jsonArray1.length();
		    	 
			    for (int i = 0; i < size; i++){
			    
			    	msgCnter=msgCnter+1;
			    	
			      jsonObj1 = jsonArray1.getJSONObject(i);
			      String[] elementNames = JSONObject.getNames(jsonObj1);
			      
			      System.out.println(appendSpaces("","-")+"ManhattanMsg "+msgCnter);
			      
			      boolean anyInventoryAttributes=false;
			      
			      for(int x=0;x<elementNames.length;x++) {
			    	  String elementName = elementNames[x];

			    	  if("SourceTransactionType".equals(elementName)) { //attribute element
			    		  String test = Convert.getInvAdjustMsgType(jsonObj1.getString(elementName));
			    		  System.out.println(appendSpaces(elementName+":") + jsonObj1.getString(elementName)+"  ("+test+")");
			    		  ParseManhattanMsg.outputXmlMessageType(jsonObj1.getString(elementName));
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
			    		  ParsePixElement.PixElement(jsonObj1.getJSONObject("PIXFields"));
			    	  }else
			    	  if("InventoryAttributes".equals(elementName)) { //jsonObject element
			    		  ParseInventoryAttributes.InventoryAttributeElements(jsonObj1);
			    		  anyInventoryAttributes=true;
			    	  }
			    	}
			      if(anyInventoryAttributes==false) {
			    	  System.out.println(appendSpaces("InventoryAttributes:") + "FYI - No InventoryAttributes elements. Needed for UOM and CatchWeight Calc for qtyMsg."); 
			    	  System.out.println(" ");
			      }
			    }
			    
		    
		
	}	
	public static String appendSpaces(String data) {
		int size = data.length();
		
		int diff = 27-size;
		StringBuilder spaces = new StringBuilder();
		for(int i=0;i<diff;i++) {
			spaces.append(" ");
		}
		
		return data+spaces.toString();
	}	
	public static String appendSpaces(String data,String test) {
		int size = data.length();
		
		int diff = 27-size;
		StringBuilder spaces = new StringBuilder();
		for(int i=0;i<diff;i++) {
			spaces.append(test);
		}
		
		return data+spaces.toString();
	}	
}
