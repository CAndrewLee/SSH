package com.somnus.core.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ACTION基础类，集成一些常用的方法
 * 
 * @author admin
 * 
 */
public class BaseAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	/*
	 * 第几页
	 */
	private int offset;
	/*
	 * 每页显示的条数
	 */
	private int limit;
	/*
	 * 查询条件
	 */
	private String whereSql;
	/*
	 * 排序条件
	 */
	private String orderSql;
	
	/**
	 * 获取url参数的值
	 * 
	 * @param parameter
	 *            :参数名称
	 * @return
	 */
	protected String getParameter(String parameter)
	{
		return this.getRequest().getParameter(parameter);
	}

	/**
	 * <p>
	 * 取得request对象
	 * </p>
	 * 
	 * @return
	 */
	protected HttpServletRequest getRequest()
	{
		return ServletActionContext.getRequest();
	}

	/**
	 * <p>
	 * 取得response对象
	 * </p>
	 * 
	 * @return
	 */
	protected HttpServletResponse getResponse()
	{
		return ServletActionContext.getResponse();
	}

	/**
	 * <p>
	 * 取得session对象
	 * </p>
	 * 
	 * @return
	 */
	protected HttpSession getSession()
	{
		return this.getRequest().getSession();
	}

	/**
	 * <p>
	 * 取得Applaction对象
	 * </p>
	 * 
	 * @return
	 */
	protected ServletContext getApplaction()
	{
		return ServletActionContext.getServletContext();
	}

	/**
	 * <p>
	 * 将对象保存到session中
	 * </p>
	 * 
	 * @param key
	 * @param obj
	 */
	protected void setSessionAttribute(String key, Object obj)
	{
		if (key != null && !key.trim().equals(""))
			this.getSession().setAttribute(key, obj);
	}

	/**
	 * <p>
	 * 获取session中对应key的对象
	 * </p>
	 * 
	 * @param key
	 * @return
	 */
	protected Object getSessionAttribute(String key)
	{
		if (key != null && !key.trim().equals(""))
			return this.getSession().getAttribute(key);
		else
			return null;
	}

	/**
	 * <p>
	 * 移除session中对应key的对象
	 * </p>
	 * 
	 * @param key
	 * @return
	 */
	protected void removeSessionAttribute(String key)
	{
		if (key != null && !key.trim().equals(""))
			this.getSession().removeAttribute(key);
	}

	/**
	 * 将数据以html方式输出到页面
	 * 
	 * @param String
	 */
	protected void outputHTMLData(String outputString)
	{
		try
		{
			this.getResponse().setContentType("text/html");
			this.getResponse().setCharacterEncoding("utf-8");
			this.getResponse().setHeader("Cache-Control", "no-cache");
			this.getResponse().getWriter().println(outputString);
			this.getResponse().getWriter().close();
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit()
	{
		return limit;
	}

	public void setLimit(int limit)
	{
		this.limit = limit;
	}

	public String getWhereSql()
	{
		return whereSql;
	}

	public void setWhereSql(String whereSql)
	{
		this.whereSql = whereSql;
	}

	public String getOrderSql()
	{
		return orderSql;
	}

	public void setOrderSql(String orderSql)
	{
		this.orderSql = orderSql;
	}
	
}
