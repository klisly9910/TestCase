package com.Gfan;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str1 = "2015-12-12~2016-12-31";
		System.out.println("活动结束时间：" + str1.substring(11, 21));
		/** 获取当前系统时间 */
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		System.out.println("当前系统时间：" + df.format(new Date()));//new Date()为获取当前系统时间
		/**
		 * 其返回的是一个int类型值.若Str1等于参数字符串Str2字符串，则返回0;若该Str1按字典顺序小于参数字符串Str2,则返回值小于0
		 * ;若Str1按字典顺序大于参数字符串Str2，则返回值大于0.
		 */
		System.out.println("比较两个字符串大小："
				+ (str1.substring(11, 21)).compareTo(df.format(new Date())));
		if (str1.substring(11, 21).equals(df.format(new Date()))) {
			System.out.println("活动结束时间与当前时间不符");

		} else {
			System.out.println("活动结束时间与当前时间相符");
		}

	}

}
