package Dal;

import java.util.List;

import DBManager.JDBCJNDI;
import Model.HighchartsDBInfo;

public class HighchartsDal {
	
	public List<HighchartsDBInfo> queryListTrafficMode() {
		String sql = " select date_format(CreateDate,'%m') m ,TrafficMode,count(*) num ";
		sql += " from accident ";
		sql += " GROUP BY date_format(CreateDate,'%m'),TrafficMode ";
		return JDBCJNDI.queryList(HighchartsDBInfo.class, sql, null);
	}
}
