package entity;

public class User {
	//user_id
	private int id;
	//用户邮箱同时也是登录账号
	private String email;
	//密码
	private String password;
	//头像byte64字符串
	private String avatar;
	
	public int getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}		
}
