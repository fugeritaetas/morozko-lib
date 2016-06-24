package org.morozko.java.core.util.crypt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.morozko.java.core.io.StreamIO;

public class CryptObject {
	
	public static CryptObject newCryptObject( String filePath ) throws Exception {
		return newCryptObject( new File( filePath ) );
	}	
	
	public static CryptObject newCryptObject( File file ) throws Exception {
		return newCryptObject( new FileInputStream( file ) );
	}
	
	public static CryptObject newCryptObject( InputStream is ) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		StreamIO.pipeStream( is , baos, StreamIO.MODE_CLOSE_BOTH );
		return newCryptObject( baos.toByteArray() );
	}	
	
	public static CryptObject newCryptObject( byte[] key ) throws Exception {
		CryptObject c = new CryptObject( key );
		return c;
	}
	
	private CryptObject( byte[] key ) {
		this.KEY = key;
	}
	
	private byte[] KEY = null;

	private static byte[] fromHEX(String hex) {
		byte[] b = new byte[hex.length() / 2];
		for (int k = 0; k < hex.length(); k += 2) {
			Integer current = Integer.decode("0x" + hex.substring(k, k + 2));
			b[k / 2] = (byte) (current.intValue() - 128);
		}
		return b;

	}

	private static String toHEX(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int k = 0; k < b.length; k++) {
			String current = Integer.toHexString(128 + b[k]);
			if (current.length() == 1) {
				current = "0" + current;
			}
			sb.append(current);
		}
		return sb.toString();
	}

	public byte[] encrypt(String original) throws Exception {
		SecretKeySpec skey = new SecretKeySpec(KEY, "AES");
		Cipher cipherCrypt = Cipher.getInstance("AES");
		cipherCrypt.init(Cipher.ENCRYPT_MODE, skey);
		byte[] encrypted = cipherCrypt.doFinal(original.getBytes());
		return encrypted;
	}

	public String decrypt(byte[] encrypted) throws Exception {
		SecretKeySpec skey = new SecretKeySpec(KEY, "AES");
		Cipher cipherDecrypt = Cipher.getInstance("AES");
		cipherDecrypt.init(Cipher.DECRYPT_MODE, skey);
		byte[] original = cipherDecrypt.doFinal(encrypted);
		return new String(original);
	}

	public String encryptHex(String original) throws Exception {
		byte[] b = encrypt(original);
		return toHEX(b);

	}

	public String decryptHex(String encrypted) throws Exception {
		byte[] b = fromHEX(encrypted);
		return decrypt(b);

	}

	public static byte[] generateKey(String file) throws Exception {
		return generateKey( file, 128 );
	}
	
	public static byte[] generateKey(String file, int len) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(len);
		SecretKey skey = kgen.generateKey();
		byte[] raw = skey.getEncoded();
		File f = new File(file);
		FileOutputStream fos = new FileOutputStream(f);
		ByteArrayInputStream is = new ByteArrayInputStream(raw);
		StreamIO.pipeStream(is, fos, StreamIO.MODE_CLOSE_BOTH);
		return raw;

	}

}
