package Dal;

import java.util.List;

import DBManager.JDBCJNDI;
import Model.HighchartsDBInfo;

public class HighchartsDal {
	
	public List<HighchartsDBInfo> queryListTrafficMode() {
		String sql = " select 	TrafficMode, ";
		sql += " sum(case  month(CreateDate) when  1 then 1 else 0 end) as Jan, ";
		sql += " sum(case  month(CreateDate) when  2 then 1 else 0 end) as Feb, ";
		sql += " sum(case  month(CreateDate) when  3 then 1 else 0 end) as Mar, ";
		sql += " sum(case  month(CreateDate) when  4 then 1 else 0 end) as Apr, ";
		sql += " sum(case  month(CreateDate) when  5 then 1 else 0 end) as May, ";
		sql += " sum(case  month(CreateDate) when  6 then 1 else 0 end) as Jun, ";
		sql += " sum(case  month(CreateDate) when  7 then 1 else 0 end) as Jul, ";
		sql += " sum(case  month(CreateDate) when  8 then 1 else 0 end) as Aug, ";
		sql += " sum(case  month(CreateDate) when  9 then 1 else 0 end) as Sep, ";
		sql += " sum(case  month(CreateDate) when  10 then 1 else 0 end) as Oct, ";
		sql += " sum(case  month(CreateDate) when  11 then 1 else 0 end) as Nov, ";
		sql += " sum(case  month(CreateDate) when  12 then 1 else 0 end) as Decb ";
		sql += "  from accident group by TrafficMode";
		return JDBCJNDI.queryList(HighchartsDBInfo.class, sql, null);
	}
	
	public List<HighchartsDBInfo> queryListAccidentSite() {
		String sql = " select 	AccidentSite as TrafficMode, ";
		sql += " sum(case  month(CreateDate) when  1 then 1 else 0 end) as Jan, ";
		sql += " sum(case  month(CreateDate) when  2 then 1 else 0 end) as Feb, ";
		sql += " sum(case  month(CreateDate) when  3 then 1 else 0 end) as Mar, ";
		sql += " sum(case  month(CreateDate) when  4 then 1 else 0 end) as Apr, ";
		sql += " sum(case  month(CreateDate) when  5 then 1 else 0 end) as May, ";
		sql += " sum(case  month(CreateDate) when  6 then 1 else 0 end) as Jun, ";
		sql += " sum(case  month(CreateDate) when  7 then 1 else 0 end) as Jul, ";
		sql += " sum(case  month(CreateDate) when  8 then 1 else 0 end) as Aug, ";
		sql += " sum(case  month(CreateDate) when  9 then 1 else 0 end) as Sep, ";
		sql += " sum(case  month(CreateDate) when  10 then 1 else 0 end) as Oct, ";
		sql += " sum(case  month(CreateDate) when  11 then 1 else 0 end) as Nov, ";
		sql += " sum(case  month(CreateDate) when  12 then 1 else 0 end) as Decb ";
		sql += "  from accident group by AccidentSite";
		return JDBCJNDI.queryList(HighchartsDBInfo.class, sql, null);
	}
}
