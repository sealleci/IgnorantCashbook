package com.wang.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
                                                              //数据库名
    public static String db_url = "jdbc:mysql://localhost:3306/cashbook?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT";
    public static String db_user = "root";   //用户名
    public static String db_password = ""; //密码
    public static PreparedStatement  pstmt=null;
    public static ResultSet  rs = null;
    public static Connection  connection=null;
     
    public static Connection getConn () {
        Connection _conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//加载驱动
            _conn = DriverManager.getConnection(db_url, db_user, db_password);//获取连接对象
        } catch (Exception e) {
            e.printStackTrace();
        }
        return _conn;
    }
     
    /**
     * 关闭连接
     * @param _state
     * @param _conn
     */
    public static void close (Statement _state, Connection _conn) {
        if (_state != null) {
            try {
                _state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (_conn != null) {
            try {
                _conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
     
    /**
     * 关闭连接
     * @param _rs
     * @param _state
     * @param _conn
     */
    public static void close (ResultSet _rs, Statement _state, Connection _conn) {
        if (_rs != null) {
            try {
                _rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (_state != null) {
            try {
                _state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (_conn != null) {
            try {
                _conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static boolean executeUpdate(String sql,Object [] params) {
        boolean flag = false;
        try {      
        	//与数据库建立连接
	        connection = getConn();
	        pstmt = connection.prepareStatement(sql);
	        for(int i=0;i<params.length;i++) {
	            pstmt.setObject(i+1, params[i]);
	        }
	        int count=pstmt.executeUpdate();//返回值表示，增删改几条数据
	        //处理结果
	        if(count>0){
	            System.out.println("DB操作成功");
	            flag=true;
	        }
        }catch(SQLException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                //先开的后关，后开的先关
	            if(pstmt!=null) {
	            	pstmt.close();
	            }
	            if(connection !=null) {
	            	connection.close();
	            }
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
       return flag;
    }

    public static ResultSet executeQuery(String sql,Object [] params) {
        try {
	        connection = getConn();
	        pstmt = connection.prepareStatement(sql);
	        if(params!=null) {
	        for(int i=0;i<params.length;i++) {
	            pstmt.setObject(i+1, params[i]);
	        }
        }
        rs = pstmt.executeQuery();
        return rs;
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
}