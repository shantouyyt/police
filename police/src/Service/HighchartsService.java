package Service;

import java.util.ArrayList;
import java.util.List;

import Dal.HighchartsDal;
import Model.HighchartsDBInfo;
import Model.HighchartsInfo;

public class HighchartsService {
	private HighchartsDal dal = new HighchartsDal();

	public List<HighchartsInfo> queryListTrafficMode() {
		List<HighchartsDBInfo> list = dal.queryListTrafficMode();

		List<HighchartsInfo> ret = new ArrayList<HighchartsInfo>();

		for (HighchartsDBInfo info : list) {
			HighchartsInfo bean = new HighchartsInfo();
			bean.setName(info.getTrafficMode());
			String data = info.getJan() + "," + info.getFeb() + ","
					+ info.getMar() + "," + info.getApr() + "," + info.getMay()
					+ "," + info.getJun() + "," + info.getJul() + ","
					+ info.getAug() + "," + info.getSep() + "," + info.getOct()
					+ "," + info.getNov() + "," + info.getDecb();
			bean.setData(data);
			ret.add(bean);
		}

		return ret;
	}
}
