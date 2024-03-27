package com.rat.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("logout success:"+request.getSession().getAttribute("username"));
    	request.getSession().invalidate();
    	request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
