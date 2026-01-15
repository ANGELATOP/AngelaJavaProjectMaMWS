package inventory.adjust.testing.utilities;

public class KeyString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(getKeyString("InventoryAdjustment","CONDITION_CODE_CHANGES","APPLIED_CONDITION_CODE","MOVE","T110000","none","KC") );
		System.out.println(getKeyString("InventoryAdjustment","CONDITION_CODE_CHANGES","REMOVED_CONDITION_CODE","MOVE","T110000","none","KC") );
		System.out.println("");
		
		System.out.println(getKeyString("InventoryAdjustment","INVENTORY_ADJUSTMENT","ADJUST_UI","SUBTRACT","T110000","none","KC") );
		System.out.println(getKeyString("InventoryAdjustment","INVENTORY_ADJUSTMENT","CYCLE_COUNT","ADD","T110000","none","KC") );
		System.out.println(getKeyString("AddLostInventory",   "INVENTORY_ADJUSTMENT","ADD_LOST","ADD","0010123","none","KC") );
		System.out.println(getKeyString("InventoryAdjustment","INVENTORY_ADJUSTMENT","CREATE_ILPN","ADD","0011157","none","KC") );
		System.out.println("");

		System.out.println(getKeyString("Trailer_Check-In",   "TRAILER_CHECKIN", "YARD","none","none","00059415920260112163304","KC") );
		System.out.println(getKeyString("Trailer_Check-Out",  "TRAILER_CHECKOUT","YARD","none","none","ASN000000000088","KC") );
		System.out.println("");

		System.out.println(getKeyString("PIX TEST",  "none","YARD","none","none","none","KC") );
		System.out.println("");

	}
	public static String getKeyString(String pixSpec, String sourceEventName, String sourceTransType, String adjType, String itemId,  String asnId, String fclty) {
		
		String value = "undefined";
		
		if("ITEM_Level".equalsIgnoreCase(pixSpec) || "LPN_Level".equals(pixSpec) ) {
			value = sourceEventName+" "+pixSpec+" Fclty:"+fclty+" ItemId:"+itemId;
		}else
		if("ASN_Level".equalsIgnoreCase(pixSpec)) {
			value = sourceEventName+" "+pixSpec+" Fclty:"+fclty+" AsnId:"+asnId;
		}else
		if("INVENTORY_ADJUSTMENT".equalsIgnoreCase(sourceEventName)) {
			if(sourceTransType.contains("CREATE_ILPN")) {
				value = "Fclty:"+fclty+" ItemId:"+itemId+" InvQtyStatAdj:"+adjType+" "+sourceTransType;
			}else {
				value = "Fclty:"+fclty+" ItemId:"+itemId+" InvQtyAdj:"+adjType+" "+sourceTransType;
			}
		}else
		if("CONDITION_CODE_CHANGES".equalsIgnoreCase(sourceEventName)) {
			value = "Fclty:"+fclty+" ItemId:"+itemId+" InvStatAdj:"+ sourceTransType;
		}else
		if("Trailer_Check-Out".equalsIgnoreCase(pixSpec)) {
			value = "Fclty:"+fclty+" "+sourceEventName+" asn:"+asnId+" any other key info???";
		}else
		if("Trailer_Check-In".equalsIgnoreCase(pixSpec)) {
			value = "Fclty:"+fclty+" "+sourceEventName+" asn:"+asnId+" any other key info?";
		}else
		{
			value = pixSpec +"-needs more key definition";
		}
//		if("".equals(pixSpec)) {
//			
//		}
		return value;
	}

}
