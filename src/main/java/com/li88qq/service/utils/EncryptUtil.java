package com.li88qq.service.utils;

import org.bouncycastle.util.encoders.Hex;

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
    //  SHA:安全散列,SHA-1,SHA-256
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
     * sha-1加密
     *
     * @param data 待加密数据
     * @return
     */
    public static String sha1(String data) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("SHA-1");
            byte[] digest = md5.digest(data.getBytes());
            return byteToHex(digest);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * sha-256加密
     *
     * @param data 待加密数据
     * @return
     */
    public static String sha256(String data) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("SHA-256");
            byte[] digest = md5.digest(data.getBytes());
            return byteToHex(digest);
        } catch (Exception e) {
            return "";
        }
    }

}
