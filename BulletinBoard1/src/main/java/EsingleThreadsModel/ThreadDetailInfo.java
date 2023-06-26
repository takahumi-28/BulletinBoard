package EsingleThreadsModel;

public class ThreadDetailInfo {
	private String userName;
	private String threadName;
	private String categoryName;
	private String threadDate;
	private String threadDetail;
	public ThreadDetailInfo(String userName, String threadName, String categoryName, String threadDate,
			String threadDetail) {
		this.userName = userName;
		this.threadName = threadName;
		this.categoryName = categoryName;
		this.threadDate = threadDate;
		this.threadDetail = threadDetail;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getThreadName() {
		return threadName;
	}
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getThreadDate() {
		return threadDate;
	}
	public void setThreadDate(String threadDate) {
		this.threadDate = threadDate;
	}
	public String getThreadDetail() {
		return threadDetail;
	}
	public void setThreadDetail(String threadDetail) {
		this.threadDetail = threadDetail;
	}
	
	
	
	
	
	
}
