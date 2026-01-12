package inventory.adjust.testing20260109b;

import org.json.JSONObject;

public class Parse {

	final static String directory      ="C:\\Users\\atopp\\Documents\\eclipseWorkspace-git\\AngelaJavaProjectMaMWS\\AngelaJavaProjectMaMWS\\src\\inventory\\adjust\\testing20260109b\\";
	final static String outputdirectory="C:\\Users\\atopp\\Documents\\eclipseWorkspace-git\\AngelaJavaProjectMaMWS\\AngelaJavaProjectMaMWS\\src\\inventory\\adjust\\testing20260109b\\";

	public static void main(String[] args) {

	}
	public static void getJsonObj(JSONObject obj) {
	      JSONObject objectInArray = obj;
	      String[] elementNames = JSONObject.getNames(objectInArray);
	}
	public static String appendSpaces(String data) {
		int size = data.length();
		
		int diff = 14-size;
		StringBuilder spaces = new StringBuilder();
		for(int i=0;i<diff;i++) {
			spaces.append(" ");
		}
		
		return data+spaces.toString();
	}
}
