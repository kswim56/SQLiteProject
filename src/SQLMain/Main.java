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
			// SQLite JDBC인지 체크한다
			Class.forName("org.sqlite.JDBC");
			
			// SQLite 데이터베이스 파일에 연결
			String dbFile = "myfirst.db";
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			
			// 데이터 조회
			readData(con);
			
			// 데이터 추가
			 addData(con);
			
			// 데이터 수정
			 editData(con);
			 
			// 데이터 삭제
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
			System.out.println("\n####데이터 조회 창입니다####");
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
			System.out.println("\n####데이터를 추가합니다####");
			stat = con.createStatement();
			String sql = "insert into g_artists (name, a_type, a_year, debut, regdate)"
					+ " values ('프로미스나인', '여성그룹', '2020년대', '2020년', datetime('now', 'localtime'))";
			int cnt = stat.executeUpdate(sql);
			if (cnt > 0)
				System.out.println("새로운 데이터가 추가되었습니다!");
			else
				System.out.println("[Error] 데이터 추가 오류!");
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void editData(Connection con) {
		Statement stat;
		try {
			System.out.println("\n####데이터를 수정합니다####");
			stat = con.createStatement();
			String sql = "update g_artists set debut = '2016년 / cheer up' "
					+ " where name = '트와이스' ;";
			int cnt = stat.executeUpdate(sql);
			if (cnt > 0)
				System.out.println("데이터가 수정되었습니다!");
			else
				System.out.println("[Error] 데이터 수정 오류!");
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void delData(Connection con) {
		Statement stat;
		try {
			System.out.println("\n####데이터를 삭제합니다####");
			stat = con.createStatement();
			String sql = "delete from g_artists where name = '프로미스나인' ";
			int cnt = stat.executeUpdate(sql);
			if (cnt > 0)
				System.out.println("데이터가 삭제되었습니다!");
			else
				System.out.println("[Error] 데이터 삭제 오류!");
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
