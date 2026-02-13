package asn.ilpn.testing20260212;

import java.text.DecimalFormat;

public class ilpnTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(convertStringToInt("77"));
		
		System.out.println(Integer.toHexString(77).toUpperCase());
		
		System.out.println(convertStringToDecimal("77"));
		
		
		System.out.println(toComp3OLD("1234",5));

		System.out.println("toBinery="+Integer.toBinaryString(1234));
		System.out.println("toHex="+Integer.toHexString(77));
		System.out.println("toHex="+Integer.highestOneBit(77));
		
		System.out.println("packvalue="+packValue(7,7));
		

	
	}
	public static int packValue(int val1, int val2) {
		int ret_val = (((val1 & 0xFFFF) << 16) | (val2 & 0xFFFF));
	    return ret_val;
	}

	   public static String toComp3(String numberString, int totalDigits) {
	       boolean negative = numberString.startsWith("-");
	       String digits = numberString.replace("-", "").replace("+", "");

	       // Pad with leading zero if odd number of digits
	       if (digits.length() % 2 == 0) {
	           digits = digits + (negative ? "D" : "C");
	       } else {
	           digits = "0" + digits + (negative ? "D" : "C");
	       }

	       int len = digits.length() / 2;
	       byte[] packed = new byte[len];
	       for (int i = 0; i < len; i++) {
	           int highNibble = Character.digit(digits.charAt(i * 2), 16);
	           int lowNibble = Character.digit(digits.charAt(i * 2 + 1), 16);
	           packed[i] = (byte) ((highNibble << 4) | lowNibble);
	       }
	       return digits;
	   }

	   public static byte[] toComp3OLD(String numberString, int totalDigits) {
	       boolean negative = numberString.startsWith("-");
	       String digits = numberString.replace("-", "").replace("+", "");

	       // Pad with leading zero if odd number of digits
	       if (digits.length() % 2 == 0) {
	           digits = digits + (negative ? "D" : "C");
	       } else {
	           digits = "0" + digits + (negative ? "D" : "C");
	       }

	       System.out.println("digits.length()="+digits.length());
	       int len = digits.length() / 2;
	       byte[] packed = new byte[len];
	       for (int i = 0; i < len; i++) {
	    	   
	    	   System.out.println();
	    	   
	           int highNibble = Character.digit(digits.charAt(i * 2), 16);
	           System.out.println("highNibble="+highNibble);
	           
	           int lowNibble = Character.digit(digits.charAt(i * 2 + 1), 16);
	           System.out.println("lowNibble="+lowNibble);
	           
	           System.out.println("what is this value?+"+(byte) ((highNibble << 4) | lowNibble));
	           packed[i] = (byte) ((highNibble << 4) | lowNibble);
	           String testing = "";
	       }
	       return packed;
	   }
	   
	
	public static int convertStringToInt(String value) {
		
		int num = 0;
		
		num = Integer.parseInt(value);
		return num;
	}
	
	public static String convertStringToDecimal(String value) {
		
		DecimalFormat df = new DecimalFormat("#.00");
        String formatted = df.format(77);
        
		int num = 0;
		
		num = Integer.parseInt(value);
		return formatted;
	}
	
}
