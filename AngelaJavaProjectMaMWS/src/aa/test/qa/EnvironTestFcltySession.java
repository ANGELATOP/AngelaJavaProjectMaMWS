package aa.test.qa;


import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EnvironTestFcltySession {
	
	private static Hashtable<String, FacilitySession> fcltySessionHashTable = new Hashtable<String, FacilitySession>();
	

	public static synchronized String getTestQaEnvironment(String fcility) throws ParseException {

		if (fcltySessionHashTable.containsKey("fcltyMap") ) {
			HashMap<String,String> fcltyMap = fcltySessionHashTable.get("fcltyMap").mawmOrgMap;
			
			String environ = fcltyMap.get("KC");
			return environ;
			
		} else {
			resetSessionMap();
			return "getNewEnvironment";
		}
	}

	public static synchronized String setTestQaEnvironment(HashMap<String,String> loadMap) {

//		HashMap<String,String> loadMap = new HashMap<String,String>();
//		loadMap = new HashMap<String,String>();
//		loadMap.put("AWG-L1T1", "T1");
//		loadMap.put("AWG-L1",   "T2");
//		loadMap.put("KCT1",     "T1");
//		loadMap.put("KC",       "T2");
		
		FacilitySession facilitySession = new EnvironTestFcltySession().new FacilitySession(loadMap);
		fcltySessionHashTable.put("fcltyMap", facilitySession);
		
		return "setTestFclty";
	}
	public class FacilitySession {
		public HashMap<String,String> mawmOrgMap = new HashMap<String,String>();

		public FacilitySession(HashMap<String,String> fcltyMap) {
			mawmOrgMap = fcltyMap;
		}
	}
	public static synchronized void resetSessionMap() {
//		Set<String> hashMapKeys = fcltySessionHashTable.keySet(); 
		fcltySessionHashTable.clear();
	}
}
