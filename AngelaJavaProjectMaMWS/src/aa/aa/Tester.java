package aa.aa;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String value = "40"; //final value should be 0000000040  size10
		System.out.println(appendValues("40", 10, "0"));

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
