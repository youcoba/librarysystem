package slib.bean;

import java.io.Serializable;
import java.sql.Date;

public class RentBean implements Serializable {
	private int userId;
	private int bookStateId;
	private int rentalId;
	private String rentalDate;
	private String returnDate;
	private String returnDeadLine;
	private String comment;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBookStateId() {
		return bookStateId;
	}
	public void setBookStateId(int bookStateId) {
		this.bookStateId = bookStateId;
	}
	public int getRentalId() {
		return rentalId;
	}
	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}
	public String getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getReturnDeadLine() {
		return returnDeadLine;
	}
	public void setReturnDeadLine(String returnDeadLine) {
		this.returnDeadLine = returnDeadLine;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public RentBean(int userId,int bookStateId,int rentalId,String rentalDate,String returnDate,String returnDeadLine,
			String comment) {
		this.userId = userId;
		this.bookStateId = bookStateId;
		this.rentalId = rentalId;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
		this.returnDeadLine = returnDeadLine;
		this.comment = comment;
	}
	
	public RentBean() {
	}
	
	
	
}
