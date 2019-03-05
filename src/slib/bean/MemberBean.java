package slib.bean;

import java.io.Serializable;

public class MemberBean implements Serializable {

	private int id;
	private String familyName;
	private String lastName;
	private String postal;
	private String address;
	private String tel;
	private String email;
	private String birthday;
	private String joinday;
	private String unsubscribeday;

	public MemberBean() {
	}

	public MemberBean(int id, String familyName, String lastName, String postal, String address, String tel,
			String email, String birthday, String joinday, String unsubscribeday) {
		this.id = id;
		this.familyName = familyName;
		this.lastName = lastName;
		this.postal = postal;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.birthday = birthday;
		this.joinday = joinday;
		this.unsubscribeday = unsubscribeday;
	}
	
	public MemberBean(int id, String familyName, String lastName, String postal, String address, String tel,
			String email, String birthday, String joinday) {
		this.id = id;
		this.familyName = familyName;
		this.lastName = lastName;
		this.postal = postal;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.birthday = birthday;
		this.joinday = joinday;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getJoinday() {
		return joinday;
	}

	public void setJoinday(String joinday) {
		this.joinday = joinday;
	}
	
	public String getUnsubscribeday() {
		return unsubscribeday;
	}
	
	public void setUnsubscribeday(String unsubscribeday) {
		this.unsubscribeday = unsubscribeday;
	}

}
