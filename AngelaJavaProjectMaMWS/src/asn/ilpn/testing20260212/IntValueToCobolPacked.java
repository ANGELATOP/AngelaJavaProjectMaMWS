package asn.ilpn.testing20260212;

public class IntValueToCobolPacked {

    public static void main(String[] args) {
//        int number = -12345;
//        int totalDigits = 5; // COBOL PIC S9(5) COMP-3

        int number = 77;
        int totalDigits = 9; // COBOL PIC S9(5) COMP-3
       
        
        byte[] packed = toPackedDecimal(number, totalDigits);

        // Print in hex for verification
        System.out.println("Packed decimal (hex): " + bytesToHex(packed));
    }
	   /**
     * Converts an integer to COBOL packed decimal (COMP-3) byte array.
     * @param value integer to convert
     * @param totalDigits total number of digits (including sign digit)
     * @return byte array in packed decimal format
     */
    public static byte[] toPackedDecimal(int value, int totalDigits) {
        boolean negative = value < 0;
        String numStr = String.format("%0" + totalDigits + "d", Math.abs(value));

        // Append sign nibble
        char signNibble = negative ? 'D' : 'C';
        numStr += signNibble;

        // Convert to packed decimal bytes
        int len = numStr.length();
        byte[] packed = new byte[(len + 1) / 2];
        for (int i = 0; i < len; i += 2) {
            String byteStr = numStr.substring(i, Math.min(i + 2, len));
            packed[i / 2] = (byte) Integer.parseInt(byteStr, 16);
        }
        return packed;
    }


    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString().trim();
    }
}
