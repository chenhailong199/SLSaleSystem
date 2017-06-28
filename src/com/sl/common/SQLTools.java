package com.sl.common;

/**
 * mybatis 防止SQL注入
 * @author chen
 *
 */
public class SQLTools {
	/**
	 * mybatis模糊查询防止sql注入
	 * @param keyword
	 * @return
	 */
	public static String transfer(String keyword){
		if (keyword.contains("%") || keyword.contains("_")){
			keyword = keyword.replaceAll("\\\\", "\\\\\\\\")
				.replaceAll("\\%", "\\\\%")
				.replaceAll("\\_", "\\\\_");
		}
		return keyword;
	}
}
