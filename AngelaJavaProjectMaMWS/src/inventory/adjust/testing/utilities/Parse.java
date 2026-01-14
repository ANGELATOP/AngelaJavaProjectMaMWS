package inventory.adjust.testing.utilities;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import aa.common.code.ListFileNames;

public class Parse {

	
	public static void main(String[] args) {
		String directory = "C:\\Users\\atopp\\Downloads\\invAdjTest\\";
		displayFileNames(directory);
	}

	public static void displayFileNames(String directory) {
		List<String> files = ListFileNames.getFileNames(directory);
		for(String fileNm:files) {
			System.out.println(fileNm);
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
}
