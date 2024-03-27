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
@WebServlet("/ChangePwdServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        
        String uname= (String)request.getSession().getAttribute("username");
        String pastpwd= request.getParameter("pastpwd");
        String newpwd=request.getParameter("newpwd");
        String captcha=request.getParameter("captcha");
        String kaptchaExpected = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
       
        System.out.println("expected captcha:"+kaptchaExpected+",input captcah:"+captcha);
        
        if(!(captcha.toLowerCase().equals(kaptchaExpected.toLowerCase()))) {//验证码不相符
        	System.out.println("wrong captcha");
            request.getRequestDispatcher("/change_pwd.jsp").forward(request, response);
        }else {
        	IUserService userservice = new UserServiceImpl();
            User user=userservice.Query(uname);
            boolean result=true;
            if(user==null) {
            	result=false;
            }else {
            	System.out.println("past pwd:"+user.getUpwd()+",input past pwd:"+pastpwd);
                if(!user.getUpwd().equals(pastpwd)) {
                	result=false;
                }
            }
            
        	if(!result) {
        		System.out.println("change fail");
                request.setAttribute("message","error");
                request.getRequestDispatcher("/change_pwd.jsp").forward(request, response);
            }else {
            	if(userservice.Update(uname, newpwd)) {
            		System.out.println("change success:"+uname);
                    request.setAttribute("message","change_suc");
                    request.setAttribute("username",uname);
                    request.setAttribute("userpwd",newpwd);
                    request.getSession().setAttribute("username",null);
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
            	}else {
            		System.out.println("change fail");
                    request.setAttribute("message","fail");
                    request.getRequestDispatcher("/change_pwd.jsp").forward(request, response);
            	}    	
            }
        }
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}