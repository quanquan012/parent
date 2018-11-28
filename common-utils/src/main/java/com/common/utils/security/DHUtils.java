package com.common.utils.security;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lihao
 * @date 2018-11-23 22:30
 */
public class DHUtils {

    /**
     * 发送方生成自己的密钥对
     *
     * @return 返回密钥对 失败则null
     */
    public static Map<String, byte[]> getKey() {
        Map<String, byte[]> map = null;
        try {
            // 实例化密钥对生成器
            KeyPairGenerator keyPairGenerator = KeyPairGenerator
                    .getInstance("DH");
            // 初始化密钥生成器 密钥长度默认1024 长度只能为512~1024 并且必须时64的倍数
            keyPairGenerator.initialize(1024);
            // 生成密钥对
            KeyPair keypair = keyPairGenerator.generateKeyPair();
            // 获得公钥
            PublicKey publicKey = keypair.getPublic();
            // 获得私钥
            PrivateKey privateKey = keypair.getPrivate();

            map = new HashMap<String, byte[]>();
            // 存储密钥
            map.put("publicKey", publicKey.getEncoded());
            map.put("privateKey", privateKey.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 得到发送方的公钥，生成接收方自己的密钥对
     *
     * @param key 发送方传递的公钥
     * @return 返回自己的密钥对 失败则null
     */
    public static Map<String, byte[]> getKey(byte[] key) {
        Map<String, byte[]> map = null;
        try {
            // 实例化密钥工厂
            KeyFactory keyFactory = KeyFactory.getInstance("DH");
            // 将对方公钥从数组转换为PublicKey
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
            // 产生对方公钥publicKey
            DHPublicKey dhpublicKey = (DHPublicKey) keyFactory.generatePublic(keySpec);
            // 剖析对方key 获取其参数
            DHParameterSpec dhParameterSpec = dhpublicKey.getParams();
            // 实例化密钥对生成器
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");
            // 使用对方公钥参数初始化生成器
            keyPairGenerator.initialize(dhParameterSpec);
            // 生成密钥对
            KeyPair keypair = keyPairGenerator.generateKeyPair();
            // 获得公钥
            PublicKey publicKey = keypair.getPublic();
            // 获得私钥
            PrivateKey privateKey = keypair.getPrivate();

            map = new HashMap<String, byte[]>();
            // 存储密钥
            map.put("publicKey", publicKey.getEncoded());
            map.put("privateKey", privateKey.getEncoded());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 将对方公钥 结合自己的私钥 生成一个双方都相同的密钥 ， 方便进行对称加密操作。
     *
     * @param publicKey  对方传递的公钥
     * @param privateKey 自己的私钥
     * @param algorithm  生成哪个对称加密算法的密钥
     * @return 返回对称算法的共同密钥 失败则null
     */
    public static byte[] getSecretKey(byte[] publicKey, byte[] privateKey,
                                      String algorithm) {
        byte[] result = null;
        try {
            // 实例化密钥工厂
            KeyFactory keyFactory = KeyFactory.getInstance("DH");
            // 将publicKey转换为Key对象
            PublicKey publicKey2 = keyFactory
                    .generatePublic(new X509EncodedKeySpec(publicKey));
            // 将privateKey转换为Key对象
            PrivateKey privateKey2 = keyFactory
                    .generatePrivate(new PKCS8EncodedKeySpec(privateKey));

            // 根据以上私钥和公钥生成本地密钥SecretKey

            // 先实例化KeyAgreement
            KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
            // 用自己的私钥初始化keyAgreement
            keyAgreement.init(privateKey2);
            // 结合对方公钥进行运算
            keyAgreement.doPhase(publicKey2, true);
            // 开始生成本地密钥SecretKey 密钥算法为对称算法
            SecretKey secretKey = keyAgreement.generateSecret(algorithm);
            result = secretKey.getEncoded();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}