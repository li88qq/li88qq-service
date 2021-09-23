package com.li88qq.service.utils;

import org.bouncycastle.util.encoders.Hex;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * 加密工具类
 *
 * @author li88qq
 * @version 1.0 2021/9/22 23:06
 */
public class EncryptUtil {
    //1.MD5(Message Digest Algorithm):信息摘要算法
    //1.1.分类
    //  MD5:128位
    //  SHA:安全散列,SHA-1,SHA-256,SHA-384,SHA-512
    //1.2.特点:单向加密
    //1.3.原理:
    //1.4.用途


    /**
     * md5加密
     *
     * @param data 待加密数据
     * @return
     */
    public static String md5(String data) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(data.getBytes());
            return byteToHex(digest);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 字节数组转hex
     *
     * @param digest
     * @return
     */
    public static String byteToHex(byte[] digest) {
        return Hex.toHexString(digest);
    }

    /**
     * sha加密
     *
     * @param encrypt 加密类型
     * @param data    待加密数据
     * @return
     */
    public static String sha(Encrypt encrypt, String data) {
        try {
            String shaType = encrypt.getType();
            MessageDigest md5 = MessageDigest.getInstance(shaType);
            md5.update(data.getBytes());
            byte[] digest = md5.digest();
            return byteToHex(digest);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * hamc统一算法
     *
     * @param encrypt
     * @param data
     * @param key
     * @return
     */
    public static String hmac(Encrypt encrypt, String data, String key) {
        try {
            String hamcType = encrypt.getType();
            KeyGenerator generator = KeyGenerator.getInstance(hamcType);
            SecretKey secretKey = generator.generateKey();//生成密钥

            //获取密钥
            byte[] keyBytes = null;
            if (key == null || key.equals("")) {
                keyBytes = secretKey.getEncoded();//自动生成
            } else {
                keyBytes = Hex.decode(key);
            }

            //还原密钥
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, hamcType);

            //3.信息摘要
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());//实例化mac
            mac.init(secretKeySpec);//初始化mac
            byte[] bytes = mac.doFinal(data.getBytes());//执行摘要

            return byteToHex(bytes);
        } catch (Exception e) {
            return "";
        }
    }


    /**
     * 加密算法枚举
     */
    public enum Encrypt {
        MD5("MD5"),

        SHA_1("SHA-1"),
        SHA_256("SHA-256"),
        SHA_384("SHA-384"),
        SHA_512("SHA-512"),

        Hmac_MD5("HmacMD5"),
        Hmac_SHA1("HmacSHA1"),
        Hmac_SHA256("HmacSHA256"),
        Hmac_SHA384("HmacSHA384"),
        Hmac_SHA512("HmacSHA512"),


        ;

        private String type;

        Encrypt(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

}
