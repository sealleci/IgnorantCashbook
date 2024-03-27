package com.rat.pojo;

import java.math.BigDecimal;

public class HomeCost {
    
    private int id;//id
    private String name;//消费名称
    private BigDecimal money;//消费金额
    private String date;//消费日期
    private BigDecimal sum;//累计消费
    private String uname="undefined";//用户名
    private String remark;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getMoney() {
        return money;
    }
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public BigDecimal getSum() {
        return sum;
    }
    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getRemark() {
		return remark;
	}
    public void setRemark(String remark) {
		this.remark = remark;
	}
    
    @Override
    public String toString() {
        return "HomeCost [uname="+uname+", id=" + id + ", name=" + name + ", money=" + money + ", date=" + date +", uname="+uname +", sum=" + sum + ", remark="+remark+"]";
    }
    
    public HomeCost() {}
    
    public HomeCost(String name, BigDecimal money) {
        super();
        this.name = name;
        this.money = money;
    }
    
    public HomeCost(int id,String name,BigDecimal money, String date) {
        super();
        this.id=id;
        this.name = name;
        this.money=money;
        this.date=date;
    }
    public HomeCost(int id, String name, BigDecimal money, String date, BigDecimal sum) {
        super();
        this.id = id;
        this.name = name;
        this.money = money;
        this.date = date;
        this.sum = sum;
    }
    
}