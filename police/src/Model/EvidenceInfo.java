package Model;

import com.google.gson.Gson;

public class EvidenceInfo {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccidentNO() {
		return accidentNO;
	}
	public void setAccidentNO(String accidentNO) {
		this.accidentNO = accidentNO;
	}
	public String getsGDD() {
		return sGDD;
	}
	public void setsGDD(String sGDD) {
		this.sGDD = sGDD;
	}
	public String gettQ() {
		return tQ;
	}
	public void settQ(String tQ) {
		this.tQ = tQ;
	}
	public String getqSRS() {
		return qSRS;
	}
	public void setqSRS(String qSRS) {
		this.qSRS = qSRS;
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
	public String getsZRS() {
		return sZRS;
	}
	public void setsZRS(String sZRS) {
		this.sZRS = sZRS;
	}
	public String getzJJJSS() {
		return zJJJSS;
	}
	public void setzJJJSS(String zJJJSS) {
		this.zJJJSS = zJJJSS;
	}
	public String getyJ1() {
		return yJ1;
	}
	public void setyJ1(String yJ1) {
		this.yJ1 = yJ1;
	}
	public String getyJ2() {
		return yJ2;
	}
	public void setyJ2(String yJ2) {
		this.yJ2 = yJ2;
	}
	public String getsWRS() {
		return sWRS;
	}
	public void setsWRS(String sWRS) {
		this.sWRS = sWRS;
	}
	private int id;
	private String accidentNO;
	private String sGDD;
	private String tQ;
	private String qSRS;
	private String createDate;
	private String endDate;
	private String sZRS;
	private String zJJJSS;
	private String yJ1;
	private String yJ2;
	private String sWRS;
	public String getdLKD() {
		return dLKD;
	}
	public void setdLKD(String dLKD) {
		this.dLKD = dLKD;
	}
	public String getzSRS() {
		return zSRS;
	}
	public void setzSRS(String zSRS) {
		this.zSRS = zSRS;
	}
	public String getjTFS() {
		return jTFS;
	}
	public void setjTFS(String jTFS) {
		this.jTFS = jTFS;
	}
	public String getbZ() {
		return bZ;
	}
	public void setbZ(String bZ) {
		this.bZ = bZ;
	}
	private String dLKD;
	private String zSRS;
	private String jTFS;
	private String bZ;
	
	public String toString(){
		return new Gson().toJson(this);
	}
	
}
