package Utils.JqTable;

import java.util.List;

import Utils.StringHelper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class jqInfoManager {
	private List<jqInfo> list;

	public List<jqInfo> getList() {
		return list;
	}

	public void setList(List<jqInfo> list) {
		this.list = list;
	}

	public jqInfoManager(String json) {
		list = new Gson().fromJson(json, new TypeToken<List<jqInfo>>() {
		}.getType());
	}

	public String getvalue(String name) {
		if (StringHelper.IsStrNull(name))
			return "";
		if (list == null || list.size() == 0)
			return "";

		for (jqInfo info : list) {
			if (name.equals(info.name)) {
				return info.value;
			}
		}
		return "";
	}
}
