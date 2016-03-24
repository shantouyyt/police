package Model;

import java.util.Date;

import com.google.gson.Gson;
/**
 * 属性第一个字母要小写
 */
public class UsersInfo {

	private int id;
	private String userName;
	private String passWord;
	private int sex;
	private String no;
	private int userType;
	private String createDate;
	private String endDate;
	private String usertypes;
	public String getUsertypes() {
		return usertypes;
	}
	public void setUsertypes(String usertypes) {
		this.usertypes = usertypes;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public String toString(){
		return new Gson().toJson(this);
	}
	
	
}
