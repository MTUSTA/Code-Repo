import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DESfinal {
	public static int counter = 0;

	/* d�zenlencek */
	public static String combiner_nonce_counter(String str1) {

		// substring containing last 8 characters -> LSB
		if (str1.length() > 1) {
			str1 = str1.substring(str1.length() - 1);
		}
		return str1;
	}

	/* xor combiner */
	private static byte[] xor(final byte[] input, final byte[] secret) {
		// 8 bit 1 bytes 64 bit 8 bytes
		final byte[] output = new byte[input.length];
		if (secret.length == 0) {
			throw new IllegalArgumentException("empty security key");
		}
		int spos = 0;
		for (int pos = 0; pos < input.length; ++pos) {
			output[pos] = (byte) (input[pos] ^ secret[spos]);
			++spos;
			if (spos >= secret.length) {
				spos = 0;
			}
		}
		return output;
	}

	public static byte[] encrypt_CBC(String plain_text, String key, byte[] IV) {
		byte[] crypted = null;
		try {
			byte[] xor_result = xor(plain_text.getBytes("UTF-8"), IV);
			SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "DES");
			/* Default block size 64/8 = 8 */
			Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, secret_key);
			// crypted bytes
			crypted = cipher.doFinal(xor_result);

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return crypted;
	}

	public static String decrypt_CBC(byte[] Cipher_text, String key, byte[] IV) {
		byte[] output_plain_text = null;
		try {
			// block cipher decryption
			SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "DES");
			Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, secret_key);
			// block cipher decryption

			output_plain_text = cipher.doFinal(Cipher_text);
			output_plain_text = xor(output_plain_text, IV);
			Main.for_exor_init_vector = Cipher_text;

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return new String(output_plain_text);
	}

	public static byte[] encrypt_OFB(String plain_text, String key, byte[] IV) {
		byte[] crypted = null;
		try {
			// block cipher decryption
			SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "DES");
			/* Default block size 128/8 = 16 */
			Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, secret_key);

			crypted = cipher.doFinal(IV);
			Main.for_exor_init_vector = crypted;
			crypted = xor(crypted, plain_text.getBytes("UTF-8"));

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return crypted;
	}

	public static String decrypt_OFB(byte[] Cipher_text, String key, byte[] IV) {
		byte[] plain_text = null;
		try {
			SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "DES");
			/* Default block size 128/8 = 16 */
			Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, secret_key);

			byte[] output = cipher.doFinal(IV);
			Main.for_exor_init_vector = output;
			plain_text = xor(output, Cipher_text);

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return new String(plain_text);

	}

	public static byte[] encrypt_CTR(String plain_text, String key, String nonce) {
		byte[] crypted = null;
		try {
			/* 56 bit nonce and 8bit counter concaatenation */
			String str1 = Integer.toString(counter);
			String nonce_and_couter = nonce + combiner_nonce_counter(str1);

			SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "DES");
			/* Default block size 64/8 = 8 */
			Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, secret_key);

			/* cryption */
			crypted = cipher.doFinal(nonce_and_couter.getBytes("UTF-8"));

			crypted = xor(crypted, plain_text.getBytes("UTF-8"));

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		counter++;
		return crypted;
	}

	public static String decrypt_CTR(byte[] Cipher_text, String key, String nonce) {
		byte[] plain_text = null;
		try {
			/* 56 bit nonce and 8bit counter concaatenation */
			String str1 = Integer.toString(counter);
			String nonce_and_couter = nonce + combiner_nonce_counter(str1);

			SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "DES");
			/* Default block size 64/8 = 8 */
			Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, secret_key);
			byte[] output = cipher.doFinal(nonce_and_couter.getBytes("UTF-8"));

			plain_text = xor(output, Cipher_text);

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		counter++;
		return new String(plain_text);
	}
}
