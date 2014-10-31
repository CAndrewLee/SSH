package com.somnus.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import com.somnus.bean.Book;
import com.somnus.core.dao.BaseHibernateDaoImpl;

@SuppressWarnings("unused")
public class BookDaoImpl extends BaseHibernateDaoImpl<Book> implements BookDao{

	private static final Logger log = LoggerFactory.getLogger(BookDaoImpl.class);

	public void save(Book book) throws Exception 
	{
		this.saveObject(book);
	}

	public void update(Book book) throws Exception
	{  
		this.saveOrUpdateObject(book);
	}

	public void delete(int  id) throws Exception
	{ 
		String delHQL="delete from Book where id="+id;
		this.deleteObjectsByIds(Book.class, new String[]{String.valueOf(id)});

	}

	public Book findById(int id) throws Exception 
	{ 
		Book book = null;
		book = (Book) this.loadObject(Book.class, id);
		return book;
	}


	public List<Book> queryBook(int  offset,int limit,Map<String,Object> paramMap) throws Exception { 
		StringBuffer hql = new StringBuffer("from Book t where 1= 1");
		if(paramMap.size() != 0)
		{
			hql.append(" and (t.bookname like :key or t.bookauthor like :key)");
		}
		List<Book> list  = this.findPageByQuery(hql.toString(),paramMap,offset, limit);
		return list;
	}


	public int queryBookCount(Map<String,Object> paramMap) throws Exception {
		StringBuffer hql = new StringBuffer("select count(*) from Book t where 1 = 1");
		if(paramMap.size() != 0)
		{
			hql.append(" and (t.bookname like :key or t.bookauthor like :key)");
		}
		int count  = this.getInt(hql.toString(),paramMap);

		return count;
	}
}
