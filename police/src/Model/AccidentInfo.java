package Model;

import com.google.gson.Gson;

public class AccidentInfo {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTrafficMode() {
		return trafficMode;
	}
	public void setTrafficMode(String trafficMode) {
		this.trafficMode = trafficMode;
	}
	public String getAccidentSite() {
		return accidentSite;
	}
	public void setAccidentSite(String accidentSite) {
		this.accidentSite = accidentSite;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	private String trafficMode;
	private String accidentSite;
	private String createDate;
	private String endDate;
	private String content;
	private int status;
	private String name;
	private String tel;
	private String sex;
	private int userID;
	private int approvalstatus;
	private int approvalID;
	
	public int getApprovalID() {
		return approvalID;
	}
	public void setApprovalID(int approvalID) {
		this.approvalID = approvalID;
	}
	public int getApprovalstatus() {
		return approvalstatus;
	}
	public void setApprovalstatus(int approvalstatus) {
		this.approvalstatus = approvalstatus;
	}
	public String toString(){
		return new Gson().toJson(this);
	}

	
}
