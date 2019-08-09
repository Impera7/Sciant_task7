package com.bean;

public class Employee {
	private int empId, salary;
	private String fname, lname, startDate;
	
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public int getEmpId() {
		return empId;
	}
	
	public int getSalary() {
		return salary;
	}
	
	public String getFname() {
		return fname;
	}
	
	public String getLname() {
		return lname;
	}
	
	public String getStartDate() {
		return startDate;
	}
}

