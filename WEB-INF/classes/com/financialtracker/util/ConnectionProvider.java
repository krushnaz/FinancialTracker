package com.financialtracker.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionProvider {
 private static Connection con;
 public static Connection getConnection() {
	 
	 try {
		 if(con == null) {
			 Class.forName("com.mysql.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost/FinancialTracker","root","");
			 
		 }
		 
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	 
	return con;
	 
 }
 public static void main(String[] args) {
	ConnectionProvider p1 = new ConnectionProvider();
	Connection val = getConnection();
	System.out.println(val);
}
/*
 * public static void closeResultSet(ResultSet resultSet) { if (resultSet !=
 * null) { try { resultSet.close(); } catch (SQLException e) { // Handle the
 * exception or log it e.printStackTrace(); } } }
 * 
 * public static void closePreparedStatement(PreparedStatement
 * preparedStatement) { if (preparedStatement != null) { try {
 * preparedStatement.close(); } catch (SQLException e) { // Handle the exception
 * or log it e.printStackTrace(); } } }
 * 
 * public static void closeConnection(Connection connection) { if (connection !=
 * null) { try { connection.close(); } catch (SQLException e) { // Handle the
 * exception or log it e.printStackTrace(); } } }
 */

}