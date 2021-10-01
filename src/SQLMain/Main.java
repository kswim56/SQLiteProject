package SQLMain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		Connection con = null;
		
		try {
			// SQLite JDBC���� üũ�Ѵ�
			Class.forName("org.sqlite.JDBC");
			
			// SQLite �����ͺ��̽� ���Ͽ� ����
			String dbFile = "myfirst.db";
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			
			// ������ ��ȸ
			readData(con);
			
			// ������ �߰�
			 addData(con);
			
			// ������ ����
			 editData(con);
			 
			// ������ ����
			 delData(con);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null ) {
				try {
					con.close();
				} catch (Exception e) {}
			}
		}	
		
	}
	
	public static void readData(Connection con) {
		Statement stat;
		try {
			System.out.println("\n####������ ��ȸ â�Դϴ�####");
			stat = con.createStatement();
			String sql = "select * from g_artists";
			ResultSet rsl = stat.executeQuery(sql);
			while (rsl.next()) {
				String id = rsl.getString("id");
				String name = rsl.getString("name");
				System.out.println(id + " " + name);
			}
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void addData(Connection con) {
		Statement stat;
		try {
			System.out.println("\n####�����͸� �߰��մϴ�####");
			stat = con.createStatement();
			String sql = "insert into g_artists (name, a_type, a_year, debut, regdate)"
					+ " values ('���ι̽�����', '�����׷�', '2020���', '2020��', datetime('now', 'localtime'))";
			int cnt = stat.executeUpdate(sql);
			if (cnt > 0)
				System.out.println("���ο� �����Ͱ� �߰��Ǿ����ϴ�!");
			else
				System.out.println("[Error] ������ �߰� ����!");
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void editData(Connection con) {
		Statement stat;
		try {
			System.out.println("\n####�����͸� �����մϴ�####");
			stat = con.createStatement();
			String sql = "update g_artists set debut = '2016�� / cheer up' "
					+ " where name = 'Ʈ���̽�' ;";
			int cnt = stat.executeUpdate(sql);
			if (cnt > 0)
				System.out.println("�����Ͱ� �����Ǿ����ϴ�!");
			else
				System.out.println("[Error] ������ ���� ����!");
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void delData(Connection con) {
		Statement stat;
		try {
			System.out.println("\n####�����͸� �����մϴ�####");
			stat = con.createStatement();
			String sql = "delete from g_artists where name = '���ι̽�����' ";
			int cnt = stat.executeUpdate(sql);
			if (cnt > 0)
				System.out.println("�����Ͱ� �����Ǿ����ϴ�!");
			else
				System.out.println("[Error] ������ ���� ����!");
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
