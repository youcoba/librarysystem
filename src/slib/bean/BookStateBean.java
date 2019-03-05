package slib.bean;

import java.io.Serializable;
import java.sql.Date;

public class BookStateBean implements Serializable {

	private int bookStateId;
	private String bookISBN;
	private String bookInfoName;
	private String stockDay;
	private String disposalDay;
	private String stateComment;

	public BookStateBean() {
	}

	
	
	public BookStateBean(int bookStateId, String bookISBN, String stockDay, String disposalDay, String stateComment) {
		this.bookStateId = bookStateId;
		this.bookISBN = bookISBN;
		this.stockDay = stockDay;
		this.disposalDay = disposalDay;
		this.stateComment = stateComment;
	}
	
	public BookStateBean(int bookStateId, String bookISBN, String bookInfoName, String stockDay, String disposalDay,
			String stateComment) {
		this.bookStateId = bookStateId;
		this.bookISBN = bookISBN;
		this.bookInfoName = bookInfoName;
		this.stockDay = stockDay;
		this.disposalDay = disposalDay;
		this.stateComment = stateComment;
	}

	public int getBookStateId() {
		return bookStateId;
	}

	public void setBookStateId(int bookStateId) {
		this.bookStateId = bookStateId;
	}

	public String getBookISBN() {
		return bookISBN;
	}

	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
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

	public String getStateComment() {
		return stateComment;
	}

	public void setStateComment(String stateComment) {
		this.stateComment = stateComment;
	}

	public String getBookInfoName() {
		return bookInfoName;
	}

	public void setBookInfoName(String bookInfoName) {
		this.bookInfoName = bookInfoName;
	}

}
