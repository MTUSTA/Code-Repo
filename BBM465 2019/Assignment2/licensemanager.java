import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;


public class licensemanager {
	private static PrivateKey privateKey;
	public PublicKey publicKey;
	public static byte[] enc_Data;
	public String decrypted_text;
	public byte[] hashed_text;
	public byte[] signature;

	public licensemanager() {

		this.publicKey = get_public_key_from_file();
		this.privateKey = get_private_key_from_file();
	}
	/*this function accepts data from the Client*/
	public static void accept_enc_data(byte[] data) {
		enc_Data = data;
		
	}
	/*this function sends data to Client */
	public static void send(byte[] signature, main.client client1) {
		client1.accept_signature(signature);
		
	}
	// this function decrypts data using private key and rsa algorithm
	public static String decrypt(byte[] buffer) {
		try {
	        Cipher decryptCipher = Cipher.getInstance("RSA");
	        decryptCipher.init(Cipher.DECRYPT_MODE, licensemanager.privateKey);
	        byte[] result = decryptCipher.doFinal(buffer);
	        return new String(result, "UTF8");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	/*this function hashes data by md5 algorithm*/
	public static byte[] md5(String data) {
		byte[] resultByte = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(data.getBytes("UTF8"));
			resultByte = messageDigest.digest();			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultByte;
	}
	/*this function signs data using private key and SHA256withRSA algorithm*/
	public static byte[] sign(byte[] hashed_by_md5) {
		byte[] signed = null;
		try {
			Signature privateSignature = Signature.getInstance("SHA256withRSA");
			privateSignature.initSign(privateKey);
			privateSignature.update(hashed_by_md5);
			signed = privateSignature.sign();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return signed;
	}
	/*this function reads the private key from the file and stores it in memory*/
	private static PrivateKey get_private_key_from_file(){
		PrivateKey return_private = null;
		try {
			byte[] keyBytes = Files.readAllBytes(Paths.get("private.key"));

			PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			return_private = kf.generatePrivate(spec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return return_private;

	}
	/*this function reads the public key from the file and stores it in memory*/
	public static PublicKey get_public_key_from_file() {
		PublicKey return_key = null;
		try {
			byte[] keyBytes = Files.readAllBytes(Paths.get("public.key"));

			X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			return_key = kf.generatePublic(spec);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return return_key;
	}

}
