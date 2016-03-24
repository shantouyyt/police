package Service;

import java.util.Date;

import Dal.UsersDal;
import Model.InPoliceInfo;
import Model.Result;
import Model.UsersInfo;
import Utils.StringHelper;
import Utils.JqTable.jqOutInfo;

public class UsersService {
	private UsersDal dal = new UsersDal();

	public Result Login(UsersInfo info) {

		if (StringHelper.IsStrNull(info.getUserName())) {
			return Result.Fail("用户名不能为空");
		}
		if (StringHelper.IsStrNull(info.getPassWord())) {
			return Result.Fail("密码不能为空");
		}
		UsersInfo retInfo = dal.Login(info);
		if (retInfo == null) {
			return Result.Fail("用户名或者密码错误");
		}

		Result ret = Result.Success("登录成功");
		ret.obj = retInfo;
		return ret;
	}

	public Result Register(UsersInfo info) {

		if (StringHelper.IsStrNull(info.getUserName())) {
			return Result.Fail("用户名不能为空");
		}
		if (StringHelper.IsStrNull(info.getPassWord())) {
			return Result.Fail("密码不能为空");
		}
		info.setCreateDate(StringHelper.GetCurrentDate());

		// 判断用户名是否被注册过
		UsersInfo retInfo = dal.GetUserInfoByUserName(info);
		if (retInfo != null) {
			return Result.Fail("用户名已经被注册过");
		}

		int Count = dal.Insert(info);
		if (Count == 0) {
			return Result.Fail("注册失败");
		}

		return Result.Success("注册成功");
	}

	public jqOutInfo<UsersInfo> List(UsersInfo info, int iDisplayStart,
			int iDisplayLength) {

		info.setUserName(StringHelper.StringFilter(info.getUserName()));

		return dal.List(info, iDisplayStart, iDisplayLength);
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

	public UsersInfo GetInfoByID(int id) {
		return dal.GetInfoByID(id);
	}

	public Result Update(UsersInfo info) {
		// if (StringHelper.IsStrNull(info.getUserName())) {
		// return Result.Fail("用户不能为空");
		// }
		if (StringHelper.IsStrNull(info.getPassWord())) {
			return Result.Fail("密码不能为空");
		}
		if (info.getId() == 0) {
			return Result.Fail("修改失败");
		}
		int ret = dal.Update(info);
		if (ret > 0) {
			return Result.Success("修改成功");
		}
		return Result.Fail("修改失败");

	}
}
