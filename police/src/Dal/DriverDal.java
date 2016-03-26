package Dal;

import DBManager.JDBCJNDI;
import Model.DriverInfo;

public class DriverDal {
	public int Insert(DriverInfo info) {
		String sql = "insert into Driver(Name,Sex,Tel,License,LicenseExpire,Address,CreateDate,Remark) values(?,?,?,?,?,?,?,?)";
		Object[] para = new Object[] { info.getName(), info.getSex(),
				info.getTel(), info.getLicense(), info.getLicenseExpire(),
				info.getAddress(), info.getCreateDate(), info.getRemark() };
		return JDBCJNDI.update(sql, para, false);
	}
}
