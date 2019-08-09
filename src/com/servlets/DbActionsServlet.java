package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Employee;
import com.dao.EmployeeDao;


@WebServlet(
		urlPatterns = "/DbActionsServlet",
		asyncSupported = false
		)
public class DbActionsServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		if(request.getParameter("insertButton") != null){
			if(!request.getParameter("fname").isEmpty() && !request.getParameter("lname").isEmpty() &&
					!request.getParameter("salary").isEmpty() && !request.getParameter("startDate").isEmpty()) {
				Employee emp = new Employee();
				
				emp.setFname(request.getParameter("fname"));
				emp.setLname(request.getParameter("lname"));
				emp.setSalary(Integer.parseInt(request.getParameter("salary")));
				emp.setStartDate(request.getParameter("startDate"));
				EmployeeDao.insertEmp(emp);
				
				response.sendRedirect("index.jsp");
			}else {
				response.sendRedirect("index.jsp");
			}
				
		}else if(request.getParameter("updateButton") != null){
			List<Employee> list = EmployeeDao.getAllEmployees();
			for(Employee emp : list){
				if(emp.getEmpId() == Integer.valueOf(request.getParameter("empId"))){
					emp.setFname(request.getParameter("fname"));
					emp.setLname(request.getParameter("lname"));
					emp.setSalary(Integer.parseInt(request.getParameter("salary")));
					emp.setStartDate(request.getParameter("startDate"));
					EmployeeDao.updateEmp(emp);
				}
			}
			
			response.sendRedirect("index.jsp");
			
		}else if(request.getParameter("deleteButton") != null){
			List<Employee> list = EmployeeDao.getAllEmployees();
			for(Employee emp : list){
				if(emp.getEmpId() == Integer.valueOf(request.getParameter("empId"))){
					EmployeeDao.deleteEmp(emp);
				}
			}
			
			response.sendRedirect("index.jsp");
		}
		
	}
}
