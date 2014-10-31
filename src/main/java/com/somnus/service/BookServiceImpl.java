package com.somnus.service;

import java.util.Map;
import com.somnus.bean.Book;
import com.somnus.core.Page;
import com.somnus.dao.BookDao;

public class BookServiceImpl implements BookService {
	
	private BookDao bookdao;
	/**
	 * 删除书籍
	 * @param 书籍id
	 * @throws Exception 
	 * @return删除成功与否
	 */
	public void deleteBook(int id) throws Exception {
		bookdao.delete(id);
	}
	/**
	 * 根据ID查找书籍
	 * @param id 书籍编号
	 * @return 找不到时返回null  
	 * @throws Exception 
	 */
	public Book findById(int id) throws Exception {
		return bookdao.findById(id);
	}
	/**
	 * 查找全部书籍
	 * @return
	 * @throws Exception 
	 */
	public Page<Book> queryBook(int  offset,int limit,Map<String,Object> paramMap) throws Exception {
		Page<Book> page = new Page<Book>();
		page.setRows(bookdao.queryBook( offset, limit, paramMap));
		int total = bookdao.queryBookCount( paramMap);
		page.setTotal(total);
		int totalpage = total/limit==0 ? total/limit : total/limit+1;
		page.setTotalPage(totalpage);
		int pagenum = (offset/limit)+1;
		if(pagenum<1) 
			pagenum = 1;
		if(pagenum>totalpage)
			pagenum = totalpage;
		page.setPageNum(pagenum);
		
		return page;
	}
	/**
	 * 更新书籍
	 * @param Book
	 * @throws Exception 
	 * @return更新成功与否  
	 */
	public void updateBook(Book Book) throws Exception {
		bookdao.update(Book);
	}
	/**
	 * 保存书籍
	 * @param Book
	 * @return
	 * @throws Exception 
	 */
	public void saveBook(Book Book) throws Exception {
		bookdao.save(Book);
	}
	public int queryBookCount(Map<String,Object> paramMap) throws Exception{
		return bookdao.queryBookCount(paramMap);
	}
	public void timeTask()
	{
		System.out.println("定时执行了timeTask方法");
	}
	public BookDao getBookdao() {
		return bookdao;
	}
	public void setBookdao(BookDao bookdao) {
		this.bookdao = bookdao;
	}

}
