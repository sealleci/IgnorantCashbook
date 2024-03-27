package com.rat.entity;

public class User {
      private int uid;
      private String uname;
      private String upwd;
      private String usex;
      private boolean is_adm;
      
	@Override
    public String toString() {
        return "User [uid=" + uid + ", uname=" + uname + ", upwd=" + upwd + ", usex=" + usex + ", is_adm="+is_adm+"]";
    }
    
    public User() {
        
    }
    public User( String uname, String upwd) {
        this.uname = uname;
        this.upwd = upwd;
    }
    public User( String uname, String upwd, String usex) {
        this.uname = uname;
        this.upwd = upwd;
        this.usex = usex;
    }
    public User(int uid, String uname, String upwd, String usex) {
        this.uid = uid;
        this.uname = uname;
        this.upwd = upwd;
        this.usex = usex;
    }

    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getUpwd() {
        return upwd;
    }
    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }
    public String getUsex() {
        return usex;
    }
    public void setUsex(String usex) {
        this.usex = usex;
    }

	public boolean isIs_adm() {
		return is_adm;
	}

	public void setIs_adm(boolean is_adm) {
		this.is_adm = is_adm;
	}  
}
