package com.rat.mytag;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MyTags extends SimpleTagSupport{
	private String message;
	public void setMessage(String msg) {
		this.message = msg;
	}
	StringWriter sw = new StringWriter();
	public void doTag() throws JspException, IOException
    {
       if (message != null) {
          /* 从属性中使用消息 */
          JspWriter out = getJspContext().getOut();
          out.println( message );
       }
       else {
          /* 从内容体中使用消息 */
    	   getJspBody().invoke(sw);
    	   try {
    		   getJspContext().getOut().println(
        			"BJT "+
        			sw.toString().split(" ")[0].split("-")[0]+"年"+
        			sw.toString().split(" ")[0].split("-")[1]+"月"+
        			sw.toString().split(" ")[0].split("-")[2]+"日"+
        			"<br/>"+
        			sw.toString().split(" ")[1].split(":")[0]+"时"+
        			sw.toString().split(" ")[1].split(":")[1]+"分"+
        			sw.toString().split(" ")[1].split(":")[2]+"秒"
        			  );
    	   }catch (Exception e) {
    		   e.printStackTrace();
    	   }
       }
   }
}
