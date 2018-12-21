package com.zf.land.comm.utils;

import android.os.Build;
import android.text.Html;
import android.util.Base64;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.DigestInputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import static com.zf.land.comm.utils.ConvertUtils.bytes2HexString;
import static com.zf.land.comm.utils.ConvertUtils.hexString2Bytes;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/12/12
 * @description: CipherUtils
 * 加解密工具类
 */
public class CipherUtils {

    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    /**算法*/
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

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

    /**
     * AES加密
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(256);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        return cipher.doFinal(content.getBytes("utf-8"));
    }

    /**
     * AES解密
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey 解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);

        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes);
    }


    /**
     * RSA-生成密钥对
     * @param keyLength
     * @return
     * @throws Exception
     */
    public static KeyPair getRSAKeyPair(int keyLength) throws Exception{
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * RSA-公钥加密
     * @param content
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] rsaEncrypt(byte[] content, PublicKey publicKey) throws Exception{
        Cipher cipher=Cipher.getInstance("RSA");//java默认"RSA"="RSA/ECB/PKCS1Padding"
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }


    /**
     * RSA-私钥解密
     * @param content
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] rsaDecrypt(byte[] content, PrivateKey privateKey) throws Exception{
        Cipher cipher=Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    /**
     * SHA-512 加密 （不可逆-安全散列算法）
     * @param data
     * @return
     */
    public static String sha512Encrypt(byte[] data) {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA-512");
            sha.update(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] resultBytes = sha.digest();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < resultBytes.length; i++) {
            if (Integer.toHexString(0xFF & resultBytes[i]).length() == 1) {
                builder.append("0").append(
                        Integer.toHexString(0xFF & resultBytes[i]));
            } else {
                builder.append(Integer.toHexString(0xFF & resultBytes[i]));
            }
        }
        return builder.toString();
    }
    /*
     * 3des-生成密钥(进行了三重DES加密的算法，对称加密算法)
     */
    public static byte[] des3InitKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("DESede");
        keyGen.init(168);  //112 168
        SecretKey secretKey = keyGen.generateKey();
        return secretKey.getEncoded();
    }

    /*
     * 3DES 加密(进行了三重DES加密的算法，对称加密算法)
     */
    public static byte[] des3Encrypt(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "DESede");

        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipherBytes = cipher.doFinal(data);
        return cipherBytes;
    }

    /*
     * 3DES 解密
     */
    public static byte[] des3Decrypt(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "DESede");

        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plainBytes = cipher.doFinal(data);
        return plainBytes;
    }

    //=========================================================================================

    /*********************** 哈希加密相关 ***********************/
    private static final String DES_Algorithm = "DES";
    private static final String TripleDES_Algorithm = "DESede";
    private static final String AES_Algorithm = "AES";
    /**
     * DES转变
     * <p>法算法名称/加密模式/填充方式</p>
     * <p>加密模式有：电子密码本模式ECB、加密块链模式CBC、加密反馈模式CFB、输出反馈模式OFB</p>
     * <p>填充方式有：NoPadding、ZerosPadding、PKCS5Padding</p>
     */
    public static String DES_Transformation = "DES/ECB/NoPadding";
    /**
     * 3DES转变
     * <p>法算法名称/加密模式/填充方式</p>
     * <p>加密模式有：电子密码本模式ECB、加密块链模式CBC、加密反馈模式CFB、输出反馈模式OFB</p>
     * <p>填充方式有：NoPadding、ZerosPadding、PKCS5Padding</p>
     */
    public static String TripleDES_Transformation = "DESede/ECB/NoPadding";
    /**
     * AES转变
     * <p>法算法名称/加密模式/填充方式</p>
     * <p>加密模式有：电子密码本模式ECB、加密块链模式CBC、加密反馈模式CFB、输出反馈模式OFB</p>
     * <p>填充方式有：NoPadding、ZerosPadding、PKCS5Padding</p>
     */
    public static String AES_Transformation = "AES/ECB/NoPadding";


    /**
     * URL编码
     * <p>若想自己指定字符集,可以使用{@link #urlEncode(String input, String charset)}方法</p>
     *
     * @param input 要编码的字符
     * @return 编码为UTF-8的字符串
     */
    public static String urlEncode(String input) {
        return urlEncode(input, "UTF-8");
    }

    /**
     * URL编码
     * <p>若系统不支持指定的编码字符集,则直接将input原样返回</p>
     *
     * @param input   要编码的字符
     * @param charset 字符集
     * @return 编码为字符集的字符串
     */
    public static String urlEncode(String input, String charset) {
        try {
            return URLEncoder.encode(input, charset);
        } catch (UnsupportedEncodingException e) {
            return input;
        }
    }

    /**
     * URL解码
     * <p>若想自己指定字符集,可以使用 {@link #urlDecode(String input, String charset)}方法</p>
     *
     * @param input 要解码的字符串
     * @return URL解码后的字符串
     */
    public static String urlDecode(String input) {
        return urlDecode(input, "UTF-8");
    }

    /**
     * URL解码
     * <p>若系统不支持指定的解码字符集,则直接将input原样返回</p>
     *
     * @param input   要解码的字符串
     * @param charset 字符集
     * @return URL解码为指定字符集的字符串
     */
    public static String urlDecode(String input, String charset) {
        try {
            return URLDecoder.decode(input, charset);
        } catch (UnsupportedEncodingException e) {
            return input;
        }
    }

    /**
     * Base64编码
     *
     * @param input 要编码的字符串
     * @return Base64编码后的字符串
     */
    public static byte[] base64Encode(String input) {
        return base64Encode(input.getBytes());
    }

    /**
     * Base64编码
     *
     * @param input 要编码的字节数组
     * @return Base64编码后的字符串
     */
    public static byte[] base64Encode(byte[] input) {
        return Base64.encode(input, Base64.NO_WRAP);
    }

    /**
     * Base64编码
     *
     * @param input 要编码的字节数组
     * @return Base64编码后的字符串
     */
    public static String base64Encode2String(byte[] input) {
        return Base64.encodeToString(input, Base64.NO_WRAP);
    }

    /**
     * Base64解码
     *
     * @param input 要解码的字符串
     * @return Base64解码后的字符串
     */
    public static byte[] base64Decode(String input) {
        return Base64.decode(input, Base64.NO_WRAP);
    }

    /**
     * Base64解码
     *
     * @param input 要解码的字符串
     * @return Base64解码后的字符串
     */
    public static byte[] base64Decode(byte[] input) {
        return Base64.decode(input, Base64.NO_WRAP);
    }

    /**
     * Base64URL安全编码
     * <p>将Base64中的URL非法字符�?,/=转为其他字符, 见RFC3548</p>
     *
     * @param input 要Base64URL安全编码的字符串
     * @return Base64URL安全编码后的字符串
     */
    public static byte[] base64UrlSafeEncode(String input) {
        return Base64.encode(input.getBytes(), Base64.URL_SAFE);
    }

    /**
     * Html编码
     *
     * @param input 要Html编码的字符串
     * @return Html编码后的字符串
     */
    public static String htmlEncode(String input) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return Html.escapeHtml(input);
        } else {
            // 参照Html.escapeHtml()中代码
            StringBuilder out = new StringBuilder();
            for (int i = 0, len = input.length(); i < len; i++) {
                char c = input.charAt(i);
                if (c == '<') {
                    out.append("&lt;");
                } else if (c == '>') {
                    out.append("&gt;");
                } else if (c == '&') {
                    out.append("&amp;");
                } else if (c >= 0xD800 && c <= 0xDFFF) {
                    if (c < 0xDC00 && i + 1 < len) {
                        char d = input.charAt(i + 1);
                        if (d >= 0xDC00 && d <= 0xDFFF) {
                            i++;
                            int codepoint = 0x010000 | (int) c - 0xD800 << 10 | (int) d - 0xDC00;
                            out.append("&#").append(codepoint).append(";");
                        }
                    }
                } else if (c > 0x7E || c < ' ') {
                    out.append("&#").append((int) c).append(";");
                } else if (c == ' ') {
                    while (i + 1 < len && input.charAt(i + 1) == ' ') {
                        out.append("&nbsp;");
                        i++;
                    }
                    out.append(' ');
                } else {
                    out.append(c);
                }
            }
            return out.toString();
        }
    }

    /**
     * Html解码
     *
     * @param input 待解码的字符串
     * @return Html解码后的字符串
     */
    public static String htmlDecode(String input) {
        return Html.fromHtml(input).toString();
    }

    /**
     * MD2加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptMD2ToString(String data) {
        return encryptMD2ToString(data.getBytes());
    }

    /**
     * MD2加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptMD2ToString(byte[] data) {
        return bytes2HexString(encryptMD2(data));
    }

    /**
     * MD2加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptMD2(byte[] data) {
        return encryptAlgorithm(data, "MD2");
    }

    /**
     * MD5加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptMD5ToString(String data) {
        return encryptMD5ToString(data.getBytes());
    }

    /**
     * MD5加密
     *
     * @param data 明文字符串
     * @param salt 盐
     * @return 16进制加盐密文
     */
    public static String encryptMD5ToString(String data, String salt) {
        return bytes2HexString(encryptMD5((data + salt).getBytes()));
    }

    /**
     * MD5加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptMD5ToString(byte[] data) {
        return bytes2HexString(encryptMD5(data));
    }

    /**
     * MD5加密
     *
     * @param data 明文字节数组
     * @param salt 盐字节数组
     * @return 16进制加盐密文
     */
    public static String encryptMD5ToString(byte[] data, byte[] salt) {
        byte[] dataSalt = new byte[data.length + salt.length];
        System.arraycopy(data, 0, dataSalt, 0, data.length);
        System.arraycopy(salt, 0, dataSalt, data.length, salt.length);
        return bytes2HexString(encryptMD5(dataSalt));
    }

    /**
     * MD5加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptMD5(byte[] data) {
        return encryptAlgorithm(data, "MD5");
    }

    /**
     * MD5加密文件
     *
     * @param filePath 文件路径
     * @return 文件的16进制密文
     */
    public static String encryptMD5File2String(String filePath) {
        return encryptMD5File2String(new File(filePath));
    }

    /**
     * MD5加密文件
     *
     * @param filePath 文件路径
     * @return 文件的MD5校验码
     */
    public static byte[] encryptMD5File(String filePath) {
        return encryptMD5File(new File(filePath));
    }

    /**
     * MD5加密文件
     *
     * @param file 文件
     * @return 文件的16进制密文
     */
    public static String encryptMD5File2String(File file) {
        return encryptMD5File(file) != null ? bytes2HexString(encryptMD5File(file)) : "";
    }

    /**
     * MD5加密文件
     *
     * @param file 文件
     * @return 文件的MD5校验码
     */
    public static byte[] encryptMD5File(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            FileChannel channel = fis.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(buffer);
            return md.digest();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null){
                    fis = null;
                    fis.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    /**
     * SHA1加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptSHA1ToString(String data) {
        return encryptSHA1ToString(data.getBytes());
    }

    /**
     * SHA1加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptSHA1ToString(byte[] data) {
        return bytes2HexString(encryptSHA1(data));
    }

    /**
     * SHA1加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptSHA1(byte[] data) {
        return encryptAlgorithm(data, "SHA-1");
    }

    /**
     * SHA224加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptSHA224ToString(String data) {
        return encryptSHA224ToString(data.getBytes());
    }

    /**
     * SHA224加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptSHA224ToString(byte[] data) {
        return bytes2HexString(encryptSHA224(data));
    }

    /**
     * SHA224加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptSHA224(byte[] data) {
        return encryptAlgorithm(data, "SHA-224");
    }

    /**
     * SHA256加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptSHA256ToString(String data) {
        return encryptSHA256ToString(data.getBytes());
    }

    /**
     * SHA256加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptSHA256ToString(byte[] data) {
        return bytes2HexString(encryptSHA256(data));
    }

    /**
     * SHA256加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptSHA256(byte[] data) {
        return encryptAlgorithm(data, "SHA-256");
    }

    /**
     * SHA384加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptSHA384ToString(String data) {
        return encryptSHA384ToString(data.getBytes());
    }

    /************************ DES加密相关 ***********************/

    /**
     * SHA384加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptSHA384ToString(byte[] data) {
        return bytes2HexString(encryptSHA384(data));
    }

    /**
     * SHA384加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptSHA384(byte[] data) {
        return encryptAlgorithm(data, "SHA-384");
    }

    /**
     * SHA512加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptSHA512ToString(String data) {
        return encryptSHA512ToString(data.getBytes());
    }

    /**
     * SHA512加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptSHA512ToString(byte[] data) {
        return bytes2HexString(encryptSHA512(data));
    }

    /**
     * SHA512加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptSHA512(byte[] data) {
        return encryptAlgorithm(data, "SHA-512");
    }

    /**
     * 对data进行algorithm算法加密
     *
     * @param data      明文字节数组
     * @param algorithm 加密算法
     * @return 密文字节数组
     */
    private static byte[] encryptAlgorithm(byte[] data, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(data);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * @param data           数据
     * @param key            秘钥
     * @param algorithm      采用何种DES算法
     * @param transformation 转变
     * @param isEncrypt      是否加密
     * @return 密文或者明文，适用于DES，3DES，AES
     */
    public static byte[] DESTemplet(byte[] data, byte[] key, String algorithm, String transformation, boolean isEncrypt) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);
            Cipher cipher = Cipher.getInstance(transformation);
            SecureRandom random = new SecureRandom();
            cipher.init(isEncrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, keySpec, random);
            return cipher.doFinal(data);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * DES加密后转为Base64编码
     *
     * @param data 明文
     * @param key  8字节秘钥
     * @return Base64密文
     */
    public static byte[] encryptDES2Base64(byte[] data, byte[] key) {
        return RxEncodeTool.base64Encode(encryptDES(data, key));
    }

    /**
     * DES加密后转为16进制
     *
     * @param data 明文
     * @param key  8字节秘钥
     * @return 16进制密文
     */
    public static String encryptDES2HexString(byte[] data, byte[] key) {
        return bytes2HexString(encryptDES(data, key));
    }

    /************************ 3DES加密相关 ***********************/

    /**
     * DES加密
     *
     * @param data 明文
     * @param key  8字节秘钥
     * @return 密文
     */
    public static byte[] encryptDES(byte[] data, byte[] key) {
        return DESTemplet(data, key, DES_Algorithm, DES_Transformation, true);
    }

    /**
     * DES解密Base64编码密文
     *
     * @param data Base64编码密文
     * @param key  8字节秘钥
     * @return 明文
     */
    public static byte[] decryptBase64DES(byte[] data, byte[] key) {
        return decryptDES(RxEncodeTool.base64Decode(data), key);
    }

    /**
     * DES解密16进制密文
     *
     * @param data 16进制密文
     * @param key  8字节秘钥
     * @return 明文
     */
    public static byte[] decryptHexStringDES(String data, byte[] key) {
        return decryptDES(hexString2Bytes(data), key);
    }

    /**
     * DES解密
     *
     * @param data 密文
     * @param key  8字节秘钥
     * @return 明文
     */
    public static byte[] decryptDES(byte[] data, byte[] key) {
        return DESTemplet(data, key, DES_Algorithm, DES_Transformation, false);
    }

    /**
     * 3DES加密后转为Base64编码
     *
     * @param data 明文
     * @param key  24字节秘钥
     * @return Base64密文
     */
    public static byte[] encrypt3DES2Base64(byte[] data, byte[] key) {
        return RxEncodeTool.base64Encode(encrypt3DES(data, key));
    }

    /**
     * 3DES加密后转为16进制
     *
     * @param data 明文
     * @param key  24字节秘钥
     * @return 16进制密文
     */
    public static String encrypt3DES2HexString(byte[] data, byte[] key) {
        return bytes2HexString(encrypt3DES(data, key));
    }

    /**
     * 3DES加密
     *
     * @param data 明文
     * @param key  24字节密钥
     * @return 密文
     */
    public static byte[] encrypt3DES(byte[] data, byte[] key) {
        return DESTemplet(data, key, TripleDES_Algorithm, TripleDES_Transformation, true);
    }

    /**
     * 3DES解密Base64编码密文
     *
     * @param data Base64编码密文
     * @param key  24字节秘钥
     * @return 明文
     */
    public static byte[] decryptBase64_3DES(byte[] data, byte[] key) {
        return decrypt3DES(RxEncodeTool.base64Decode(data), key);
    }

    /************************ AES加密相关 ***********************/

    /**
     * 3DES解密16进制密文
     *
     * @param data 16进制密文
     * @param key  24字节秘钥
     * @return 明文
     */
    public static byte[] decryptHexString3DES(String data, byte[] key) {
        return decrypt3DES(hexString2Bytes(data), key);
    }

    /**
     * 3DES解密
     *
     * @param data 密文
     * @param key  24字节密钥
     * @return 明文
     */
    public static byte[] decrypt3DES(byte[] data, byte[] key) {
        return DESTemplet(data, key, TripleDES_Algorithm, TripleDES_Transformation, false);
    }

    /**
     * AES加密后转为Base64编码
     *
     * @param data 明文
     * @param key  16、24、32字节秘钥
     * @return Base64密文
     */
    public static byte[] encryptAES2Base64(byte[] data, byte[] key) {
        return RxEncodeTool.base64Encode(encryptAES(data, key));
    }

    /**
     * AES加密后转为16进制
     *
     * @param data 明文
     * @param key  16、24、32字节秘钥
     * @return 16进制密文
     */
    public static String encryptAES2HexString(byte[] data, byte[] key) {
        return bytes2HexString(encryptAES(data, key));
    }

    /**
     * AES加密
     *
     * @param data 明文
     * @param key  16、24、32字节秘钥
     * @return 密文
     */
    public static byte[] encryptAES(byte[] data, byte[] key) {
        return DESTemplet(data, key, AES_Algorithm, AES_Transformation, true);
    }

    /**
     * AES解密Base64编码密文
     *
     * @param data Base64编码密文
     * @param key  16、24、32字节秘钥
     * @return 明文
     */
    public static byte[] decryptBase64AES(byte[] data, byte[] key) {
        return decryptAES(RxEncodeTool.base64Decode(data), key);
    }

    /**
     * AES解密16进制密文
     *
     * @param data 16进制密文
     * @param key  16、24、32字节秘钥
     * @return 明文
     */
    public static byte[] decryptHexStringAES(String data, byte[] key) {
        return decryptAES(hexString2Bytes(data), key);
    }

    /**
     * AES解密
     *
     * @param data 密文
     * @param key  16、24、32字节秘钥
     * @return 明文
     */
    public static byte[] decryptAES(byte[] data, byte[] key) {
        return DESTemplet(data, key, AES_Algorithm, AES_Transformation, false);
    }

}
