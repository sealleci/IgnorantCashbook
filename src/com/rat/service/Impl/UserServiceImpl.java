package com.rat.service.Impl;

import java.util.List;

import com.rat.dao.IUserDao;
import com.rat.dao.Impl.UserDaoImpl;
import com.rat.entity.User;
import com.rat.pojo.PageBean;
import com.rat.service.IUserService;

public class UserServiceImpl implements IUserService{
    IUserDao userdao= new UserDaoImpl();
    //登录
     public boolean Login(User user) {
          boolean flag=false;
          if(userdao.Login(user.getUname(),user.getUpwd())) {
                flag=true;
          }
          return flag;
     }
    //注册
    public boolean Register(User user) {
        boolean flag=false;
        if(!userdao.isExist(user.getUname())) {
            userdao.Register(user);
            flag=true;
        }else {
            System.out.println("此人已存在");
        }
       return flag; 
    }
  //根据账号查询用户
    public User Query(String uname) {
        return userdao.Query(uname);
    }
    
    //
    public User getUserById(int uid) {
    	return userdao.getUserById(uid);
    }
    //更新用户信息
    public boolean Update(String uname,String pwd) {
    	return userdao.Update(uname, pwd);
    }
    
    public boolean allUpdate(User user) {
        return userdao.allUpdate(user);
    }
    
  //分页显示
    public PageBean listPage(int currentPage,int pageSize) {
    	// 创建pagebean的对象
        PageBean pageBean =new PageBean();
        //当前页
        pageBean.setCurrentPage(currentPage);
        List<User> list =userdao.list();
        //总记录数
        int totalCount =list.size();
        pageBean.setTotalCount(totalCount);
        //每页显示记录数
        pageBean.setPageSize(pageSize);
        //总页数
        //总页数除以每页显示记录数
        //能够整除
        int totalPage=0;
        if(totalCount%pageSize==0) {//整除

            totalPage=totalCount/pageSize;
        }else {
            totalPage=totalCount/pageSize+1;
        }
        pageBean.setTotalPage(totalPage);
        //开始位置
        int begin =(currentPage-1)*pageSize;
        //每页记录的list集合
        pageBean.setList(list.subList(begin, Math.min(begin+pageSize,totalCount)));
        return pageBean;
    	//return homeCostDao.listPage(currentPage,pageSize,uname);
    }
    
  //分页查询
    public PageBean queryPage(String keyword,int currentPage,int pageSize) {
    	PageBean pageBean =new PageBean();
        //当前页
        pageBean.setCurrentPage(currentPage);
        List<User> list =userdao.listQuery(keyword);
        //总记录数
        int totalCount =list.size();
        pageBean.setTotalCount(totalCount);
        //每页显示记录数
        pageBean.setPageSize(pageSize);
        //总页数
        //总页数除以每页显示记录数
        //能够整除
        int totalPage=0;
        if(totalCount%pageSize==0) {//整除

            totalPage=totalCount/pageSize;
        }else {
            totalPage=totalCount/pageSize+1;
        }
        pageBean.setTotalPage(totalPage);
        //开始位置】
        int begin =(currentPage-1)*pageSize;
        //每页记录的list集合
        pageBean.setList(list.subList(begin, Math.min(begin+pageSize,totalCount)));
        return pageBean;
    	//return homeCostDao.queryPage(keyword,currentPage,pageSize,uname);
    }
    
    public boolean delete (int uid) {
        return  userdao.delete(uid);
    }
}