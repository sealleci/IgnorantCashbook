package com.rat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.code.kaptcha.Constants;

@WebServlet("/checkCaptcha")
public class CheckCaptchaServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter myOut=response.getWriter();
    	request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String trueCaptcha = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String inputCaptcha = request.getParameter("captcha");
        if(trueCaptcha.toLowerCase().equals(inputCaptcha.toLowerCase())) {
        	myOut.write("验证码输入正确");
        } else {
        	myOut.write("验证码输入错误");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
