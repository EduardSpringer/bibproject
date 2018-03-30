package javabeans;

import java.io.Serializable;

public class LoginBean implements Serializable{
	
	private String username;
	private String passwort;
	
	public LoginBean(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}


}
