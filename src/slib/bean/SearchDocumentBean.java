package slib.bean;

import java.io.Serializable;

public class SearchDocumentBean implements Serializable {

	private int bookStateId;
	private String bookInfoIsbn;
	private int categoryCode;
	private String categoryName;
	private String bookInfoName;
	private String bookInfoAuthor;
	private String publisher;
	private String publishDay;
	private String stockDay;
	private String disposalDay;
	private String comment;

	public SearchDocumentBean() {
	}

	public SearchDocumentBean(int bookStateId, String bookInfoIsbn, int categoryCode, String categoryName,
			String bookInfoName, String bookInfoAuthor, String publisher, String publishDay, String stockDay,
			String disposalDay, String comment) {
		this.bookStateId = bookStateId;
		this.bookInfoIsbn = bookInfoIsbn;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.bookInfoName = bookInfoName;
		this.bookInfoAuthor = bookInfoAuthor;
		this.publisher = publisher;
		this.publishDay = publishDay;
		this.stockDay = stockDay;
		this.disposalDay = disposalDay;
		this.comment = comment;
	}

	public int getBookStateId() {
		return bookStateId;
	}

	public void setBookStateId(int bookStateId) {
		this.bookStateId = bookStateId;
	}

	public String getBookInfoIsbn() {
		return bookInfoIsbn;
	}

	public void setBookInfoIsbn(String bookInfoIsbn) {
		this.bookInfoIsbn = bookInfoIsbn;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublishDay() {
		return publishDay;
	}

	public void setPublishDay(String publishDay) {
		this.publishDay = publishDay;
	}

	public String getStockDay() {
		return stockDay;
	}

	public void setStockDay(String stockDay) {
		this.stockDay = stockDay;
	}

	public String getDisposalDay() {
		return disposalDay;
	}

	public void setDisposalDay(String disposalDay) {
		this.disposalDay = disposalDay;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	

}