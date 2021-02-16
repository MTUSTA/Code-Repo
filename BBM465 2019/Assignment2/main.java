import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.Signature;

import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.sound.midi.Soundbank;

public class main {

	public static class client {

		private String username, serial_number, mac_adress, disk_serial_number, motherboard_id;
		private static PublicKey public_key;
		private static byte[] enc_data, signature;
		public byte[] hashed_text;

		public client() {
			this.username = System.getProperty("user.name");
			this.serial_number = readfile().get(0);
			this.mac_adress = get_mac_adress();
			this.disk_serial_number = get_serial_number("diskdrive");
			this.motherboard_id = get_serial_number("baseboard");
			this.public_key = get_public_key_from_file();
		}

		public static ArrayList<String> readfile() {
			ArrayList<String> line = null;
			try {
				line = new ArrayList<String>();
				// the file to be opened for reading
				FileInputStream fis = new FileInputStream("user_serial.txt");
				Scanner sc = new Scanner(fis); // file to be scanned
				// returns true if there is another line to read
				while (sc.hasNextLine()) {
					//System.out.println(sc.nextLine()); // returns the line that was skipped
					line.add(sc.nextLine());
				}
				sc.close(); // closes the scanner
			} catch (IOException e) {
				e.printStackTrace();
			}
			return line ;
		}

