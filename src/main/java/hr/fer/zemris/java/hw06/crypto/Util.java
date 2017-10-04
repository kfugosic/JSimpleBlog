package hr.fer.zemris.java.hw06.crypto;

/**
 * Class that provides two methods, hextobyte and bytetohex,
 * Each provides conversion according to its name.
 * @author Kristijan Fugosic
 *
 */
public class Util {
	
	/**
	 * Converts hex-encoded string into byte array
	 * @param keyText hex-encoded string
	 * @return byte array
	 * @throws IllegalArgumentException if string is odd-sized or has invalid characters
	 */
	public static byte[]  hextobyte(String keyText){
		if ((keyText.length() % 2) != 0){
			throw new IllegalArgumentException("Input text must contain an even number of characters!");
		}

		int len = keyText.length();
		byte[] bytes = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			int dig1 = Character.digit(keyText.charAt(i), 16);
			int dig2 = Character.digit(keyText.charAt(i+1), 16);
			if(dig1 == -1 || dig2 == -1){
				throw new IllegalArgumentException("Input text contains invalid characters!");
			}
			bytes[i / 2] = (byte) ((dig1 << 4) + dig2);
		}
		
		return bytes;
	}
	
	/**
	 * Converts byte array to hex-encoded string
	 * @param bytes byte array
	 * @return hex-encoded string
	 */
	public static String  bytetohex(byte[] bytes){
		StringBuilder sb = new StringBuilder();
		for(byte b: bytes){
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
	
}
