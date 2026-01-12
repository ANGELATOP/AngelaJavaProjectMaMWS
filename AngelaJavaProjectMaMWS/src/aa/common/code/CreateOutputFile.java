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

public class CreateOutputFile 
{
	
	public static String createOutputFile(String outputFileName, List<String> list)
	{
		BufferedWriter bufferedWriter = null;

		try 
		{
			bufferedWriter = new BufferedWriter(new FileWriter(outputFileName));

			//writeout to text file
			for(int i=0;i<list.size();i++)
			{
				bufferedWriter.write(list.get(i));
				bufferedWriter.newLine();
			}
       } 
		catch (FileNotFoundException ex) 
       {
	            ex.printStackTrace();
       } catch (IOException ex) 
       {
	            ex.printStackTrace();
       } finally 
       {
	            //Close the BufferedWriter
            try {
	                if (bufferedWriter != null) 
	                {
	                    bufferedWriter.flush();
	                    bufferedWriter.close();
	                }
	            } catch (IOException ex) 
	            {
	                ex.printStackTrace();
	            }
	        }
		return "";
		
	}
}

