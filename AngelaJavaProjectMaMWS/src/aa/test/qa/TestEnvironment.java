package aa.test.qa;

import java.text.ParseException;
import java.util.HashMap;

public class TestEnvironment {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		String abc = "IIBT1";
		System.out.println(abc + " "+abc.substring(3,5));
		System.out.println(abc + " "+abc.length());
		
		HashMap<String,String> loadMap = new HashMap<String,String>();
		loadMap = new HashMap<String,String>();
		loadMap.put("AWG-L1T1", "T1");
		loadMap.put("AWG-L1",   "T2");
		loadMap.put("KCT1",     "T1");
		loadMap.put("KC",       "T2");

		EnvironTestFcltySession.setTestQaEnvironment(loadMap);
		String test = EnvironTestFcltySession.getTestQaEnvironment("KC");
		
		System.out.println("TestEnvironment "+test);
	}

}
