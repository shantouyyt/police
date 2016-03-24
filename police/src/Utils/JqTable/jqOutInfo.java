package Utils.JqTable;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class jqOutInfo<T> {
	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public List<T> getAaData() {
		return aaData;
	}

	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}

	public String sEcho;
	public int iTotalRecords;
	public int iTotalDisplayRecords;
	public List<T> aaData;

	public jqOutInfo(String sEcho, int iTotalRecords, int iTotalDisplayRecords,
			List<T> aaData) {
		this.sEcho = sEcho;
		this.iTotalRecords = iTotalRecords;
		this.iTotalDisplayRecords = iTotalDisplayRecords;
		this.aaData = aaData;
	}

	public String toString() {
		return new Gson().toJson(this);
	}

}
