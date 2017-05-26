package com.ydj.smart.api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.ydj.smart.common.tools.JdbcTemplate;

/**
 * 
 * @author : Ares.yi
 * @createTime : Aug 17, 2012 11:03:26 AM
 * @version : 1.0
 * @description :
 */
public class DBTableDao {
	

	public List<JSONObject> getAllTables(String host,String port,String userName,String password,String dbName){
		
		JdbcTemplate jdbcTemp = new JdbcTemplate(host,port,userName,password);
		
		String sql = "SELECT table_comment AS 'comment',table_name AS 'tableName' FROM INFORMATION_SCHEMA.TABLES  WHERE table_schema = '"+dbName+"' AND table_name NOT LIKE 'z_%' ";
		ResultSet result = jdbcTemp.getResultSet(sql);
		
		List<JSONObject> res = new ArrayList<JSONObject>();
		try {
			while (result.next()) {
				String tableName = result.getString("tableName");
				String tableComment = result.getString("comment");
				
				JSONObject one = new JSONObject();
				one.put("tableName", tableName);
				one.put("tableComment", tableComment);
				
				res.add(one);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public List<JSONObject> getTableFileds(String host,String port,String userName,String password,String dbName,String tableName){
		
		List<JSONObject> res = new ArrayList<JSONObject>();
		
		JdbcTemplate jdbcTemp = new JdbcTemplate(host,port,userName,password);
		
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT COLUMN_NAME 'column',DATA_TYPE 'type', COLUMN_COMMENT 'comment',CHARACTER_MAXIMUM_LENGTH AS 'length' ")
			.append(" FROM COLUMNS ")
			.append(" WHERE ")
			.append(" table_schema = '"+dbName+"' ")
			.append(" AND table_name = '"+tableName+"'");
			
			ResultSet result = jdbcTemp.getResultSet(query.toString());
			while (result.next()) {
				
				String column = result.getString("column");
				String type = result.getString("type");
				String comment = result.getString("comment");
				String length = result.getString("length");
				
				
				JSONObject one = new JSONObject();
				one.put("column", column);
				one.put("type", type);
				one.put("length", length);
				one.put("comment", comment);
				
				res.add(one);
			}
			
		} catch (SQLException ex) {
		}
		
		return res;
		
	}
	
	
	public static void main(String args[]) throws SQLException{
	}
}
