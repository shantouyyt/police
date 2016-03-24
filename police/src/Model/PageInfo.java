package Model;

import java.util.List;

public class PageInfo<T> {
	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	private long count;
	private List<T> list;

	public PageInfo(long count, List<T> list) {
		
	}
}
