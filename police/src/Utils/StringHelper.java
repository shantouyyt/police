package Utils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {
	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean IsStrNull(String str) {
		return str == null || str.trim().length() <= 0;
	}

	/**
	 * 字符串转为int
	 * 
	 * @param str
	 * @return
	 */
	public static int Str2Int(String str) {
		if (IsStrNull(str)) {
			return 0;
		}
		return Integer.parseInt(str);
	}

	public static String StringFilter(String str) {
		if (IsStrNull(str)) {
			return "";
		}
		str = str.replaceAll("'", "")
				.replaceAll("%", "");
		return str.trim();
	}

	/**
	 * 得到当前时间
	 * 
	 * @param Format
	 * @return
	 */
	public static String GetCurrentDate(String Format) {
		return (new java.text.SimpleDateFormat(Format)).format(new Date());
	}

	/**
	 * 得到当前时间
	 * 
	 * @return
	 */
	public static String GetCurrentDate() {
		return (new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss"))
				.format(new Date());
	}
}
