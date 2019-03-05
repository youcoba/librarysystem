package slib.bean;

import java.io.Serializable;
import java.sql.Date;

public class BookInfoBean implements Serializable {

	private String bookISBN;
	private int categoryCode;
	private String bookInfoName;
	private String bookInfoAuthor;
	private String publishDay;
	private String publisher;
	private String infoComment;

	public BookInfoBean() {
	}

	public BookInfoBean(String bookISBN, int categoryCode, String bookInfoName, String bookInfoAuthor,
			String publishDay, String publisher, String infoComment) {
		this.bookISBN = bookISBN;
		this.categoryCode = categoryCode;
		this.bookInfoName = bookInfoName;
		this.bookInfoAuthor = bookInfoAuthor;
		this.publishDay = publishDay;
		this.publisher = publisher;
		this.infoComment = infoComment;
	}

	public BookInfoBean(String bookISBN, int categoryCode, String bookInfoName, String bookInfoAuthor,
			String publishDay, String publisher) {
		this.bookISBN = bookISBN;
		this.categoryCode = categoryCode;
		this.bookInfoName = bookInfoName;
		this.bookInfoAuthor = bookInfoAuthor;
		this.publishDay = publishDay;
		this.publisher = publisher;
	}

	public String getBookISBN() {
		return bookISBN;
	}

	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getBookInfoName() {
		return bookInfoName;
	}

	public void setBookInfoName(String bookInfoName) {
		this.bookInfoName = bookInfoName;
	}

	public String getBookInfoAuthor() {
		return bookInfoAuthor;
	}

	public void setBookInfoAuthor(String bookInfoAuthor) {
		this.bookInfoAuthor = bookInfoAuthor;
	}

	public String getPublishDay() {
		return publishDay;
	}

	public void setPublishDay(String publishDay) {
		this.publishDay = publishDay;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getInfoComment() {
		return infoComment;
	}

	public void setInfoComment(String infoComment) {
		this.infoComment = infoComment;
	}

}
