package EsingleThreadsModel;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class IdMakerDAO {
	public String PreIdMake(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		String connectionUr1 = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=ESingleThreads;user=javauser;password=javauser";
		try(Connection con = DriverManager.getConnection(connectionUr1);){
			String sql = "SELECT alphabet, "
					+ "	    	 last_date, "
					+ "	  		 serial_number "
					+ "	  FROM IdMaker ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			String alpha = null;
			Date last = null;
			String serial = null;
			if(rs.next()) {
				alpha = rs.getString("alphabet");
				last = rs.getDate("last_date");
				serial = rs.getString("serial_number");
			}
			String date = new SimpleDateFormat("yyyyMMdd").format(last);
			String id = alpha.concat(date.concat(serial));
			
			rs.close();
			pstmt.close();
			
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public Date GetDay(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		String connectionUr1 = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=ESingleThreads;user=javauser;password=javauser";
		try(Connection con = DriverManager.getConnection(connectionUr1);){
			String sql = "SELECT last_date "
					+ "	  FROM IdMaker ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			Date last = null;
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				last = rs.getDate("last_date");
			}
			pstmt.close();
			rs.close();
			return last;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public String GetSerial(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		String connectionUr1 = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=ESingleThreads;user=javauser;password=javauser";
		try(Connection con = DriverManager.getConnection(connectionUr1);){
			String sql = "SELECT serial_number "
					+ "	  FROM IdMaker ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			String num = null;
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getString("serial_number");
			}
			pstmt.close();
			rs.close();
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public String GetAlpha(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		String connectionUr1 = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=ESingleThreads;user=javauser;password=javauser";
		try(Connection con = DriverManager.getConnection(connectionUr1);){
			String sql = "SELECT alphabet  "
					+ " FROM IdMaker";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			String alpha = null;
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				alpha = rs.getString("alphabet");
			}
			pstmt.close();
			rs.close();
			return alpha;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public String GetToday(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		String connectionUr1 = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=ESingleThreads;user=javauser;password=javauser";
		try(Connection con = DriverManager.getConnection(connectionUr1);){
			String sql = "SELECT CONVERT(DATE,GETDATE()) AS today";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			String today = null;
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				today = rs.getString("today");
			}
			pstmt.close();
			rs.close();
			return today;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public int IdSwap(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String connectionUr1 = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=ESingleThreads;user=javauser;password=javauser";
		try(Connection con = DriverManager.getConnection(connectionUr1);){
			String sql = " UPDATE IdMaker "
							+ " SET alphabet = ? , "
							+ "	last_date = ? , "
							+ "	serial_number = ? ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			int serial = Integer.parseInt(this.GetSerial());
			Date lastidday = this.GetDay();
			
			ArrayList<String> alpha = new ArrayList<String>();
			alpha.add("A");
			alpha.add("B");
			alpha.add("C");
			alpha.add("D");
			alpha.add("E");
			alpha.add("F");
			alpha.add("G");
			alpha.add("H");
			alpha.add("I");
			alpha.add("J");
			alpha.add("K");
			alpha.add("L");
			alpha.add("M");
			alpha.add("N");
			alpha.add("O");
			alpha.add("P");
			alpha.add("Q");
			alpha.add("R");
			alpha.add("S");
			alpha.add("T");
			alpha.add("U");
			alpha.add("V");
			alpha.add("W");
			alpha.add("X");
			alpha.add("Y");
			alpha.add("Z");
							
			String lastday = new SimpleDateFormat("yyyy-MM-dd").format(lastidday);
			String alphabet = this.GetAlpha();
			String today = this.GetToday();
			System.out.println(alphabet);
			System.out.println(today);
			System.out.println(lastday);
		
		
			int num = alpha.indexOf(alphabet);
			serial += 1;
			String numserial = null;
			numserial = String.format("%02d", serial); 
			int day = lastday.compareTo(today);
			
			if(serial >= 100) {
				serial = 1;
				num += 1;
				numserial = String.format("%02d", serial); 
			}
			if(day < 0) {
				pstmt.setString(1, alpha.get(0));
				pstmt.setString(2, today);
				pstmt.setLong(3, 1);
			} else {
				pstmt.setString(1, alpha.get(num));
				pstmt.setString(2, lastday);
				pstmt.setString(3, numserial);
			}
			
			pstmt.executeUpdate();
			pstmt.close();
			
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
