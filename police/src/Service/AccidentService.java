package Service;

import Dal.AccidentDal;
import Model.AccidentInfo;
import Model.Result;
import Utils.StringHelper;

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
		info.setStatus(0);

		int Count = dal.Insert(info);
		if (Count == 0) {
			return Result.Fail("添加失败");
		}
		return Result.Success("添加成功");
	}
}
