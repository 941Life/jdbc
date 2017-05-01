package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTest {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2. Connection 하기
			String url = "jdbc:mysql://localhost:3306/dev?useUnicode=true&characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "dev", "dev");

			// 3. Statement 생성
			 stmt = conn.createStatement();

			// 4. sql문 실행
			String sql = "insert into member values(null,'smwoo55@naver.com',password('123'),'123','1234',now())";
			int count = stmt.executeUpdate(sql);
			System.out.println(count == 1);
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("error" + e);
			return;
		} finally {
			// 자원 정리
			try {
				if( stmt != null){
					stmt.close();
				}
				if( conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
