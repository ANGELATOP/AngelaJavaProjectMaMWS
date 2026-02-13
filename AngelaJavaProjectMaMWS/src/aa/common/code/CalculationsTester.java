package aa.common.code;

import java.math.BigDecimal;

public class CalculationsTester {

	public static void main(String[] args) {

		System.out.println(convertToInt("775.594285714"));
		System.out.println(convertToInt("626.4"));
		System.out.println(convertToInt("735.35"));
		System.out.println(convertToInt("1500"));
		System.out.println(convertToInt("25"));
		System.out.println(convertToInt("25.3"));
		System.out.println(convertToInt("25.5"));
		System.out.println(convertToInt("25.6"));
	}
	
	public static String convertToInt( String inputValue) {
		float floatValue = Float.parseFloat(inputValue);
		return String.valueOf(Math.round(floatValue));
	}
	public static String convertToIntOLD( String inputValue) {
		float value1 = Float.parseFloat(inputValue);
		return String.valueOf((int) value1);
	}
	public static String calculateMargin( double unitCostValue, double priceValue, double quantityValue, String houseZone) {
		
		BigDecimal unitCost = new BigDecimal(unitCostValue);  
		BigDecimal price    = new BigDecimal(priceValue); 
		BigDecimal quantity = new BigDecimal(quantityValue); 

		BigDecimal zero = new BigDecimal("0.0000");
		BigDecimal one = new BigDecimal("1.0000");
	
		if (price.compareTo(zero)==0) {
					return zero.toString();
		}
		if (quantity.compareTo(zero)==0) {
			quantity = one;
		}
		BigDecimal oneHundred = new BigDecimal("100.0000"); 		                
//		System.out.println("   variable:oneHundred = "+oneHundred);
		BigDecimal tmp  = price.divide(quantity,6,BigDecimal.ROUND_HALF_UP); 		
//		System.out.println("   variable:tmp        = "+tmp);
		BigDecimal tmp1 = unitCost.divide(one,6,BigDecimal.ROUND_HALF_UP); 	        
//		System.out.println("   variable:tmp1       = "+tmp1);
		BigDecimal tmp2 = tmp.subtract(tmp1); 		                                
//		System.out.println("   variable:tmp2       = "+tmp2);
		BigDecimal tmp3 = tmp2.divide(tmp,6,BigDecimal.ROUND_HALF_UP); 		        
//		System.out.println("   variable:tmp3       = "+tmp3);

		BigDecimal margin = tmp3.multiply(oneHundred);		                        
//		System.out.println("   variable:margin     = "+margin);
		margin = margin.setScale(4, BigDecimal.ROUND_HALF_UP);      		
//		System.out.println("   variable:margin     = "+margin+"       ===> Re-calculated margin displayed on HZM 'New %' and 'Net %'.  ");

		return margin.toString();
	}
}
