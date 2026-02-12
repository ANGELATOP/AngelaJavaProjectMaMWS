package asn.verify.testing20260211_multSamePoLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AsnVerifyTester {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String[] manhattanPoLines = {"001","001"};

		String[] xref = {"001|60","001|0"};
//		String[] xref = {"001|0","001|60"};

		String poLineSeq = "001"; //real poLine value
//		String poLineSeq = "002"; //dummied poLine value

		String poValue = "68";  //what came from POH table
		String manhattanValue = "testing";
		
		String test = getValue(manhattanPoLines, poLineSeq, poValue, manhattanValue, xref);
		
		System.out.println(test);
	}
	
	//logic from Convert.java in ACE application
	public static String getValue(String[] manhattanPoLines, String poLineSeq, String poValue, String manhattanValue, String[] xref) throws Exception {
		
//		String status = existOnManhattanMsg(manhattanPoLines, poLineSeq);
		String status = "yes";
		
		if(status.contains("yes")){
			return getNewValue(xref, poLineSeq, poValue);
		}
//		return "awgData "+poValue;
		return poValue;
	}
	
	//logic from Convert.java in ACE application
	private static String getNewValue(String[] xref, String poLineSeq, String poValue) throws Exception {
		
		HashMap<String,String> hash = new HashMap<String,String>();
		
		int totalPoQty = 0;
		
		List<String> temp = new ArrayList<String>();
		String[] split;
		for (String x:xref){
			temp = splitValue("|", x);
			hash.put(temp.get(0), temp.get(1));

			if(temp.get(0).equals(poLineSeq)) {
				totalPoQty = totalPoQty + Integer.parseInt(temp.get(1));
			}
		}
		
		if(hash.containsKey(poLineSeq)) {
			if(hash.get(poLineSeq).contains("NoManhattanDataUsePoQty"))
//     			return "NoManhattanDataUsePoQty - use awgData "+poValue;
 			    return poValue;
			else
//			    return "manhattanData "+hash.get(poLineSeq);
//		        return hash.get(poLineSeq);
			    return String.valueOf(totalPoQty);
		}
		
//		return "awgData "+poValue;
		return poValue;
	}	
	
	//logic from Convert.java in ACE application
	private static ArrayList<String> splitValue(String sep, String original) throws Exception{

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
