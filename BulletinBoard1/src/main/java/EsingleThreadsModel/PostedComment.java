package EsingleThreadsModel;

public class PostedComment {
	private int threadCode; 
	private String userId;
	private String contents;
	
	public PostedComment(int threadCode, String userId, String contents) {
		this.threadCode = threadCode;
		this.userId = userId;
		this.contents = contents;
	}

	public int getThreadCode() {
		return threadCode;
	}

	public void setThreadCode(int threadCode) {
		this.threadCode = threadCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
	
}
