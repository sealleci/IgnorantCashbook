package com.rat.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rat.entity.User;
import com.rat.service.IUserService;
import com.rat.service.Impl.UserServiceImpl;
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        
        String name= request.getParameter("uname");
        String pwd= request.getParameter("upwd");
        String sex= request.getParameter("usex");
        String captcha=request.getParameter("captcha");
        String kaptchaExpected = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        
        System.out.println("expected captcha:"+kaptchaExpected+",input captcah:"+captcha);
        
        User user = new User(name,pwd,sex);

        if(!(captcha.toLowerCase().equals(kaptchaExpected.toLowerCase()))) {//验证码不相符
        	System.out.println("wrong captcha");
        	request.setAttribute("message","captwrong");
        	request.setAttribute("username",name);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }else {
	        IUserService userservice = new UserServiceImpl();
	        boolean result=userservice.Register(user);
	        if(!result) {
	        	System.out.println("login fail");
	            request.setAttribute("message","error");
	            request.getRequestDispatcher("/register.jsp").forward(request, response);
	        }else {
	        	System.out.println("login success:"+name);
	            request.setAttribute("message","noerror");
	            request.setAttribute("username",name);
	            request.setAttribute("userpwd",pwd);
	            request.getRequestDispatcher("/login.jsp").forward(request, response);
	        }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
