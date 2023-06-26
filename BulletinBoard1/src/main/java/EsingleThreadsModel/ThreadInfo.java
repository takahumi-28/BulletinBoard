package EsingleThreadsModel;

public class ThreadInfo {
	private String threadCode;
	private String threadName;
	private String threadDate;
	private String categoryName;
	
	
	public ThreadInfo(String threadCode, String threadName, String categoryName, String threadDate) {
		this.threadCode = threadCode;
		this.threadName = threadName;
		this.categoryName = categoryName;
		this.threadDate = threadDate;
	}

	
	public String getThreadCode() {
		return threadCode;
	}


	public void setThreadCode(String threadCode) {
		this.threadCode = threadCode;
	}


	public String getThreadName() {
		return threadName;
	}
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
	
	public String getThreadDate() {
		return threadDate;
	}
	public void setThreadDate(String threadDate) {
		this.threadDate = threadDate;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
