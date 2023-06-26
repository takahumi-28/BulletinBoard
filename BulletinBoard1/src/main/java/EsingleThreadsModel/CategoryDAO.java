package EsingleThreadsModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO extends ESingleThreadsDAO{
	private String baseSqlSelect =  "SELECT	category_code, "
								+ "			category_name, "
								+ "			category_name_kana "
								+ "FROM		Category "
								+ "WHERE	category_delete = 0 ";	
	public String getBaseSqlSelect() {
		return baseSqlSelect;
	}

	public ArrayList<CategoryInfo> getCategoriesByKeyword(String keyword){
		this.startJDBC();
		ArrayList<CategoryInfo> categories = new ArrayList<>();
		try(Connection con = DriverManager.getConnection(this.getConnectionUrl());){
			String sql = this.getBaseSqlSelect()
					+ " AND category_name LIKE ? ";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				categories.add(new CategoryInfo(
													rs.getInt("category_code"),						
													rs.getString("category_name"),
													rs.getString("category_name_kana")
												)
								);
			}
			rs.close();
			pstmt.close();
			return categories;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<CategoryInfo> getCategories() {
		this.startJDBC();
		ArrayList<CategoryInfo> categories = new ArrayList<>();
		try(Connection con = DriverManager.getConnection(this.getConnectionUrl());){
			String sql = this.getBaseSqlSelect();
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				categories.add(new CategoryInfo(
													rs.getInt("category_code"),						
													rs.getString("category_name"),
													rs.getString("category_name_kana")
												)
								);
			}
			rs.close();
			pstmt.close();
			return categories;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public int getCategoryCodeByName(String categoryName) {
		this.startJDBC();
		int categoryCode = 0;
		try(Connection con = DriverManager.getConnection(this.getConnectionUrl());){
			String sql = "SELECT	category_code "
						+ "FROM		Category "
						+ "WHERE	category_delete = 0 AND "
						+ "			category_name =  ? ";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, categoryName);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				categoryCode = rs.getInt("category_code");
			}
			rs.close();
			pstmt.close();
			return categoryCode;
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public String getCategoryNameByCode(int categoryCode) {
		this.startJDBC();
		String categoryName = null;
		try(Connection con = DriverManager.getConnection(this.getConnectionUrl());){
			String sql = "SELECT	category_name "
						+ "FROM		Category "
						+ "WHERE	category_delete = 0 AND "
						+ "			category_code =  ? ";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, categoryCode);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				categoryName = rs.getString("category_name");
			}
			rs.close();
			pstmt.close();
			return categoryName;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public int addCategory(AddedCategory category) {
		this.startJDBC();
		try(Connection con = DriverManager.getConnection(this.getConnectionUrl());){
			String sql = "INSERT INTO Category VALUES( "
					+ "									(SELECT	COUNT(*) + 1 "
					+ "									 FROM	Category), "
					+ "								 	? , "
					+ "								 	? , "
					+ "								 	? , "
					+ "								 	GETDATE(), "
					+ "								 	null, "
					+ "								 	null, "
					+ "								 	0 "
					+ "								 )";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category.getCategoryName());
			pstmt.setString(2, category.getCategoryNameKana());
			pstmt.setString(3, category.getUserId());
			
			return pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public int modifyCategory(ModifiedCategory category) {
		this.startJDBC();
		try(Connection con = DriverManager.getConnection(this.getConnectionUrl());){
			String sql = "UPDATE Category SET category_name = ?,"
											+ "category_name_kana = ?,"
											+ "updater_id = ?,"
											+ "update_date = GETDATE()　"
										+ " WHERE category_code = ?;";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category.getCategoryName());
			pstmt.setString(2, category.getCategoryNameKana());
			pstmt.setString(3, category.getUserId());
			pstmt.setInt(4, category.getCategoryCode());
			
			return pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public String deleteCategory(int categoryCode) {
		this.startJDBC();
		String message = null;
		Connection con = null;
		try{
			con = DriverManager.getConnection(this.getConnectionUrl());
			con.setAutoCommit(false);
			
			//カテゴリー削除
			String sql = "UPDATE Category SET category_delete = 1　WHERE category_code = ? ;";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, categoryCode);
			
			int rsCategoryRow = pstmt.executeUpdate();
			
			//スレッド削除
			sql = "UPDATE	Thread	SET thread_delete = 1 WHERE category_code = ? ;";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, categoryCode);
			
			int rsThreadRow = pstmt.executeUpdate();
			
			con.commit();
			pstmt.close();
			
			if(rsCategoryRow != 0 || rsThreadRow != 0) {
				message = "カテゴリー削除に成功しました";
			}else {
				message = "削除対象のデータがありませんでした";
			}
		}catch(SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
				message = "削除に失敗した為、ロールバックしました";
			}catch(SQLException e2) {
				e2.printStackTrace();
				message = "ロールバックに失敗しました";
			}
		}finally{
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException e3){
				e3.printStackTrace();
				message = "データベースとの切断に失敗しました";
			}
		}
		return message;
	}
}
