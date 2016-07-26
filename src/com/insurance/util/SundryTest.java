package com.insurance.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

/*
 * 杂项（无符号数转换，字符串补齐，md5,uuid,随机数）
 */
public class SundryTest {

	// 转成无符号数
	public static Number toUnsignedNumber(Number num) {
		if (num instanceof Byte) {
			return (Byte) num & 0xff;
		} else if (num instanceof Short) {
			return (Short) num & 0xffff;
		} else if (num instanceof Integer) {
			return (Integer) num & 0xffffffffL;
		} else {
			return -1;
		}
	}

	// 左补齐
	public static String leftPad(String str, String pad, int len) {
		String newStr = (str == null ? "" : str);
		while (newStr.length() < len) {
			newStr = pad + newStr;
		}
		if (newStr.length() > len) {
			newStr = newStr.substring(newStr.length() - len);
		}
		return newStr;
	}

	// 右补齐
	public static String rightPad(String str, String pad, int len) {
		String newStr = (str == null ? "" : str);
		while (newStr.length() < len) {
			newStr = newStr + pad;
		}
		if (newStr.length() > len) {
			newStr = newStr.substring(0, len);
		}
		return newStr;
	}

	// md5
	public static String md5(String str) {
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] message = digest.digest(str.getBytes());
			for (int i = 0; i < message.length; i++) {
				sb.append(leftPad( // 左补齐
						Integer.toHexString( // 转成16进制数
						(Integer) toUnsignedNumber(message[i])), // 转成无符号数
						"0", 2).toUpperCase()); // 转成大写
			}
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("不存在md5服务！");
		}
		return sb.toString();
	}

	// UUID
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}

	// 随机数(包括min,不包括max)
	public static int random(int min, int max) {
		if (min <= max) {
			Random random = new Random();
			return random.nextInt(max - min) + min;
		} else {
			throw new IllegalArgumentException("无法处理一个不合法的数字区间！");
		}
	}

	public static void main(String[] args) {
		System.out.println("MD5(123456):" + md5("123456"));
		System.out.println("UUID:" + uuid());
		System.out.println("随机数：" + random(1, 100));
	}
}
