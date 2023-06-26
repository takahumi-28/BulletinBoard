package EsingleThreadsModel;

public class CategoryInfo {
	private int categoryCode;
	private String categoryName;
	private String categoryNameKana;
	
	public CategoryInfo(int categoryCode, String categoryName, String categoryNameKana) {
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.categoryNameKana = categoryNameKana;
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
	
}
