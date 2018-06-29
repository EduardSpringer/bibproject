//Helene Akulow
package javabeans;

import java.io.Serializable;
import java.util.Date;

public class ReservationBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int reservierungID;
	private int platzID;
	private String username;
	private String datumString;
	private String zeitraum;
	private String terminbezeichnung;
	private Date zeitstempel;
	private String nachname; 
	private String vorname; 
	
	public String getDatumString() {
		return datumString;
	}
	public void setDatumString(String datumString) {
		this.datumString = datumString;
	}
	public int getReservierungID() {
		return reservierungID;
	}
	public void setReservierungID(int reservierungID) {
		this.reservierungID = reservierungID;
	}
	public int getPlatzID() {
		return platzID;
	}
	public void setPlatzID(int platzID) {
		this.platzID = platzID;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getZeitraum() {
		return zeitraum;
	}
	public void setZeitraum(String zeitraum) {
		this.zeitraum = zeitraum;
	}
	public String getTerminbezeichnung() {
		return terminbezeichnung;
	}
	public void setTerminbezeichnung(String terminbezeichnung) {
		this.terminbezeichnung = terminbezeichnung;
	}
	public Date getZeitstempel() {
		return zeitstempel;
	}
	public void setZeitstempel(Date zeitstempel) {
		this.zeitstempel = zeitstempel;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname; 
	}
	public String getNachname() {
		return nachname; 
	}
	public void setVorname(String vorname) {
		this.vorname = vorname; 
	}
	public String getVorname() {
		return vorname; 
	}
}
