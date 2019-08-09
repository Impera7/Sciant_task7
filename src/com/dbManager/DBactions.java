package com.dbManager;

import java.sql.*;
import java.util.*;

import com.bean.Employee;

public class DBactions {
	public static int insertIntoDb(Connection connection, String fname, String lname, String salary, String startDate) {
		int result = -1;
		PreparedStatement prpStmt = null;
		ResultSet rs = null;
		Statement getID = null;
		
		try {
			connection = DBmanager.getConnection();
			prpStmt = connection.prepareStatement("insert into employeesdata (FIRST_NAME, LAST_NAME, SALARY, START_DATE)" + "values (?, ?, ?, ?)");
			prpStmt.setString(1, fname);
			prpStmt.setString(2, lname);
			prpStmt.setInt(3, Integer.parseInt(salary));
			prpStmt.setString(4, startDate);
			prpStmt.execute();
			
			getID = connection.createStatement();
			rs = getID.executeQuery("select MAX(ID) from employeesdata");
			while(rs.next()) {
				result = rs.getInt(1);
			}
		} catch(SQLException ex) {
			System.out.println("Exception caught in insertIntoDb inside DBactions: " + ex);
		} finally {
			DBmanager.closeConnToDbWithPrpStmt(connection, rs, prpStmt);
			DBmanager.closeStatement(getID);
		}
		return result;
	}
	
	public static void deleteFromDb(Connection connection, String id) {
		PreparedStatement prpStmt = null;
		
		try {
			connection = DBmanager.getConnection();
			prpStmt = connection.prepareStatement("delete from employeesdata where ID = ?");
			prpStmt.setInt(1, Integer.parseInt(id));
			prpStmt.executeUpdate();
		} catch(SQLException ex) {
			System.out.println("Exception caught in deleteFromDb inside DBactions");
		} finally {
			DBmanager.closeConnToDbWithPrpStmtNoRs(connection, prpStmt);
		}
	}
	
	public static void updateDb(Connection connection, String id, String fname, String lname, String salary, String startDate) {
		PreparedStatement prpStmt = null;
		
		try {
			connection = DBmanager.getConnection();
			prpStmt = connection.prepareStatement("update employeesdata " + "set " + "FIRST_NAME = ? , "
					+ "LAST_NAME = ? , " + "SALARY = ? ," + "START_DATE = ? " + "WHERE ID = ? ");
			prpStmt.setString(1, fname);
			prpStmt.setString(2, lname);
			prpStmt.setInt(3, Integer.parseInt(salary));
			prpStmt.setString(4, startDate);
			prpStmt.setInt(5, Integer.parseInt(id));
			prpStmt.executeUpdate();
		} catch(SQLException ex) {
			System.out.println("Exception caught while updating db: " + ex);
		} finally {
			DBmanager.closeConnToDbWithPrpStmtNoRs(connection, prpStmt);
		}
	}
	
	public static List<Employee> selectFromDb(Connection connection){
		List<Employee> list = new ArrayList<Employee>();
		ResultSet rs = null;
		Statement st = null;
		
		try {
			connection = DBmanager.getConnection();
			st = connection.createStatement();
			rs = st.executeQuery("select * from employeesdata");
			
			while(rs.next()) {
				Employee emp = new Employee();
				emp.setEmpId(rs.getInt(1));
				emp.setFname(rs.getString(2));
				emp.setLname(rs.getString(3));
				emp.setSalary(rs.getInt(4));
				emp.setStartDate(rs.getString(5));
				String dateChange = emp.getStartDate().substring(0, 10);
				emp.setStartDate(dateChange);
				list.add(emp);
			}
		} catch(SQLException ex) {
			System.out.println("Exception caught while selecting form db: " + ex);
		} finally {
			DBmanager.closeConnToDbWithStatement(connection, rs, st);
		}
		
		return list;
	}
	
}
