package com.ydj.smart.common.tools;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * 
 * @author : Ares.yi
 * @createTime : Jul 19, 2012 1:10:14 PM
 * @version : 1.0
 * @description :
 */
public class DBManager {
	
	
	String dbName = "INFORMATION_SCHEMA";
	String driver = "com.mysql.jdbc.Driver";

	/**
	 * 获取数据库连接
	 * @param host
	 * @param port
	 * @param userName
	 * @param password
	 * @return
	 * @throws SQLException
	 * @throws java.lang.ClassNotFoundException
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月27日 下午5:54:03
	 */
	public  Connection getConnection(String host,String port,String userName,String password) throws SQLException,
	  java.lang.ClassNotFoundException {
		
		String charset = "utf-8";

	   return getConnection(host,port,dbName,charset,userName,password);
    }
	
	
	public  Connection getConnection(String host,String port,String dbName,String charset,String userName,String password) throws SQLException,
	  java.lang.ClassNotFoundException {
	  String url = "jdbc:mysql://"+host+":"+port+"/"+dbName+"?characterEncoding="+charset;
	  Class.forName(driver);
	  Connection con = DriverManager.getConnection(url, userName, password);
	  return con;
  }
	
	/**
	 * TEST
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public static void main(String args[]) throws SQLException, ClassNotFoundException, IOException{
//		DBManager obj = new DBManager();
		
	}
	
}
