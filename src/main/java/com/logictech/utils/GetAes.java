package com.logictech.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import static com.logictech.utils.MessageUtils.get;

public final class GetAes {

	// 判断集合为空
	public static boolean isEmpty(List<?> arrList) {
		return !isNotEmpty(arrList);
	}

	// 判断集合不为空
	public static boolean isNotEmpty(List<?> arrList) {
		return arrList != null && !arrList.isEmpty();
	}

	// 判断Map为空
	public static boolean isEmpty(Map<String, Object> map) {
		return !isNotEmpty(map);
	}

	// 判断Map不为空
	public static boolean isNotEmpty(Map<String, Object> map) {
		return map != null && map.size() != 0;
	}

	// 判断Object不为空
	public static boolean isNotEmpty(Object o) {
		return o != null;
	}

	// 获取Map中Key对于的字符串
	public static String getMapValue(Map<String, Object> map, String key) {
		if (isEmpty(map) || StringUtils.isEmpty(key)) {
			return "";
		}
		return String.valueOf(map.get(key));
	}

	// 字符转左侧补足
	public static String addLeft(String oldStr, int len, String addStr) {
		if (addStr.length() == 0 || len == 0) {
			return oldStr;
		}
		while (len > oldStr.length()) {
			oldStr = addStr + oldStr;
		}
		return oldStr;
	}

	/**
	 * 正则表达式验证，不满足返回错误信息
	 * @param inStr 验证的字符串
	 * @param regex 正则表达式
	 * @param msgCd 提示信息CODE
	 * @param param 提示信息的参数
	 * @return 通过返回true;
	 * @throws Exception 其他异常及不满足的情况都抛出错误信息
	 */
	public static boolean ckPattern(String inStr, String regex, String msgCd, String[] param) throws Exception {
		boolean retSta = Pattern.matches(regex, inStr);
		if (!retSta) {
			throw new Exception(get(msgCd, param));
		}
		return retSta;
	}

	/**
	 * 正则表达式验证，不满足返回错误信息
	 * @param inStr 验证的字符串
	 * @param regex 正则表达式
	 * @param msgCd 提示信息CODE
	 * @return 通过返回true;
	 * @throws Exception 其他异常及不满足的情况都抛出错误信息
	 */
	public static boolean ckPattern(String inStr, String regex, String msgCd) throws Exception {
		return ckPattern(inStr, regex, msgCd, new String[] {});
	}

	/**
	 * 验证字符串的长度
	 * @param inStr 验证的字符串
	 * @param min 最小长度
	 * @param max 最大长度
	 * @param msgCd 提示信息CODE
	 * @return 通过返回true;
	 * @throws Exception 其他异常及不满足的情况都抛出错误信息
	 */
	public static boolean ckStrLen(String inStr, int min, int max, String msgCd, String[] param) throws Exception {
		boolean retSta = false;
		if (inStr == null) {
			retSta = false;
		}
		if (inStr.length() >= min && inStr.length() <= max) {
			retSta = true;
		}
		if (!retSta) {
			throw new Exception(get(msgCd, param));
		}
		return retSta;
	}

	/**
	 * 验证字符串的长度
	 * @param inStr 验证的字符串
	 * @param max 最大长度
	 * @param msgCd 提示信息CODE
	 * @return 通过返回true;
	 * @throws Exception 其他异常及不满足的情况都抛出错误信息
	 */
	public static boolean ckMaxStrLen(String inStr, int max, String msgCd, String[] param) throws Exception {
		return ckStrLen(inStr, 0, max, msgCd, param);
	}

	/**
	 * 验证字符串非空
	 * @param inStr 验证的字符串
	 * @param msgCd 提示信息CODE
	 * @return 通过返回true;
	 * @throws Exception 其他异常及不满足的情况都抛出错误信息
	 */
	public static boolean ckNotEmpty(String inStr, String msgCd, String[] param) throws Exception {
		if (StringUtils.isEmpty(inStr)) {
			throw new Exception(get(msgCd, param));
		}
		return true;
	}

