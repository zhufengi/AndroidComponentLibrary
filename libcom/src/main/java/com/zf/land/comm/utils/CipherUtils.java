package com.zf.land.comm.utils;

import android.util.Base64;

import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/12/12
 * @description: CipherUtils
 * 加解密工具类
 */
public class CipherUtils {

    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private CipherUtils(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * md5 字符串
     * @param input
     * @return
     */
    public static String md5(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(input.getBytes());
            byte[] resultByteArray = messageDigest.digest();
            char[] resultCharArray = new char[resultByteArray.length * 2];
            int index = 0;
            for (byte b : resultByteArray) {
                resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
                resultCharArray[index++] = hexDigits[b & 0xf];
            }
            return new String(resultCharArray);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * md5 小写字符串
     * @param input
     * @return
     */
    public static String md5L(String input) {
        try {
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(input.getBytes());
            byte[] md = mdInst.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte aMd : md) {
                String shaHex = Integer.toHexString(aMd & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

    }

    /**
     * md5 io流
     * @param in
     * @return
     */
    public static String md5(InputStream in) {
        int bufferSize = 256 * 1024;
        DigestInputStream digestInputStream = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            digestInputStream = new DigestInputStream(in, messageDigest);
            byte[] buffer = new byte[bufferSize];
            while (digestInputStream.read(buffer) > 0) ;
            messageDigest = digestInputStream.getMessageDigest();
            byte[] resultByteArray = messageDigest.digest();
            char[] resultCharArray = new char[resultByteArray.length * 2];
            int index = 0;
            for (byte b : resultByteArray) {
                resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
                resultCharArray[index++] = hexDigits[b & 0xf];
            }
            return new String(resultCharArray);
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (digestInputStream != null)
                    digestInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * base64 Encode
     * @param str
     * @return
     */
    public static String base64Encode(String str) {
        return Base64.encodeToString(str.getBytes(), Base64.DEFAULT);
    }

    /**
     * base64 Decode
     * @param str
     * @return
     */
    public static String base64Decode(String str) {
        return Base64.decode(str.getBytes(), Base64.DEFAULT).toString();
    }

    /**
     * Xor Encode
     * @param str
     * @param privatekey
     * @return
     */
    public static String XorEncode(String str, String privatekey) {
        int[] snNum = new int[str.length()];
        String result = "";
        String temp = "";
        for (int i = 0, j = 0; i < str.length(); i++, j++) {
            if (j == privatekey.length())
                j = 0;
            snNum[i] = str.charAt(i) ^ privatekey.charAt(j);
        }
        for (int k = 0; k < str.length(); k++) {
            if (snNum[k] < 10) {
                temp = "00" + snNum[k];
            } else {
                if (snNum[k] < 100) {
                    temp = "0" + snNum[k];
                }
            }
            result += temp;
        }
        return result;
    }

    /**
     * Xor Decode
     * @param str
     * @param privateKey
     * @return
     */
    public static String XorDecode(String str, String privateKey) {
        char[] snNum = new char[str.length() / 3];
        String result = "";

        for (int i = 0, j = 0; i < str.length() / 3; i++, j++) {
            if (j == privateKey.length())
                j = 0;
            int n = Integer.parseInt(str.substring(i * 3, i * 3 + 3));
            snNum[i] = (char) ((char) n ^ privateKey.charAt(j));
        }
        for (int k = 0; k < str.length() / 3; k++) {
            result += snNum[k];
        }
        return result;
    }
}
