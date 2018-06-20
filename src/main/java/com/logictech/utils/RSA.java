/*
 * Copyright 2017 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.logictech.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.*;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 类RSA.java的实现描述：
 * 
 * @author chenzhong 2017年5月24日 下午5:10:08
 */
public class RSA {

    public static String rsaEncrypt(String content, String publicKeyPath, String charset) throws Exception {
    	String publicKey = getKeyPath(publicKeyPath);
    	PublicKey pubKey = getPublicKeyFromX509("RSA", new ByteArrayInputStream(publicKey.getBytes()));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] data = content.getBytes(charset);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密  
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > 117) {
                cache = cipher.doFinal(data, offSet, 117);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * 117;
        }
        //Base64.encodeBase64(out.toByteArray())
        byte[] encryptedData = Base64.encodeBase64(out.toByteArray());
        out.close();

        return new String(encryptedData, charset);
    }

    public static PublicKey getPublicKeyFromX509(String algorithm, InputStream ins) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        StringWriter writer = new StringWriter();
        //StreamUtils.io(new InputStreamReader(ins), writer);
        Reader reader = new InputStreamReader(ins);
        char[] buffer = new char[1024];
        int amount;

        while ((amount = reader.read(buffer)) >= 0) {
            //out.write(buffer, 0, amount);
            writer.write(buffer, 0, amount);
        }

        byte[] encodedKey = writer.toString().getBytes();

        encodedKey = Base64.decodeBase64(encodedKey);

        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    }

    public static String rsaDecrypt(String content, String privateKeyPath, String charset) throws Exception {
        try {
        	String privateKey = getKeyPath(privateKeyPath);
            PrivateKey priKey = getPrivateKeyFromPKCS8("RSA", new ByteArrayInputStream(privateKey.getBytes()));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            byte[] encryptedData = Base64.decodeBase64(content.getBytes(charset));
            int inputLen = encryptedData.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密  
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > 128) {
                    cache = cipher.doFinal(encryptedData, offSet, 128);
                } else {
                    cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * 128;
            }
            byte[] decryptedData = out.toByteArray();
            out.close();
            return new String(decryptedData,charset);
        } catch (Exception e) {
            throw new Exception("EncodeContent = " + content + ",charset = " + charset, e);
        }
    }

    public static PrivateKey getPrivateKeyFromPKCS8(String algorithm, InputStream ins) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        Reader reader = new InputStreamReader(ins, "utf-8");
        StringWriter writer = new StringWriter();
        char[] buffer = new char[1024];
        int amount;

        while ((amount = reader.read(buffer)) >= 0) {
            writer.write(buffer, 0, amount);
        }
        String data = writer.toString();
        byte[] encodedKey = data.getBytes();
        encodedKey = Base64.decodeBase64(encodedKey);
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    }
    static String getKeyPath(String path)

	{

	    int len=0;

	    StringBuffer str=new StringBuffer("");

	    File file=new File(path);

	    try {

	        FileInputStream is=new FileInputStream(file);

	        InputStreamReader isr= new InputStreamReader(is);

	        BufferedReader in= new BufferedReader(isr);

	        String line=null;

	        while( (line=in.readLine())!=null )

	        {
	            if(len != 0)  // 处理换行符的问题

	            {
	                str.append(line);
	            }
	            else
	            {
	            	str.append(line);

	            }

	            len++;

	        }

	        in.close();

	        is.close();

	    } catch (IOException e) {

	        e.printStackTrace();

	    }

	    return str.toString();

	}
}
