package com.somnus.dao;

import java.util.List;
import java.util.Map;
import com.somnus.bean.Book;
import com.somnus.core.dao.BaseHibernateDao;


public interface BookDao extends BaseHibernateDao<Book>{
	public void save(Book book)throws Exception ;
	public void delete(int  id)throws Exception ;
	public void update(Book Book) throws Exception;
	public Book findById(int id)throws Exception ;
	public List<Book> queryBook(int  offset,int limit,Map<String,Object> paramMap)throws Exception ;
	public int queryBookCount(Map<String,Object> paramMap)throws Exception ;
}
