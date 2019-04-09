package model;

public class User {
	private String uName;
	private String uPwd;
	public User(String uName,String uPwd){
		this.uName=uName;
		this.uPwd=uPwd;
	}
	public String getUname() {
		return uName;
	}
	public void setUname(String uname) {
		this.uName = uname;
	}
	public String getUpwd() {
		return uPwd;
	}
	public void setUpwd(String upwd) {
		this.uPwd = upwd;
	}

}
