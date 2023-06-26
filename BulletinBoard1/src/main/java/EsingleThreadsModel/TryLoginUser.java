package EsingleThreadsModel;

public class TryLoginUser {
	private String email;
	private String pass;
	
	public TryLoginUser(String email, String pass) {
		this.email = email;
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return pass;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