	/**
	 * MD5加密
	 * @param input 需要加密的字符串
	 * @return 加密后的字符串
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String encoderByMd5(String input) {
		String md5str = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] buff = md.digest(input.getBytes());
			md5str = bytesToHex(buff);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5str;
	}

	/**
	 * AES加密
	 *
	 * @param input 需要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String encoderByAES(String input, String aesKeyPath, String aesIVPath) {
		if (StringUtils.isEmpty(input)) {
			return null;
		}
		try {
			return Base64.encodeBase64String(
					aesByte(input.getBytes("UTF-8"), Cipher.ENCRYPT_MODE, aesKeyPath, aesIVPath));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * AES解密     *
	 *
	 * @param ciphertext 需要解密的密文
	 * @return 明文
	 */
	public static String decoderByAES(String ciphertext, String aesKeyPath, String aesIVPath) {

		try {
			return new String(aesByte(Base64.decodeBase64(ciphertext.getBytes("UTF-8")), Cipher.DECRYPT_MODE,
					aesKeyPath, aesIVPath), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] aesByte(byte[] content, int mode, String aesKeyPath, String aesIVPath) {
		// 读取文件中的信息作为秘钥 和偏移量
		Scanner input = null;
		byte[] result = null;
		try {
			// 从文件进行读取密钥和偏移量
			input = new Scanner(new File(aesKeyPath));
			byte[] key = input.nextLine().getBytes();
			input = new Scanner(new File(aesIVPath));
			byte[] iv = input.nextLine().getBytes();
			// 密钥进行MD5加密，防止密钥泄露
			MessageDigest md = MessageDigest.getInstance("MD5");
			key = md.digest(key);
			// 20161122修正==========================================
			/*
			 * KeyGenerator kgen = KeyGenerator.getInstance("AES"); kgen.init(256, new
			 * SecureRandom(key)); SecretKey secretKey = kgen.generateKey(); byte[]
			 * enCodeFormat = secretKey.getEncoded();
			 */
			SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
			// ======================================================
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // "算法/模式/补码方式"
			IvParameterSpec ivps = new IvParameterSpec(iv);// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			cipher.init(mode, skeySpec, ivps);
			result = cipher.doFinal(content);

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				input.close();
			}
		}
		return result;
	}

	public static String bytesToHex(byte[] bytes) {
		StringBuffer md5str = new StringBuffer();
		int digital;
		for (int i = 0; i < bytes.length; i++) {
			digital = bytes[i];
			if (digital < 0) {
				digital += 256;
			}
			if (digital < 16) {
				md5str.append("0");
			}
			md5str.append(Integer.toHexString(digital));
		}
		return md5str.toString();
	}

	/**
	 * 仅供Excel使用的防治下标越界的错误提示
	 * @param list
	 * @param sub
	 * @return
	 * @throws Exception
	 */
	public static String getExcelMapValue(List<Object> list, int sub) throws Exception {
		if (isEmpty(list)) {
			return "";
		}
		String retStr;
		try {
			retStr = String.valueOf(list.get(sub));
		} catch (Exception e) {
			throw new Exception(get("EM0029"));
		}
		return retStr;
	}

	/**
	 * 电话号码格式化
	 * 
	 * @param mobileNum
	 * @return
	 */
	public static String PrivateFmt4Moblie(String mobileNum) {
		if (!StringUtils.isEmpty(mobileNum)) {
			return mobileNum.replace(mobileNum.substring(3, 7), "****");
		} else {
			return "";
		}
	}

	/**
	 * 银行号码格式化
	 * 
	 * @param bankNum
	 * @return
	 */
	public static String PrivateFmt4BankNo(String bankNum) {
		if (!StringUtils.isEmpty(bankNum)) {
			String temp = bankNum;
			String first = temp.substring(0, 6);
			String last = temp.substring(temp.length() - 4, temp.length());
			return first + "********" + last;
		} else {
			return "";
		}
	}

	/**
	 * 身份证格式化  后四前10
	 *
	 * @return
	 */
	public static String PrivateFmtIdNo(String IdNo) {
		if (!StringUtils.isEmpty(IdNo)) {
			String temp = IdNo;
			String first = temp.substring(0, 10);
			String last = temp.substring(temp.length() - 4, temp.length());
			return first + "****" + last;
		} else {
			return "";
		}
	}

	public static void main(String[] args) {
		String myPwd = "15850165540";
		System.out.println(
				encoderByAES(myPwd, "E:/Workspace/config/suzyxt/key.txt", "E:/Workspace/config/suzyxt/iv.txt"));
		// System.out.println(encoderByAES(myTelNo,"E:/Workspace/config/suzyxt/key.txt","E:/Workspace/config/suzyxt/iv.txt"));
		//System.out.println(encoderByMd5(myPwd));
		// System.out.println(PrivateFmtIdNo("330324199411021012"));
	}
}