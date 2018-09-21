package com.sanss.lyh.web.business.utils;

import java.security.Key;
import java.security.Security;
import javax.crypto.Cipher;


public class DesUtil {
	private static final String ENCRYPT_TYPE = "DES";
	private static String defaultKey = "";// 字符串默认键值
	private Cipher encryptCipher = null;// 加密工具
	private Cipher decryptCipher = null;// 解密工具

	public DesUtil() throws Exception {
		this(defaultKey);
	}

	/**
	 * 指定密钥构造方法
	 * 
	 * @param strKey
	 *            指定的密钥
	 * @throws Exception
	 */
	public DesUtil(String strKey) throws Exception {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		Key key = getKey(strKey.getBytes());
		encryptCipher = Cipher.getInstance(ENCRYPT_TYPE);
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);
		decryptCipher = Cipher.getInstance(ENCRYPT_TYPE);
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
	}

	/**
	 * 加密字节数组
	 * 
	 * @param arr
	 *            需加密的字节数组
	 * @return 加密后的字节数组
	 * @throws Exception
	 */
	private byte[] encryptStr(byte[] arr) throws Exception {
		return encryptCipher.doFinal(arr);
	}

	/**
	 * 加密字符串
	 * 
	 * @param strIn
	 *            需加密的字符串
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public String encrypt(String strIn) throws Exception {
		return StrConvertUtil.byteArrToHexStr(encryptStr(strIn.getBytes()));
	}

	/**
	 * 解密字节数组
	 * 
	 * @param arr
	 *            需解密的字节数组
	 * @return 解密后的字节数组
	 * @throws Exception
	 */
	private byte[] decryptStr(byte[] arr) throws Exception {
		return decryptCipher.doFinal(arr);
	}

	/**
	 * 解密字符串
	 * 
	 * @param strIn
	 *            需解密的字符串
	 * @return 解密后的字符串
	 * @throws Exception
	 */
	public String decrypt(String strIn) throws Exception {
		return new String(decryptStr(StrConvertUtil.hexStrToByteArr(strIn)));
	}

	/**
	 * 从指定字符串生成密钥，密钥所需的字节数组长度为8位。不足8位时后面补0，超出8位只取前8位
	 * 
	 * @param arrBTmp
	 *            构成该字符串的字节数组
	 * @return 生成的密钥
	 */
	private Key getKey(byte[] arrBTmp) {
		byte[] arrB = new byte[8];// 创建一个空的8位字节数组（默认值为0）
		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, ENCRYPT_TYPE);// 生成密钥
		return key;
	}
	
	public static void main(String[] args) {
		String s="18016038006";
		DesUtil desUtil;
		try {
			desUtil = new DesUtil("sanss123");
			String encrypt = desUtil.encrypt(s);
			System.out.println(encrypt);
			String decrypt = desUtil.decrypt("3a33eb3568b3e07d");
			System.out.println(decrypt);
			//e2313a53fb49b244(2141的加密)
			//9acff5777e2242dd  (12345的加密)
			//9ae4f37e8d7ee041(213321的加密)
			//53306385be69bc81 (134的加密)
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
