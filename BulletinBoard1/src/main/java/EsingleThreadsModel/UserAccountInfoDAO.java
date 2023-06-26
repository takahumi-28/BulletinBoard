package EsingleThreadsModel;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UserAccountInfoDAO {
	public UserAccountInfo findUserByMailPass(TryLoginUser user){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
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
					+ "WHERE e_mail = ? AND "
					+ "	  password = ? ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] result = digest.digest(user.getPass().getBytes());
            String login = String.format("%040x", new BigInteger(1, result));
			
			System.out.println("db" + user.getEmail());
			System.out.println("db" + user.getPass());
			
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, login);
			
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
				System.out.println("dbkoko" + resultUser.geteMail());
			}
			rs.close();
			pstmt.close();;
			
			return resultUser;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<UserAccountInfo> UserList(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
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
					+ "WHERE user_delete = 0 ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ArrayList<UserAccountInfo> accounts = new ArrayList<>();
			
			while(rs.next()) {
				accounts.add(new UserAccountInfo(
													rs.getString("user_id"), 
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
						 
												)
											
				);
			}
			rs.close();
			pstmt.close();
			return accounts;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public String IdConfirm(UserAccountInfo makeuser){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return "登録に失敗しました。";
		}
		String connectionUr1 = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=ESingleThreads;user=javauser;password=javauser";
		try(Connection con = DriverManager.getConnection(connectionUr1);){
			String sql = "INSERT INTO UserAccount VALUES( 	? , "
					+ "								    	? , "
					+ "										? , "
					+ "										? , "
					+ "										? , "
					+ "										? , "
					+ "										? , "
					+ "										? , "
					+ "										CONVERT(DATE,GETDATE()), "
					+ "										null, "
					+ "										null, "
					+ "										'0', "
					+ "										'0' "
					+ "								)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] result = digest.digest(makeuser.getPassword().getBytes());
            String login = String.format("%040x", new BigInteger(1, result));
			
			pstmt.setString(1, makeuser.getUserId());
			pstmt.setString(2, makeuser.geteMail());
			pstmt.setString(3, login);
			pstmt.setString(4, makeuser.getUserName());
			pstmt.setString(5, makeuser.getBirthDay());
			pstmt.setLong(6, makeuser.getGender());
			pstmt.setLong(7, makeuser.getAdministrator());
			pstmt.setString(8, makeuser.getResister_user());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			
			return "登録完了しました。";
		
		} catch (SQLException e) {
			e.printStackTrace();
			return "登録に失敗しました。";
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "登録に失敗しました。";
		}
	}
	public UserAccountInfo findUserById(String modify){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
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
					+ "WHERE user_id = ?  ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, modify);
			
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
			}
			rs.close();
			pstmt.close();;
			
			return resultUser;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public String ModifyConfirm(UserAccountInfo after, UserAccountInfo logined){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return "修正に失敗しました。";
		}
		String connectionUr1 = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=ESingleThreads;user=javauser;password=javauser";
		try(Connection con = DriverManager.getConnection(connectionUr1);){
			String sql = "UPDATE UserAccount "
					+ "SET e_mail = ? , "
					+ "	password = ? , "
					+ "	user_name = ? , "
					+ "	birth_day = ? , "
					+ "	gender = ? , "
					+ "	administrator = ? , "
					+ "	update_user = ? , "
					+ "	update_date = CONVERT(DATE,GETDATE()), "
					+ "	user_lock = ? "
					+ "WHERE user_id = ? ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] result = digest.digest(after.getPassword().getBytes());
            String afterpass = String.format("%040x", new BigInteger(1, result));
            
			
			pstmt.setString(1, after.geteMail());
			pstmt.setString(2, afterpass);
			pstmt.setString(3, after.getUserName());
			pstmt.setString(4, after.getBirthDay());
			pstmt.setLong(5, after.getGender());
			pstmt.setLong(6, after.getAdministrator());
			pstmt.setString(7, logined.getUserId());
			pstmt.setLong(8, after.getUser_lock());
			pstmt.setString(9, after.getUserId());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			
			return after.getUserId() + "を修正しました。";
		
		} catch (SQLException e) {
			e.printStackTrace();
			return "修正に失敗しました。";
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "修正に失敗しました。";
		}
	}
	public String DeleteUser(UserAccountInfo deleteUser){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return "削除に失敗しました。";
		}
		String connectionUr1 = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=ESingleThreads;user=javauser;password=javauser";
		try(Connection con = DriverManager.getConnection(connectionUr1);){
			String sql = "UPDATE UserAccount "
					+ "SET user_delete = '1'  "
					+ "WHERE user_id = ? ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, deleteUser.getUserId());
			
			pstmt.executeUpdate();
			pstmt.close();
			
			return deleteUser.getUserId() + "を削除しました。";
		
		} catch (SQLException e) {
			e.printStackTrace();
			return "削除に失敗しました。";
		}
	}
}
