package DBManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 * 泛型 http://www.cnblogs.com/iyangyuan/archive/2013/04/09/3011274.html
 * 
 * @author Administrator
 * 
 */
public class JDBCJNDI {

	private static DataSource ds = null;
	// 在静态代码块中创建数据库连接池
	static {
		try {
			// 初始化JNDI 数据库配置在/WebRoot/META-INF/context.xml
			Context initCtx = new InitialContext();
			// 得到JNDI容器
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			// 从JNDI容器中检索name为jdbc/datasource的数据源
			ds = (DataSource) envCtx.lookup("jdbc/mysqlds");
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static Connection getConnection() throws SQLException {
		// 从数据源中获取数据库连接
		return ds.getConnection();
	}

	public static void release(Connection conn) {
		if (conn != null) {
			try {
				// 将Connection连接对象还给数据库连接池
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 执行sql语句，增删改
	 * 
	 * @param sql
	 * @param para
	 * @param retIncrement
	 * @return
	 */
	public static int update(String sql, Object[] para, Boolean retIncrement) {
		Connection conn = null;
		QueryRunner qr = new QueryRunner();
		try {
			conn = JDBCJNDI.getConnection();
			int count = qr.update(conn, sql, para);
			if (retIncrement) {
				// 得到主键
				Integer newId = (Integer) qr.query(conn,
						"select last_insert_id()",
						new ScalarHandler<Integer>(1));
				count = newId;
			}
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		} finally {
			release(conn);
		}
	}

	/**
	 * 查询列表
	 * 
	 * @param t
	 * @param sql
	 * @param para
	 * @return
	 */
	public static <T> List<T> queryList(Class<T> t, String sql, Object[] para) {
		Connection conn = null;
		QueryRunner qr = new QueryRunner();
		try {
			conn = JDBCJNDI.getConnection();
			List<T> retInfo = qr.query(conn, sql, new BeanListHandler<T>(t),
					para);
			return retInfo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		} finally {
			JDBCJNDI.release(conn);
		}
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param sql
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param para
	 * @return
	 */
	public static <T> List<T> findPage(Class<T> t, String sql,
			int iDisplayStart, int iDisplayLength) {
		Connection conn = null;
		QueryRunner qr = new QueryRunner();
		try {
			conn = JDBCJNDI.getConnection();

			StringBuilder sb = new StringBuilder();
			sb.append(sql).append(" ORDER BY id DESC ");

			// 分页
			if (iDisplayLength > 0) {
				sb.append(" limit ").append(iDisplayStart).append(",").append(iDisplayLength);
			}

			List<T> retInfo = qr.query(conn, sb.toString(),
					new BeanListHandler<T>(t));
			return retInfo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		} finally {
			JDBCJNDI.release(conn);
		}
	}

	/**
	 * 查询单个实体
	 * 
	 * @param t
	 * @param sql
	 * @param para
	 * @return
	 */
	public static <T> T queryInfo(Class<T> t, String sql, Object[] para) {
		Connection conn = null;
		QueryRunner qr = new QueryRunner();
		try {
			conn = JDBCJNDI.getConnection();
			T retInfo = qr.query(conn, sql, new BeanHandler<T>(t), para);
			return retInfo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		} finally {
			JDBCJNDI.release(conn);
		}
	}

	/**
	 * 查询总条数
	 * 
	 * @param sql
	 * @return
	 */
	public static int count(String sql) {
		Number num = 0;
		Connection conn = null;
		QueryRunner qr = new QueryRunner();
		try {
			conn = JDBCJNDI.getConnection();
			num = (Number) qr.query(conn, sql, new ScalarHandler<Object>(1));
			return (num != null) ? num.intValue() : -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return -1;
		} finally {
			JDBCJNDI.release(conn);
		}

	}

}
