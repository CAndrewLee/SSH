package com.somnus.service;

import java.util.Map;
import com.somnus.bean.Book;
import com.somnus.core.Page;


public interface BookService {
	/**
	 * 删除书籍
	 * @param 书籍id
	 * @return删除成功与否
	 */
	public void deleteBook(int id)throws Exception;
	/**
	 * 根据ID查找书籍
	 * @param id 书籍编号
	 * @return 找不到时返回null  
	 */
	public Book findById(int id)throws Exception;
	/**
	 * 查找全部书籍
	 * @return
	 */
	public Page<Book> queryBook(int  offset,int limit,Map<String,Object> paramMap)throws Exception;
	/**
	 * 更新书籍
	 * @param Book
	 * @return更新成功与否  
	 */
	public void updateBook(Book Book)throws Exception;
	/**
	 * 保存书籍
	 * @param Book
	 * @return
	 */
	public void saveBook(Book Book)throws Exception;
	
	public int queryBookCount(Map<String,Object> paramMap)throws Exception;
	public void timeTask();

}
