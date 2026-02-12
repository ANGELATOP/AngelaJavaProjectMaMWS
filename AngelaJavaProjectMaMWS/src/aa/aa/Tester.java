package aa.aa;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		String value = "40"; //final value should be 0000000040  size10
//		System.out.println(appendValues("40", 10, "0"));
//		
//		getFirstValue();
//		
//		System.out.println(convertStringToInt("123"));
//		
//		System.out.println("testTruncate="+truncate("9111111110922222222033333333309444444440",20));

		System.out.println(calcCatchWght("001","15000","4"));
	}
	
	public static String calcCatchWght(String asnSeqNbr, String wght, String ilpnReceived) {
		
		
		int value1= Integer.parseInt(ilpnReceived);
		int value2=Integer.parseInt(ilpnReceived);
		
		int calcValue = Integer.parseInt(wght)/Integer.parseInt(ilpnReceived);
		return asnSeqNbr+"|"+String.valueOf(calcValue);
	}
	public static String truncate(String value, int maxSize) {
		
		return value.substring(0,maxSize);
	}
	public static int convertStringToInt(String value) {
		int num = Integer.parseInt(value);
		return num;
	}
	public static void getFirstValue() {
		String value = "abc";
		
		System.out.println("getFirstValue="+value.substring(0, 1));
		System.out.println("getFirstValue="+value.substring(1, 1));
		System.out.println("getFirstValue="+value.substring(1, 2));
		System.out.println("getFirstValue="+value.substring(1, 3));
	}
	public static String appendValues(String inputValue, int finalSize, String appendValue){
		
		int inputSize = inputValue.length();
		int diff = finalSize-inputSize;
		
		StringBuilder newValue = new StringBuilder();
		
		for(int i=0;i<diff;i++) {
			newValue.append(appendValue);
		}
		return newValue.toString()+inputValue;
	}

}
