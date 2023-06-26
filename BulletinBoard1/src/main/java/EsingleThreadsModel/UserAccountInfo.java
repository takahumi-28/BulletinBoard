package EsingleThreadsModel;

import java.io.Serializable;

public class UserAccountInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String eMail;
	private String password;
	private String userName;
	private String birthDay;
	private int gender;
	private int administrator;
	private String resister_user;
	private String resister_date;
	private String update_user;
	private String update_date;
	private int user_delete;
	private int user_lock;
	
	public UserAccountInfo() {}

	public UserAccountInfo(String userId, String eMail, String password, String userName, String birthDay, int gender,
			int administrator, String resister_user, String resister_date, String update_user, String update_date,
			int user_delete, int user_lock) {
		this.userId = userId;
		this.eMail = eMail;
		this.password = password;
		this.userName = userName;
		this.birthDay = birthDay;
		this.gender = gender;
		this.administrator = administrator;
		this.resister_user = resister_user;
		this.resister_date = resister_date;
		this.update_user = update_user;
		this.update_date = update_date;
		this.setUser_delete(user_delete);
		this.setUser_lock(user_lock);
	}

	public String getUserId() {
		return userId;
	}

	public String geteMail() {
		return eMail;
	}

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public int getGender() {
		return gender;
	}

	public int getAdministrator() {
		return administrator;
	}

	public String getResister_user() {
		return resister_user;
	}

	public String getResister_date() {
		return resister_date;
	}

	public String getUpdate_user() {
		return update_user;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public void setAdministrator(int administrator) {
		this.administrator = administrator;
	}

	public void setResister_user(String resister_user) {
		this.resister_user = resister_user;
	}

	public void setResister_date(String resister_date) {
		this.resister_date = resister_date;
	}

	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public int getUser_delete() {
		return user_delete;
	}

	public void setUser_delete(int user_delete) {
		this.user_delete = user_delete;
	}

	public int getUser_lock() {
		return user_lock;
	}

	public void setUser_lock(int user_lock) {
		this.user_lock = user_lock;
	}
	
}
