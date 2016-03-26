package Service;

import Dal.DriverDal;
import Model.DriverInfo;
import Model.InPoliceInfo;
import Model.Result;
import Utils.StringHelper;
import Utils.JqTable.jqOutInfo;

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
		info.setCreateDate(StringHelper.GetCurrentDate());

		int Count = dal.Insert(info);
		if (Count == 0) {
			return Result.Fail("添加失败");
		}
		return Result.Success("添加成功");

	}

	public DriverInfo GetInfoByID(int id) {
		return dal.GetInfoByID(id);
	}

	public Result Delete(int id) {
		if (id == 0) {
			return Result.Fail("删除失败");
		}
		int ret = dal.Delete(id);
		if (ret > 0) {
			return Result.Success("删除成功");
		}
		return Result.Fail("删除失败");
	}

	public Result Update(DriverInfo info) {
		if (StringHelper.IsStrNull(info.getName())) {
			return Result.Fail("姓名不能为空");
		}
		if (StringHelper.IsStrNull(info.getLicense())) {
			return Result.Fail("驾驶证不能为空");
		}
		if (StringHelper.IsStrNull(info.getLicenseExpire())) {
			return Result.Fail("驾驶证到期时间不能为空");
		}
		int ret = dal.Update(info);
		if (ret > 0) {
			return Result.Success("修改成功");
		}
		return Result.Fail("修改失败");

	}
	
	public jqOutInfo<DriverInfo> List(DriverInfo info, int iDisplayStart,
			int iDisplayLength) {

		info.setName(StringHelper.StringFilter(info.getName()));

		return dal.List(info, iDisplayStart, iDisplayLength);
	}
}
