package com.rat.service;

import java.util.List;

import com.rat.dao.HomeCostDao;
import com.rat.pojo.HomeCost;
import com.rat.pojo.PageBean;

public class HomeCostService {
    
    HomeCostDao homeCostDao = new HomeCostDao();
    
    //新增消费账单
    public int add(HomeCost homecost) {
        return homeCostDao.add(homecost);
    }
    
    //删除消费账单
    public int delete(int id,String uname) {
        return homeCostDao.delete(id,uname);
    }
    
    //修改消费账单
    public int update(HomeCost homecost) {
        return homeCostDao.update(homecost);
    }
    
    //关键字查询
    public List<HomeCost> query(String keyword,String uname) {
        return homeCostDao.query(keyword,uname);
    }
    
    //全部消费记录
    public List<HomeCost> list(String uname) {
        return homeCostDao.list(uname);
    }
    
    //由id查找某条消费记录
    public HomeCost getHomeCostById(int id,String uname) {
        return homeCostDao.getHomeCostById(id,uname);
    }
    
    //分页显示
    public PageBean listPage(int currentPage,int pageSize,String uname) {
    	// 创建pagebean的对象
        PageBean pageBean =new PageBean();
        //当前页
        pageBean.setCurrentPage(currentPage);
        List<HomeCost> list =homeCostDao.list(uname);
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
    public PageBean queryPage(String keyword,int currentPage,int pageSize,String uname) {
    	PageBean pageBean =new PageBean();
        //当前页
        pageBean.setCurrentPage(currentPage);
        List<HomeCost> list =homeCostDao.query(keyword,uname);
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
    
}