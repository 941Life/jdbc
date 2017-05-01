package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.AuthorVo;
import jdbc.BookVO;

public class BookDao {
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		// 1. 드라이버 로딩
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 2. Connection 하기
			String url = "jdbc:mysql://localhost:3306/dev?useUnicode=true&characterEncoding=utf8";

			conn = DriverManager.getConnection(url, "dev", "dev");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver를 찾을 수 없습니다.");
		} 
		return conn;
	}

	public boolean insert(BookVO bookVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "insert into book values (null, ?, ?, 1)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookVO.getTitle());
			pstmt.setInt(2, bookVO.getPrice());
			 pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("error" + e);
			return true;
		} finally {
			// 자원 정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean delete(Long isbn){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			// 3. Statement 준비
			String sql = "delete from book where isbn = ?";
			pstmt = conn.prepareStatement(sql);
			// 4. 바인딩
			pstmt.setLong(1, isbn);
			// 5. sql문 실행
			int count = pstmt.executeUpdate();
			return (count == 1);
		} catch (SQLException e) {
			System.out.println("error:" + e);
			return false;
		} finally {
			/* 자원정리 */
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean update(BookVO bookVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			// 3. Statement 준비
			String sql = "update book set title=?, price = ? where isbn = ?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setString(1, bookVO.getTitle());
			pstmt.setInt(2, bookVO.getPrice());
			pstmt.setLong(3, bookVO.getIsbn());

			// 5. sql문 실행
			int count = pstmt.executeUpdate();
			return (count == 1);

		} catch (SQLException e) {
			System.out.println("error:" + e);
			return false;
		} finally {
			/* 자원정리 */
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public BookVO get(Long isbn){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookVO bookVO = null;
		try {
			// 3. Statement 생성
			conn = getConnection();
			// 4. sql문 실행
			String sql = "select	* from book where isbn = ?";
			pstmt = conn.prepareStatement(sql);
			//
			pstmt.setLong(1, isbn);

			//
			rs = pstmt.executeQuery();
			// 5. fetch row(row를 하나씩 가져오기)
			if (rs.next()) {
				bookVO = new BookVO();

				bookVO.setIsbn(rs.getLong(1));
				bookVO.setTitle(rs.getString(2));
				bookVO.setPrice(rs.getInt(3));
			}

			return bookVO;
		} catch (SQLException e) {
			System.out.println("error" + e);
			return bookVO;
		} finally {
			// 자원 정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
