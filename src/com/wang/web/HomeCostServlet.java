package com.wang.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wang.pojo.HomeCost;
import com.wang.pojo.PageBean;
import com.wang.service.HomeCostService;
import com.wang.utils.WebUtils;

/**
 * 访问地址url:localhost:8080/homeCost/manager/homeCostServlet
 * Servlet implementation class HomeCostServlet
 */
@WebServlet("/manager/homeCostServlet")
public class HomeCostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HomeCostService homeCostService = new HomeCostService();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        
        System.out.println("action:"+action);
        
        if("add".equals(action)) {
            add(request, response);
        }else if("delete".equals(action)) {
            delete(request, response);
        }else if("update".equals(action)) {
            update(request, response);
        }else if("list".equals(action)) {
            listPage(request, response);
        }else if("getHomeCostById".equals(action)) {
            getHomeCostById(request, response);
        }else if("query".equals(action)) {
        	queryPage(request, response);
        }else if ("showDetails".equals(action)) {
        	showDetails(request, response);
        }
    }
    
    //添加消费记录
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取网页提交的参数
        String name = request.getParameter("name");
        BigDecimal money = WebUtils.bigdecimal(request.getParameter("money"), new BigDecimal(0.00));
        String uname=(String)request.getSession().getAttribute("username");
        String remark=(String)request.getParameter("remark");
        remark=remark.substring(0,Math.min(200,remark.length()));
        //封装成类对象
        HomeCost homeCost = new HomeCost(name,money);
        homeCost.setUname(uname);
        homeCost.setRemark(remark);
        //执行添加操作，返回1,添加成功，反之失败
        if(homeCostService.add(homeCost) == 1) {
            //页面重定向
        	System.out.println("add success");
            response.sendRedirect(request.getContextPath()+"/manager/homeCostServlet?action=list&currentPage=1&pageSize=6");
        }else {
            //请求转发
        	System.out.println("add fail");
            request.setAttribute("msg", "添加失败，联系管理员");
            request.setAttribute("homeCost", homeCost);
            request.getRequestDispatcher("/cost_edit.jsp").forward(request, response);
        }
    }
    
    //删除消费记录
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取id
        int id  = WebUtils.parseInt(request.getParameter("id"), 0);
        String uname=(String)request.getSession().getAttribute("username");
        //执行删除操作，返回1,删除成功，反之失败
        if (homeCostService.delete(id,uname) == 1) {
            //页面重定向
        	System.out.println("delete success");
            response.sendRedirect(request.getContextPath()+"/manager/homeCostServlet?action=list&currentPage=1&pageSize=6");
        }else {
            //请求转发
        	System.out.println("delete fail");
            request.setAttribute("msg", "删除失败，联系管理员");
            request.getRequestDispatcher("/manager/homeCostServlet?action=list&currentPage=1&pageSize=6").forward(request, response);
        }
        
    }
    
    //修改消费记录
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        int id  = WebUtils.parseInt(request.getParameter("id"), 0);
        String name = request.getParameter("name");
        BigDecimal money = WebUtils.bigdecimal(request.getParameter("money"), new BigDecimal(0.00));
        String date = request.getParameter("date");
        String uname=(String)request.getSession().getAttribute("username");
        String remark=(String)request.getParameter("remark");
        remark=remark.substring(0,Math.min(200,remark.length()));
        //封装
        HomeCost homeCost = new HomeCost(id,name,money,date);
        homeCost.setUname(uname);
        homeCost.setRemark(remark);
        //执行删除操作，返回1,修改成功，反之失败
        if(homeCostService.update(homeCost) == 1) {
        	System.out.println("update success");
            response.sendRedirect(request.getContextPath()+"/manager/homeCostServlet?action=list&currentPage=1&pageSize=6");
        }else {
        	System.out.println("update fail");
            request.setAttribute("msg", "修改失败，联系管理员");
            request.setAttribute("homeCost", homeCost);
            request.getRequestDispatcher("/cost_edit.jsp").forward(request, response);
        }
    }
    
 
    
    //查询全部消费记录
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String uname=(String)request.getSession().getAttribute("username");
    	List<HomeCost> homeCost = homeCostService.list(uname);
    	System.out.println("list info");
        request.setAttribute("homeCost", homeCost);
        request.getRequestDispatcher("/manager.jsp").forward(request, response);
        
    }
    
    //通过id查询该条消费记录
    private void getHomeCostById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id  = WebUtils.parseInt(request.getParameter("id"), 0);
        String uname=(String)request.getSession().getAttribute("username");
        HomeCost homeCost = homeCostService.getHomeCostById(id,uname);
        System.out.println("get info by id");
        request.setAttribute("homeCost", homeCost);
        request.getRequestDispatcher("/cost_edit.jsp").forward(request, response);
    }
    
    //显示详情页面
    protected void showDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id  = WebUtils.parseInt(request.getParameter("id"), 0);
        String uname=(String)request.getSession().getAttribute("username");
        HomeCost homeCost = homeCostService.getHomeCostById(id,uname);
        System.out.println("show details");
        request.setAttribute("homeCost", homeCost);
        request.getRequestDispatcher("/show_details.jsp").forward(request, response);
    }
    
    //分页显示
    public void listPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String uname=(String)request.getSession().getAttribute("username");
    	int currentPage= WebUtils.parseInt(request.getParameter("currentPage"), 0);
    	int pageSize= WebUtils.parseInt(request.getParameter("pageSize"), 0);
    	System.out.println("currentPage:"+currentPage+",pageSize:"+pageSize);
    	System.out.println("list page");
    	PageBean pageBean = homeCostService.listPage(currentPage,pageSize,uname);
        request.setAttribute("pageBean", pageBean);
        request.getRequestDispatcher("/manager.jsp").forward(request, response);
    }
    
    //通过关键词查询
    protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword=request.getParameter("keyword");
        String uname=(String)request.getSession().getAttribute("username");
        List<HomeCost> homeCost = homeCostService.query(keyword,uname);
        System.out.println("query info");
        request.setAttribute("homeCost", homeCost);
        request.getRequestDispatcher("/query.jsp").forward(request, response);
        
    }
    
    //分页查询
    public void queryPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String keyword=request.getParameter("keyword");
    	String uname=(String)request.getSession().getAttribute("username");
    	int currentPage= WebUtils.parseInt(request.getParameter("currentPage"), 0);
    	int pageSize= WebUtils.parseInt(request.getParameter("pageSize"), 0);
    	System.out.println("currentPage:"+currentPage+",pageSize:"+pageSize);
    	System.out.println("query page");
    	PageBean pageBean = homeCostService.queryPage(keyword, currentPage,pageSize,uname);
        request.setAttribute("pageBean", pageBean);
        request.getRequestDispatcher("/query.jsp").forward(request, response);
    }
}