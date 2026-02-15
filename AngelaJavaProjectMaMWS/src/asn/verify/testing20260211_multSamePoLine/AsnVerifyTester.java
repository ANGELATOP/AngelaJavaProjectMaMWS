package asn.verify.testing20260211_multSamePoLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AsnVerifyTester {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

//		test1_uom_list();
//		test2_poQty_list();
//		test3_poQty_list();
//		test4_poQty_list();
//		test5_poQty_list();
//		test6_poQty_list();
		test7_poQty_list();

		//		System.out.println(getIntValue("abc"));
	}
	public static void test7_poQty_list() throws Exception {
		String[] manhattanPoLines = {"001","001"};
		String[] xref             = {"001|60"};
		String poLineSeq          = "001"; //poLine value
		String poValue            = "68";  //what came from POH table
		String manhattanValue     = "poQty";
		
		String test = getValue(manhattanPoLines, poLineSeq, poValue, manhattanValue, xref);
		System.out.println("test="+test);
		
	}
	public static void test6_poQty_list() throws Exception {
		String[] manhattanPoLines = {"001","001"};
		String[] xref             = {"001|0","001|0","001|0"};
		String poLineSeq          = "002"; //poLine value
		String poValue            = "68";  //what came from POH table
		String manhattanValue     = "poQty";
		
		String test = getValue(manhattanPoLines, poLineSeq, poValue, manhattanValue, xref);
		System.out.println("test="+test);
		
	}
	public static void test5_poQty_list() throws Exception {
		String[] manhattanPoLines = {"001","001"};
		String[] xref             = {"001|0","001|0","001|0"};
		String poLineSeq          = "001"; //poLine value
		String poValue            = "68";  //what came from POH table
		String manhattanValue     = "poQty";
		
		String test = getValue(manhattanPoLines, poLineSeq, poValue, manhattanValue, xref);
		System.out.println("test="+test);
		
	}
	public static void test4_poQty_list() throws Exception {
		String[] manhattanPoLines = {"001","001"};
		String[] xref             = {"001|0","001|60","001|60"};
		String poLineSeq          = "001"; //poLine value
		String poValue            = "68";  //what came from POH table
		String manhattanValue     = "poQty";
		
		String test = getValue(manhattanPoLines, poLineSeq, poValue, manhattanValue, xref);
		System.out.println("test="+test);
		
	}
	public static void test3_poQty_list() throws Exception {
		String[] manhattanPoLines = {"001","001"};
		String[] xref             = {"001|0","001|60"};
		String poLineSeq          = "001"; //poLine value
		String poValue            = "68";  //what came from POH table
		String manhattanValue     = "poQty";
		
		String test = getValue(manhattanPoLines, poLineSeq, poValue, manhattanValue, xref);
		System.out.println("test="+test);
		
	}
	public static void test2_poQty_list() throws Exception {
		String[] manhattanPoLines = {"001","001"};
		String[] xref             = {"001|60","001|0"};
		String poLineSeq          = "001"; //poLine value
		String poValue            = "68";  //what came from POH table
		String manhattanValue     = "poQty";
		
		String test = getValue(manhattanPoLines, poLineSeq, poValue, manhattanValue, xref);
		System.out.println("test="+test);
		
	}

	public static void test1_uom_list() throws Exception {
		String[] manhattanPoLines = {"001","001"};

		String[] xref = {"001|lb","001|lb"};
		String poLineSeq = "001"; //real poLine value
		String poValue = "68";  //what came from POH table
		String manhattanValue = "testing";
		
		String test = getValue(manhattanPoLines, poLineSeq, poValue, manhattanValue, xref);
		System.out.println("test="+test);
		
	}	
	//logic from Convert.java in ACE application
	public static String getValue(String[] manhattanPoLines, String poLineSeq, String poValue, String manhattanValue, String[] xref) throws Exception {
		
//		String status = existOnManhattanMsg(manhattanPoLines, poLineSeq);
		String status = "yes";
		
		if(status.contains("yes")){
			return getNewValue(xref, poLineSeq, poValue, manhattanValue);
		}
//		return "awgData "+poValue;
		return poValue;
	}
	
	public static int getIntValue(String inputValue) {
		
		int newValue = 0;
		
		try {
		newValue = Integer.parseInt(inputValue);
		
		}catch(NumberFormatException e) {
			//System.out.println("here");
			newValue=999999999;//means not numeric value
		}
		
		return newValue;
	}
	
	//logic from Convert.java in ACE application
	private static String getNewValue(String[] xref, String poLineSeq, String poValue, String manhattanValue) throws Exception  {
		
		HashMap<String,String> hash = new HashMap<String,String>();
		
		int totalPoQty = 0;
		boolean isNumeric = false;
		int totalDupPoLine = 0;
		
		List<String> temp = new ArrayList<String>();
		String[] split;
		for (String x:xref){
			temp = splitValue("|", x);
			hash.put(temp.get(0), temp.get(1));
			
			if(temp.get(0).equals(poLineSeq)) {
				int test = getIntValue(temp.get(1));
				if(test==999999999) {
					//not numeric value
				}else {
					isNumeric=true;
					totalPoQty = totalPoQty + Integer.parseInt(temp.get(1));
					if(manhattanValue.contains("poQty") && (Integer.parseInt(temp.get(1))>0 ) ) {
						totalDupPoLine=totalDupPoLine+1;
					}
				}
			}

		}
		
		if(hash.containsKey(poLineSeq)) {
			if(hash.get(poLineSeq).contains("NoManhattanDataUsePoQty"))
//     			return "NoManhattanDataUsePoQty - use awgData "+poValue;
 			    return poValue;
			else
//			    return "manhattanData "+hash.get(poLineSeq);
//		        return hash.get(poLineSeq);
				if(isNumeric==true && manhattanValue.contains("poQty")) {
					if(totalPoQty>0) {
						//assume the poQty from Manhattan is all the same value for the same poLine based on testing with Manhattan
					  int test = totalPoQty / totalDupPoLine;
					  return String.valueOf(test);
					}
					else {
						//in case the poQty is zero from Manhattan for all messages for same poLine
						return String.valueOf(totalPoQty);
					}
				}else
				if(manhattanValue.contains("catchWeight"))
				{
					//the data could be a float value and it needs to be an integer value
					return convertToInt(hash.get(poLineSeq));
					
				}else
				{
					return hash.get(poLineSeq);
				}
		}
		
//		return "awgData "+poValue;
		return poValue;
	}	
	
	public static String convertToInt( String inputValue) {
		float floatValue = Float.parseFloat(inputValue);
		return String.valueOf(Math.round(floatValue));
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
