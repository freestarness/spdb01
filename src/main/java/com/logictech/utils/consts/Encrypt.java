package com.logictech.utils.consts;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

public class Encrypt {
    public static void main(String[] args) {
        //加密后的内容
        String s = "QPBNbRNDohmlvyzLSgCy1iwo+juaDACo";
        //密码，用户名前8位，不足补0
        String password ="testtest";
        //打印解密后的结果
       // System.out.println(decrypt(s, password));

        System.out.println("---:"+encrypt("012345679", "logictec"));


        String s2 ="C/4ARetVYG46SE6M6Bqx/A==";

      System.out.println( decrypt(s2,"logictec"));

    }
    /**
     * 解密
     * @param srcMsg 密文
     * @param password    密码
     * @return         解密后的明文
     */
    public static String decrypt(String srcMsg,String password){
        byte[] bb = Base64.decodeBase64(srcMsg.getBytes());
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom random = new SecureRandom();
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
            // 真正开始解密操作
            byte[] decryResult = cipher.doFinal(bb);
            return new String (decryResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 加密
     * @param srcMsg  待加密字符串
     * @param password        密码
     * @return   加密后的密文
     */
    public static String encrypt(String srcMsg, String password){
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            // 现在，获取数据并加密
            // 正式执行加密操作
            byte[] result =cipher.doFinal(srcMsg.getBytes());
            //Base64编码
            byte[] resultBase = Base64.encodeBase64(result, true);
            return new String(resultBase);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}