package Dal;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import DBManager.JDBCJNDI;
import Model.InPoliceInfo;
import Model.UsersInfo;
import Utils.StringHelper;
import Utils.JqTable.jqOutInfo;

public class UsersDal {
	/**
	 * 用户登录
	 * 
	 * @param info
	 * @return 用户实体
	 */
	public UsersInfo Login(UsersInfo info) {

		String sql = "select * from Users where UserName=? and PassWord=? and UserType=?";
		Object[] para = new Object[] { info.getUserName(), info.getPassWord(),
				info.getUserType() };
		return JDBCJNDI.queryInfo(UsersInfo.class, sql, para);
	}

	public UsersInfo GetUserInfoByUserName(UsersInfo info) {

		String sql = "select * from Users where UserName=? ";
		Object[] para = new Object[] { info.getUserName() };
		return JDBCJNDI.queryInfo(UsersInfo.class, sql, para);
	}

	public int Insert(UsersInfo info) {
		Connection conn = null;
		QueryRunner qr = new QueryRunner();
		String sql = "insert into Users(UserName,PassWord,Sex,UserType,CreateDate) values(?,?,?,?,?)";
		try {
			conn = JDBCJNDI.getConnection();
			int count = qr.update(conn, sql, info.getUserName(),
					info.getPassWord(), info.getSex(), info.getUserType(),
					info.getCreateDate());
			// 得到主键
			BigInteger newId = (BigInteger) qr
					.query(conn, "select last_insert_id()",
							new ScalarHandler<BigInteger>(1));
			info.setId(Integer.valueOf(String.valueOf(newId)));

			// 更新警员编号
			String NO = info.getUserType()
					+ String.format("%06d", info.getId());
			sql = "update Users set No=? where id=?";
			qr.update(conn, sql, new Object[] { NO, info.getId() });

			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		} finally {
			JDBCJNDI.release(conn);
		}
	}

	public jqOutInfo<UsersInfo> List(UsersInfo info, int iDisplayStart,
			int iDisplayLength) {

		String sql = "select * from users where 1=1 ";

		StringBuilder sb = new StringBuilder();
		if (!StringHelper.IsStrNull(info.getUserName())) {
			sb.append(" and UserName like '%").append(info.getUserName())
					.append("%' ");
		}
		if (!StringHelper.IsStrNull(info.getCreateDate())) {
			sb.append(" and CreateDate >='").append(info.getCreateDate())
					.append("' ");
		}
		if (!StringHelper.IsStrNull(info.getEndDate())) {
			sb.append(" and CreateDate <='").append(info.getEndDate())
					.append("' ");
		}
		if (!StringHelper.IsStrNull(info.getUsertypes())) {
			sb.append(" and usertype in(").append(info.getUsertypes())
					.append(") ");
		}

		String countsql = "select count(1) from users where 1=1 ";
		int count = JDBCJNDI.count(countsql + sb.toString());

		List<UsersInfo> list = JDBCJNDI.findPage(UsersInfo.class,
				sql + sb.toString(), iDisplayStart, iDisplayLength);

		jqOutInfo<UsersInfo> joi = new jqOutInfo<UsersInfo>("", count, count,
				list);
		return joi;
	}

	public int Delete(int id) {
		String sql = "delete from  Users where id=" + id;
		return JDBCJNDI.update(sql, null, false);
	}
	public UsersInfo GetInfoByID(int id) {
		String sql = "select * from Users where id=" + id;
		return JDBCJNDI.queryInfo(UsersInfo.class, sql, null);
	}
	public int Update(UsersInfo info) {
		String sql = "update Users set Sex=?,PassWord=? where id=?";
		Object[] para = new Object[] {  info.getSex(),
				info.getPassWord(),  info.getId() };
		return JDBCJNDI.update(sql, para, false);
	}
	
	public List<UsersInfo> queryList(){
		String sql = "select * from users";
		return JDBCJNDI.queryList(UsersInfo.class, sql, null);
	}
	
	
	
}
