package com.ydj.smart.common.tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author : Ares.yi
 * @createTime : Aug 17, 2012 10:46:08 AM
 * @version : 1.0
 * @description :
 */
public class JdbcTemplate {

	private  Connection connnection ;
	
	public JdbcTemplate(Connection connnection){
		this.connnection = connnection;
	}
	
	public JdbcTemplate(String host,String port,String userName,String password){
		DBManager dbManager = new DBManager();
		try {
			connnection = dbManager.getConnection(host,port,userName,password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getResultSet(String sql){
		Statement statement = null;
		try {
			statement = connnection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet result = null;
		try {
			result = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		try {
//			statement.close();
//			connnection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return result;
	}
}
