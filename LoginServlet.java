package com.briup.ch08.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.ch08.service.ICustomerService;
import com.briup.ch08.service.impl.CustomerServiceImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ICustomerService customerService;
    public LoginServlet() {
        super();
        customerService = new CustomerServiceImpl();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 设置编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//2. 获取参数
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		//3.调用service完成业务逻辑
		customerService.login(name, password);
		//4.页面跳转
		request.getRequestDispatcher("/success.jsp").forward(request, response);
	}

}
