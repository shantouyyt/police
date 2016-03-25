package DBManager;

import java.io.IOException;
import java.util.Properties;

public class BackupConfig {
	private static Properties prop = new Properties();
	static{        
        try {
            //加载backupConfig.properties配置文件
            prop.load(BackupConfig.class.getResourceAsStream("backupConfig.properties"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	//设置常量
    public static final String MYSQL_BASE_BIN = prop.getProperty("MYSQL_BASE_BIN");
    public static final String IPADDRESS = prop.getProperty("IPADDRESS");
    public static final String BACKUPDB = prop.getProperty("BACKUPDB");
    public static final String USERNAME = prop.getProperty("USERNAME");
    public static final String PASSWORD = prop.getProperty("PASSWORD");
    public static final String BACKFILE = prop.getProperty("BACKFILE");
}
