/**
 * Project: A00973641_assignment1
 * File: Decoder.java
 * Date: Jun 28, 2016
 * Time: 3:06:47 PM
 */
package a00973641.database.decode;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * @author Mara
 *
 */
public class Decoder {

	// salt for password-based encryption-decryption algorithm
	private static final byte[] salt = { (byte) 0xf5, (byte) 0x33, (byte) 0x01, (byte) 0x2a, (byte) 0xb2, (byte) 0xcc,
			(byte) 0xe4, (byte) 0x7f };

	// iteration count
	private int iterationCount = 100;

	public byte[] readAndDecrypt(String fileName, String password) {
		Cipher cipher = null;

		try {
			// create password based encryption key object
			PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
			// obtain instance for secret key factory
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			// generate secret key for encryption
			SecretKey secretKey = keyFactory.generateSecret(keySpec);
			// specify parameters used for encryption
			PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, iterationCount);

			// get cipher instance
			cipher = Cipher.getInstance("PBEWithMD5AndDES");
			// initialize cipher in decrypt mode
			cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);
		}
		// handle NoSuchAlgorithmException
		catch (NoSuchAlgorithmException exception) {
			exception.printStackTrace();
			System.exit(1);
		}

		// handle InvalidKeySpecException
		catch (InvalidKeySpecException exception) {
			exception.printStackTrace();
			System.exit(1);
		}

		// handle InvalidKeyException
		catch (InvalidKeyException exception) {
			exception.printStackTrace();
			System.exit(1);
		}

		// handle NoSuchPaddingException
		catch (NoSuchPaddingException exception) {
			exception.printStackTrace();
			System.exit(1);
		}

		// handle InvalidAlgorithmParameterException
		catch (InvalidAlgorithmParameterException exception) {
			exception.printStackTrace();
			System.exit(1);
		}

		byte[] decryptedText = readFile(cipher, fileName);
		return decryptedText;
	}

	/**
	 * Read byte values from the given file.
	 * 
	 * @param cipher
	 * @param fileName
	 * @return vector of bytes read from given file
	 */
	private byte[] readFile(Cipher cipher, String fileName) {
		FileInputStream inputfile = null;
		ByteArrayOutputStream buffer = null;
		CipherInputStream in = null;

		// get byte value of given file
		try {
			inputfile = new FileInputStream(fileName);
			buffer = new ByteArrayOutputStream();
			int ch;
			in = new CipherInputStream(inputfile, cipher);
			@SuppressWarnings("unused")
			int i = 0;
			while ((ch = in.read()) != -1) {
				byte b = (byte) (ch);
				buffer.write(b);
				i++;
			}

			in.close();
		} catch (FileNotFoundException e) {
			// TODO
		} catch (IOException e) {
			// TODO
		}

		return buffer.toByteArray();
	}
}
