package EsingleThreadsModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ThreadDAO extends ESingleThreadsDAO{
	private String orderByDate = " ORDER BY thread_date DESC ";
	private String baseSqlSelect = "SELECT	t1.thread_code, "
									+ "			t1.thread_name, "
									+ "			c1.category_name, "
									+ "			t1.thread_date "
									+ "FROM		Thread AS t1 INNER JOIN  "
									+ "			Category AS c1 ON t1.category_code = c1.category_code "
									+ "WHERE	thread_delete = 0 ";
	
	public String getOrderByDate() {
		return orderByDate;
	}
	
	public String getBaseSqlSelect() {
		return baseSqlSelect;
	}

	public int createThread(CreatedThread thread) {
		this.startJDBC();
		try(Connection con = DriverManager.getConnection(this.getConnectionUrl());){
			String sql = "INSERT INTO Thread VALUES((SELECT	COUNT(*) +1 FROM	Thread),?,?,?,?, GETDATE() ,?)";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString	(1, thread.getId());
			pstmt.setString	(2, thread.getThreadName());
			pstmt.setString	(3, thread.getThreadDetail());
			pstmt.setInt	(4, thread.getCategoryCode());
			pstmt.setInt	(5, thread.getThreadDelete());
			
			return pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public int deleteThread(int threadCode) {
		this.startJDBC();
		try(Connection con = DriverManager.getConnection(this.getConnectionUrl());){
			String sql = "UPDATE	Thread	SET thread_delete = 1 WHERE thread_code = ? ;";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, threadCode);
			
			return pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public ArrayList<ThreadInfo> getThreadTitles() {
		this.startJDBC();
		ArrayList<ThreadInfo> threads = new ArrayList<>();
		try(Connection con = DriverManager.getConnection(this.getConnectionUrl());){
			String sql = this.getBaseSqlSelect() + this.getOrderByDate();
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				threads.add(new ThreadInfo(
												rs.getString("thread_code"),
												rs.getString("thread_name"),
												rs.getString("category_name"),
												rs.getString("thread_date")
											)
							);
			}
			rs.close();
			pstmt.close();
			return threads;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public ArrayList<ThreadInfo> getThreadTitlesByCategory(int categoryCode) {
		this.startJDBC();
		ArrayList<ThreadInfo> threads = new ArrayList<>();
		try(Connection con = DriverManager.getConnection(this.getConnectionUrl());){
			String sql = this.getBaseSqlSelect() 
						+ "			AND "
						+ "			t1.category_code = ? "
						+ this.getOrderByDate();
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, categoryCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				threads.add(new ThreadInfo(
												rs.getString("thread_code"),
												rs.getString("thread_name"),
												rs.getString("category_name"),
												rs.getString("thread_date")
											)
							);
			}
			rs.close();
			pstmt.close();
			return threads;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<ThreadInfo> getThreadTitlesBykeyword(String keyword) {
		this.startJDBC();
		ArrayList<ThreadInfo> threads = new ArrayList<>();
		try(Connection con = DriverManager.getConnection(this.getConnectionUrl());){
			String sql = this.getBaseSqlSelect() 
						+ "			AND "
						+ "			t1.thread_name LIKE ? "
						+ this.getOrderByDate();
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				threads.add(new ThreadInfo(
												rs.getString("thread_code"),
												rs.getString("thread_name"),
												rs.getString("category_name"),
												rs.getString("thread_date")
											)
							);
			}
			rs.close();
			pstmt.close();
			return threads;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}	
	//スレッドとコメントをスレ詳細画面で表示するメソッドを作る
	public ThreadDetailInfo getThreadDetail(int threadCode) {
		ThreadDetailInfo threadDetail = null;
		try(Connection con = DriverManager.getConnection(this.getConnectionUrl());){
			String sql = "SELECT	ua1.user_name, "
						+ "			t1.thread_name, "
						+ "			c1.category_name, "
						+ "			t1.thread_date, "
						+ "			t1.thread_detail "
						+ "FROM		Thread AS t1 INNER JOIN  "
						+ "			Category AS c1 ON t1.category_code = c1.category_code INNER JOIN "
						+ "			UserAccount AS ua1 ON t1.id = ua1.user_id "
						+ "WHERE	t1.thread_delete = 0 AND "
						+ "			t1.thread_code = ? ;";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, threadCode);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				threadDetail = new ThreadDetailInfo(
														rs.getString("user_name"),	
														rs.getString("thread_name"),
														rs.getString("category_name"),
														rs.getString("thread_date"),
														rs.getString("thread_detail")
													);
			}
			rs.close();
			pstmt.close();
			return threadDetail;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public int deleteUserThread(UserAccountInfo deleteUser) {
		this.startJDBC();
		try(Connection con = DriverManager.getConnection(this.getConnectionUrl());){
			String sql = "UPDATE	Thread	"
					+ "	  SET thread_delete = 1 "
					+ "	  WHERE id = ? ;";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, deleteUser.getUserId());
			
			return pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return 100;
		}
	}
}
