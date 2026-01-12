package aa.common.code;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedWriter; 
import java.io.FileWriter; 
import java.io.FileNotFoundException; 
import java.net.URLDecoder;
import java.net.URLEncoder;

public class RetrieveTextFile 
{
	public static List<String> retrieveTextFile(String fileName)
	{
		List<String> list                = new ArrayList<String>();
		File textFile;
		String line     = null; 
		int nbrOfBlankLines=0;
		try 
		{
			textFile             = new File(fileName);

			BufferedReader input = new BufferedReader(new FileReader(textFile));
			
			while ((line = input.readLine()) != null)
			{
//				list = Utilities.tokenize("|", line.trim());
				//System.out.println("line: "+line);
				if("".equals(line))
				{
					nbrOfBlankLines++;
				}else
				{
					list.add(line);
				}
			}
			//System.out.println(new Date()+" Utilities.retrieveTextFile() number of blank records skipped "+nbrOfBlankLines);
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}finally{
			
		}
		
		return list;
	}
	public static String concatenateRecs(String fileNm) {
		List<String> list = RetrieveTextFile.retrieveTextFileTrim(fileNm);
		
		StringBuilder data = new StringBuilder();
		for(String recs:list) {
			data.append(recs);
		}
		return data.toString();
	}
	public static List<String> retrieveTextFileTrim(String fileName)
	{
		List<String> list                = new ArrayList<String>();
		File textFile;
		String line     = null; 
		int nbrOfBlankLines=0;
		try 
		{
			textFile             = new File(fileName);

			BufferedReader input = new BufferedReader(new FileReader(textFile));
			
			while ((line = input.readLine()) != null)
			{
//				list = Utilities.tokenize("|", line.trim());
				//System.out.println("line: "+line);
				if("".equals(line))
				{
					nbrOfBlankLines++;
				}else
				{
					list.add(line.trim());
				}
			}
			//System.out.println(new Date()+" Utilities.retrieveTextFile() number of blank records skipped "+nbrOfBlankLines);
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}finally{
			
		}
		
		return list;
	}
	public static List<String> retrieveTextFileIncludeFileName(String fileName)
	{
		List<String> list                = new ArrayList<String>();
		File textFile;
		String line     = null; 
		int nbrOfBlankLines=0;
		try 
		{
			textFile             = new File(fileName);

			BufferedReader input = new BufferedReader(new FileReader(textFile));
			
			while ((line = input.readLine()) != null)
			{
//				list = Utilities.tokenize("|", line.trim());
				//System.out.println("line: "+line);
				if("".equals(line))
				{
					nbrOfBlankLines++;
				}else
				{
					list.add(fileName+"|"+line);
				}
			}
			//System.out.println(new Date()+" Utilities.retrieveTextFile() number of blank records skipped "+nbrOfBlankLines);
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}finally{
			
		}
		
		return list;
	}
}

