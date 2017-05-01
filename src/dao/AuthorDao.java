package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.AuthorVo;

public class AuthorDao {
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

	public boolean insert(AuthorVo authorVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			// 3. Statement 생성
			conn = getConnection();
			String sql = "insert into author values(null, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setString(1, authorVo.getName());
			pstmt.setString(2, authorVo.getDesc());

			// 5. sql문 실행
			int count = pstmt.executeUpdate(sql);
			System.out.println(count == 1);

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

	public List<AuthorVo> getList() {
		List<AuthorVo> list = new ArrayList<AuthorVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 3. Statement 생성
			conn = getConnection();

			stmt = conn.createStatement();

			// 4. sql문 실행
			String sql = "select	* from Author";
			rs = stmt.executeQuery(sql);

			// 5. fetch row(row를 하나씩 가져오기)
			while (rs.next()) {
				Long No = rs.getLong(1);
				String Name = rs.getString(2);
				String Desc = rs.getString(3);

				AuthorVo authorVo = new AuthorVo();
				authorVo.setNo(No);
				authorVo.setName(Name);
				authorVo.setDesc(Desc);

				list.add(authorVo);
			}

			return list;
		} catch (SQLException e) {
			System.out.println("error" + e);
			return list;
		} finally {
			// 자원 정리
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public AuthorVo get(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AuthorVo authorVo = null;
		try {
			// 3. Statement 생성
			conn = getConnection();
			// 4. sql문 실행
			String sql = "select	* from Author where no = ?";
			pstmt = conn.prepareStatement(sql);
			//
			pstmt.setLong(1, no);

			//
			rs = pstmt.executeQuery();
			// 5. fetch row(row를 하나씩 가져오기)
			if (rs.next()) {
				authorVo = new AuthorVo();

				authorVo.setNo(rs.getLong(1));
				authorVo.setName(rs.getString(2));
				authorVo.setDesc(rs.getString(3));
			}

			return authorVo;
		} catch (SQLException e) {
			System.out.println("error" + e);
			return authorVo;
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

	public boolean update(AuthorVo authorVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			// 3. Statement 준비
			String sql = "update author set name=?, bio = ? where no = ?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setString(1, authorVo.getName());
			pstmt.setString(2, authorVo.getDesc());
			pstmt.setLong(3, authorVo.getNo());

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

	public AuthorVo get(String name) {
		return null;
	}

	public boolean delete(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			// 3. Statement 준비
			String sql = "delete from author where no = ?";
			pstmt = conn.prepareStatement(sql);
			// 4. 바인딩
			pstmt.setLong(1, no);
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

}
