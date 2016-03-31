package Model;

import com.google.gson.Gson;

public class InPoliceInfo {
	private String name;
	private int sex;
	private String createDate;
	private String endDate;
	
	
	public String getsGDD() {
		return sGDD;
	}
	public void setsGDD(String sGDD) {
		this.sGDD = sGDD;
	}
	public String getsWQK() {
		return sWQK;
	}
	public void setsWQK(String sWQK) {
		this.sWQK = sWQK;
	}
	private String sGDD;
	private String sWQK;
	
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	private String remark;
	private String tel;
	
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String toString(){
		return new Gson().toJson(this);
	}

}
