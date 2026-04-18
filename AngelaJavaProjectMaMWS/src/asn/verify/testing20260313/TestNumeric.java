package asn.verify.testing20260313;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TestNumeric {

	public static void main(String[] args) {
//		System.out.println(getIntValue("123"));
//		System.out.println(getIntValue("4532.64"));

		Float test = getFloatValue("abc");
		float val = test.floatValue();
		System.out.println("val=" + val);
		if(val==99999.99f)
			System.out.println("invalid input");
		
		System.out.println(getFloatValue("abc"));
		System.out.println(getFloatValue("123"));
		System.out.println(getFloatValue("123.5"));
		System.out.println(getFloatValue("123.55"));

	}

	public static float getFloatValue(String inputValue) {

		float floatValue = 0f;

		try {
			floatValue = Float.parseFloat(inputValue);
		} catch (Exception e) {
			return floatValue = 99999.99f;
		}

		return floatValue;
	}

	public static int getIntValue(String inputValue) {

		int newValue = 0;

		try {
			newValue = Integer.parseInt(inputValue);

		} catch (NumberFormatException e) {
			newValue = 999999999;// means not numeric value
		}

		return newValue;
	}
}
