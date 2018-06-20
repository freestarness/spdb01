package com.logictech.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5Util {
	private static final Logger logger = LoggerFactory.getLogger(MD5Util.class);

	private MD5Util() {
	}

	public static String toMD5(String signature) {
		MessageDigest mDigest = null;
		try {
			mDigest = MessageDigest.getInstance("MD5");
			mDigest.update(signature.getBytes());
			return encodeHex(mDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			logger.error("NoSuchAlgorithmException", e);
		}
		return null;
	}

	private static String encodeHex(byte[] bytes) {
		StringBuffer buffer = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10)
				buffer.append("0");
			buffer.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		String str = "123456";
		System.out.println(toMD5(str));
	}
}
