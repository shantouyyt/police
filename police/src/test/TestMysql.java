package test;

import Utils.BackupMysql;

public class TestMysql {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("开始备份...");
		BackupMysql.backup1();
		System.out.println("备份成功...");
	}

}
