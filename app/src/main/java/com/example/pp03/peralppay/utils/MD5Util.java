package com.example.pp03.peralppay.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	/**
	 * md5加密
	 * 
	 * @Title: md5
	 * @Description: md5加密
	 * @param str
	 *            需要加密的字符串
	 * @return String 加密后的字符串
	 */
	public static String md5(String s) {
		try {
			// Create MD5 Hash
			MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();

			// Create HEX String
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				String shex = Integer.toHexString(0xFF & messageDigest[i]);
				if (shex.length() < 2) {
					shex = "0" + shex;
				}
				hexString.append(shex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

}
