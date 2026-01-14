package inventory.adjust.testing.utilities;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Convert {

	public static String getInvAdjustMsgType(String sourceTransactionType) {
		
		if(sourceTransactionType.contains("CYCLE_COUNT") || 
				sourceTransactionType.contains("MODIFY_ILPN") || 
				sourceTransactionType.contains("ADJUST_UI"))
			return "qtyChg";
		else
//		if(sourceTransactionType.contains("APPLIED_CONDITION_CODE") || 
//				sourceTransactionType.contains("REMOVED_CONDITION_CODE"))
//				return "statusChg";
    	if(sourceTransactionType.contains("APPLIED_CONDITION_CODE") || 
    		sourceTransactionType.contains("REMOVED_CONDITION_CODE"))
    		return sourceTransactionType;
    	else
//		if(sourceTransactionType.contains("APPLIED_CONDITION_CODE")) 
//				return "applyStatusChg";
//		else
//		if(sourceTransactionType.contains("REMOVED_CONDITION_CODE"))
//			return "removeStatusChg";
//		else
		if(sourceTransactionType.contains("CREATE_ILPN")) 
				return "createILPN";
		
		return "Error - typeOfOutputMessageNotDefined:error";
	}
	public static String formatCatchWght(String value) {
		
		double newValue = Double.parseDouble(value);
		Double calcValue = newValue/453.592;
//		DecimalFormat formatter = new DecimalFormat("#0000000.00");
		DecimalFormat formatter = new DecimalFormat("#0.00");
		return formatter.format(calcValue);
	}
	public static String getNewValue(String[] list, String keyString) throws Exception {
		
		HashMap<String,String> hash = new HashMap<String,String>();
		for (String val:list){
			hash.put(keyString, val);
		}
		
		if(hash.containsKey(keyString)) {
		   return hash.get(keyString);
		}
		
		if(keyString.equals("UM"))
			return "CS";
		
		return "0000000.00";
	}
	public static String getInventoryStatus(String status) {
		if(status.contains("UNAVAILABLE"))
			return "U";
		else
			if(status.contains("AVAILABLE"))
				return "A";
			else
				return "ERROR-getInventoryStatus";
			
	}
	public static String formatAdjQty(String adjustType, String adjustQty) {
		String sign = "SUBTRACT".equals(adjustType)?"-":"";
		return sign+convertFromDoubleToInteger(adjustQty);
	}
	public static String convertFromDoubleToInteger(String value ) {
		double doubleValue = Double.parseDouble(value);
		int intValue = (int) doubleValue;
		return String.valueOf(intValue);
	}
	public static String getCurrDate() throws ParseException {
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyyMMddHHmmss");
		return dateFmt.format(new Date());
	}	
}
