package com.rat.dao.Impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rat.dao.IUserDao;
import com.rat.entity.User;
import com.rat.utils.DBUtils;

public class UserDaoImpl implements IUserDao{
    //注册
    public  boolean Register(User user) {
         String sql="insert into user(uname,upwd,usex) values(?,?,?)" ;
         Object [] params= {user.getUname(),user.getUpwd(),user.getUsex()};
         return  DBUtils.executeUpdate(sql, params);
    }
    //查询账户是否存在
    public  boolean isExist(String uname) {
        return Query(uname)==null? false:true;
    }
    //登录
    public boolean Login(String uname,String upwd) {
       return Query(uname,upwd)==null? false:true;
   }
    //根据账号查询用户全部信息
    public User Query(String uname) {
         User user= null;
         ResultSet rs = null; 
         try {
             String sql="select * from user where uname =?" ;
             Object [] params= {uname};
             rs=DBUtils.executeQuery(sql, params);
             if(rs.next()) {
            	 int id=rs.getInt("uid");
                 String name=rs.getString("uname");
                 String pwd=rs.getString("upwd");
                 String sex=rs.getString("usex");
                 boolean is_adm=rs.getBoolean("is_adm");
                 user= new User(id,name,pwd,sex);
                 user.setIs_adm(is_adm);
                 System.out.println(user);
             }
         }catch(SQLException e) {
             e.printStackTrace();
         }catch(Exception e) {
             e.printStackTrace();
         }finally {
             try {
                    //先开的后关，后开的先关
                if(rs!=null)rs.close();
                if(DBUtils.pstmt!=null)DBUtils.pstmt.close();
                if(DBUtils.connection !=null)DBUtils.connection.close();
                }catch(SQLException e) {
                    e.printStackTrace();
                }finally {
                    
                }
         }
         return user;
    }
    //根据账户密码确定是否存在
    public User Query(String uname,String upwd) {
         User user= null;
         ResultSet rs = null; 
         try {
             String sql="select * from user where uname =? and upwd=?" ;
             Object [] params= {uname,upwd};
             rs=DBUtils.executeQuery(sql, params);
             if(rs.next()) {
            	 int id=rs.getInt("uid");
                 String name=rs.getString("uname");
                 String pwd=rs.getString("upwd");
                 String sex=rs.getString("usex");
                 boolean is_adm=rs.getBoolean("is_adm");
                 user= new User(id,name,pwd,sex);
                 user.setIs_adm(is_adm);
                 System.out.println(user);
             }
         }catch(SQLException e) {
             e.printStackTrace();
         }catch(Exception e) {
             e.printStackTrace();
         }finally {
             try {
                   //先开的后关，后开的先关
               if(rs!=null)rs.close();
               if(DBUtils.pstmt!=null)DBUtils.pstmt.close();
               if(DBUtils.connection !=null)DBUtils.connection.close();
               }catch(SQLException e) {
                   e.printStackTrace();
               }finally {
                   
               }
         }
         return user;
    }
    //
    public User getUserById(int uid) {
    	 String sql = "select * from user where uid ='" + uid +"'";
         Connection conn = DBUtils.getConn();
         Statement state = null;
         ResultSet rs = null;
         User user = null;
         try {
             state = conn.createStatement();
             rs = state.executeQuery(sql);
             while (rs.next()) {
            	 int id=rs.getInt("uid");
                 String name=rs.getString("uname");
                 String pwd=rs.getString("upwd");
                 String sex=rs.getString("usex");
                 boolean is_adm=rs.getBoolean("is_adm");
                 user=new User(id, name, pwd, sex);
                 user.setIs_adm(is_adm);
             }
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             DBUtils.close(rs, state, conn);
         }
         return user;
    }
    //更新用户信息
    public boolean Update(String uname,String pwd) {
    	String sql="update user set upwd=? where uname=?";
        Object [] params= {pwd,uname};
        return  DBUtils.executeUpdate(sql, params);
    }
    
    public boolean allUpdate(User user) {
    	String sql="update user set uname=?,upwd=?,usex=? where uid=?";
        Object [] params= {user.getUname(),user.getUpwd(),user.getUsex(),user.getUid()};
        return  DBUtils.executeUpdate(sql, params);
    }
    
    public List<User> list() {
        String sql = "select * from user";
        List<User> list = new ArrayList<>();
        Connection conn = DBUtils.getConn();
        Statement state = null;
        ResultSet rs = null;
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            User user = null;
            while (rs.next()) {
            	int id=rs.getInt("uid");
                String name=rs.getString("uname");
                String pwd=rs.getString("upwd");
                String sex=rs.getString("usex");
                boolean is_adm=rs.getBoolean("is_adm");
                user=new User(id, name, pwd, sex);
                user.setIs_adm(is_adm);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, state, conn);
        }
        return list;
    }
    
    public List<User> listQuery(String keyword) {
    	String sql = "select * from user WHERE (uname LIKE '%"+keyword+"%' OR usex LIKE '%"+keyword
                +"%'OR uid LIKE '%"+keyword+ "%' OR is_adm LIKE '%"+keyword+"%')";
        List<User> list = new ArrayList<>();
        Connection conn = DBUtils.getConn();
        Statement state = null;
        ResultSet rs = null;
        try {
        	 state = conn.createStatement();
             rs = state.executeQuery(sql);
             User user = null;
             while (rs.next()) {
             	int id=rs.getInt("uid");
                 String name=rs.getString("uname");
                 String pwd=rs.getString("upwd");
                 String sex=rs.getString("usex");
                 boolean is_adm=rs.getBoolean("is_adm");
                 user=new User(id, name, pwd, sex);
                 user.setIs_adm(is_adm);
                 list.add(user);
             }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, state, conn);
        }
        return list;
    }
    
    public boolean delete (int uid) {
        //delete语句，形如delete from 表名 where id='值';
        String sql = "delete from user where uid=?";
        Object [] params= {uid};
        return  DBUtils.executeUpdate(sql, params);
    }
}