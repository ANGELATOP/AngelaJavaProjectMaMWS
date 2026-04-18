package asn.verify.testing20260313;

import java.util.ArrayList;
import java.util.List;

import aa.common.code.CreateOutputFile;
import aa.common.code.RetrieveTextFile;

public class ParseFile {

//	static String directory = "C:\\Users\\atopp\\Downloads\\googleMsgs\\input\\";
	
	static String directory = "C:\\Users\\atopp\\Documents\\eclipseWorkspace-git\\AngelaJavaProjectMaMWS\\AngelaJavaProjectMaMWS\\src\\asn\\verify\\testing20260313\\";
	static String outputDir = "C:\\Users\\atopp\\Documents\\eclipseWorkspace-git\\AngelaJavaProjectMaMWS\\AngelaJavaProjectMaMWS\\src\\asn\\verify\\testing20260313\\";

//	static String directory = "C:\\Users\\atopp\\Documents\\eclipseWorkspace-git\\AngelaJavaProjectMaMWS\\AngelaJavaProjectMaMWS\\src\\manhattan\\msg\\errors\\";
//	static String outputDir = "C:\\Users\\atopp\\Documents\\eclipseWorkspace-git\\AngelaJavaProjectMaMWS\\AngelaJavaProjectMaMWS\\src\\manhattan\\msg\\errors\\";

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

//		process("manhattanMsg.json");
//		process("2026-04-15 1b - asnVerify manhattan msg-soapUI.json");
		
//		process("2026-04-02 2 asn verify manhattan msg issue T2.json");
//		process("2026-04-03 group 1b - asn manhattan msg - from RichardM.json");
//		process("2026-04-03 group 2b - asn manhattan msg - from RichardM.json");
//		process("2026-04-07 group 1b asn manhattan msg-soapUI.json");
//		process("2026-04-07 group 2b asn manhattan msg-soapUI.json");
//		process("2026-02-13 asn verify dupPoLineDupValue.json");
//		process("2026-04-08 group-1b ASN Verify msg-soapUI.json");
		
//		getKeyInfoAsnVerify("2026-04-03 group 1b - asn manhattan msg - from RichardM.json");
//		getKeyInfoAsnVerify("2026-04-03 group 2b - asn manhattan msg - from RichardM.json");
//		getKeyInfoAsnVerify("2026-04-07 group 1b asn manhattan msg-soapUI.json");
//		getKeyInfoAsnVerify("2026-04-07 group 2b asn manhattan msg-soapUI.json");
//		getKeyInfoAsnVerify("2026-04-15 1b - asnVerify manhattan msg-soapUI.json");

//		getKeyInfoAsnVerifySpecificMessages("2026-04-07 group 2b asn manhattan msg-soapUI.json","\"AsnSequenceNumber\": \"001\"");
//		getKeyInfoAsnVerifySpecificMessages("2026-04-07 group 2b asn manhattan msg-soapUI.json","\"AsnSequenceNumber\": \"003\"");
//		getKeyInfoAsnVerifySpecificMessages("2026-04-07 group 2b asn manhattan msg-soapUI.json","\"AsnSequenceNumber\": \"005\"");
//		getKeyInfoAsnVerifySpecificMessages("2026-04-07 group 2b asn manhattan msg-soapUI.json","\"AsnSequenceNumber\": \"004\"");
//		getKeyInfoAsnVerifySpecificMessages("2026-02-13 asn verify dupPoLineDupValue.json","\"AsnSequenceNumber\": \"007\"");
//		getKeyInfoAsnVerifySpecificMessages("2026-04-08 group-1b ASN Verify msg-soapUI.json","\"AsnSequenceNumber\": \"001\"");
//		getKeyInfoAsnVerifySpecificMessages("2026-04-15 1b - asnVerify manhattan msg-soapUI.json","\"AsnSequenceNumber\": \"001\"");
		getKeyInfoAsnVerifySpecificMessages("2026-04-15 1b - asnVerify manhattan msg-soapUI.json","\"AsnSequenceNumber\": \"015\"");

		
		
//		getKeyInfoInvAdjust("2026-04-02 group 1b - invAdjust manhattan msg.json");	
//		getKeyInfoInvAdjust("2026-04-02 group 2b - invAdjust manhattan msg.json");	
//		getKeyInfoInvAdjust("2026-04-02 group 3b - invAdjust manhattan msg.json");	
//		getKeyInfoInvAdjust("2026-04-02 group 4b - invAdjust manhattan msg-soapUI.json");	
		
	}
	public static void process(String googleFileNm) throws Exception {
		
		List<String> output = RetrieveTextFile.retrieveTextFile(directory+googleFileNm);
		List<String> temp = new ArrayList<String>();
		
		boolean currRec = false;
		int counter=100;
		int elementCnter=0;
		
		List<String> list = new ArrayList<String>();
		
		StringBuilder text = new StringBuilder();
		int totalAttributes=1;
	    for(String x:output){
	    	temp = new ArrayList<String>();
	    	if(x.contains("ITEM_Level")) 
	    	{
//	    		counter=counter+1;
//	    		temp.add(x);
//	    		CreateOutputFile.createOutputFile(outputDir+" googleMsg "+counter+".txt", output);
	    		text = new StringBuilder();
	    		text.append(x.trim());
	    		list.add(x.trim());
	    		currRec=true;
	    	}
	    	if(currRec==true) {
		    	if(x.contains("ItemId") || 
		    	   x.contains("AsnSequenceNumber")  || 
		    	   x.contains("AsnId")  || 
		    	   x.contains("AttributeName")  || 
		    	   x.contains("AttributeValue")  || 
		    	   x.contains("AttributeUom")  || 
		    	   x.contains("UnitsShipped")  || 
		    	   x.contains("UnitsReceived")) {
		    		elementCnter=elementCnter+1;
//		    		System.out.println("ITEM_Level "+ x);

		    		text.append(x.trim()+"    ");
		    		list.add(x.trim());
		    		totalAttributes=totalAttributes+1;
		    	}
	    	}
	    	if(elementCnter>5) {
	    		currRec = false;
	    		System.out.println(elementCnter+"   "+totalAttributes+" "+text.toString());
	    		elementCnter=0;
	    	}
   	  }
//    	for(String t:list)
//    		System.out.println(t);
	}
	public static void getKeyInfoAsnVerify(String googleFileNm) throws Exception {
		//get the info that I need to look at
		
		List<String> output = RetrieveTextFile.retrieveTextFile(directory+googleFileNm);
		boolean skip = false;
		
	    for(String x:output){
	    	if(x.contains("Messages")) {
	    		//new message group
//	    		System.out.println("\"----------------------------------------------- ");//add blank line
	    		skip = false;
	    	}else
	    	if(x.contains("LPN_Level")) {
	    		skip = true; //no need to view this data
	    	}else
	    	if(skip==false){
	    		displayInfo(x);
	    	}

   	    }
	}
	public static void getKeyInfoAsnVerifySpecificMessages(String googleFileNm, String criteria) throws Exception {
		//get the info that I need to look at
		
		List<String> output = RetrieveTextFile.retrieveTextFile(directory+googleFileNm);
		boolean skip = false;
		
		
		List<String> specificMessage = new ArrayList<String>();
		boolean foundSpecificMessage = false;
		
	    for(String x:output){
	    	if(x.contains("Messages")) {
	    		//new message group
//	    		System.out.println("\"----------------------------------------------- ");//add blank line
	    		skip = false;
	    		if(foundSpecificMessage==true) {
	    			for(String t:specificMessage) {
//	    				displayInfo(t);
	    				System.out.println(t);
	    			}
        		}
	    		specificMessage = new ArrayList<String>();
	    		foundSpecificMessage = false;
	    	}else
	    	if(x.contains("LPN_Level")) {
	    		skip = true; //no need to view this data
	    	}else
	    	if(x.contains(criteria)){
	    		foundSpecificMessage=true;
	    		specificMessage.add(x);	
	    	}else
	    	if(skip==false){
	    		specificMessage.add(x);	
	    	}

   	    }
	    //get last set
		if(foundSpecificMessage==true) {
			for(String t:specificMessage) {
//				displayInfo(t);
				System.out.println(t);
			}
		}
	    
	}	
	private static void displayInfo(String x) throws Exception {
		List temp = new ArrayList();
		temp = splitString(":",x);
		if(temp.size()>0) {
			if(temp.size()>1) {
				String test = (String)temp.get(1);
				if(!test.contains("\"\"")) {
					System.out.println(x);
				}
			}else
			{
				System.out.println(x);
			}
		}else
		{
			System.out.println(x);
		}
	}
	
	public static void getKeyInfoInvAdjust(String googleFileNm) throws Exception {
		//get the info that I need to look at
		
		List<String> output = RetrieveTextFile.retrieveTextFile(directory+googleFileNm);
		
	    for(String x:output){
	    	if(x.contains("Messages")) {
	    		//skip
	    	}else
	    	{
	    		displayInfo(x);
	    	}
   	    }
	}	
	private static String appendSpaces(String value, int total) {
		int valueLength = value.length();
		
		int diff = total - valueLength;
		StringBuilder spaces = new StringBuilder();
		spaces.append("");
		for(int i=0;i<total;i++) {
			spaces.append(" ");
		}
		
		return value+spaces.toString();
	}
	private static ArrayList<String> splitString(String sep, String original) throws Exception
	{
//		String original = RemoveUnconvertibleChars.removeUnconvertibleChars(original2);

	   if (sep == null || sep.equals("") || original == null)
	      throw new IllegalArgumentException("null or empty String");
	   ArrayList<String>  result = new ArrayList<String> ();
	   int oldpos = 0;
	   int pos;
	   int sepLength = sep.length();
	   String substr="";
	   
	   try{
		   while ((pos = original.toUpperCase().indexOf(sep, oldpos)) >= 0)
		   {
		   	  substr = original.substring(oldpos, pos);
		      if (substr.startsWith("\n"))
		         result.add(original.substring(oldpos + 1, pos));
		      else
		         result.add(substr);
		      oldpos = pos + sepLength;
		   }
	
		   if (original.substring(oldpos).toUpperCase().startsWith("\n"))
		      result.add(original.substring(oldpos + 1));
		   else
		      result.add(original.substring(oldpos));
	   }catch(Exception e){
		   System.out.println("TESTING ERROR "+original+"  "+substr);
	   }
	   return result;
	}
}
