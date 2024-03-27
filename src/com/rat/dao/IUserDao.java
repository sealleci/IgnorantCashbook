package com.rat.dao;

import java.util.List;

import com.rat.entity.User;

public interface IUserDao {
    //注册
    public  boolean Register(User user) ;
    //查询账户是否存在
    public  boolean isExist(String uname) ;
    //登录
    public  boolean Login(String uname,String upwd) ;
    //根据帐号查询用户全部信息
    public User Query(String uname) ;
    //
    public List<User> listQuery(String keyword) ;
    //更新用户信息
    public boolean Update(String uname,String pwd);
    //
    public boolean allUpdate(User user);
    //分页显示
    public List<User> list();
    //
    public User getUserById(int uid) ;
    
    public boolean delete (int uid);
}