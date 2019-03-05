package slib.bean;

import java.io.Serializable;

public class ReturnBean implements Serializable {

	private int userId;
	private int rentalId;
	private int bookStateId;
	private String rentalRent;
	private String rentalReturn;
	private String rentalDeadline;
	private String comment;

	public ReturnBean(int userId, int rentalId, int bookStateId, String rentalRent, String rentalReturn,
			String rentalDeadline, String comment) {
		this.userId = userId;
		this.rentalId = rentalId;
		this.bookStateId = bookStateId;
		this.rentalRent = rentalRent;
		this.rentalReturn = rentalReturn;
		this.rentalDeadline = rentalDeadline;
		this.comment = comment;
	}

	public String getRentalRent() {
		return rentalRent;
	}

	public void setRentalRent(String rentalRent) {
		this.rentalRent = rentalRent;
	}

	public String getRentalReturn() {
		return rentalReturn;
	}

	public void setRentalReturn(String rentalReturn) {
		this.rentalReturn = rentalReturn;
	}

	public String getRentalDeadline() {
		return rentalDeadline;
	}

	public void setRentalDeadline(String rentalDeadline) {
		this.rentalDeadline = rentalDeadline;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ReturnBean() {
	}

	public int getRentalId() {
		return rentalId;
	}

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	public int getBookStateId() {
		return bookStateId;
	}

	public void setBookStateId(int bookStateId) {
		this.bookStateId = bookStateId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
