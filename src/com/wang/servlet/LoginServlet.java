package com.wang.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wang.entity.User;
import com.wang.service.IUserService;
import com.wang.service.Impl.UserServiceImpl;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        
        String name= request.getParameter("uname");
        String pwd= request.getParameter("upwd");
        String captcha=request.getParameter("captcha");
        String kaptchaExpected = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
       
        System.out.println("expected captcha:"+kaptchaExpected+",input captcah:"+captcha);
        
        User user = new User(name,pwd);
        
        if(!(captcha.toLowerCase().equals(kaptchaExpected.toLowerCase()))) {//验证码不相符
        	System.out.println("wrong captcha");
        	request.setAttribute("message","captwrong");
        	request.setAttribute("username",name);
            request.setAttribute("userpwd",pwd);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }else {
        	IUserService userservice = new UserServiceImpl();
            boolean result=userservice.Login(user);
        	if(!result) {
        		System.out.println("login fail");
                request.setAttribute("message","error");
                request.setAttribute("username",name);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }else {
            	boolean is_adm=userservice.Query(name).isIs_adm();
            	System.out.println("login success:"+name+","+is_adm);
                request.setAttribute("message","noerror");
                request.getSession().setAttribute("username",name);
                request.getSession().setAttribute("is_adm",is_adm);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}