package com.somnus.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.somnus.bean.Book;
import com.somnus.core.Page;
import com.somnus.core.action.BaseAction;
import com.somnus.service.BookService;

public class BookAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private BookService bookService;
	private Integer id; 
	private String bookname;
	private String bookauthor;
	private Float bookprice;
	private Page<Book> page;

	public String addBook()  {
		HttpServletRequest request = this.getRequest();
		Book book = new Book();
		book.setBookname(bookname);
		book.setBookauthor(bookauthor);
		book.setBookprice(bookprice);
		try
		{
			bookService.saveBook(book);
			return SUCCESS;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			String message = "新增失败!";
			request.setAttribute("message", message);
			return  ERROR;
		}
	}
	public String deleteBook() {
		HttpServletRequest request = this.getRequest();
		int id = Integer.parseInt(request.getParameter("id"));
		try
		{
			bookService.deleteBook(id);
			return SUCCESS;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			String message = "删除失败!";
			request.setAttribute("message", message);
			return ERROR;
		}
	}
	public String listBook() {
		HttpServletRequest request = this.getRequest();
		int offset = request.getParameter("offset") == null ?0:Integer.parseInt(request.getParameter("offset"));
		int limit = request.getParameter("limit") == null?10:Integer.parseInt(request.getParameter("limit"));
		String key = request.getParameter("key");
		Map<String,Object> map = new HashMap<String,Object>();
		if(!"".equals(key) && key !=null)
		{
			map.put("key", "%"+key+"%");
		}
		try
		{
			page = bookService.queryBook(offset, limit, map);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	
		request.setAttribute("books", page.getRows());
		
		request.setAttribute("pagenum", page.getPageNum());
		
		request.setAttribute("totalcount", page.getTotal());
		
		request.setAttribute("totalPage", page.getTotalPage());
		
		return SUCCESS;
	}
	public String queryBook() {
		HttpServletRequest request = this.getRequest();
		String id = request.getParameter("id");
		Book book = null;
		try
		{
			book = bookService.findById(Integer.parseInt(id));
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		request.setAttribute("book", book);
		
		return SUCCESS;
		
	}	
	public String updateBook() {
		HttpServletRequest request = this.getRequest();
		Book book =new Book();
		book.setBookname(bookname);
		book.setBookauthor(bookauthor);
		book.setBookprice(bookprice);
		book.setId(id);
		try
		{
			bookService.updateBook(book);
			return SUCCESS;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			String message = "更新失败!";
			request.setAttribute("message", message);
			return ERROR;
		}
	}	
	
	public void setbookService(BookService bookService) {
		this.bookService = bookService;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getBookauthor() {
		return bookauthor;
	}

	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}

	public Float getBookprice() {
		return bookprice;
	}

	public void setBookprice(Float bookprice) {
		this.bookprice = bookprice;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Page<Book> getPage() {
		return page;
	}
	public void setPage(Page<Book> page) {
		this.page = page;
	}

}
