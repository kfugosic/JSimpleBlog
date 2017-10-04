package hr.fer.zemris.java.hw06.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * Program enkriptira lozinku koristeću SHA-256.
 * 
 * @author Kristijan Fugosic
 *
 */
public class Crypto {

	/**
	 * Metoda u kojoj se dani string enkriptira.
	 * @param password Lozinka
	 * @return Enkriptirana lozinka
	 */
	public static String calcHash(String password) {
		
		MessageDigest digest = null;
				
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("[MessageDigest] Something went wrong, closing program.");
			System.exit(1);
		}
		
		byte[] b1 = digest.digest(password.getBytes());
		return Util.bytetohex(b1);
	
	}

}
