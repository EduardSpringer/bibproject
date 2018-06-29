/**
 * Sara Viktoria Miller
 */
package javabeans;

import java.io.Serializable;

public class ProfileBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String vorname; 
	private String nachname; 
	private String passwort; 
	private String passwortbestaetigen;
	private String email;
	private String username; 
	private boolean adminrechte; 

	private byte[] profilbild; 
	private String submittedFileName;


	
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

	public byte[] getProfilbild() {
		// TODO Auto-generated method stub
		return profilbild;
	}
	public void setProfilbild(byte[] profilbild) {
		this.profilbild = profilbild; 
	}
	public byte[] getImage() {
		// TODO Auto-generated method stub
		return null;

	}
	public void setId(long long1) {
		// TODO Auto-generated method stub
		
	}
	public String getFilename() {
		return submittedFileName; 
	}
	public void setFilename(String submittedFileName) {
		this.submittedFileName = submittedFileName; 
	}
	public boolean getAdminrechte() {
		return adminrechte;
	}

	public void setAdminrechte(boolean adminrechte) {
		this.adminrechte = adminrechte;
	}
	

}
