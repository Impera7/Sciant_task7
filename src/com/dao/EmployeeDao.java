package com.dao;

import java.sql.Connection;
import java.util.*;

import com.bean.Employee;
import com.dbManager.DBactions;
import com.dbManager.DBmanager;

public class EmployeeDao {
	private static Connection conn = null;
	
	public static int insertEmp(Employee emp) {
		conn = DBmanager.getConnection();
		int insertIntoDb = DBactions.insertIntoDb(conn, emp.getFname(), emp.getLname(), Integer.toString(emp.getSalary()), emp.getStartDate());
		return insertIntoDb;
	}
	
	public static void deleteEmp(Employee emp) {
		conn = DBmanager.getConnection();
		DBactions.deleteFromDb(conn, Integer.toString(emp.getEmpId()));
	}
	
	public static void updateEmp(Employee emp) {
		conn = DBmanager.getConnection();
		Employee newEmp = new Employee();
		newEmp.setEmpId(emp.getEmpId());
		newEmp.setFname(emp.getFname());
		newEmp.setLname(emp.getLname());
		newEmp.setSalary(emp.getSalary());
		newEmp.setStartDate(emp.getStartDate());
		DBactions.updateDb(conn, Integer.toString(newEmp.getEmpId()), newEmp.getFname(), newEmp.getLname(), Integer.toString(newEmp.getSalary()), newEmp.getStartDate());
	}
	
	public static List<Employee> getAllEmployees(){
		conn = DBmanager.getConnection();
		List<Employee> list = new ArrayList<Employee>();
		list = DBactions.selectFromDb(conn);
		return list;	
	}
}
