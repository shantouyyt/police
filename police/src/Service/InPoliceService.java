package Service;

import Dal.InPoliceDal;
import Model.InPoliceInfo;
import Model.Result;
import Utils.StringHelper;
import Utils.JqTable.jqOutInfo;

public class InPoliceService {
	private InPoliceDal dal = new InPoliceDal();

	public Result Insert(InPoliceInfo info) {

		if (StringHelper.IsStrNull(info.getName())) {
			return Result.Fail("报警人不能为空");
		}
		if (StringHelper.IsStrNull(info.getTel())) {
			return Result.Fail("报警人电话");
		}
		info.setCreateDate(StringHelper.GetCurrentDate());

		int Count = dal.Insert(info);
		if (Count == 0) {
			return Result.Fail("添加失败");
		}
		return Result.Success("添加成功");
	}

	public jqOutInfo<InPoliceInfo> List(InPoliceInfo info, int iDisplayStart,
			int iDisplayLength) {

		info.setName(StringHelper.StringFilter(info.getName()));

		return dal.List(info, iDisplayStart, iDisplayLength);
	}
	public InPoliceInfo GetInfoByID(int id){
		return dal.GetInfoByID(id);
	}
	public Result Update(InPoliceInfo info){
		if (StringHelper.IsStrNull(info.getName())) {
			return Result.Fail("报警人不能为空");
		}
		if (StringHelper.IsStrNull(info.getTel())) {
			return Result.Fail("报警人电话");
		}
		if(info.getId()==0){
			return Result.Fail("修改失败");
		}
		int ret = dal.Update(info);
		if(ret > 0){
			return Result.Success("修改成功");
		}
		return Result.Fail("修改失败");
		
	}
	public Result Delete(int id){
		if(id==0){
			return Result.Fail("删除失败");
		}
		int ret = dal.Delete(id);
		if(ret > 0){
			return Result.Success("删除成功");
		}
		return Result.Fail("删除失败");
	}
}
