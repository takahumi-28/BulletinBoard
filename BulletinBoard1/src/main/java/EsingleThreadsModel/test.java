package EsingleThreadsModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test {
	public static void main(String[] args) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			
		}
		String connectionUr1 = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=ESingleThreads;user=javauser;password=javauser";
		try(Connection con = DriverManager.getConnection(connectionUr1);){
			String sql = "SELECT user_id, "
					+ "	   e_mail, "
					+ "	   password, "
					+ "	   user_name, "
					+ "	   birth_day, "
					+ "	   gender, "
					+ "	   administrator, "
					+ "	   resister_user, "
					+ "	   resister_date, "
					+ "	   update_user, "
					+ "	   update_date, "
					+ "	   user_delete, "
					+ "	   user_lock "
					+ "FROM UserAccount "
					+ "WHERE e_mail = 'no_other@yahoo.co.jp' AND "
					+ "	  password = HASHBYTES('SHA2_256', ? ) ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			UserAccountInfo resultUser = null;
			if(rs.next()) {
				resultUser = new UserAccountInfo(rs.getString("user_id"), 
												 rs.getString("e_mail"),
												 rs.getString("password"),
												 rs.getString("user_name"),
												 rs.getString("birth_day"),
												 rs.getInt("gender"),
												 rs.getInt("administrator"),
												 rs.getString("resister_user"),
												 rs.getString("resister_date"),
												 rs.getString("update_user"),
												 rs.getString("update_date"),
												 rs.getInt("user_delete"),
												 rs.getInt("user_lock")
												);
				System.out.println(resultUser.geteMail());
				System.out.println(resultUser.getPassword());
				System.out.println(resultUser.getBirthDay());
				System.out.println(resultUser.getGender());
				System.out.println(resultUser.getUserId());
				System.out.println(resultUser.getUserName());
				System.out.println(resultUser.getResister_user());
				System.out.println(resultUser.getAdministrator());
				System.out.println(resultUser.getResister_date());
				System.out.println(resultUser.getUpdate_user());
				System.out.println(resultUser.getUpdate_date());
				System.out.println(resultUser.getUser_delete());
				System.out.println(resultUser.getUser_lock());
				
				
			}
			System.out.println(resultUser.geteMail());
			System.out.println(resultUser.getPassword());
			System.out.println(resultUser.getBirthDay());
			System.out.println(resultUser.getGender());
			System.out.println(resultUser.getUserId());
			System.out.println(resultUser.getUserName());
			System.out.println(resultUser.getResister_user());
			System.out.println(resultUser.getAdministrator());
			System.out.println(resultUser.getResister_date());
			System.out.println(resultUser.getUpdate_user());
			System.out.println(resultUser.getUpdate_date());
			System.out.println(resultUser.getUser_delete());
			System.out.println(resultUser.getUser_lock());
			
			rs.close();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
