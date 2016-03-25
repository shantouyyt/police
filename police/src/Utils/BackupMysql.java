package Utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import DBManager.BackupConfig;
import Model.Result;

public class BackupMysql {

	public static void backup1() {
		try {
			Runtime rt = Runtime.getRuntime();
			// 调用 调用mysql的安装目录的命令

			Process child = rt
					.exec("E://mysql//mysql-5.6.22-winx64//bin//mysqldump -h localhost -uroot -p123456  police");

			// 设置导出编码为utf-8。这里必须是utf-8
			// 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
			InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
			InputStreamReader xx = new InputStreamReader(in, "utf-8");
			// 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			// 组合控制台输出信息字符串
			BufferedReader br = new BufferedReader(xx);
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();
			// 要用来做导入用的sql目标文件：
			FileOutputStream fout = new FileOutputStream("c:/test.sql");
			OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
			writer.write(outStr);
			writer.flush();
			in.close();
			xx.close();
			br.close();
			writer.close();
			fout.close();
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Result backup() {
		try {
			Runtime rt = Runtime.getRuntime();
			// 调用 调用mysql的安装目录的命令
			String rtstr = BackupConfig.MYSQL_BASE_BIN + "mysqldump -h "
					+ BackupConfig.IPADDRESS + " -u" + BackupConfig.USERNAME
					+ " -p" + BackupConfig.PASSWORD + "  "
					+ BackupConfig.BACKUPDB + "";
			
			Process child = rt.exec(rtstr);

			// 设置导出编码为utf-8。这里必须是utf-8
			// 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
			InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
			InputStreamReader xx = new InputStreamReader(in, "utf-8");
			// 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			// 组合控制台输出信息字符串
			BufferedReader br = new BufferedReader(xx);
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();
			// 要用来做导入用的sql目标文件：
			FileOutputStream fout = new FileOutputStream(BackupConfig.BACKFILE);
			OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
			writer.write(outStr);
			writer.flush();
			in.close();
			xx.close();
			br.close();
			writer.close();
			fout.close();
			return Result.Success("备份成功");
		} catch (Exception e) {
			e.printStackTrace();
			return Result.Fail("备份失败");
		}
	}

	public static Result load() {
		try {
			String fPath = BackupConfig.BACKFILE;
			Runtime rt = Runtime.getRuntime();

			// 调用 mysql 的 cmd:
			Process child = rt.exec(BackupConfig.MYSQL_BASE_BIN
					+ "mysql.exe -h " + BackupConfig.IPADDRESS + " -u"
					+ BackupConfig.USERNAME + " -p" + BackupConfig.PASSWORD
					+ "  " + BackupConfig.BACKUPDB + " ");
			OutputStream out = child.getOutputStream();// 控制台的输入信息作为输出流
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(fPath), "utf8"));
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();

			OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
			writer.write(outStr);
			// 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
			writer.flush();
			// 别忘记关闭输入输出流
			out.close();
			br.close();
			writer.close();

			return Result.Success("还原成功");

		} catch (Exception e) {
			e.printStackTrace();
			return Result.Success("还原失败");
		}

	}
}
