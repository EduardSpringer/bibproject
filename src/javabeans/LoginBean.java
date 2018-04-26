package javabeans;

import java.io.Serializable;
import java.sql.Blob;

public class LoginBean implements Serializable{
	
	private String username;
	private String passwort;
	private String fehlermeldung;
	private byte[] profilbild;
	
	public LoginBean() {		
	}
	
	public byte[] getProfilbild() {
		return profilbild;
	}

	public void setProfilbild(byte[] profilbild) {
		this.profilbild = profilbild;
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
	public String getFehlermeldung() {
		return fehlermeldung;
	}

	public void setFehlermeldung(String fehlermeldung) {
		this.fehlermeldung = fehlermeldung;
	}
	


}
