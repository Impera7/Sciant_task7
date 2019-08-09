package com.dbManager;

import java.sql.*;

public class DBmanager {
	private static Connection connection = null;
	private static DBmanager single_instance = null;
	
	private DBmanager() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/employees", "root", "1234");
		} catch(ClassNotFoundException ex) {
			System.out.println("Exception caught while establishing connection to DB: " + ex);
		} catch(SQLException ex) {
			System.out.println("Exception caught while establishing connection to DB: " + ex);
		}
	}
	
	public static void closeConnToDbWithStatement(Connection connection, ResultSet rs, Statement st) {
		try {
			rs.close();
			st.close();
			connection.close();
		} catch (SQLException ex) {
			System.out.println("Exception caught while closing connection: " + ex);
		}
	}

	public static void closeConnToDbWithPrpStmt(Connection connection, ResultSet rs, PreparedStatement prpStmt) {
		try {
			rs.close();
			prpStmt.close();
			connection.close();
		} catch (SQLException ex) {
			System.out.println("Exception caught while closing connection with prepared statement: " + ex);
		}
	}

	public static void closeConnToDbWithPrpStmtNoRs(Connection connection, PreparedStatement prpStmt) {
		try {
			prpStmt.close();
			connection.close();
		} catch (SQLException ex) {
			System.out.println("Exception caught while closing connection with prepared statement: " + ex);
		}
	}

	public static void closeConnToDbWithStmtNoRs(Connection connection, Statement prpStmt) {
		try {
			prpStmt.close();
			connection.close();
		} catch (SQLException ex) {
			System.out.println("Exception caught while closing connection with prepared statement: " + ex);
		}
	}

	public static void closeStatement(Statement st) {
		try {
			st.close();
		} catch (SQLException ex) {
			System.out.println("Exception caught while closing statement in CloseConnectionToDb: " + ex);
		}
	}
	
	public static Connection getConnection() {
		if(single_instance == null) {
			new DBmanager();
			return connection;
		}else {
			return connection;
		}
	}
}
