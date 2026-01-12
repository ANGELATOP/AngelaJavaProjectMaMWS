package aa.common.code;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ListFileNames {

	// String directory =
	// "C:/a - oncall/2019-07-17 double payroll issues/logs/";

	public static List<String> getFileNames(String directory) {
		
		//get a list of the file names
		
		List<String> list = new ArrayList<String>();
		
		File incomingFolder = new File(directory);
		InputFileFilter filter = new InputFileFilter();
		File[] incomingFiles = incomingFolder.listFiles(filter);

		int nbrOfFiles = 0;
		
		if(incomingFiles!=null) {
			nbrOfFiles = incomingFiles.length;
		}
		//System.out.println("ListFileNames.getFileNames() ==> Number of folder or files found: " + nbrOfFiles +" ==> Current Directory:"+directory);
		
		for (int i = 0; i < nbrOfFiles; i++) {
//			System.out.println("ListFileNames.getFileNames: "+incomingFiles[i].getName());
			list.add(incomingFiles[i].getName());
		}
		return list;
	}
	public List<String> getFileTextDetails(String directory) {
		
		//get the actual records in the text file
		
		List<String> list = new ArrayList<String>();
		return list;
	}	
}
