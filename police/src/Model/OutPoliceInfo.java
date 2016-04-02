package Model;

import com.google.gson.Gson;

public class OutPoliceInfo {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInPoliceID() {
		return inPoliceID;
	}
	public void setInPoliceID(int inPoliceID) {
		this.inPoliceID = inPoliceID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	private int id;
	private int inPoliceID;
	private int userID;
	private String createDate;
	private String endDate;
	private String userName;
	private String no;
	
	public String toString(){
		return new Gson().toJson(this);
	}
}
