package Model;

import com.google.gson.Gson;

public class AccidentApprovalInfo {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccidentNo() {
		return accidentNo;
	}
	public void setAccidentNo(String accidentNo) {
		this.accidentNo = accidentNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLeaderRemark() {
		return leaderRemark;
	}
	public void setLeaderRemark(String leaderRemark) {
		this.leaderRemark = leaderRemark;
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
	private int id;
	private String accidentNo;
	private String remark;
	private int status;
	private String leaderRemark;
	private String createDate;
	private String endDate;
	
	public String toString(){
		return new Gson().toJson(this);
	}
}
