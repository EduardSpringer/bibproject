//Helene Akulow
package javabeans;

import java.io.Serializable;
import java.util.Date;

public class ReservationBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int reservierungID;
	private int placeID;
	private String username;
	private Date datum;
	private String zeitraum;
	private String terminbezeichnung;
	private Date zeitstempel;
	
	public int getReservierungID() {
		return reservierungID;
	}
	public void setReservierungID(int reservierungID) {
		this.reservierungID = reservierungID;
	}
	public int getPlaceID() {
		return placeID;
	}
	public void setPlaceID(int placeID) {
		this.placeID = placeID;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
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

}
