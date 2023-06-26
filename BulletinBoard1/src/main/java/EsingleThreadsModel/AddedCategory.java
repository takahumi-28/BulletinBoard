package EsingleThreadsModel;

public class AddedCategory {
	private String categoryName;
	private String categoryNameKana;
	private String userId;
	
	public AddedCategory(String categoryName, String categoryNameKana, String userId) {
		this.categoryName = categoryName;
		this.categoryNameKana = categoryNameKana;
		this.userId = userId;
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
