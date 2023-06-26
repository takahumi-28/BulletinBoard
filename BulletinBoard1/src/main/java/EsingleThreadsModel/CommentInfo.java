package EsingleThreadsModel;

public class CommentInfo {
	private int threadCode;
	private int commentCode;
	private String userName;
	private String contribution;
	private String contents;
	
	
	public CommentInfo(int threadCode, int commentCode, String userName, String contribution, String contents) {
		this.threadCode = threadCode;
		this.commentCode = commentCode;
		this.userName = userName;
		this.contribution = contribution;
		this.contents = contents;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContribution() {
		return contribution;
	}
	public void setContribution(String contribution) {
		this.contribution = contribution;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getThreadCode() {
		return threadCode;
	}
	public void setThreadCode(int threadCode) {
		this.threadCode = threadCode;
	}
	public int getCommentCode() {
		return commentCode;
	}
	public void setCommentCode(int commentCode) {
		this.commentCode = commentCode;
	}
	
	
}
