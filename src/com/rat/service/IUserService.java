package com.rat.service;

import com.rat.entity.User;
import com.rat.pojo.PageBean;

public interface IUserService {
	public boolean Login(User user);
   //注册
   public boolean Register(User user);
 //根据账号查询用户
   public User Query(String uname);
   //
   public User getUserById(int uid);
   //更新用户信息
   public boolean Update(String uname,String pwd);
   
   public boolean allUpdate(User user);
 //分页显示
   public PageBean listPage(int currentPage,int pageSize);
   
 //分页查询
   public PageBean queryPage(String keyword,int currentPage,int pageSize);
   
   public boolean delete (int uid);
}
