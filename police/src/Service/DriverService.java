package Service;

import Dal.DriverDal;
import Model.DriverInfo;
import Model.Result;
import Utils.StringHelper;

public class DriverService {
	private DriverDal dal = new DriverDal();

	public Result Insert(DriverInfo info) {
		if (StringHelper.IsStrNull(info.getName())) {
			return Result.Fail("姓名不能为空");
		}
		if (StringHelper.IsStrNull(info.getLicense())) {
			return Result.Fail("驾驶证不能为空");
		}
		if (StringHelper.IsStrNull(info.getLicenseExpire())) {
			return Result.Fail("驾驶证到期时间不能为空");
		}
		int Count = dal.Insert(info);
		if (Count == 0) {
			return Result.Fail("添加失败");
		}
		return Result.Success("添加成功");

	}
}
