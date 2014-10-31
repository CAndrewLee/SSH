package com.somnus.bean;

public class Book {
	private int id;   
    private String bookname;   
    private String bookauthor;   
    private Float bookprice;   
 
    public Book() {   
    }   
 
    public Book(String bookname, String bookauthor, Float bookprice) {   
        this.bookname = bookname;   
        this.bookauthor = bookauthor;   
        this.bookprice = bookprice;   
    }


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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


}
