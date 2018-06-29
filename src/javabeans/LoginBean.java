//Helene Akulow
package javabeans;

import java.io.Serializable;

public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String passwort;
	private String fehlermeldung;
	private byte[] profilbild; //braucht man eigentlich nicht
	private boolean bildexist;
	private boolean adminrechte; 
	
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

	public boolean getAdminrechte() {
		return adminrechte;
	}

	public void setAdminrechte(boolean adminrechte) {
		this.adminrechte = adminrechte;
	}

	public boolean getBildexist() {
		return bildexist;
	}

	public void setBildexist(boolean bildexist) {
		this.bildexist = bildexist;
	}
	


}
