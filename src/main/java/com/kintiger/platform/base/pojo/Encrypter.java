package com.kintiger.platform.base.pojo;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

public class Encrypter {
	private static final Logger log = Logger.getLogger(Encrypter.class);
	private static byte[] salt = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8,
			(byte) 0x32, (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03 };
	private static int iterationCount = 19;
	private Cipher ecipher;
	private Cipher dcipher;

	public Encrypter() {
		String passPhrase = "!@#$%^&*()_&^$%^$%$#@KJOIJ*&W&^T$%$#W@%*&(U)(JUOIJJIieohfiuehgtru";

		try {
			KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt,
					iterationCount);
			SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
					.generateSecret(keySpec);
			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt,
					iterationCount);

			ecipher = Cipher.getInstance(key.getAlgorithm());
			dcipher = Cipher.getInstance(key.getAlgorithm());
			ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException("Failed to initialize cookie encoder", e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Failed to initialize cookie encoder", e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException("Failed to initialize cookie encoder", e);
		} catch (InvalidKeySpecException e) {
			throw new RuntimeException("Failed to initialize cookie encoder", e);
		} catch (InvalidAlgorithmParameterException e) {
			throw new RuntimeException("Failed to initialize cookie encoder", e);
		}
	}

	public byte[] encrypt(byte[] plaintext) {
		try {
			return ecipher.doFinal(plaintext);
		} catch (IllegalStateException e) {
			log.error(
					"Failed to encrypt object" + ArrayUtils.toString(plaintext),
					e);
		} catch (IllegalBlockSizeException e) {
			log.error(
					"Failed to encrypt object" + ArrayUtils.toString(plaintext),
					e);
		} catch (BadPaddingException e) {
			log.error(
					"Failed to encrypt object" + ArrayUtils.toString(plaintext),
					e);
		}

		return null;
	}

	public byte[] decrypt(byte[] cryptotext) {
		try {
			return dcipher.doFinal(cryptotext);
		} catch (IllegalStateException e) {
			log.error(
					"Failed to decrypt object"
							+ ArrayUtils.toString(cryptotext), e);
		} catch (IllegalBlockSizeException e) {
			log.error(
					"Failed to decrypt object"
							+ ArrayUtils.toString(cryptotext), e);
		} catch (BadPaddingException e) {
			log.error(
					"Failed to decrypt object"
							+ ArrayUtils.toString(cryptotext), e);
		}

		return null;
	}
}
