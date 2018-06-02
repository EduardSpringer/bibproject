package javabeans;

import java.io.Serializable;

public class ProfileBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String vorname; 
	private String nachname; 
	private String passwort; 
	private String passwortbestaetigen;
	private String email;
	private String username; 
	private Byte image; 
	
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	public String getPasswortbestaetigen() {
		return passwortbestaetigen;
	}
	public void setPasswortbestaetigen(String passwortbestaetigen) {
		this.passwortbestaetigen = passwortbestaetigen;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Byte getImage() {
		// TODO Auto-generated method stub
		return image;
	}
	public void setId(long long1) {
		// TODO Auto-generated method stub
		
	}

}
