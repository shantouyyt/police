package Utils;

import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import Utils.JqTable.jqInfoManager;
import Utils.JqTable.jqProcessInfo;

import com.sun.org.apache.commons.beanutils.BeanUtils;

public class WebUtils {
	public static <T> T request2Bean(HttpServletRequest request, Class<T> clazz) {
		try {
			T bean = clazz.newInstance();
			Enumeration<String> e = request.getParameterNames();
			while (e.hasMoreElements()) {
				String name = (String) e.nextElement();
				String value = request.getParameter(name);
				BeanUtils.setProperty(bean, name, value);
			}
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String makeId() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 得到表格的页数、页码
	 * @param request
	 * @return
	 */
	public static jqProcessInfo GetJqProcessInfo(HttpServletRequest request) {
		String aoData = request.getParameter("aoData");
		jqInfoManager jm = new jqInfoManager(aoData);

		String sEcho = "0";
		String iDisplayStart = "0";
		String iDisplayLength = "10";
		sEcho = jm.getvalue("sEcho");
		iDisplayStart = jm.getvalue("iDisplayStart");
		iDisplayLength = jm.getvalue("iDisplayLength");

		jqProcessInfo jpi = new jqProcessInfo();
		jpi.setsEcho(sEcho);
		jpi.setiDisplayStart(StringHelper.Str2Int(iDisplayStart));
		jpi.setiDisplayLength(StringHelper.Str2Int(iDisplayLength));
		
		return jpi;
	}
}
