package com.wang.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wang.entity.User;
import com.wang.pojo.PageBean;
import com.wang.service.IUserService;
import com.wang.service.Impl.UserServiceImpl;
import com.wang.utils.WebUtils;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet{
	 private static final long serialVersionUID = 1L;
	    //private HomeCostService homeCostService = new HomeCostService();
	    private IUserService userService=new UserServiceImpl();
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doPost(request, response);
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	request.setCharacterEncoding("utf-8");
	    	response.setCharacterEncoding("utf-8");
	        String action = request.getParameter("action");
	        
	        System.out.println("action:"+action);
	        
	        if("delete".equals(action)) {
	            delete(request, response);
	        }else if("update".equals(action)) {
	            update(request, response);
	        }else if("list".equals(action)) {
	            listPage(request, response);
	        	//request.getRequestDispatcher("/user_manager.jsp").forward(request, response);
	        }else if("getUserById".equals(action)) {
	            getUserById(request, response);
	        }else if("query".equals(action)) {
	        	queryPage(request, response);
	        }
	    }
	        
	    //删除消费记录
	    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        //获取id
	        int id  = WebUtils.parseInt(request.getParameter("uid"), 0);
	        if (userService.delete(id)) {
	            //页面重定向
	        	System.out.println("delete success");
	            response.sendRedirect(request.getContextPath()+"/userServlet?action=list&currentPage=1&pageSize=6");
	        }else {
	            //请求转发
	        	System.out.println("delete fail");
	            request.setAttribute("msg", "删除失败，联系管理员");
	            request.getRequestDispatcher("/userServlet?action=list&currentPage=1&pageSize=6").forward(request, response);
	        }
	    }
	    
	    //修改消费记录
	    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        //获取参数
	        int id  = WebUtils.parseInt(request.getParameter("uid"), 0);
	        String name = request.getParameter("uname");
	        String pwd = request.getParameter("upwd");
	        String sex=request.getParameter("usex");
	        boolean is_adm=(request.getParameter("is_adm")=="true");
	        //封装
	        User user = new User(id,name,pwd,sex);
	        user.setIs_adm(is_adm);
	        //执行删除操作，返回1,修改成功，反之失败
	        if(userService.allUpdate(user)) {
	        	System.out.println("update success");
	            response.sendRedirect(request.getContextPath()+"/userServlet?action=list&currentPage=1&pageSize=6");
	        }else {
	        	System.out.println("update fail");
	            request.setAttribute("msg", "修改失败，联系管理员");
	            request.setAttribute("userinfo", user);
	            request.getRequestDispatcher("/user_edit.jsp").forward(request, response);
	        }
	    }
    
	    //通过id查询该条消费记录
	    private void getUserById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        int uid  = WebUtils.parseInt(request.getParameter("uid"), 0);
	        User user = userService.getUserById(uid);
	        System.out.println("get user info by id");
	        request.setAttribute("userinfo", user);
	        request.getRequestDispatcher("/user_edit.jsp").forward(request, response);
	    }
	        
	    //分页显示
	    public void listPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	int currentPage= WebUtils.parseInt(request.getParameter("currentPage"), 0);
	    	int pageSize= WebUtils.parseInt(request.getParameter("pageSize"), 0);
	    	System.out.println("currentPage:"+currentPage+",pageSize:"+pageSize);
	    	System.out.println("list page");
	    	PageBean pageBean = userService.listPage(currentPage,pageSize);
	        request.setAttribute("pageBean", pageBean);
	        request.getRequestDispatcher("/user_manager.jsp").forward(request, response);
	    }
    
	    //分页查询
	    public void queryPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	String keyword=request.getParameter("keyword");
	    	int currentPage= WebUtils.parseInt(request.getParameter("currentPage"), 0);
	    	int pageSize= WebUtils.parseInt(request.getParameter("pageSize"), 0);
	    	System.out.println("currentPage:"+currentPage+",pageSize:"+pageSize);
	    	System.out.println("query page");
	    	PageBean pageBean = userService.queryPage(keyword, currentPage,pageSize);
	        request.setAttribute("pageBean", pageBean);
	        request.getRequestDispatcher("/user_query.jsp").forward(request, response);
	    }
}
