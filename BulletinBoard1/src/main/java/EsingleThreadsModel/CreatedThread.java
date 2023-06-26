package EsingleThreadsModel;

public class CreatedThread {
	private String id;
	private String threadName;
	private String threadDetail;
	private int categoryCode;
	private int threadDelete;
	
	public CreatedThread(String id, String threadName, String threadDetail, int categoryCode, int threadDelete) {
		this.id = id;
		this.threadName = threadName;
		this.threadDetail = threadDetail;
		this.categoryCode = categoryCode;
		this.threadDelete = threadDelete;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getThreadName() {
		return threadName;
	}
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
	public String getThreadDetail() {
		return threadDetail;
	}
	public void setThreadDetail(String threadDetail) {
		this.threadDetail = threadDetail;
	}
	public int getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}
	public int getThreadDelete() {
		return threadDelete;
	}
	public void setThreadDelete(int threadDelete) {
		this.threadDelete = threadDelete;
	}
	
	
	
	
}
