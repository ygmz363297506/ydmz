package com.sanss.lyh.web.business.utils;

public class SQLUtil {
	public static String oraclePage(StringBuffer sqlBuffer, Page page) throws Exception {
		StringBuffer temp = new StringBuffer();
		temp.append(" SELECT * ");
		temp.append(" FROM (SELECT * ");
		temp.append(" FROM (SELECT T.*, ROW_NUMBER() OVER(ORDER BY NULL) AS ROW_NUMBER");
		temp.append(" FROM (");
		temp.append(sqlBuffer);
		temp.append(" )T) P ");
		temp.append("WHERE P.ROW_NUMBER > ");
		temp.append(page.getPageFrom());
		temp.append(" ) Q ");
		temp.append(" WHERE ROWNUM <= ");
		temp.append(page.getRow());
		return temp.toString();
	}

	public static String mysqlPage(StringBuffer sqlBuffer, Page page) throws Exception {
		StringBuffer temp = new StringBuffer();
		temp.append(sqlBuffer);
		temp.append(" LIMIT ");
		temp.append(page.getPageFrom());
		temp.append(" , ");
		temp.append(page.getRow());
		return temp.toString();
	}

	public static String oraclePage(String sql, Page page) throws Exception {
		StringBuffer temp = new StringBuffer();
		temp.append(" SELECT * ");
		temp.append(" FROM (SELECT * ");
		temp.append(" FROM (SELECT T.*, ROW_NUMBER() OVER(ORDER BY NULL) AS ROW_NUMBER");
		temp.append(" FROM (");
		temp.append(sql);
		temp.append(" )T) P ");
		temp.append("WHERE P.ROW_NUMBER > ");
		temp.append(page.getPageFrom());
		temp.append(" ) Q ");
		temp.append(" WHERE ROWNUM <= ");
		temp.append(page.getRow());
		return temp.toString();
	}

	public static String mysqlPage(String sql, Page page) throws Exception {
		StringBuffer temp = new StringBuffer();
		temp.append(sql);
		temp.append(" LIMIT ");
		temp.append(page.getPageFrom());
		temp.append(" , ");
		temp.append(page.getRow());
		return temp.toString();
	}
}