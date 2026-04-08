package aa.test.qa;

import java.text.ParseException;
import java.util.HashMap;

//VERSION IS AOF 2-20-2026 BEFORE I STARTING USING THE NEW TABLE.  LOGIC WILL BE DIFFERENT NOW.
//KEEPING THIS TO MAKE SURE I DON'T NEED IT AGAIN.

public class EnvironTestFcltySessionVDI {

	private static HashMap<String, FacilitySession> fcltySessionHashTable = new HashMap<String, FacilitySession>();


	
	public static synchronized String getTestQaEnvironment(String fcility, String runningEnvironment, String queue) throws ParseException {

		if(runningEnvironment.contains("IIBP1")) {
			//no need to do any remote queue logic
			return "production";
		}else
		if (fcltySessionHashTable.containsKey("fcltyMap")) {
			HashMap<String, String> fcltyMap = fcltySessionHashTable.get("fcltyMap").mawmOrgMap;

			String environ = fcltyMap.get(fcility);
			String queueName = getQueueName(runningEnvironment, environ, queue);
			
			return queueName;

		} else {
//			resetSessionMap();
			return "readTableAgain";
		}
	}
	private static String getQueueName(String runningEnvironment, String tableEnviron, String queue) {
		
		if(tableEnviron==null)
			return queue;
		
		String runningEnviron = runningEnvironment.substring(3,5);
		
		if(tableEnviron.equals("T1") || tableEnviron.equals("T2") || tableEnviron.equals("Q1") || tableEnviron.equals("Q2")) {
			if(runningEnviron.equals(tableEnviron)) {
				return "sameEnviron";
			}else
			{
				return queue+"."+tableEnviron;
			}
		}
		return queue;
	}	
	public static synchronized String getTestQaQueueMgrName(String fcility, String runningEnvironment) throws ParseException {

		if(runningEnvironment.contains("IIBP1")) {
			return "AISP1";
		}else
		if (fcltySessionHashTable.containsKey("fcltyMap")) {
			HashMap<String, String> fcltyMap = fcltySessionHashTable.get("fcltyMap").mawmOrgMap;

			String environ = fcltyMap.get(fcility);
			String queueMgrName = getQueueMgrName(environ);
			
			return queueMgrName;

		} else {
//			resetSessionMap();
			return "AIST1";
		}
	}
	private static String getQueueMgrName(String tableEnviron) {
		
		if(tableEnviron==null)
			return "AIST1";
		
		if(tableEnviron.equals("T1") ) {
			return "AIST1";
		}else
		if(tableEnviron.equals("T2") ) {
			return "AIST2";
		}else
		if(tableEnviron.equals("Q1") ) {
			return "AISQ1";
		}else
		if(tableEnviron.equals("Q2") ) {
			return "AISQ2";
		}
		return "AIST1";
	}

	public static synchronized String isSessionNull() {
		if (fcltySessionHashTable == null) {
			return "readTableAgain";
		} else
			return "continue";
	}

	public static synchronized String setTestQaEnvironment(HashMap<String, String> loadMap) {

		FacilitySession facilitySession = new EnvironTestFcltySessionVDI().new FacilitySession(loadMap);
		fcltySessionHashTable.put("fcltyMap", facilitySession);

		return "setTestFclty";
	}

	public class FacilitySession {
		public HashMap<String, String> mawmOrgMap = new HashMap<String, String>();

		public FacilitySession(HashMap<String, String> fcltyMap) {
			mawmOrgMap = fcltyMap;
		}
	}

	public static String resetSessionMap() {
		fcltySessionHashTable.clear();
		return "resetSessionMap";
	}	
}
