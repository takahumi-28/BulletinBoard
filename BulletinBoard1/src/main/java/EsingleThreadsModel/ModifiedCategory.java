package EsingleThreadsModel;

public class ModifiedCategory {
	private int categoryCode;
	private String categoryName;
	private String categoryNameKana;
	private String userId;
	
	public ModifiedCategory(int categoryCode, String categoryName, String categoryNameKana, String userId) {
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.categoryNameKana = categoryNameKana;
		this.userId = userId;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryNameKana() {
		return categoryNameKana;
	}

	public void setCategoryNameKana(String categoryNameKana) {
		this.categoryNameKana = categoryNameKana;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
