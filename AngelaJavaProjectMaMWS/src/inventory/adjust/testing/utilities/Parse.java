package inventory.adjust.testing.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import aa.common.code.ListFileNames;

public class Parse {

	
	public static void main(String[] args) {
//		String directory = "C:\\Users\\atopp\\Downloads\\invAdjTest\\";
//		displayFileNames(directory);
		
		reformatDate("2026-01-13 00:00:03.072");
	}
	public static String reformatDate(String inputDate) {
		String test="";
		inputDate = inputDate.replace("T", " ");
		    try {
		        String inputFormat = "yyyy-MM-dd HH:mm:ss.SSS";
		        String outputFormat = "yyyyMMddHHmmssSSS";
		        
		        test = new SimpleDateFormat(outputFormat).format(new SimpleDateFormat(inputFormat).parse(inputDate));
		        //System.out.println("test===>"+test);
			    

		    } catch (ParseException e) {
		        e.printStackTrace();
		    }
		    return test;
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
		
		return data+spaces.toString()+"|";
	}
}
