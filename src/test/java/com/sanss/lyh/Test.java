package com.sanss.lyh;

import org.apache.commons.codec.digest.DigestUtils;

public class Test {

	public static void main(String[] args) {
		String s=DigestUtils.md5Hex("asd");
		System.out.println(s);
		String s1=DigestUtils.shaHex(s);
		System.out.println(s1);
	}
}
