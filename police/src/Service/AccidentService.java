package Service;

import Dal.AccidentDal;
import Model.AccidentInfo;
import Model.DriverInfo;
import Model.Result;
import Utils.StringHelper;
import Utils.JqTable.jqOutInfo;

public class AccidentService {
	private AccidentDal dal = new AccidentDal();

	public Result Insert(AccidentInfo info) {

		if (StringHelper.IsStrNull(info.getName())) {
			return Result.Fail("姓名不能为空");
		}
		if (StringHelper.IsStrNull(info.getTel())) {
			return Result.Fail("电话不能为空");
		}
		if (StringHelper.IsStrNull(info.getAccidentSite())) {
			return Result.Fail("地点不能为空");
		}
		if (StringHelper.IsStrNull(info.getTrafficMode())) {
			return Result.Fail("交通方式不能为空");
		}
		if (StringHelper.IsStrNull(info.getContent())) {
			return Result.Fail("内容不能为空");
		}
		info.setCreateDate(StringHelper.GetCurrentDate());
		info.setStatus(2);

		int Count = dal.Insert(info);
		if (Count == 0) {
			return Result.Fail("添加失败");
		}
		return Result.Success("添加成功");
	}
	
	public Result UpdateStatus(AccidentInfo info) {
		int ret = dal.UpdateStatus(info.getStatus(), info.getId());
		if (ret > 0) {
			return Result.Success("操作成功");
		}
		return Result.Fail("操作失败");
	}
	
	public Result Update(AccidentInfo info) {
		if (StringHelper.IsStrNull(info.getName())) {
			return Result.Fail("姓名不能为空");
		}
		if (StringHelper.IsStrNull(info.getTel())) {
			return Result.Fail("电话不能为空");
		}
		if (StringHelper.IsStrNull(info.getAccidentSite())) {
			return Result.Fail("地点不能为空");
		}
		if (StringHelper.IsStrNull(info.getTrafficMode())) {
			return Result.Fail("交通方式不能为空");
		}
		if (StringHelper.IsStrNull(info.getContent())) {
			return Result.Fail("内容不能为空");
		}
		int ret = dal.Update(info);
		if (ret > 0) {
			return Result.Success("修改成功");
		}
		return Result.Fail("修改失败");

	}

	public jqOutInfo<AccidentInfo> List(AccidentInfo info, int iDisplayStart,
			int iDisplayLength) {

		info.setName(StringHelper.StringFilter(info.getName()));

		return dal.List(info, iDisplayStart, iDisplayLength);
	}
	
	public AccidentInfo GetInfoByID(int id) {
		return dal.GetInfoByID(id);
	}
}
