package EsingleThreadsModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentDAO extends ESingleThreadsDAO{
	
	public ArrayList<CommentInfo> getComments(int threadCode) {
		this.startJDBC();
		ArrayList<CommentInfo> comments = new ArrayList<>();
		try(Connection con = DriverManager.getConnection(this.getConnectionUrl());){
			String sql = "SELECT	c1.thread_code, "
						+ "			c1.comment_code, "
						+ "			ua1.user_name, "
						+ "			c1.contribution, "
						+ "			c1.contents "
						+ "FROM		Comment AS c1 INNER JOIN "
						+ "			UserAccount AS ua1 ON c1.id = ua1.user_id "
						+ "WHERE	c1.comment_delete = 0 AND "
						+ "			c1.thread_code = ? ";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, threadCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				comments.add(new CommentInfo(
												rs.getInt("thread_code"),
												rs.getInt("comment_code"),
												rs.getString("user_name"),
												rs.getString("contribution"),
												rs.getString("contents")	
											)
							);
			}
			rs.close();
			pstmt.close();
			return comments;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public int postComment(PostedComment comment) {
		this.startJDBC();
		try(Connection con = DriverManager.getConnection(this.getConnectionUrl());){
			String sql = "INSERT INTO Comment VALUES((SELECT	COUNT(*) + 1 "
							+ "							FROM	Comment "
							+ "							WHERE	thread_code = ? ), "
							+ "							?, "
							+ "							? , "
							+ "							GETDATE(), "
							+ "							? , "
							+ "							0 "
							+ "							) ";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comment.getThreadCode());
			pstmt.setInt(2, comment.getThreadCode());
			pstmt.setString(3, comment.getUserId());
			pstmt.setString(4, comment.getContents());
			int rsRow = pstmt.executeUpdate();
			
			pstmt.close();
			return rsRow;
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public int deleteComment(int threadCode, int commentCode) {
		this.startJDBC();
		try(Connection con = DriverManager.getConnection(this.getConnectionUrl());){
			String sql = "UPDATE Comment SET comment_delete = 1 WHERE thread_code = ? AND comment_code = ? ";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, threadCode);
			pstmt.setInt(2, commentCode);
			
			return pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public int deleteUserComment(UserAccountInfo deleteUser) {
		this.startJDBC();
		try(Connection con = DriverManager.getConnection(this.getConnectionUrl());){
			String sql = "UPDATE Comment "
					+ "	  SET comment_delete = 1 "
					+ "	  WHERE id = ? ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, deleteUser.getUserId());
			
			return pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return 100;
		}
	}
}
