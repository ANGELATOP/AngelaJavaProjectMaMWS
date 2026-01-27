package inventory.adjust.testing.utilities;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import aa.common.code.CreateOutputFile;
import aa.common.code.ListFileNames;
import aa.common.code.RetrieveTextFile;

public class CreateSeparateGoogleMsgs {

//	String directory ="C:\\Users\\atopp\\Documents\\eclipseWorkspace-git\\AngelaJavaProjectMaMWS\\AngelaJavaProjectMaMWS\\src\\inventory\\adjust\\testing\\utilities\\";
//	String outputDir = "C:\\Users\\atopp\\Downloads\\invAdjTest\\";

	static String directory = "C:\\Users\\atopp\\Downloads\\googleMsgs\\input\\";
//	static String outputDir = "C:\\Users\\atopp\\Downloads\\googleMsgs\\output\\";
	static String outputDir = "C:\\Users\\atopp\\Downloads\\googleMsgs\\outputAsnVerify\\";

	public static void main(String[] args) throws JSONException, Exception {
		parseGoogleMsgs();
	}
	public static void parseGoogleMsgs() throws JSONException, Exception {
		List<String> files = ListFileNames.getFileNames(directory);
		
		for(String fileNm:files) {
//			if(fileNm.contains("googleMsgs 2026-01-21.txt")) {
//				process(fileNm,"2026-01-21");
//			}
			if(fileNm.contains("googleMsgs 2026-01-23.txt")) {
				process(fileNm,"2026-01-23");
			}
		}
	}
	public static void process(String googleFileNm, String dateString) throws Exception {
		
		List<String> output = RetrieveTextFile.retrieveTextFile(directory+googleFileNm);
		List<String> temp = new ArrayList<String>();
		int counter=100;
	    for(String x:output){
	    	temp = new ArrayList<String>();
//	    	if(!x.contains("XNT_HST_VerifyASN_GCPQ")) 
	    	{
	    		counter=counter+1;
	    		temp.add(x);
	    		CreateOutputFile.createOutputFile(outputDir+dateString+" googleMsg "+counter+".txt", output);
	    	}
   	  }
	}
}