		/* this function reads the public key from the file and stores it in memory */
		private static PublicKey get_public_key_from_file() {
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

		/* this function returns disk_serial_number or motherboard_id by parameter */
		private static String get_serial_number(String input) {
			StringBuilder sb = null;
			try {
				// diskdrive or baseboard
				String sc = "cmd /c" + "wmic " + input + " get serialnumber";

				Process p = Runtime.getRuntime().exec(sc);
				p.waitFor();

				BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

				String line;
				sb = new StringBuilder();

				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return sb.substring(sb.toString().lastIndexOf("r") + 1).trim();
		}

		/*
		 * returns the mac address of the machine if there is an active internet
		 * connection
		 */
		private static String get_mac_adress() {
			InetAddress ip;
			StringBuilder sb = null;
			try {
				ip = InetAddress.getLocalHost();

				NetworkInterface network = NetworkInterface.getByInetAddress(ip);

				byte[] mac = network.getHardwareAddress();

				sb = new StringBuilder();
				/* if no connection, set mac adress = "standart" */
				if (mac == null) {
					mac = "Standart".getBytes();
				}
				for (int i = 0; i < mac.length; i++) {
					sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
				}

			} catch (Exception e) {

				e.printStackTrace();

			}
			return sb.toString();
		}

		/* this function sends data to license manager */
		public void send(byte[] enc_data, licensemanager licence_manager) {
			licence_manager.accept_enc_data(enc_data);

		}

		/* this function accepts data from the license manager */
		public static void accept_signature(byte[] signature1) {
			signature = signature1;
		}

		// this function encrypts data using public key and rsa algorithm
		private static byte[] encrypt(String plainText, PublicKey publicKey) {
			byte[] cipherText = null;

			try {
				Cipher encryptCipher = Cipher.getInstance("RSA");
				encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
				cipherText = encryptCipher.doFinal(plainText.getBytes("UTF8"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return cipherText;
		}

		/* this function hashes data by md5 algorithm */
		private static byte[] md5(String data) {
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

		/*
		 * this function verify signature using public key and SHA256withRSA algorithm
		 */
		private static boolean verification(byte[] hashed_text) {
			boolean bool = false;
			try {
				Signature sign_verify = Signature.getInstance("SHA256withRSA");
				sign_verify.initVerify(public_key);
				sign_verify.update(hashed_text);
				bool = sign_verify.verify(signature);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bool;
		}

		// Method which write the bytes into a file
		private static void writeByte(byte[] bytes) {
			try {
				// Initialize a pointer
				// in file using OutputStream
				OutputStream os = new FileOutputStream(new File("license.txt"));

				// Starts writing the bytes in it
				os.write(bytes);
				// System.out.println("Successfully" + " byte inserted");

				// Close the file
				os.close();
			}

			catch (Exception e) {
				System.out.println("Exception: " + e);
			}
		}

		/**
		 * This method uses java.io.FileInputStream to read file content into a byte
		 * array
		 * 
		 * @param file
		 * @return
		 */
		private static byte[] readFileToByteArray(File file) {
			FileInputStream fis = null;
			// Creating a byte array using the length of the file
			// file.length returns long which is cast to int
			byte[] bArray = new byte[(int) file.length()];
			try {
				fis = new FileInputStream(file);
				fis.read(bArray);
				fis.close();

			} catch (IOException ioExp) {
				ioExp.printStackTrace();
			}
			return bArray;
		}

		// helper function for file exist
		public static boolean check() {
			return new File("license.txt").exists();
		}
	}

	/* the assignment process begins */
	private static void process(client client1) {
		System.out.println("Client started...");
		System.out.println("My Mac: " + client1.mac_adress);
		System.out.println("My DiskID: " + client1.disk_serial_number);
		System.out.println("My Motherboard ID: " + client1.motherboard_id);
		System.out.println("LicenseManager service started...");
		licensemanager licence_manager = new licensemanager();

		// "user-serialid-hw spec info"
		String plain_text = client1.username + "$" + client1.serial_number + "$" + client1.mac_adress + "$"
				+ client1.disk_serial_number + "$" + client1.motherboard_id;
		System.out.println("Client -- Raw Licence Text: " + plain_text);

		client1.enc_data = client1.encrypt(plain_text, client1.public_key);
		System.out.println("Client -- Encryted License Text: " + Base64.getEncoder().encodeToString(client1.enc_data));

		client1.hashed_text = client1.md5(plain_text);
		System.out.println(
				"Client -- MD5fied Plain License Text: " + Base64.getEncoder().encodeToString(client1.hashed_text));

		System.out.println("Server -- Server is being requested...");

		client1.send(client1.enc_data, licence_manager);
		System.out.println(
				"Server -- Incoming Encryted Text: " + Base64.getEncoder().encodeToString(licence_manager.enc_Data));

		licence_manager.decrypted_text = licence_manager.decrypt(licence_manager.enc_Data);
		System.out.println("Server -- Decrypted Text: " + licence_manager.decrypted_text);

		licence_manager.hashed_text = licence_manager.md5(licence_manager.decrypted_text);
		System.out.println("Server -- MD5fied Plain License Text: "
				+ Base64.getEncoder().encodeToString(licence_manager.hashed_text));

		licence_manager.signature = licence_manager.sign(licence_manager.hashed_text);
		System.out.println(
				"Server -- Digital Signature: " + Base64.getEncoder().encodeToString(licence_manager.signature));

		licence_manager.send(licence_manager.signature, client1);
		if (client1.verification(client1.hashed_text)) {
			// store licence in license.txt file
			client1.writeByte(client1.signature);
			System.out.println("Client -- Succeed. The License file content is secured and signed by the server");

		} else {
			System.out.println("Client -- verification failed");
		}
	}

	public static void main(String[] args) {
		client client1 = new client();
		// check license file if existed
		if (!client1.check()) {
			process(client1);
		} else {
			// System.out.println("license file created, checking");
			// "user-serialid-hw spec info"
			String plain_text = client1.username + "$" + client1.serial_number + "$" + client1.mac_adress + "$"
					+ client1.disk_serial_number + "$" + client1.motherboard_id;
			client1.enc_data = client1.encrypt(plain_text, client1.public_key);
			client1.hashed_text = client1.md5(plain_text);
			client1.signature = client1.readFileToByteArray(new File("license.txt"));
			if (client1.verification(client1.hashed_text)) {
				System.out.println("Succeed. The license is correct.");
			} else {
				System.out.println("The license file has been broken!!");
				// re-execute the licensing process again to obtain a valid digital signature
				process(client1);
			}

		}

	}

}
