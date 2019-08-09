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
        urlPatterns = {"/tableServlet"},
        asyncSupported = false
)
public class tableServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("asdasdasd");
		List<Employee> list = EmployeeDao.getAllEmployees();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		response.sendRedirect("index.jsp");
	}
}
